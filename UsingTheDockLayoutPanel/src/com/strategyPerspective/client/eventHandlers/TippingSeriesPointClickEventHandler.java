/**
 * 
 */
package com.strategyPerspective.client.eventHandlers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.events.PointClickEvent;
import org.moxieapps.gwt.highcharts.client.events.PointClickEventHandler;

import com.strategyPerspective.client.controlPanel.charts.ControlPanelSplineChart;

import static com.strategyPerspective.client.constants.ClientConstants.*;

/**
 * @author rahulsood
 *
 */
public class TippingSeriesPointClickEventHandler implements PointClickEventHandler {

	private Logger logger = Logger.getLogger("com.strategyPerspective.client.eventHandlers.TippingSeriesPointClickEventHandler");
	
	private ControlPanelSplineChart splineChart = null;
	
	 public TippingSeriesPointClickEventHandler() {
		
	}
	
	
	 public TippingSeriesPointClickEventHandler(ControlPanelSplineChart cpSplineChart){
		 this.splineChart = cpSplineChart;
	 }
	 
	
	/* (non-Javadoc)
	 * @see org.moxieapps.gwt.highcharts.client.events.PointClickEventHandler#onClick(org.moxieapps.gwt.highcharts.client.events.PointClickEvent)
	 */
	@Override
	public boolean onClick(PointClickEvent pointClickEvent) {
		
		boolean successfullySelected = false;
		
		if (logger.isLoggable(Level.FINE)) {
			logger.fine(logger.getName() + ": received pointClickEvent for Series: " + pointClickEvent.getSeriesName() + " point clicked x: " + 
						 pointClickEvent.getXAsLong() + " y: " + pointClickEvent.getYAsLong());
		}
		
		Series seriesOfThisPoint= null;
		try{
			seriesOfThisPoint = this.splineChart.getSeriesByName(pointClickEvent.getSeriesName());
			
		}catch(Exception ex){
			logger.severe("Issue selecting series, please check, exception: " + ex.getMessage());
			return false;
		}
		 
		if(pointClickEvent.getSeriesName().equalsIgnoreCase(apatheticForecastSeriesName) &&
		   ( pointClickEvent.getYAsLong() == apatheticForecastStartingPoint
		   ||pointClickEvent.getYAsLong() <= apatheticForecastStartingPoint+3 
		   ||pointClickEvent.getYAsLong() >= apatheticForecastStartingPoint-3 )){
			
			//lets mark this series selected and lets mark the other series not selected.
			seriesOfThisPoint.select(true);
			successfullySelected = true;
			//get the counterpart bottomSeries and mark it unselected
			try{
				Series advocateForeCastSeries = this.splineChart.getSeriesByName(advocateForecastSeriesName);
				advocateForeCastSeries.select(false);
			}catch(Exception ex){
				logger.severe("Issue selecting series, please check, exception: " + ex.getMessage());
				
			}
			
			
		}else if(pointClickEvent.getSeriesName().equalsIgnoreCase(advocateForecastSeriesName) && 
				 (pointClickEvent.getYAsLong() == advocateForecastStartingPoint ||
				  pointClickEvent.getYAsLong()  <= advocateForecastStartingPoint +3 )){
			
			//lets mark this series selected and lets mark the other series not selected.
			seriesOfThisPoint.select(true);
			successfullySelected = true;
			//get the counterpart topSeries and mark it unselected
			try{
				Series apatheticForeCastSeries = this.splineChart.getSeriesByName(apatheticForecastSeriesName);
				apatheticForeCastSeries.select(false);
			}catch(Exception ex){
				logger.severe("Issue selecting series, please check, exception: " + ex.getMessage());
				
			}
		}
		
		if(seriesOfThisPoint != null &&
		   seriesOfThisPoint.getPoints().length > 1 &&
		   (pointClickEvent.getYAsLong() != apatheticForecastStartingPoint &&
		    pointClickEvent.getYAsLong() != advocateForecastStartingPoint)){
		   	pointClickEvent.getPoint().remove();
			successfullySelected = true;
		}
			
		
		
		return successfullySelected;
	}

}
