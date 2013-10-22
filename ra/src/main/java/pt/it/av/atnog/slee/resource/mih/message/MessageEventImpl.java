//==============================================================================
// Brief   : Message Event Implementation
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

package pt.it.av.atnog.slee.resource.mih.message;

import java.util.EventObject;

import pt.it.av.atnog.slee.resource.mih.message.Message;
import pt.it.av.atnog.slee.resource.mih.message.MessageEvent;

/**
 * The MessageEvent wraps an Message object and adds the footprint of the
 * requesting object to it. The implementation class MessageEventImpl implements
 * the interface MessageEvent.
 *
 * @author Carlos Guimarães
 *
 *         Based on code from:
 * @author Michael Maretzke
 */
public class MessageEventImpl extends EventObject implements MessageEvent {

	private Message message;

	/**
	 * The factory method to generate a new instance of MessageEvent.
	 *
	 * @param obj
	 *            the generating object's reference
	 * @param message
	 *            message to attach to this event object
	 * @return a newly created MessageEvent object
	 */
	public static MessageEvent getInstance(Object obj, Message message) {
		return new MessageEventImpl(obj, message);
	}

	/**
	 * Creates a new instance of MessageEvent
	 */
	private MessageEventImpl(Object source, Message message) {
		super(source);
		this.message = message;
	}

	public Message getMessage() {
		return message;
	}
}
