//==============================================================================
// Brief   : Message Factory Implementation
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

import pt.it.av.atnog.slee.resource.mih.message.Message;
import pt.it.av.atnog.slee.resource.mih.message.MessageEvent;
import pt.it.av.atnog.slee.resource.mih.message.MessageFactory;

/**
 * MessageFactoryImpl implements the functions of MessageFactory.
 *
 * @author Carlos Guimarães
 *
 *         Based on code from:
 * @author Michael Maretzke
 */
public class MessageFactoryImpl implements MessageFactory {

	public MessageFactoryImpl() {
	}

	public Message createMessage(int version, boolean ack_req, boolean ack_rsp,
			boolean uir, boolean m, int fn, boolean rsvd1, int mid, int rsvd2,
			int tid, int payload_length, byte[] payload) {

		return MessageImpl.getInstance(version, ack_req, ack_rsp, uir, m, fn,
				rsvd1, mid, rsvd2, tid, payload_length, payload);
	}

	public MessageEvent createMessageEvent(Object obj, Message message) {
		return MessageEventImpl.getInstance(obj, message);
	}
}
