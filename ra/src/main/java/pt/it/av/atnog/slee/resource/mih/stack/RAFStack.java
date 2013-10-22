//==============================================================================
// Brief   : Resource Adaptor Frame Stack
// Authors : Carlos Guimarães <cguimaraes@av.it.pt>
//------------------------------------------------------------------------------
// ODTONE - Open Dot Twenty One
//
// Copyright (C) 2013 Universidade Aveiro
// Copyright (C) 2013 Instituto de Telecomunicações - Pólo Aveiro
//
// This file is part of MIH-Resource-Adaptor.
//
// MIH-Resource-Adaptor is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 2.1 of the License, or
// (at your option) any later version.
//
// MIH-Resource-Adaptor is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with MIH-Resource-Adaptor. If not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
//==============================================================================

/*
 * ***************************************************
 *                                                 *
 *  Mobicents: The Open Source JSLEE Platform      *
 *                                                 *
 *  Distributable under LGPL license.              *
 *  See terms of license at gnu.org.               *
 *                                                 *
 ***************************************************
 */

package pt.it.av.atnog.slee.resource.mih.stack;

import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.slee.facilities.Tracer;
import javax.slee.resource.ResourceAdaptorContext;

import org.apache.log4j.Logger;

/**
 * RAFStack represents the core of the RAFrame resource adaptor. It is a simple
 * TCP/IP server socket, listening in a separate Thread on incoming connections.
 * Listening is terminated every 1000ms to react on incoming "shutdown"
 * commands. <br>
 * The socket connection starts on incoming connections a separate Thread of
 * type pt.it.av.atnog.slee.resource.mih.stack.RAStackThread which receives
 * byte[] information (max. 2kb). The received information of any kind is then
 * published to the registered listeners. This information is distributed as
 * String.<br>
 * The intention of the RAFStack is not to be very efficient or effective - it
 * is implemented to fulfill the requirements of a simple to understand resource
 * adaptor stack implementation which almost everybody is familiar with and it
 * does not cost too much time to understand the message flows.
 *
 * @author Carlos Guimarães
 *
 *         Based on code from:
 * @author amit bhayani
 * @author Michael Maretzke
 */
public class RAFStack extends Thread {
	private Tracer tracer;

	private Logger logger = Logger.getLogger(RAFStack.class);

	// the socket to listen on
	private DatagramSocket server;

	// the registered listeners
	private ArrayList<RAFStackListener> listener;

	// flag to indicate shutdown
	private boolean shutdown = false;

	private ResourceAdaptorContext resourceAdaptorContext;

	private String remoteHost;
	private int remotePort = 0;

	public RAFStack(String localHost, int port, String remotehost,
			int remoteport) throws IOException {
		this(localHost, port, remotehost, remoteport, null);
	}

	/**
	 * Create an instance of RAFStack.
	 *
	 * @param port
	 *            the port number to listen on
	 * @param remotehost
	 *            the remotehost name the stack sends information to
	 * @param remoteport
	 *            the remotehost's port the stack sends information to
	 */
	public RAFStack(String localHost, int port, String remotehost,
			int remoteport, ResourceAdaptorContext resourceAdaptorContext)
			throws IOException {

		if (resourceAdaptorContext != null) {
			this.resourceAdaptorContext = resourceAdaptorContext;
			this.tracer = this.resourceAdaptorContext.getTracer(RAFStack.class
					.getSimpleName());
		}
		this.listener = new ArrayList<RAFStackListener>();

		this.remoteHost = remotehost;
		this.remotePort = remoteport;

		InetSocketAddress local = new InetSocketAddress(localHost, port);
		server = new DatagramSocket(local);

		// set socket timeout to 1000ms
		server.setSoTimeout(1000);

		if (this.tracer != null) {
			if (this.tracer.isFineEnabled()) {
				this.tracer.fine("Started ServerSocket and bound to " + local);
			}
		} else {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Started ServerSocket and bound to " + local);
			}
		}
	}

	public void addListener(RAFStackListener listener) {
		this.listener.add(listener);
	}

	public void run() {

		while (!shutdown) {
			try {
				byte[] receiveData = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				server.receive(receivePacket);

				// create a new working thread to work with the incoming
				// information
				new RAFStackThread(receivePacket, this, listener,
						resourceAdaptorContext).start();
			} catch (SocketTimeoutException ste) {
				// if (this.tracer != null) {
				// this.tracer
				// .severe(
				// "SocketTimeoutException while accepting new Client Socket",
				// ste);
				// } else {
				// logger
				// .error(
				// "SocketTimeoutException while accepting new Client Socket",
				// ste);
				// }
			} catch (SocketException ste) {
				if (this.tracer != null) {
					this.tracer
							.severe("SocketException while accepting new Client Socket",
									ste);
				} else {
					logger.error(
							"SocketException while accepting new Client Socket",
							ste);
				}
			} catch (IOException ioe) {
				if (this.tracer != null) {
					this.tracer.severe(
							"IOException while accepting new Client Socket",
							ioe);
				} else {
					logger.error(
							"IOException while accepting new Client Socket",
							ioe);
				}
			}
		}
	}

	/**
	 * This method sets the flag to shut down the stack. The stack will shut
	 * down in less than 1000ms.
	 */
	public void shutdown() {
		shutdown = true;
		try {
			server.close();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Send a string message to a TCP/IP socket. The information is transported
	 * as a byte[] array.
	 *
	 * @param message
	 *            the message to transport to the listening port
	 */
	public void send(byte[] message) {
		try {
			DatagramSocket clientSocket = new DatagramSocket();

			if (this.tracer != null) {
				if (this.tracer.isFineEnabled()) {
					this.tracer
							.fine("RAFStack sends the following information: "
									+ message);
				}
			} else {
				this.logger.debug("RAFStack sends the following information: "
						+ message);
			}

			InetAddress IPAddress = InetAddress.getByName(remoteHost);

			DatagramPacket sendPacket = new DatagramPacket(message,
					message.length, IPAddress, remotePort);
			clientSocket.send(sendPacket);

			clientSocket.close();

		} catch (SocketException ste) {
			if (this.tracer != null) {
				this.tracer
						.severe("SocketException caught while sending message"
								+ ste);
			} else {
				this.logger
						.error("SocketException caught while sending message"
								+ ste);
			}
		} catch (UnknownHostException uhe) {
			if (this.tracer != null) {
				this.tracer
						.severe("SocketException caught while sending message"
								+ uhe);
			} else {
				this.logger
						.error("SocketException caught while sending message"
								+ uhe);
			}
		} catch (IOException ioe) {
			if (this.tracer != null) {
				this.tracer.severe("IOException caught while sending message: "
						+ ioe);
			} else {
				this.logger.error("IOException caught while sending message: "
						+ ioe);
			}
		}

	}
}
