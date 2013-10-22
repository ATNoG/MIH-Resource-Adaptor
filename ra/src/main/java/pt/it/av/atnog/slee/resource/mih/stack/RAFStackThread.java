//==============================================================================
// Brief   : Resource Adaptor Frame Thread
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
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.net.DatagramPacket;
import java.net.Socket;
import java.util.ArrayList;

import javax.slee.facilities.Tracer;
import javax.slee.resource.ResourceAdaptorContext;

import org.apache.log4j.Logger;

/**
 * RAStackThread implements a single working thread to work on incoming data
 * after RAFStack.serversocket.accept() returns with a socket.<br>
 * The thread simply receives the byte[] stream information and publish it to
 * the registered listeners.<br>
 * The thread terminates immediately after information was received and the
 * connection is closed.
 *
 * @author Carlos Guimarães
 *
 *         Based on code from:
 * @author Michael Maretzke
 */
public class RAFStackThread extends Thread {

	private static Logger logger = Logger.getLogger(RAFStackThread.class);

	private Tracer tracer;

	DatagramPacket receivePacket;

	private ArrayList<RAFStackListener> listener;
	// the thread owning instance of RAFStack
	private RAFStack parent;

	public RAFStackThread(DatagramPacket receivePacket, RAFStack parent,
			ArrayList<RAFStackListener> listener) {
		this(receivePacket, parent, listener, null);
	}

	/**
	 * Create an instance of RAStackThread
	 *
	 * @param TODO
	 * @param parent
	 *            the thread owning parent instance
	 * @param listener
	 *            the listeners to be informed when information received.
	 */
	public RAFStackThread(DatagramPacket receivePacket, RAFStack parent,
			ArrayList<RAFStackListener> listener,
			ResourceAdaptorContext resourceAdaptorContext) {
		if (resourceAdaptorContext != null) {
			this.tracer = resourceAdaptorContext.getTracer(RAFStackThread.class
					.getSimpleName());
		}
		this.parent = parent;
		this.receivePacket = receivePacket;
		this.listener = listener;
	}

	/**
	 * Iterates through the listeners and invokes the onEvent() method with the
	 * information received through the socket.
	 */
	private void informListeners(byte[] incomingData) {
		for (RAFStackListener rsl : listener) {
			rsl.onEvent(incomingData);
		}
	}

	public void run() {
		if (this.tracer != null) {
			if (this.tracer.isFineEnabled()) {
				this.tracer.fine("Serverthread " + getName() + " started. ");
			}
		} else {
			logger.debug("Serverthread " + getName() + " started. ");
		}

		if (this.tracer != null) {
			if (this.tracer.isFineEnabled()) {
				this.tracer.fine("bytes received (" + receivePacket.getLength()
						+ ")");
			}
		} else {
			logger.debug("bytes received (" + receivePacket.getLength()
					+ ")");
		}

		byte[] request = new byte[receivePacket.getLength()];
		for (int i = 0; i < request.length; ++i) {
  			request[i] = receivePacket.getData()[i];
		}

		informListeners(request);
	}
}
