/**
 * 
 */
package com.strategyPerspective.client.controlPanel.charts;

import static com.strategyPerspective.client.constants.ClientConstants.*;

import org.moxieapps.gwt.highcharts.client.Animation;
import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.ChartTitle;
import org.moxieapps.gwt.highcharts.client.ChartTitle.VerticalAlign;
import org.moxieapps.gwt.highcharts.client.Pane;
import org.moxieapps.gwt.highcharts.client.PaneBackground;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.Style;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.DataLabels;
import org.moxieapps.gwt.highcharts.client.Series.Type;
import org.moxieapps.gwt.highcharts.client.plotOptions.GaugePlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.Marker;
import org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.SolidGaugePlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.SplinePlotOptions;
import org.moxieapps.gwt.highcharts.client.labels.Labels;
import org.moxieapps.gwt.highcharts.client.labels.Labels.Align;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.strategyPerspective.client.eventHandlers.ControlPanelSplineChartClickEventHandler;
import com.strategyPerspective.client.eventHandlers.TippingSeriesPointClickEventHandler;

/**
 * @author rahulsood
 *
 */
public class ControlPanelGaugeChart extends TippingPointChartCreator {

	@Override
	public Type getTypeForChart(){
		return Series.Type.SOLID_GAUGE;
	}
	
	
	/* (non-Javadoc)
	 * @see com.strategyPerspective.client.controlPanel.charts.TippingPointChartCreator#setTippingOptions(org.moxieapps.gwt.highcharts.client.Chart)
	 */
	@Override
	public Chart setTippingOptions(Chart tippingChart) {
		Chart optionedTippingChart = tippingChart
				.setOption(new StringBuilder().append(chartPath).append(chartAnimationPath).toString(),
						chartAnimationType)
				.setBorderWidth(0)
				.setShadow(false)
				.setPane(new Pane()
			            .setCenter("50%", "60%")
			            .setStartAngle(-90)
			            .setEndAngle(90)
			           
			            
			            
			            .setSize("115%")//this is good size
			            .setBackground(new PaneBackground()
			                //.setBackgroundColor("#EEE")
			                .setInnerRadius("60%")
			                .setOuterRadius("100%")
			                .setOption("shape", "arc")
			            ))
				
				.setSolidGaugePlotOptions(new SolidGaugePlotOptions()
						.setDataLabels(new DataLabels()
		            			   .setBorderWidth(0)
		                
		                .setVerticalAlign(Labels.VerticalAlign.BOTTOM)
		                .setUseHTML(true)
		                .setFormat("<div style=\"text-align:center\"><span style=\"font-size:2em;color:#000\">{y}</span><span style=\"font-size:2em;color:#000\">%</span></div>")
		            )
						)

		.setMargin(0, 0, 0, 0);
		
		
		
		
		return optionedTippingChart;
	}

	/* (non-Javadoc)
	 * @see com.strategyPerspective.client.controlPanel.charts.TippingPointChartCreator#setTippingAxesOptions(org.moxieapps.gwt.highcharts.client.Chart)
	 */
	@Override
	public Chart setTippingAxesOptions(Chart tippingChart) {
		JSONArray stops = new JSONArray();
	    addStop(stops, 0, 0.1, "#55BF3B"); // green
	    addStop(stops, 1, 0.5, "#DDDF0D"); // yellow
	    addStop(stops, 2, 0.9, "#DF5353"); // red

	    tippingChart.getYAxis()
        .setOption("stops", stops)
        .setLineWidth(0)
        .setMinorTickInterval(null)
        .setTickInterval(300)
        .setTickWidth(0)
        .setShowFirstLabel(false)
        .setShowLastLabel(false)
        
        .setMin(0)
        .setMax(300);
	    return tippingChart;
	}

	/* (non-Javadoc)
	 * @see com.strategyPerspective.client.controlPanel.charts.TippingPointChartCreator#createAndAddSeries(org.moxieapps.gwt.highcharts.client.Chart)
	 */
	@Override
	public Chart createAndAddSeries(Chart tippingChart) {
		Series gaugeSeries = tippingChart.createSeries();
		gaugeSeries.setName("gaugeSeries");
		gaugeSeries.select(false);
		
		
		gaugeSeries.addPoint(80);
		tippingChart.addSeries(gaugeSeries);
		return tippingChart;
	}

	/* (non-Javadoc)
	 * @see com.strategyPerspective.client.controlPanel.charts.TippingPointChartCreator#customizeAnimation()
	 */
	@Override
	public Animation customizeAnimation() {
		 return 	new Animation()
			 		.setDuration(500)
			 		.setEasing(Animation.Easing.LINEAR);
	}

	/* (non-Javadoc)
	 * @see com.strategyPerspective.client.controlPanel.charts.TippingPointChartCreator#constructCustomTitle()
	 */
	@Override
	public ChartTitle constructCustomTitle() {
		ChartTitle tippingTitle = new ChartTitle()
		   .setText("%Budget")
		   .setVerticalAlign(VerticalAlign.TOP)
		   .setFloating(true)
		  .setY(17);
		return tippingTitle;
	}

	/* (non-Javadoc)
	 * @see com.strategyPerspective.client.controlPanel.charts.TippingPointChartCreator#getCustomizedToolTip()
	 */
	@Override
	public ToolTip getCustomizedToolTip() {
		ToolTip tippingToolTip = new ToolTip();
		tippingToolTip.setEnabled(false);
		
		

return tippingToolTip;
		
	}

	
	private JSONArray addStop(JSONArray stops, int index, double value, String color) {
	    JSONArray stop = new JSONArray();
	    stop.set(0, new JSONNumber(value));
	    stop.set(1, new JSONString(color));
	    stops.set(index, stop);
	    return stops;
	}
}
