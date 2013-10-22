//==============================================================================
// Brief   : Message Parser Implementation
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

/**
 * The MIHMessageParser accepts strings and decodes them according to predefined
 * rules into separate elements and creates value objects of type Message.<br>
 * The MIHStack stack supports messages from MIH protocol. <br>
 * The MIHMessageParser implements the simple message of the MIH protocol.
 *
 * @author Carlos Guimarães
 *
 *         Based on code from
 * @author Michael Maretzke
 */
public class MessageParserImpl implements MessageParser {

	public MessageParserImpl() {
	}

	public Message parse(byte[] message) throws MalformedMessageException {
		if (message.length < 8)
			throw new MalformedMessageException(
					"MessageParser.parse(): Received malformed message (Message size < 8)");

		int payload_length = ((message[6] & 0xFF) << 8) | (message[7] & 0xFF);
		if (payload_length + 8 != message.length)
			throw new MalformedMessageException(
					"MessageParser.parse(): Received malformed message (Message payload)");

		int version = (message[0] & 0xF0) >> 4;
		boolean ack_req = ((message[0] & 0x08) >> 3) == 1;
		boolean ack_rsp = ((message[0] & 0x04) >> 2) == 1;
		boolean uir = ((message[0] & 0x02) >> 1) == 1;
		boolean m = (message[0] & 0x01) == 1;
		int fn = (message[1] & 0xFE) >> 1;
		boolean rsvd1 = (message[1] & 0x01) == 1;
		int mid = ((message[2] & 0xFF) << 8) | (message[3] & 0xFF);
		int rsvd2 = (message[4] & 0xF0) >> 4;
		int tid = ((message[4] & 0x0F) << 8) | (message[5] & 0xFF);

		byte[] payload = new byte[message.length - 8];
		for (int i = 0; i < payload.length; ++i) {
  			payload[i] = message[i + 8];
		}

		return new MessageFactoryImpl().createMessage(version, ack_req,
				ack_rsp, uir, m, fn, rsvd1, mid, rsvd2, tid, payload_length,
				payload);
	}
}
