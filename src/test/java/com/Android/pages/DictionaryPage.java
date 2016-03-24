package com.Android.pages;

import static ch.lambdaj.Lambda.convert;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ch.lambdaj.function.convert.Converter;

@DefaultUrl("https://www.google.com")
public class DictionaryPage extends PageObject {

	@FindBy(name = "q")
	private WebElementFacade searchTerms;
	
	@FindBy(css = "div._cwh._mwh a:nth-child(2)")
	private WebElementFacade imagesTab;

	@FindBy(css = "button[type='submit']")
	private WebElementFacade lookupButton;

	@FindBy(css = "#gws-lite__body g-card:nth-child(2) div")
	private WebElementFacade results;

	public void enter_keywords(String keyword) {
		searchTerms.type(keyword);
	}

	public void lookup_terms() {
		lookupButton.click();
	}
	public void clickImagesTab() {
		imagesTab.click();
		waitABit(5000);
	}

	public void openResult(String name) {
		List<WebElement> results = getDriver().findElements(By.cssSelector("#gws-lite__body g-card:nth-child(2) div"));
		boolean found = false;

		for (WebElement result : results) {
			if (result.getText().contains(name)) {
				found = true;
				result.click();
				break;
			}
		}
		Assert.assertTrue("Not found", found);

	}

	public List<String> getDefinitions() {
		WebElementFacade definitionList = find(By.tagName("ol"));
		List<WebElement> results = definitionList.findElements(By.tagName("li"));
		return convert(results, toStrings());
	}

	private Converter<WebElement, String> toStrings() {
		return new Converter<WebElement, String>() {
			public String convert(WebElement from) {
				return from.getText();
			}
		};
	}
}