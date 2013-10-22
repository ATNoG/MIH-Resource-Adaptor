//==============================================================================
// Brief   : Message Activity
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
package pt.it.av.atnog.slee.resource.mih.ratype;

import java.io.Serializable;

/**
 * The RAFActivity object models an activity controlled by the resource adaptor.
 * In the case of RAFrame resource adaptor the activity is quite simple. The
 * RAFrame resource adaptor accepts messages from the MIH protocol. The various
 * protocol messages are embedded into a dialog. <br>
 * Malformed protocol messages are simply discarded. <br>
 * The RAFActivity is referenced in the deployment descriptor file
 * "resource-adapter-type-jar.xml" in the tag <activity-type>, sub-tag
 * <activity-type-name>: pt.it.av.atnog.slee.resource.mih.ratype.RAFActivity. <br>
 *
 * @author Carlos Guimarães
 *
 *         Based on code from:
 * @author Michael Maretzke
 * @author amit bhayani
 */
public interface MessageActivity extends Serializable {

	/**
	 * Access the time of creation of this activity object
	 */
	public long getStartTime();

}
