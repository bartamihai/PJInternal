package com.Android.features.search;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.Android.steps.serenity.EndUserSteps;

@RunWith(SerenityRunner.class)
public class SearchByKeywordStory {

	@Managed(uniqueSession = true)
	public WebDriver webdriver;

	@Steps
	public EndUserSteps anna;

	@Test
	public void searching_by_keyword_apple_should_display_the_corresponding_article() {

		anna.is_the_home_page();
		anna.looks_for("apple");
		anna.openResult("Laptopstein");
		anna.clickImagesTab();
		//this is a workaround because the last screenshot is not taken (pending)
		Serenity.takeScreenshot();

	}

}