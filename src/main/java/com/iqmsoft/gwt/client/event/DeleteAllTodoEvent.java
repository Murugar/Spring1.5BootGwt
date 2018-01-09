package com.iqmsoft.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class DeleteAllTodoEvent extends GwtEvent<DeleteAllTodoEventHandler>{

	public static Type<DeleteAllTodoEventHandler> TYPE = new Type<DeleteAllTodoEventHandler>();

	public DeleteAllTodoEvent(){
		
	}
	
	@Override
	protected void dispatch(DeleteAllTodoEventHandler handler) {
		handler.onDeleteAllTodoEventHandler(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DeleteAllTodoEventHandler> getAssociatedType() {
		return TYPE;
	}
	
	

}
