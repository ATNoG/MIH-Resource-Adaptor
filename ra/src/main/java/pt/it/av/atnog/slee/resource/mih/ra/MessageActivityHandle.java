//==============================================================================
// Brief   : Message Activity Handle
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

import java.io.Serializable;

import javax.slee.resource.ActivityHandle;

/**
 * RAFActivityHandle represents the specific activity handle for RAFrame
 * resource adaptor. According to JSLEE 1.1 specification, "an activity handle
 * parameter is a distributable reference to an activity. The activity handle is
 * used by the SLEE to determine the activity context that represents the
 * underlying activity in the resource for an event to be delivered on. The
 * resource adaptor defines the activity handle and activity association however
 * this association must be a 1:1 relationship." <br>
 * In the case of RAFrame, the activity handle is bound to the "dialog" which is
 * indicated by the identifier (id) in incoming protocol messages. So, the id
 * itself is the activity handle and is wrapped and represented through
 * RAFActivityHandle. <br>
 * For further information, please refer to JSLEE v1.1 Specification, Early
 * Draft Review Page 314.
 *
 * @author Michael Maretzke
 * @author amit bhayani
 */
public class MessageActivityHandle implements ActivityHandle, Serializable {
	private String handle = null;

	public MessageActivityHandle(String id) {
		this.handle = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((handle == null) ? 0 : handle.hashCode());
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
		final MessageActivityHandle other = (MessageActivityHandle) obj;
		if (handle == null) {
			if (other.handle != null)
				return false;
		} else if (!handle.equals(other.handle))
			return false;
		return true;
	}

	public String getHandle() {
		return handle;
	}

}
