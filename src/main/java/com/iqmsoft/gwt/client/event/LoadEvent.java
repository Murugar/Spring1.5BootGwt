package com.iqmsoft.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class LoadEvent extends GwtEvent<LoadEventHandler> {

	public static Type<LoadEventHandler> TYPE = new Type<LoadEventHandler>();
	
	public LoadEvent(){
		
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoadEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoadEventHandler handler) {
		handler.onLoadEventHandler(this);
	}

}
