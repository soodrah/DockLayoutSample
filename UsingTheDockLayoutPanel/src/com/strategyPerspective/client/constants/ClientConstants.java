/**
 * 
 */
package com.strategyPerspective.client.constants;

/**
 * @author rahulsood
 *
 */
public class ClientConstants {
	
	//page elements
	public static final String pageTitleElId					= "tippingPointTitle";
	public static final String tipVersion						= "tippingVersion";
	public static final String pageTitle						= "Tipping Point ";
	public static final String aboutVersion						= "aboutVersion";
	public static final String version							= "Version";

	
	//base content
	public static final String tippingPointCopyRight			= "&#169;1998-2015 Andrea Shapiro, Ph.D., all rights reserved";
	
	//annotationConstants
	public static final String cssFile							= "../css/tippingPoint.css";
	
	
	//default TippingVersion
	public static final String defaultVersion					= "10.0.0";
	
	//default popupContent
	public static final String defaultPopupText					= "Tipping Point pop up, supply message to customize";
	
	
	//Credits html Text
	public static final String credits							= "<div style='font-family: Arial, Helvetica;background-color:#dfdfe6;text-align:center'>Credits</div><div style='font-family: Arial, Helvetica;margin:1em;margin:2vmin'>Thanks to the change management</div><div style='margin:1em;margin:2vmin'><p>Expertise from:<br/>Tim Dempsey<br/>Jeff Kingdon<br/>Carol Lorenz<br/>Gene Mendoza <br/>Jennie Ratcliffe<br/>Richard Ross<br/>Jim Young<br/>David Yarrow</p><p>Icons provided by:<br/><a href='http://www.famfamfam.com/lab/icons/silk' target='_blank'>FamFamFam Icons</a><br/>under Creative Commons License 2.5</p></div>";
	
	//popUp html Template
	public static final String popUpHtmlTemplateStringPrefix    ="<div><div style='font-family: Arial,Helvetica;background-color:#dfdfe6;text-align:center'>";
	public static final String popUpHtmlTemplateStringMiddle 	="</div><div style='font-family: Arial, Helvetica;margin:1em;margin:2vmin'><p>";
	public static final String popuUpHtmlTemplateStringSuffix	="</p></div></div>";
	
	
	/**
	 * Constants for Control Panel
	 */
	
	public static final String splineCreator					= "ctpSpline";
	public static final String gaugeChartCreator				= "gaugeChart";
	public static final String collChartCreator					= "colChart";
	public static final String chartContainer					= "chartContainer";
	public static final String colChartContainer				= "colContainer";
	public static final double chartWidthPercent				= 49.0;
	public static final double chartHeightPercent				= 46.0;
	public static final double chartGaugeWidthPercent			= 22.0;//this seems to be the right size for the box, use this value when porting code tippingPoint
	public static final double chartGaugeHeightPercent			= 20.0;
	public static final double chartColWidthPercent				= 24.3;
	public static final double chartColHeightPercent			= 46.0;
	
	/**
	 * Chart constants
	 */
	public static final String chartAnimationPath				= "/animation";
	public static final String chartAnimationType				= "Highcharts.svg";
	public static final String chartPath						= "/chart";
	public static final String chartTitle						= "/title";
	public static final String chartStyle						= "/style";
	public static final String chartVisiblityStyle				= "visibility";
	public static final String chartMarginTopPath				= "/marginTop";
	public static final String chartYAxisTitle					= "% of Employees";
	public static final String chartXAxisTitle					= "Months";
	public static final String colChartXAxisTitle				= "Month:";
	public static final String chartMarginRightPath				= "/marginRight";
	public static final String apatheticForecastSeriesName 		= "ApatheticForecast";
	public static final String apatheticSeriesName				= "Apathetic";
	public static final String advocateSeriesName				= "Advocate";
	public static final String incubatorSeriesName				= "Incubator";
	public static final String advocateForecastSeriesName		= "AdvocateForecast";
	public static final String resisterSeriesName				= "ResisterSeries";
	public static final int    apatheticForecastStartingPoint	= 95;
	public static final int advocateForecastStartingPoint		= 2;
	public static final String dynamicTopSeriesColor			= "#FF0000";
	public static final String advocateSeriesColor				= "#007F00";
	public static final String incubatorSeriesColor				= "#f5ef31";
	public static final String resisterSeriesColor				= "#990099";
	public static final String splineChartToolTipBkgrdColor		= "#ffffff";
	public static final int    seriesMaxX						= 36;
	public static final int    seriesMaxY					    = 100;
	public static final String splineChartTitle					= "Tipping Spline";
	public static final String gaugePlotTitle					= "% Budget";
	public static final String guageChartTitle					= "Gauge Chart";
	public static final String guageCenterX						= "50%";
	public static final String guageCenterY						= "60%";
	public static final String guagePaneSize					= "115%";
	public static final String guageInnerRadius					= "60%";
	public static final String guageOuterRadius					= "100%";
	public static final String guageDataLabelFormat				= "<div style=\"text-align:center\"><span style=\"font-size:2em;font-size:2.5vmin;color:#000\">{y}</span><span style=\"font-size:2em;font-size:2.5vmin;color:#000\">%</span></div>";
	public static final String guageColorStop1					= "#55BF3B";
	public static final String guageColorStop2					= "#DDDF0D";
	public static final String guageColorStop3					= "#DF5353";
	public static final String guageChartShape					= "arc";
	public static final String guageFifthRect					= "div[id='gaugeContainer']> div > div > svg[version='1.1'] > rect:nth-of-type(5)";
	public static final String guageFourthRect					= "div[id='gaugeContainer']> div > div > svg[version='1.1'] > rect:nth-of-type(4)";
	public static final String guageSixthRect					= "div[id='gaugeContainer']> div > div > svg[version='1.1'] > rect:nth-of-type(6)";
	public static final String guageFifthRectStyle			    = "fill";
	public static final String guageFourthRectStyle				= "stroke-opacity";
	public static final String guageSixthRectStyle				= "stroke-width";
	public static final String guageFourthRectStyleValue		= "0";
	public static final String gugageSixthRectStyleValue		= "0";
	public static final String guageChartTitleFontStyle			="bold 2vmin 'Times New Roman',Verdana, sans-serif";
}
