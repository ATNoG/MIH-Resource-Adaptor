//==============================================================================
// Brief   : Message Resource Adaptor SBB Interface Implementation
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

import javax.slee.facilities.Tracer;

import pt.it.av.atnog.slee.resource.mih.message.Message;
import pt.it.av.atnog.slee.resource.mih.message.MessageFactory;
import pt.it.av.atnog.slee.resource.mih.ratype.MessageResourceAdaptorSbbInterface;

/**
 * RAFrameProviderImpl implements the interface RAFrameProvider and provides
 * functionalities of the underlying resource adaptor to the Sbb. <br>
 * For further information, please refer to the interface RAFrameProvider.
 *
 * @author Carlos Guimarães
 *
 *         Based on code from:
 * @author Michael Maretzke
 * @author amit bhayani
 */
public class MessageResourceAdaptorSbbInterfaceImpl implements
		MessageResourceAdaptorSbbInterface {
	private Tracer tracer;
	private MessageFactory messageFactory;
	// reference to the RAFrame resource adaptor
	private MessageResourceAdaptor ra;

	/**
	 * Create an instance of RAFrameProvicer
	 *
	 * @param ra
	 *            the parent resource adaptor
	 * @param messageFactory
	 *            the associated message factory
	 * @return a new created instance of RAFrameProvider
	 */
	public MessageResourceAdaptorSbbInterfaceImpl(MessageResourceAdaptor ra,
			MessageFactory messageFactory) {
		this.ra = ra;
		this.messageFactory = messageFactory;

	}

	void initTracer() {

		this.tracer = this.ra.getResourceAdaptorContext().getTracer(
				MessageResourceAdaptorSbbInterfaceImpl.class.getSimpleName());
	}

	public MessageResourceAdaptor getRAFrameRA() {
		if (this.tracer.isFineEnabled()) {
			this.tracer.fine("getRAFrameRA() called.");
		}
		return ra;
	}

	/**
	 * send() is a functionality of the resource adaptor exposed to the Sbb. Due
	 * to the architecture of the Sbb having only access to the RAFrameProvider
	 * instead of the RAFrameResourceAdaptor directly, the resource adaptor
	 * developer is able to limit the privileges of a Sbb to invoke methods of
	 * the resource adaptor directly.
	 *
	 * @param message
	 *            is the message to send
	 */
	public void send(byte[] message) {
		if (this.tracer.isFineEnabled()) {
			this.tracer.fine("Sending the message to the stack");
		}

		ra.send(message);
	}

	// implements RAFrameResourceAdaptorSbbInterface
	public MessageFactory getMessageFactory() {
		if (this.tracer.isFineEnabled()) {
			this.tracer.fine("getMessageFactory() called.");
		}
		return messageFactory;
	}
}
