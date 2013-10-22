//==============================================================================
// Brief   : Message Resource Adaptor SBB Interface
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

import pt.it.av.atnog.slee.resource.mih.message.Message;
import pt.it.av.atnog.slee.resource.mih.message.MessageFactory;

/**
 * RAFrameResourceAdaptorSbbInterface describes the interface between SBBs and
 * the RAFrame resource adaptor. This interface provides means for SBBs to
 * communicate with the resource adaptor. <br>
 * RAFrameResourceAdaptorSbbInterface is defined in the deployment descriptor
 * file "resource-adaptor-type-jar.xml". Therein, the tag
 * <resource-adaptor-interface> and its sub-tag
 * <resource-adaptor-interface-name> reference to
 * pt.it.av.atnog.slee.resource.mih.ratype.MessageResourceAdaptorSbbInterface. <br>
 * To be available for SBBs the deployment descriptor file "sbb-jar.xml" needs
 * to bind a JNDI name to this interface. This is done in the tag
 * <resource-adaptor-entity-binding>. The tag <resource-adaptor-object-name>
 * defines the JNDI name of this interface: "slee/resources/raframe/1.0/sbb2ra". <br>
 * The class pt.it.av.atnog.slee.resource.mih.ra.RAFrameProvider implements this
 * interface. For further information, please refer to JAIN SLEE Specification
 * 1.0, Final Release Page 239 and following pages.
 *
 * @author Carlos Guimarães
 *
 *         Based on code from:
 * @author Michael Maretzke
 * @author amit bhayani
 */
public interface MessageResourceAdaptorSbbInterface {
	/**
	 * send() is a functionality of the resource adaptor exposed to the SBB. Due
	 * to the architecture of the SBB having only access to the RAFrameProvider
	 * instead of the RAFrameResourceAdaptor directly, the resource adaptor
	 * developer is able to limit the privileges of a SBB to invoke methods of
	 * the resource adaptor directly.
	 *
	 * @param message
	 *            is the message to send
	 */
	public void send(byte[] message);

	/**
	 * The MessageFactory is needed inside the SBB to create Message objects,
	 * which are sent through the resource adaptor.
	 */
	public MessageFactory getMessageFactory();

}
