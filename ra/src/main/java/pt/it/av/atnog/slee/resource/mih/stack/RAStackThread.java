//==============================================================================
// Brief   : Resource Adaptor Thread
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
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

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
public class RAStackThread extends Thread {
	private static Logger logger = Logger.getLogger(RAStackThread.class);
	// the socket returned by RAFStack.serversocket.accept()
	private Socket socket;
	private ArrayList listener;
	// the thread owning instance of RAFStack
	private RAFStack parent;

	/**
	 * Create an instance of RAStackThread
	 *
	 * @param socket
	 *            the socket returned by RAFStack.serversocket.accept()
	 * @param parent
	 *            the thread owning parent instance
	 * @param listener
	 *            the listeners to be informed when information received.
	 */
	public RAStackThread(Socket socket, RAFStack parent, ArrayList listener) {
		this.parent = parent;
		this.socket = socket;
		this.listener = listener;
	}

	/**
	 * Iterates through the listeners and invokes the onEvent() method with the
	 * information received through the socket.
	 */
	private void informListeners(byte[] incomingData) {
		Iterator it = listener.iterator();

		while (it.hasNext()) {
			RAFStackListener rsl = (RAFStackListener) it.next();
			rsl.onEvent(incomingData);
		}
	}

	public void run() {
		try {
			logger.debug("Serverthread " + getName() + " started. ");
			InputStream in;
			byte[] b = new byte[2000];

			// get the socket's input stream
			in = socket.getInputStream();
			// read information of type byte[] - read contains the amount of
			// information read
			int read = in.read(b);
			// extract the "real" information

			logger.debug("bytes received (" + read + ") = " + b.toString());
			informListeners(b);
		} catch (IOException io) {
			logger.error("IOException caught. " + io);
			io.printStackTrace();
		}
		logger.debug("Serverthread " + getName() + " finished. ");
	}
}
