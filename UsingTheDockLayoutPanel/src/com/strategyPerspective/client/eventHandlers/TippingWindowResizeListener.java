/**
 * 
 */
package com.strategyPerspective.client.eventHandlers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;

import com.strategyPerspective.client.MyDockLayoutPanel;

import static com.strategyPerspective.client.constants.ClientConstants.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rahulsood
 *
 */
public class TippingWindowResizeListener implements ResizeHandler {

	private Logger logger = Logger.getLogger("com.strategyPerspective.client.TippingWindowResizeListener");
	private MyDockLayoutPanel tippingUi = null;

	public TippingWindowResizeListener() {

	}

	public TippingWindowResizeListener(MyDockLayoutPanel tippingUi) {
		this.tippingUi = tippingUi;
	}

	@Override
	public void onResize(ResizeEvent event) {
		if (logger.isLoggable(Level.INFO)) {
			logger.info("ResizeEvent event=" + event + " - start -"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// get Window Size and Height
		int clientWidth = event.getWidth();
		int clientHeight = event.getHeight();

		if (logger.isLoggable(Level.FINE)) {
			logger.fine("containerChartHeight in windowResize "
					+ this.tippingUi.getControlPanelTopChart().getOffsetHeight());
			logger.fine("containerChartHeight in windowResize "
					+ this.tippingUi.getControlPanelTopChart().getOffsetWidth());
		}

		//windows resize event should resize all charts that need to be resized....one at a time, in reality, i will use an array to cycle through for resizing , should subclass certain classes.
		//resize spline chart
		if(this.tippingUi.getSplineChart() != null){
			tippingUi.sizeTippingCharts(this.tippingUi.getSplineChart(), this.tippingUi.getControlPanelTopChart(), chartWidthPercent, chartHeightPercent);
			
		} 
		if(this.tippingUi.getGaugeChart() != null){
			tippingUi.sizeTippingCharts(this.tippingUi.getGaugeChart(), null, chartGaugeWidthPercent, chartGaugeHeightPercent);
			this.tippingUi.getColChart().getXAxis().setAxisTitleText("Month: 2");
			
		}
		if(this.tippingUi.getColChart() != null){
			tippingUi.sizeTippingCharts(this.tippingUi.getColChart(), null, chartColWidthPercent, chartColHeightPercent);
		}
		else {
			if (logger.isLoggable(Level.FINE)) {
				logger.fine("Warning: No chart found, Please create a chart and add to TippingPoint");
			}
		}

		if (logger.isLoggable(Level.CONFIG)) {
			logger.config("ResizeEvent event=" + event + " - end"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
