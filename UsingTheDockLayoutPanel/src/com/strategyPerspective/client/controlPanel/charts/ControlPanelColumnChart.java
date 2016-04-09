/**
 * 
 */
package com.strategyPerspective.client.controlPanel.charts;

import static com.strategyPerspective.client.constants.ClientConstants.*;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.Series.Type;
import org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions;



/**
 * @author rahulsood
 *
 */
public class ControlPanelColumnChart extends ControlPanelSplineChart {
	
	@Override
	public  Type getTypeForChart(){
		return Series.Type.COLUMN;
	}
	
	@Override
	protected SeriesPlotOptions createSeriesPlotOptions(){
		SeriesPlotOptions allSeriesPlotOptions = new SeriesPlotOptions();
		allSeriesPlotOptions.setShowInLegend(false);
		allSeriesPlotOptions.setShowCheckbox(false);
		allSeriesPlotOptions.setAnimation(this.customizeAnimation());
		allSeriesPlotOptions.setLineWidth(1);
		return allSeriesPlotOptions;
	}
	
	
	@Override
	public Chart setTippingAxesOptions(Chart tippingChart) {
		//setting YAxis options
		tippingChart.getYAxis()
					.setMin(0)
					.setMax(100)
					.setTickInterval(25);
		
		//setting XAxis options
		tippingChart.getXAxis()
					.setAxisTitleText(colChartXAxisTitle);
					
					
		return tippingChart;
	}
	
	
	
	@Override
	public Chart createAndAddSeries(Chart tippingChart) {
		
		
		tippingChart.addSeries(this.createApatheticSeries(tippingChart));
		tippingChart.addSeries(this.createAdvocateSeries(tippingChart));
		tippingChart.addSeries(this.createIncubatorSeries(tippingChart));
		tippingChart.addSeries(this.createResisterSeries(tippingChart));
		
		return tippingChart;
	}

}
