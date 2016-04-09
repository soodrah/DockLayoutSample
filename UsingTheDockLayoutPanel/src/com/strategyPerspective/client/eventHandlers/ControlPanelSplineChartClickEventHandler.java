/**
 * 
 */
package com.strategyPerspective.client.eventHandlers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.events.ChartClickEvent;
import org.moxieapps.gwt.highcharts.client.events.ChartClickEventHandler;

import com.strategyPerspective.client.controlPanel.charts.ControlPanelSplineChart;
import static com.strategyPerspective.client.constants.ClientConstants.*;

/**
 * @author rahulsood
 *
 */
public class ControlPanelSplineChartClickEventHandler implements ChartClickEventHandler {

	private Logger logger = Logger.getLogger("com.strategyPerspective.client.eventHandlers.ControlPanelSplineChartClickEventHandler");
	
	private ControlPanelSplineChart cpSplineChart = null;
	
	public  ControlPanelSplineChartClickEventHandler() {
		
	}
	
	
	public ControlPanelSplineChartClickEventHandler(ControlPanelSplineChart splineChart){
		this.cpSplineChart = splineChart;
	}
	
	
	/* (non-Javadoc)
	 * @see org.moxieapps.gwt.highcharts.client.events.ChartClickEventHandler#onClick(org.moxieapps.gwt.highcharts.client.events.ChartClickEvent)
	 */
	@Override
	public boolean onClick(ChartClickEvent chartClickEvent) {
		boolean successfullyAdded = false;
		
		Series selectedSeries = null;
		try{
			selectedSeries = this.cpSplineChart.getSelectedSeries();
			if(logger.isLoggable(Level.FINE)){
				logger.log(Level.FINE, logger.getName() + ": Selected series found, series name is: " + selectedSeries.getName());
			}
		}catch(Exception ex){
			logger.log(Level.ALL, ex.getMessage());
			logger.log(Level.ALL, "By default selecting Apathetic series, correct the problem to proceed better.");
			//just set apathetic as selected series.
			selectedSeries = this.cpSplineChart.getApatheticForecast();
			selectedSeries.select(true);
			successfullyAdded = false;
		}
		
		if(selectedSeries != null &&
		   chartClickEvent.getXAxisValueAsLong() <= seriesMaxX &&
		   chartClickEvent.getYAxisValueAsLong() <= seriesMaxY){
			   selectedSeries.addPoint(chartClickEvent.getXAxisValueAsLong(), chartClickEvent.getYAxisValueAsLong(), 
					   				   true, false, true);
		   }
		
		return successfullyAdded;
	}

}
