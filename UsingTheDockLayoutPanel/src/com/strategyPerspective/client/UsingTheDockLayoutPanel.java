package com.strategyPerspective.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.strategyPerspective.client.GQuerySelectors.TippingSelectors;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class UsingTheDockLayoutPanel implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		MyDockLayoutPanel dockLayoutPanel = new MyDockLayoutPanel();
		
		RootLayoutPanel.get().add(dockLayoutPanel);
		TippingSelectors tippingSelectors = GWT.create(TippingSelectors.class);
		
		 tippingSelectors.getFifthRectangle().css("fill", "#39F");
		 tippingSelectors.getFourthRectangle().css("stroke-opacity", "0");
		 tippingSelectors.getSixthRectangle().css("stroke-width", "0");
	}
}
