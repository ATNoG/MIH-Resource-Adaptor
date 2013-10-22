//==============================================================================
// Brief   : Message
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

/**
 * The Message represents the means of communication in the MIHStack resource
 * adaptor defined protocol. It abstracts the underlying protocol information by
 * wrapping the protocol information into Java objects.<br>
 * Message follows the value object pattern and can only be constructed by a
 * factory object.
 *
 * @author Carlos Guimarães
 *
 *         Based on code from:
 * @author Michael Maretzke
 */
public interface Message {

	public final static int MIH_Capability_Discover_request = 5121;
	public final static int MIH_Capability_Discover_indication = 7169;
	public final static int MIH_Capability_Discover_response = 6145;
	public final static int MIH_Capability_Discover_confirm = 4097;

	public final static int MIH_Register_request = 5122;
	public final static int MIH_Register_indication = 7170;
	public final static int MIH_Register_response = 6146;
	public final static int MIH_Register_confirm = 4098;

	public final static int MIH_DeRegister_request = 5123;
	public final static int MIH_DeRegister_indication = 7171;
	public final static int MIH_DeRegister_response = 6147;
	public final static int MIH_DeRegister_confirm = 4099;

	public final static int MIH_Event_Subscribe_request = 5124;
	public final static int MIH_Event_Subscribe_indication = 7172;
	public final static int MIH_Event_Subscribe_response = 6148;
	public final static int MIH_Event_Subscribe_confirm = 4100;

	public final static int MIH_Event_Unsubscribe_request = 5125;
	public final static int MIH_Event_Unsubscribe_indication = 7173;
	public final static int MIH_Event_Unsubscribe_response = 6149;
	public final static int MIH_Event_Unsubscribe_confirm = 4101;

	public final static int MIH_Link_Detected_indication = 11265;
	public final static int MIH_Link_Up_indication = 11266;
	public final static int MIH_Link_Down_indication = 11267;
	public final static int MIH_Link_Parameters_Report_indication = 11269;
	public final static int MIH_Link_Going_Down_indication = 11270;
	public final static int MIH_Link_Handover_Imminent_indication = 11271;
	public final static int MIH_Link_Handover_Complete_indication = 11272;
	public final static int MIH_Link_PDU_Transmit_Status_indication = 11273;

	public final static int MIH_Get_Parameters_request = 13313;
	public final static int MIH_Get_Parameters_indication = 15361;
	public final static int MIH_Get_Parameters_response = 14337;
	public final static int MIH_Get_Parameters_confirm = 12289;

	public final static int MIH_Configure_Thresholds_request = 13314;
	public final static int MIH_Configure_Thresholds_indication = 15362;
	public final static int MIH_Configure_Thresholds_response = 14338;
	public final static int MIH_Configure_Thresholds_confirm = 12290;

	public final static int MIH_Link_Actions_request = 13315;
	public final static int MIH_Link_Actions_indication = 15363;
	public final static int MIH_Link_Actions_response = 14339;
	public final static int MIH_Link_Actions_confirm = 12291;

	public final static int MIH_Net_HO_Candidate_Query_request = 13316;
	public final static int MIH_Net_HO_Candidate_Query_indication = 15364;
	public final static int MIH_Net_HO_Candidate_Query_response = 14340;
	public final static int MIH_Net_HO_Candidate_Query_confirm = 12292;

	public final static int MIH_MN_HO_Candidate_Query_request = 13317;
	public final static int MIH_MN_HO_Candidate_Query_indication = 15365;
	public final static int MIH_MN_HO_Candidate_Query_response = 14341;
	public final static int MIH_MN_HO_Candidate_Query_confirm = 12293;

	public final static int MIH_N2N_HO_Query_Resoures_request = 13318;
	public final static int MIH_N2N_HO_Query_Resoures_indication = 15366;
	public final static int MIH_N2N_HO_Query_Resoures_response = 14342;
	public final static int MIH_N2N_HO_Query_Resoures_confirm = 12294;

	public final static int MIH_MN_HO_Commit_request = 13319;
	public final static int MIH_MN_HO_Commit_indication = 15367;
	public final static int MIH_MN_HO_Commit_response = 14343;
	public final static int MIH_MN_HO_Commit_confirm = 12295;

	public final static int MIH_Net_HO_Commit_request = 13320;
	public final static int MIH_Net_HO_Commit_indication = 15368;
	public final static int MIH_Net_HO_Commit_response = 14344;
	public final static int MIH_Net_HO_Commit_confirm = 12296;

	public final static int MIH_N2N_HO_Commit_request = 13321;
	public final static int MIH_N2N_HO_Commit_indication = 15369;
	public final static int MIH_N2N_HO_Commit_response = 14345;
	public final static int MIH_N2N_HO_Commit_confirm = 12297;

	public final static int MIH_MN_HO_Complete_request = 13322;
	public final static int MIH_MN_HO_Complete_indication = 15370;
	public final static int MIH_MN_HO_Complete_response = 14346;
	public final static int MIH_MN_HO_Complete_confirm = 12298;

	public final static int MIH_N2N_HO_Complete_request = 13323;
	public final static int MIH_N2N_HO_Complete_indication = 15371;
	public final static int MIH_N2N_HO_Complete_response = 14347;
	public final static int MIH_N2N_HO_Complete_confirm = 12299;

	public final static int MIH_Get_Information_request = 17409;
	public final static int MIH_Get_Information_indication = 19457;
	public final static int MIH_Get_Information_response = 18433;
	public final static int MIH_Get_Information_confirm = 16385;

	public final static int MIH_Push_Information_request = 17410;
	public final static int MIH_Push_Information_indication = 19458;

	/**
	 * Access the message's version.
	 *
	 * @return the message's version
	 */
	public int getVersion();
	public void setVersion(int version);

	/**
	 * Access the message's acknowledge request flag.
	 *
	 * @return the message's acknowledge request flag
	 */
	public boolean getAckReq();
	public void setAckReq(boolean ack_req);

	/**
	 * Access the message's acknowledge response flag.
	 *
	 * @return the message's acknowledge response flag
	 */
	public boolean getAckRsp();
	public void setAckRsp(boolean ack_rsp);

	/**
	 * Access the message's unauthenticated information request flag.
	 *
	 * @return the message's unauthenticated information request flag
	 */
	public boolean getUir();
	public void setUir(boolean uir);

	/**
	 * Access the message's more fragments flag.
	 *
	 * @return the message's more fragments flag
	 */
	public boolean getM();
	public void setM(boolean m);

	/**
	 * Access the message's fragment number.
	 *
	 * @return the message's fragment number
	 */
	public int getFn();
	public void setFn(int fn);

	/**
	 * Access the message's reserved1 field.
	 *
	 * @return the message's reserved1 field
	 */
	public boolean getRsvd1();
	public void setRsvd1(boolean rsvd1);

	/**
	 * Access the message's MIH identifier.
	 *
	 * @return the message's MIH identifier
	 */
	public int getMid();
	public void setMid(int mid);

	/**
	 * Access the message's reserved2 field.
	 *
	 * @return the message's reserved2 field
	 */
	public int getRsvd2();
	public void setRsvd2(int rsvd2);

	/**
	 * Access the message's transaction identifier.
	 *
	 * @return the message's transaction identifier
	 */
	public int getTid();
	public void setTid(int tid);

	/**
	 * Access the message's variable payload length.
	 *
	 * @return the message's variable payload length
	 */
	public int getPayloadLength();

	/**
	 * Access the message's payload.
	 *
	 * @return the message's payload
	 */
	public byte[] getPayload();
	public void setPayload(byte[] payload);

	/**
	 * Serialize the message.
	 *
	 * @return Message bytes encapsulated in a String.
	 */
	public byte[] toBytes();

	/**
	 * Deserialize the message.
	 *
	 * @return Message bytes encapsulated in a String.
	 */
	public void fromBytes(byte[] message);
}
