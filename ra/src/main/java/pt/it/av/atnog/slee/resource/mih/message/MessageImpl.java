//==============================================================================
// Brief   : Message Implementation
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

import java.io.UnsupportedEncodingException;

import pt.it.av.atnog.slee.resource.mih.message.Message;

/**
 * The Message represents the means of communication in the MIHStack resource
 * adaptor defined protocol. It abstracts the underlying protocol information by
 * wrapping the protocol information into Java objects.<br>
 * Message follows the value object pattern and can only be constructed by a
 * factory object. The MessageImpl object implements the Message interface and
 * is the concrete implementation class of the more abstract interface which is
 * the only visible interface for the SBB.
 *
 * @author Carlos Guimarães
 *
 *         Based on code from:
 * @author Michael Maretzke
 */
public class MessageImpl implements Message {
	int version;
	boolean ack_req;
	boolean ack_rsp;
	boolean uir;
	boolean m;
	int fn;
	boolean rsvd1;
	int mid;
	int rsvd2;
	int tid;
	int payload_length;
	byte[] payload;

	public MessageImpl() {}

	/**
	 * The factory method to generate a new instance of Message.
	 *
	 * @param version
	 *            This field is used to specify the version of the MIH protocol
	 *            used
	 * @param ack_req
	 *            This field is used for requesting an acknowledgement for the
	 *            message
	 * @param ack_rsp
	 *            This field is used for responding to the request of an
	 *            acknowledgement for the message
	 * @param uir
	 *            This field is used to indicate of the protocol message is sent
	 *            in pre.authentication/pre-association state
	 * @param m
	 *            This field is used for indicating that the message is a
	 *            fragment to be followed by another fragment
	 * @param fn
	 *            This field is used for representing the sequence number of a
	 *            fragment
	 * @param rsvd1
	 *            This field is intentionally kept reserved
	 * @param mid
	 *            This field indicates the message ID
	 * @param rsvd2
	 *            This field is intentionally kept reserved
	 * @param tid
	 *            This field is used for identifying the transaction
	 * @param payload_length
	 *            This field indicates the total length of the variable payload
	 * @param payload
	 *            This field contains the payload information
	 * @return a newly created Message object
	 */
	public static Message getInstance(int version, boolean ack_req,
			boolean ack_rsp, boolean uir, boolean m, int fn, boolean rsvd1,
			int mid, int rsvd2, int tid, int payload_length, byte[] payload) {
		return new MessageImpl(version, ack_req, ack_rsp, uir, m, fn, rsvd1,
				mid, rsvd2, tid, payload_length, payload);
	}

	private MessageImpl(int version, boolean ack_req, boolean ack_rsp,
			boolean uir, boolean m, int fn, boolean rsvd1, int mid, int rsvd2,
			int tid, int payload_length, byte[] payload) {
		this.version = version;
		this.ack_req = ack_req;
		this.ack_rsp = ack_rsp;
		this.uir = uir;
		this.m = m;
		this.fn = fn;
		this.rsvd1 = rsvd1;
		this.mid = mid;
		this.rsvd2 = rsvd2;
		this.tid = tid;
		this.payload_length = payload_length;
		this.payload = payload;
	}

	/**
	 * Access the message's version.
	 *
	 * @return the message's version
	 */
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Access the message's acknowledge request flag.
	 *
	 * @return the message's acknowledge request flag
	 */
	public boolean getAckReq() {
		return ack_req;
	}
	public void setAckReq(boolean ack_req) {
		this.ack_req = ack_req;
	}

	/**
	 * Access the message's acknowledge response flag.
	 *
	 * @return the message's acknowledge response flag
	 */
	public boolean getAckRsp() {
		return ack_rsp;
	}
	public void setAckRsp(boolean ack_rsp) {
		this.ack_rsp = ack_rsp;
	}

	/**
	 * Access the message's unauthenticated information request flag.
	 *
	 * @return the message's unauthenticated information request flag
	 */
	public boolean getUir() {
		return uir;
	}
	public void setUir(boolean uir) {
		this.uir = uir;
	}

	/**
	 * Access the message's more fragments flag.
	 *
	 * @return the message's more fragments flag
	 */
	public boolean getM() {
		return m;
	}
	public void setM(boolean m) {
		this.m = m;
	}

	/**
	 * Access the message's fragment number.
	 *
	 * @return the message's fragment number
	 */
	public int getFn() {
		return fn;
	}
	public void setFn(int fn) {
		this.fn = fn;
	}

	/**
	 * Access the message's reserved1 field.
	 *
	 * @return the message's reserved1 field
	 */
	public boolean getRsvd1() {
		return rsvd1;
	}
	public void setRsvd1(boolean rsvd1) {
		this.rsvd1 = rsvd1;
	}

	/**
	 * Access the message's MIH identifier.
	 *
	 * @return the message's MIH identifier
	 */
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}


	/**
	 * Access the message's reserved2 field.
	 *
	 * @return the message's reserved2 field
	 */
	public int getRsvd2() {
		return rsvd2;
	}
	public void setRsvd2(int rsvd2) {
		this.rsvd2 = rsvd2;
	}

	/**
	 * Access the message's transaction identifier.
	 *
	 * @return the message's transaction identifier
	 */
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}

	/**
	 * Access the message's variable payload length.
	 *
	 * @return the message's variable payload length
	 */
	public int getPayloadLength() {
		return payload_length;
	}

	/**
	 * Access the message's payload.
	 *
	 * @return the message's payload
	 */
	public byte[] getPayload() {
		return payload;
	}
	public void setPayload(byte[] payload) {
		this.payload = payload;
		this.payload_length = payload.length;
	}

	/**
	 * Serialize the message.
	 *
	 * @return Message bytes encapsulated in a String.
	 */
	public byte[] toBytes() {
		byte message[] = new byte[8 + payload_length];

		message[0] = (byte) ((version << 4) | (ack_req ? 1 << 3 : 0 << 3)
				| (ack_rsp ? 1 << 2 : 0 << 2) | (uir ? 1 << 1 : 0 << 1) | (m ? 1
				: 0));
		message[1] = (byte) ((fn << 1) | (rsvd1 ? 1 : 0));
		message[2] = (byte) (mid >> 8);
		message[3] = (byte) (mid & 0x00FF);
		message[4] = (byte) ((rsvd2 << 4) | ((tid & 0xF00) >> 8));
		message[5] = (byte) (tid & 0x00FF);
		message[6] = (byte) (payload_length >> 8);
		message[7] = (byte) (payload_length & 0x00FF);

		for (int i = 0; i < payload_length; ++i)
			message[i + 8] = payload[i];

		return message;
	}

	public void fromBytes(byte[] message) {
		version = (message[0] & 0xF0)  >> 4;
		ack_req = ((message[0] & 0x08) >> 3) == 1;
		ack_rsp = ((message[0] & 0x04) >> 2) == 1;
		uir     = ((message[0] & 0x02) >> 1) == 1;
		m       = ((message[0] & 0x01))      == 1;
		fn      = (message[1] & 0xFE) >> 1;
		rsvd1   = ((message[1] & 0x01))      == 1;
		mid     = (message[2] << 8) | message[3];
		rsvd2   = (message[4] & 0xF0) >> 4;
		tid     = (int)(((message[4] & 0x0F) << 8) | message[5]);

		payload_length = message.length - 8;
		payload = new byte[message.length - 8];
		for (int i = 0; i < payload_length; ++i)
			payload[i] = message[i + 8];
	}
}
