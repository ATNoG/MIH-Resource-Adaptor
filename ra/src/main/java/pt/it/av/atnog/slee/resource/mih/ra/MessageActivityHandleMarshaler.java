//==============================================================================
// Brief   : Message Activity Handle Marshaler
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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.slee.resource.ActivityHandle;
import javax.slee.resource.FireableEventType;
import javax.slee.resource.Marshaler;

public class MessageActivityHandleMarshaler implements Marshaler {

	@Override
	public int getEstimatedEventSize(FireableEventType arg0, Object arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getEstimatedHandleSize(ActivityHandle arg0) {
		MessageActivityHandle mah = (MessageActivityHandle) arg0;

		return mah.getHandle().getBytes().length;
	}

	@Override
	public ByteBuffer getEventBuffer(FireableEventType arg0, Object arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void marshalEvent(FireableEventType arg0, Object arg1,
			DataOutput arg2) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void marshalHandle(ActivityHandle arg0, DataOutput arg1)
			throws IOException {
		MessageActivityHandle mah = (MessageActivityHandle) arg0;
		arg1.writeUTF(mah.getHandle());

	}

	@Override
	public void releaseEventBuffer(FireableEventType arg0, Object arg1,
			ByteBuffer arg2) {
		throw new UnsupportedOperationException();

	}

	@Override
	public Object unmarshalEvent(FireableEventType arg0, DataInput arg1)
			throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ActivityHandle unmarshalHandle(DataInput arg0) throws IOException {
		MessageActivityHandle mah = new MessageActivityHandle(arg0.readUTF());
		return mah;

	}

}
