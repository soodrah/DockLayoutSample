/**
 * 
 */
package com.strategyPerspective.client.controlPanel.charts;

import org.moxieapps.gwt.highcharts.client.Animation;
import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.ChartTitle;
import org.moxieapps.gwt.highcharts.client.PlotLine.DashStyle;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.Series.Type;
import org.moxieapps.gwt.highcharts.client.Style;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.plotOptions.LinePlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.Marker;
import org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.SplinePlotOptions;

import com.strategyPerspective.client.eventHandlers.TippingSeriesPointClickEventHandler;
import com.strategyPerspective.client.eventHandlers.ControlPanelSplineChartClickEventHandler;

import static com.strategyPerspective.client.constants.ClientConstants.*;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author rahulsood
 *
 */
public class ControlPanelSplineChart extends TippingPointChartCreator {

	private Logger logger = Logger.getLogger("com.strategyPerspective.client.eventHandlers.ControlPanelSplineChart");
	
	private Series apathetic 			= null;
	private Series advocate 			= null;
	private Series incubator 			= null;
	private Series resistor 			= null;
	private Series apatheticForecast	= null;
	private Series advocateForecast 	= null;
	
	
	
	@Override
	public  Type getTypeForChart(){
		return Series.Type.SPLINE;
	}

	
	@Override
	public  Chart setTippingOptions(Chart tippingChart){
		
		Chart optionedTippingChart = tippingChart.setOption(new StringBuilder().append(chartPath)
															.append(chartAnimationPath)
															.toString(), 
															chartAnimationType)
												.setOption(new StringBuilder().append(chartPath)
																			  .append(chartMarginTopPath).toString(), 30)
												.setOption(new StringBuilder().append(chartPath)
												.append(chartMarginRightPath).toString(), 30);
		
		//add charClickEventHandler to this....
		tippingChart.setClickEventHandler(new ControlPanelSplineChartClickEventHandler(this));
		//set Generic SeriesPlotOptions for all series...
		tippingChart.setSeriesPlotOptions(this.createSeriesPlotOptions());
		return optionedTippingChart;
	}

	
	protected SeriesPlotOptions createSeriesPlotOptions(){
		SeriesPlotOptions allSeriesPlotOptions = new SeriesPlotOptions();
		allSeriesPlotOptions.setShowInLegend(false);
		allSeriesPlotOptions.setShowCheckbox(false);
		allSeriesPlotOptions.setAnimation(this.customizeAnimation());
		allSeriesPlotOptions.setLineWidth(1);
		allSeriesPlotOptions.setPointClickEventHandler(new TippingSeriesPointClickEventHandler(this));
		return allSeriesPlotOptions;
	}

	@Override
	public Animation customizeAnimation() {
	 return 	new Animation()
			 		.setDuration(500)
			 		.setEasing(Animation.Easing.LINEAR);
	}


	@Override
	public ChartTitle constructCustomTitle() {
		ChartTitle tippingTitle = new ChartTitle();
		//set style, this title is to be invisible.
		Style style = new Style();
		style.setOption(chartVisiblityStyle, "hidden");
		tippingTitle.setStyle(style);
		return tippingTitle;
	}


	@Override
	public Chart setTippingAxesOptions(Chart tippingChart) {
		//setting YAxis options
		tippingChart.getYAxis()
					.setMin(0)
					.setMax(100)
					.setTickInterval(25)
					.setAxisTitleText(chartYAxisTitle);
		
		//setting XAxis options
		tippingChart.getXAxis()
					.setMin(0)
					.setMax(36)
					.setTickInterval(12)
					.setMinorTickInterval(6)
					.setAxisTitleText(chartXAxisTitle)
					.setAllowDecimals(false);
		return tippingChart;
	}
	
	
	

	/**
	 * ControlPanel Spline chart uses a maximum of 6 series 
	 * and a minium of 0 visible at a time.
	 */
	@Override
	public Chart createAndAddSeries(Chart tippingChart) {
		//add Apathetic forecast series.
		tippingChart.addSeries(this.createApatheticForeCastSeries(tippingChart));
		tippingChart.addSeries(this.createApatheticSeries(tippingChart));
		tippingChart.addSeries(this.createAdvocateSeries(tippingChart));
		tippingChart.addSeries(this.createIncubatorSeries(tippingChart));
		tippingChart.addSeries(this.createResisterSeries(tippingChart));
		tippingChart.addSeries(this.createAdvocateSeriesForecast(tippingChart));
		return tippingChart;
	}

	
	private Series createApatheticForeCastSeries(Chart tippingChart) {
		// createing dynamic series 1, since that goes on top
		this.apatheticForecast = tippingChart.createSeries();
		// setitng properties of this series...
		apatheticForecast.setType(Type.LINE);
		apatheticForecast.setName(apatheticForecastSeriesName);
		apatheticForecast.select(false);
		apatheticForecast.addPoint(apatheticForecastStartingPoint);//TODO this will eventually come from model based on the selection loaded, change now
		// create the series plot options -
		LinePlotOptions apatheticForecastPlotOptions = new LinePlotOptions();
		apatheticForecastPlotOptions.setColor(dynamicTopSeriesColor);
		apatheticForecastPlotOptions.setDashStyle(DashStyle.LONG_DASH);
		apatheticForecast.setPlotOptions(apatheticForecastPlotOptions);

		return apatheticForecast;
	}
	
	/*
	 * Create apathetic Series - initially no data added to it.
	 */
	protected Series createApatheticSeries(Chart tippingChart){
		this.apathetic = tippingChart.createSeries();
		this.apathetic.setName(apatheticSeriesName);
		this.apathetic.select(false);
		//create options for the series
		SeriesPlotOptions apatheticSeriesPlotOptions = new SeriesPlotOptions();
		apatheticSeriesPlotOptions.setMarker(new Marker().setEnabled(false))
								  .setColor(dynamicTopSeriesColor);
		this.apathetic.setPlotOptions(apatheticSeriesPlotOptions);
		return this.apathetic;
	}
	

	/*
	 * Create initial advocate Series, no data added at initial.
	 */
	protected Series createAdvocateSeries(Chart tippingChart){
		this.advocate = tippingChart.createSeries();
		this.advocate.setName(advocateSeriesName);
		this.advocate.select(false);
		//create options for the series
		SeriesPlotOptions advocateSeriesPlotOptions = new SeriesPlotOptions();
		advocateSeriesPlotOptions.setMarker(new Marker().setEnabled(false))
								 .setColor(advocateSeriesColor);
		this.advocate.setPlotOptions(advocateSeriesPlotOptions);
		return this.advocate;
	}
	
	
	private Series createAdvocateSeriesForecast(Chart tippingChart) {
		// createing dynamic series 1, since that goes on top
		this.advocateForecast = tippingChart.createSeries();
		// setitng properties of this series...
		advocateForecast.setType(Type.LINE);
		advocateForecast.setName(advocateForecastSeriesName);
		advocateForecast.select(false);
		advocateForecast.addPoint(advocateForecastStartingPoint);//TODO this will eventually come from model based on the selection loaded, change now
		// create the series plot options -
		LinePlotOptions advocateForecastPlotOptions = new LinePlotOptions();
		advocateForecastPlotOptions.setColor(advocateSeriesColor);
		advocateForecastPlotOptions.setDashStyle(DashStyle.LONG_DASH);
		// dynamic1PlotOptions.setPointClickEventHandler(pointClickEventHandler);
		advocateForecast.setPlotOptions(advocateForecastPlotOptions);

		return advocateForecast;

	}
	
	
	/*
	 * Initial set up Incubator series.
	 * @param tippingChart
	 * @return
	 */
	protected Series createIncubatorSeries(Chart tippingChart){
		this.incubator = tippingChart.createSeries();
		this.incubator.setName(incubatorSeriesName);
		this.incubator.select(false);
		//create options for the series
		SeriesPlotOptions incubatorSeriesPlotOptions = new SeriesPlotOptions();
		incubatorSeriesPlotOptions.setMarker(new Marker().setEnabled(false))
								  .setColor(incubatorSeriesColor);
		this.incubator.setPlotOptions(incubatorSeriesPlotOptions);
		return this.incubator;
	}
	
	
	/*
	 * initial set up of resister Series.
	 */
	protected Series createResisterSeries(Chart tippingChart){
		this.resistor = tippingChart.createSeries();
		this.resistor.setName(resisterSeriesName);
		this.resistor.select(false);
		//create options for the series
		SeriesPlotOptions resistorSeriesPlotOptions = new SeriesPlotOptions();
		resistorSeriesPlotOptions.setMarker(new Marker().setEnabled(false))
								 .setColor(resisterSeriesColor);
		this.incubator.setPlotOptions(resistorSeriesPlotOptions);
		return this.resistor;
	}
	
	
	/**
	 * @return the apathetic
	 */
	public Series getApathetic() {
		return apathetic;
	}


	/**
	 * @return the advocate
	 */
	public Series getAdvocate() {
		return advocate;
	}


	/**
	 * @return the incubator
	 */
	public Series getIncubator() {
		return incubator;
	}


	/**
	 * @return the resistor
	 */
	public Series getResistor() {
		return resistor;
	}


	/**
	 * @return the apatheticForecast
	 */
	public Series getApatheticForecast() {
		return apatheticForecast;
	}


	/**
	 * @return the advocateForecast
	 */
	public Series getAdvocateForecast() {
		return advocateForecast;
	}


	@Override
	public ToolTip getCustomizedToolTip() {
		ToolTip tippingToolTip = new ToolTip();
		tippingToolTip.setShadow(false)
					  .setFormatter(new ToolTipFormatter() {
						@Override
						public String format(ToolTipData toolTipData) {
							
							return  toolTipData.getSeriesName() + ": The value for <b> " + toolTipData.getXAsLong() + " months </b>" 
				                     + "is <b> " + toolTipData.getYAsLong() + "% </b>"; 
						}
					})
					.setValueDecimals(0)
					.setBorderWidth(0)
					.setBackgroundColor(splineChartToolTipBkgrdColor)
					.setHideDelay(5)
					.setUseHTML(false)
					.setOption("/tooltip/positioner", "function(labelWidth, labelHeight, point){ return {x:0, y:20};}");//currently does not work
					
		
		return tippingToolTip;
	}
	
	
	
	public Series getSeriesByName(String seriesName) throws Exception{
		Series foundSeries = null;
		if(logger.isLoggable(Level.FINE)){
			logger.fine(logger.getName() + " : tippingChart has a total of " + this.getTippingChart().getSeries().length + " series");
		}
		for(Series seriesToCheck: this.getTippingChart().getSeries()){
			if(seriesToCheck.getName().equalsIgnoreCase(seriesName)){
				foundSeries = seriesToCheck;
				break;
			}
			
		}
		if(foundSeries == null){
			throw new Exception("series not found, please check the name : " + seriesName);
		}
		return foundSeries;
	}
	
	/**
	 * This method returns a selected series. At a given time there should only be one selected series
	 * Of the six series only ApatheticsForeCast and AdvocatesForeCast participate in selection game.
	 * @return
	 */
	public Series getSelectedSeries() throws Exception{
		Series selected =null;
	    
		Series [] selectedSeriesArray = this.getTippingChart().getSelectedSeries();
		if(selectedSeriesArray.length == 1)
			selected = selectedSeriesArray[0];
		else{
			throw new Exception("More then one series selected, please check, selected series are " + this.getTippingChart().getSelectedSeries());
		}
				
		return selected;
	}
	
}
