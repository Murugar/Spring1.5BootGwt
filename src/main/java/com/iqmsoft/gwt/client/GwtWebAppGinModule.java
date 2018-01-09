package com.iqmsoft.gwt.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.iqmsoft.gwt.client.controller.WebAppController;
import com.iqmsoft.gwt.client.model.ModelHandler;
import com.iqmsoft.gwt.client.resource.ApplicationResources;
import com.iqmsoft.gwt.client.resource.Messages;
import com.iqmsoft.gwt.client.ui.MainPanel;


public class GwtWebAppGinModule extends AbstractGinModule{

	@Override
	protected void configure() {
		// Resources
		bind(Messages.class).in(Singleton.class);
		bind(ApplicationResources.class).in(Singleton.class);
		
		// Core
		bind(SimpleEventBus.class).in(Singleton.class);
		bind(WebAppController.class).in(Singleton.class);
		bind(ModelHandler.class).in(Singleton.class);
		
		// UI
		bind(MainPanel.class).in(Singleton.class);
	}

}
