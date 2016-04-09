/**
 * 
 */
package com.strategyPerspective.client.GQuerySelectors;

import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.Selector;
import com.google.gwt.query.client.Selectors;

/**
 * @author rahulsood
 *
 */
public interface TippingSelectors extends Selectors {

	
	@Selector("div[id='gaugeContainer']> div > div > svg[version='1.1'] > rect[class=' highcharts-background']") GQuery getFifthRectangle();
	@Selector("svg[version='1.1'] > rect:nth-of-type(4)") GQuery getFourthRectangle();
	@Selector("svg[version='1.1'] > rect:nth-of-type(6)") GQuery getSixthRectangle();
}
