//==============================================================================
// Brief   : Message Activity Implementation
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
package pt.it.av.atnog.slee.resource.mih.ra;

import pt.it.av.atnog.slee.resource.mih.ratype.MessageActivity;

/**
 * The RAFActivityImpl implements the RAFActivity interface. For further
 * information, please refer to the description of the interface RAFActivity.
 *
 * @author Carlos Guimarães
 *
 *         Based on code from:
 * @author Michael Maretzke
 * @author amit bhayani
 */
public class MessageActivityImpl implements MessageActivity {

	// timestamp of creation of activity
	private long initTime;

	private String messageId;

	private String raImprint;

	public MessageActivityImpl(String messageId, String raImprint) {
		initTime = System.currentTimeMillis();
		this.messageId = messageId;
		this.raImprint = raImprint;
	}

	public long getStartTime() {
		return initTime;
	}

	public String getRaImprint() {
		return raImprint;
	}

	public void setRaImprint(String raImprint) {
		this.raImprint = raImprint;
	}

	@Override
	public String toString() {
		return "MessageActivityImpl messagenId=" + messageId + ", raImprint="
				+ raImprint + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (initTime ^ (initTime >>> 32));
		result = prime * result
				+ ((raImprint == null) ? 0 : raImprint.hashCode());
		result = prime * result
				+ ((messageId == null) ? 0 : messageId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageActivityImpl other = (MessageActivityImpl) obj;
		if (initTime != other.initTime)
			return false;
		if (raImprint == null) {
			if (other.raImprint != null)
				return false;
		} else if (!raImprint.equals(other.raImprint))
			return false;
		if (messageId == null) {
			if (other.messageId != null)
				return false;
		} else if (!messageId.equals(other.messageId))
			return false;

		return true;
	}
}
