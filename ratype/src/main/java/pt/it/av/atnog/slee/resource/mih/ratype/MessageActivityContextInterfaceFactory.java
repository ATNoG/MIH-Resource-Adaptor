//==============================================================================
// Brief   : Message Activity Context Interface Factory
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

import javax.slee.ActivityContextInterface;
import javax.slee.FactoryException;
import javax.slee.UnrecognizedActivityException;

/**
 * The RAFrameActivityContextInterfaceFactory interface defines the activity
 * context interface factory as described in Section 7.6.1 of the JAIN SLEE
 * Specification, Final Release Page 91. The SBB uses this object to access the
 * activity context of a specific resource adaptors activity object. <br>
 * The RAFrameAcitvityContextInterfaceFactory is referenced in the deployment
 * descriptor file "resource-adaptor-type-jar.xml" in the tag
 * <activity-context-interface-factory-interface>, sub-tag
 * <activity-context-interface-factory-interface-name>:
 * pt.it.av.atnog.slee.resource
 * .mih.ratype.RAFrameActivityContextInterfaceFactory <br>
 * A SBB references this object through a link in the deployment descriptor file
 * "sbb-jar.xml". The tag <resource-adaptor-type-binding> contains a sub-tag
 * <activity-context-interface-factory-name> where a JNDI name for this object
 * is defined: "slee/resources/RAFrameRA/raframeacif". <br>
 * The class pt.it.av.atnog.slee.resource.mih.ra.
 * RAFrameActivityContextInterfaceFactoryImpl implements this interface. For
 * further information, please refer to JAIN SLEE Specification 1.0, Final
 * Release Page 91 and 239 and following pages.
 *
 * @author Carlos Guimarães
 *
 *         Based on code from:
 * @author Michael Maretzke
 * @author amit bhayani
 */
public interface MessageActivityContextInterfaceFactory {
	public ActivityContextInterface getActivityContextInterface(
			MessageActivity activity) throws NullPointerException,
			UnrecognizedActivityException, FactoryException;
}
