/**
 * 
 */
package com.strategyPerspective.client.controlPanel.charts;

import org.moxieapps.gwt.highcharts.client.Animation;
import org.moxieapps.gwt.highcharts.client.Axis.TickPosition;
import org.moxieapps.gwt.highcharts.client.AxisTitle;
import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.ChartTitle;
import org.moxieapps.gwt.highcharts.client.ChartTitle.VerticalAlign;
import org.moxieapps.gwt.highcharts.client.Credits;
import org.moxieapps.gwt.highcharts.client.Exporting;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Pane;
import org.moxieapps.gwt.highcharts.client.PaneBackground;
import org.moxieapps.gwt.highcharts.client.PaneBackground.Shape;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.Series.Type;
import org.moxieapps.gwt.highcharts.client.Style;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.YAxis;
import org.moxieapps.gwt.highcharts.client.events.ChartClickEvent;
import org.moxieapps.gwt.highcharts.client.events.ChartClickEventHandler;
import org.moxieapps.gwt.highcharts.client.events.ChartRedrawEvent;
import org.moxieapps.gwt.highcharts.client.events.ChartRedrawEventHandler;
import org.moxieapps.gwt.highcharts.client.events.PointClickEvent;
import org.moxieapps.gwt.highcharts.client.events.PointClickEventHandler;
import org.moxieapps.gwt.highcharts.client.labels.DataLabels;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsFormatter;
import org.moxieapps.gwt.highcharts.client.labels.Labels;
import org.moxieapps.gwt.highcharts.client.labels.Labels.Align;
import org.moxieapps.gwt.highcharts.client.labels.YAxisLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.Dial;
import org.moxieapps.gwt.highcharts.client.plotOptions.GaugePlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.Pivot;
import org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.SolidGaugePlotOptions;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dev.jjs.Correlation.Axis;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.strategyPerspective.client.eventHandlers.TippingSeriesPointClickEventHandler;

import javafx.scene.paint.Color;

/**
 * @author rahulsood
 * This class uses Template design pattern, lots of abstract methods will be defined
 * that will be implemented by concrete classes subclassing TippingPointChartCreator.
 * TippingPointChartCreator supplies wrappers over many of the methods of gwt-charts to create
 * flexible charts through simple api calls. This makes it easy for client code to use this API
 */
public abstract class TippingPointChartCreator {

	private Chart tippingChart = null;
	
	
	/**
	 * Default implmentation provided, 
	 * subclassed are encouraged to override these to provide their own implementation.
	 * @return
	 */
	public Type getTypeForChart(){
		return Series.Type.LINE;
	}

	/**
	 * An abstract method to setOptions for a chart that cannot be 
	 * set through the moxiegroup's api, always defer to using the api first
	 * @return
	 */
	
	public abstract Chart setTippingOptions(Chart tippingChart);
	
   /**
    * n abstract method that allows subclasses constructing specific charts to set their axes property as they wish
    * @param tippingChart
    * @return
    */
	public abstract Chart setTippingAxesOptions(Chart tippingChart);
	
	
	/**
	 * Given a chart, subclasses can override this method to add 
	 * on or more customized series to the chart based on their liking.
	 * @param tippingChart
	 * @return
	 */
	public abstract Chart createAndAddSeries(Chart tippingChart);
	
	
	/**
	 * Customize Animation, subclasses should override this to address their animation needs....
	 * @return
	 */
	public abstract Animation customizeAnimation();
	
	/**
	 * 
	 * @return
	 */
	public abstract ChartTitle constructCustomTitle();
	
	
	/**
	 * Subclasses override this method to customized the credits they need
	 * @return
	 */
	public Credits createCustomizedCredits(){
		Credits credits = new Credits()
							  .setEnabled(false);
		return credits;
	}
	
	/**
	 * Subclasses should format their tooltip 
	 * as they wish.
	 * @return
	 */
	public abstract ToolTip getCustomizedToolTip();
	
	
	public final Chart createTippingChart(){
		tippingChart = new Chart();
		tippingChart.setType(this.getTypeForChart())
					.setReflow(true)
					.setAnimation(this.customizeAnimation())
					.setChartTitle(this.constructCustomTitle())
					.setExporting(new Exporting()  
				            .setEnabled(false)  
				        )  
				        .setLegend(new Legend()  
				            .setEnabled(false)  
				        )
				    .setShowAxes(true)
				    .setCredits(this.createCustomizedCredits())
				    .setToolTip(getCustomizedToolTip());
		return tippingChart;
		
		 /* Chart chart = new Chart()
				    
			        .setType(Series.Type.SOLID_GAUGE)
			        .setChartTitle(new ChartTitle()
			        				   .setText("%Budget")
			        				   .setVerticalAlign(VerticalAlign.TOP)
			        				   .setFloating(true)
			        				  .setY(17)
			        				   
			        				   
			        				   )
			        .setBorderWidth(0)
			        .setShadow(false)
			        
			        
			        			
			        .setPane(new Pane()
			            .setCenter("50%", "60%")
			            .setStartAngle(-90)
			            .setEndAngle(90)
			           
			            
			            
			            .setSize("115%")//this is good size
			            .setBackground(new PaneBackground()
			                .setBackgroundColor("#EEE")
			                .setInnerRadius("60%")
			                .setOuterRadius("100%")
			                .setOption("shape", "arc")
			            )
			        )
			        .setSeriesPlotOptions(new SeriesPlotOptions()
			        					  
			            .setDataLabels(new DataLabels()
			            			   .setBorderWidth(0)
			                
			                .setVerticalAlign(Labels.VerticalAlign.BOTTOM)
			                .setUseHTML(true)
			                .setFormat("<div style=\"text-align:center\"><span style=\"font-size:2em;color:#000\">{y}</span><span style=\"font-size:2em;color:#000\">%</span></div>")
			            )
			        )
			        .setMargin(0, 0, 0, 0)
			        
			        .setToolTip(new ToolTip()
			            .setEnabled(false)
			        )
			        .setCredits(new Credits()
			            .setEnabled(false)
			        )
			        .setExporting(new Exporting()
			            .setEnabled(false)
			        );

			    JSONArray stops = new JSONArray();
			    addStop(stops, 0, 0.1, "#55BF3B"); // green
			    addStop(stops, 1, 0.5, "#DDDF0D"); // yellow
			    addStop(stops, 2, 0.9, "#DF5353"); // red

			    chart.getYAxis()
			        .setOption("stops", stops)
			        .setLineWidth(0)
			        .setMinorTickInterval(null)
			        .setTickInterval(300)
			        .setTickWidth(0)
			        .setShowFirstLabel(false)
			        .setShowLastLabel(false)
			        
			        .setMin(0)
			        .setMax(300);
			   
			   Series mySeries = chart.createSeries();
			   mySeries.addPoint(300);
			   chart.addSeries(mySeries);
			  
			    return chart;
			     */
	}

	/**
	 * @return the tippingChart
	 */
	public Chart getTippingChart() {
		return tippingChart;
	}

	private JSONArray addStop(JSONArray stops, int index, double value, String color) {
	    JSONArray stop = new JSONArray();
	    stop.set(0, new JSONNumber(value));
	    stop.set(1, new JSONString(color));
	    stops.set(index, stop);
	    return stops;
	}
	
	
}
