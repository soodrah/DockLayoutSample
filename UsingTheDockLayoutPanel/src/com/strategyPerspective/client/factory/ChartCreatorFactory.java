/**
 * 
 */
package com.strategyPerspective.client.factory;

import com.strategyPerspective.client.controlPanel.charts.ControlPanelColumnChart;
import com.strategyPerspective.client.controlPanel.charts.ControlPanelGaugeChart;
import com.strategyPerspective.client.controlPanel.charts.ControlPanelSplineChart;
import com.strategyPerspective.client.controlPanel.charts.TippingPointChartCreator;

import static com.strategyPerspective.client.constants.ClientConstants.*;

/**
 * @author rahulsood
 *
 */
public class ChartCreatorFactory {
	
	
	
	
	public TippingPointChartCreator getChartCreator(String chartCreatorType){
		
		TippingPointChartCreator tippingPointChartCreator = null;
		
		if(chartCreatorType.equalsIgnoreCase(splineCreator))
			tippingPointChartCreator = new ControlPanelSplineChart();
		else if(chartCreatorType.equalsIgnoreCase(gaugeChartCreator))
			tippingPointChartCreator = new ControlPanelGaugeChart();
		else if(chartCreatorType.equalsIgnoreCase(collChartCreator))
			tippingPointChartCreator = new ControlPanelColumnChart();
		
		
		return tippingPointChartCreator;
	}
	

}
