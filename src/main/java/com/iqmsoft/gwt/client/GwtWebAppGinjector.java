package com.iqmsoft.gwt.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.iqmsoft.gwt.client.controller.WebAppController;
import com.iqmsoft.gwt.client.model.ModelHandler;
import com.iqmsoft.gwt.client.resource.ApplicationResources;
import com.iqmsoft.gwt.client.resource.Messages;
import com.iqmsoft.gwt.client.ui.MainPanel;


@GinModules(GwtWebAppGinModule.class)
public interface GwtWebAppGinjector extends Ginjector {
	
	public SimpleEventBus getEventBus();
	
	public ApplicationResources getApplicationResources();
	
	public Messages getMessages();
	
	public WebAppController getWebAppController();
	
	public ModelHandler getModelHandler();
	
	public MainPanel getMainPanel(); 
}
