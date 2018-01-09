package com.iqmsoft.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.iqmsoft.gwt.client.controller.WebAppController;
import com.iqmsoft.gwt.client.resource.ApplicationResources;
import com.iqmsoft.gwt.client.ui.MainPanel;


public class GwtWebApp implements EntryPoint {

	/**
	 * gin injector
	 */
	private final GwtWebAppGinjector injector = GWT.create(GwtWebAppGinjector.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// ensure resources are injected
		ApplicationResources.INSTANCE.style().ensureInjected();
		// get controler from gin jector
		WebAppController controller = injector.getWebAppController();
		// bind event handlers
		controller.bindHandlers();
		// get main panel
		MainPanel mainPanel = injector.getMainPanel();
		// add for display
		RootLayoutPanel.get().add(mainPanel);
	}
}
