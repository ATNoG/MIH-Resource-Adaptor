//==============================================================================
// Brief   : Fireable Event Type Cache
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

import java.util.concurrent.ConcurrentHashMap;

import javax.slee.EventTypeID;
import javax.slee.facilities.EventLookupFacility;
import javax.slee.facilities.Tracer;
import javax.slee.resource.FireableEventType;

/**
 *
 * @author Carlos Guimarães
 *
 *         Based on code from:
 * @author amit bhayani
 */
public class FireableEventTypeCache {
	public static final String VENDOR = "pt.it.av.atnog";
	public static final String VERSION = "1.0";

	private ConcurrentHashMap<String, FireableEventType> eventTypes = new ConcurrentHashMap<String, FireableEventType>();

	private final Tracer tracer;

	public FireableEventTypeCache(Tracer tracer) {
		this.tracer = tracer;
	}

	public FireableEventType getEventType(
			EventLookupFacility eventLookupFacility, String eventName) {
		FireableEventType eventType = eventTypes.get(eventName);
		if (eventType == null) {
			try {
				eventType = eventLookupFacility
						.getFireableEventType(new EventTypeID(eventName,
								VENDOR, VERSION));
			} catch (Throwable e) {
				tracer.severe(
						"Failed to obtain fireable event type for event with name "
								+ eventName, e);
				return null;
			}
			eventTypes.put(eventName, eventType);
		}
		return eventType;
	}
}
