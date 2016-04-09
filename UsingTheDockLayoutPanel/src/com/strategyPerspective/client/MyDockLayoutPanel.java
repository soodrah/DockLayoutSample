package com.strategyPerspective.client;

import org.moxieapps.gwt.highcharts.client.Chart;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.strategyPerspective.client.GQuerySelectors.TippingSelectors;
import com.strategyPerspective.client.controlPanel.charts.TippingPointChartCreator;
import com.strategyPerspective.client.eventHandlers.TippingWindowResizeListener;
import com.strategyPerspective.client.factory.ChartCreatorFactory;
import static com.strategyPerspective.client.constants.ClientConstants.*;
import com.google.gwt.query.client.GQuery; 
import com.google.gwt.query.client.Function; 
import com.google.gwt.query.client.Selector; 
import com.google.gwt.query.client.Selectors;
import com.google.gwt.query.client.css.CSS;

import static com.google.gwt.query.client.GQuery.*; 
import static com.google.gwt.query.client.css.CSS.*;

public class MyDockLayoutPanel extends Composite  {

	private static MyDockLayoutPanelUiBinder uiBinder = GWT.create(MyDockLayoutPanelUiBinder.class);
	@UiField HTMLPanel controlPanelTopChart;
	@UiField HTMLPanel controlPanelGaugeChart;
	@UiField HTMLPanel controlPanelTopColChart;
	private Chart splineChart = null;
	private Chart gaugeChart = null;
	private Chart colChart	= null;
	@UiTemplate ("MyDockLayoutPanel.ui.xml")
	interface MyDockLayoutPanelUiBinder extends UiBinder<Widget, MyDockLayoutPanel> {
	}

	public MyDockLayoutPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		this.addWindowResizeListener();
	
		//adding the chart
		ChartCreatorFactory chartFactory = new ChartCreatorFactory();
		TippingPointChartCreator tippingChartCreator = chartFactory.getChartCreator(splineCreator);
		splineChart = tippingChartCreator.createTippingChart();
		splineChart = tippingChartCreator.setTippingOptions(splineChart);
		tippingChartCreator.createAndAddSeries(tippingChartCreator.setTippingAxesOptions(splineChart));
		this.controlPanelTopChart.add(splineChart, "chartContainer");
		GWT.log(this.controlPanelTopChart.getElementById("chartContainer").getClientWidth() + " Offsetwidth");
		//splineChart.setSize("49.3vw", "46.2vh");
		this.sizeTippingCharts(splineChart,controlPanelTopChart, chartWidthPercent, chartHeightPercent);
		GWT.log(Window.getClientWidth() + " window Width");
		GWT.log(Window.getClientHeight() + " window Height");
		TippingPointChartCreator gaugeChartCreater = chartFactory.getChartCreator(gaugeChartCreator);
		gaugeChart = gaugeChartCreater.createTippingChart();
		gaugeChart = gaugeChartCreater.setTippingOptions(gaugeChart);
		gaugeChart = gaugeChartCreater.createAndAddSeries(gaugeChartCreater.setTippingAxesOptions(gaugeChart));
		
		this.controlPanelGaugeChart.add(gaugeChart, "gaugeContainer");
		//now that we have the chart created lets manipulate few of its properties.
		
		//gaugeChart.setSize("13vw", "12vw");
		this.sizeTippingCharts(gaugeChart, controlPanelGaugeChart,chartGaugeWidthPercent, chartGaugeHeightPercent );
		
		//doing the colchart.
		TippingPointChartCreator colChartCreator = chartFactory.getChartCreator(collChartCreator);
		colChart = colChartCreator.createTippingChart();
		colChart = colChartCreator.setTippingOptions(colChart);
		colChart = colChartCreator.createAndAddSeries(colChartCreator.setTippingAxesOptions(colChart));
		
		this.controlPanelTopColChart.add(colChart, colChartContainer);
		this.sizeTippingCharts(colChart, controlPanelTopColChart, chartColWidthPercent, chartColHeightPercent );
		
	}

	private void addWindowResizeListener(){
		Window.addResizeHandler(new TippingWindowResizeListener(this));
		
	}
	
	public Chart getSplineChart() {
		return splineChart;
	}

	public void setSplineChart(Chart splineChart) {
		this.splineChart = splineChart;
	}
	
	

	public Chart getGaugeChart() {
		return gaugeChart;
	}

	
	
	public void sizeTippingCharts(Chart chartToSize, HTMLPanel container, 
								  double chartWidthPct, double chartHeightPct){
		if (chartToSize != null) {
			if (container != null && 
				container.getOffsetHeight() != 0
				&& container.getOffsetWidth() != 0) {
				// get controlPanelChartContainer's height and width
				int chartContainerHeight = container.getOffsetHeight();
				int chartContainerWidth = container.getOffsetWidth();
				// set the size of the chart based on chart container width and
				// height...
				chartToSize.setSize((chartContainerWidth - 2) + "px",
									(chartContainerHeight - 2) + "px");

			} else {
				// set the size of the chart based on window size.
				int clientWidth = Window.getClientWidth();
				int clientHeight = Window.getClientHeight();
				// cal chart sizes
				double chartWidth = ((clientWidth * chartWidthPct) / 100);
				double chartHeight = ((clientHeight * chartHeightPct) / 100);

				GWT.log("gauge chart width " + chartWidth);
				GWT.log("gauge chart height " + chartHeight);
				chartToSize.setSize(chartWidth + "px", chartHeight + "px");
				
				

			}
		} else {
			
				GWT.log("Warning: No TippingPoint chart, please create a chart and then add");
			
		}
		
	}
	
	
	

	public HTMLPanel getControlPanelTopColChart() {
		return controlPanelTopColChart;
	}

	public Chart getColChart() {
		return colChart;
	}

	public HTMLPanel getControlPanelTopChart() {
		return controlPanelTopChart;
	}

	public HTMLPanel getControlPanelGaugeChart() {
		return controlPanelGaugeChart;
	}
	
	

}
