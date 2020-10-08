package com.visionit.automation.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="classpath:features",//to tell cucumber where is ur feature file
        glue="com.visionit.automation.stepdefs", // to tell cucumber where is ur step def code
        tags="", // to tell which tagged feature file to execute
        plugin = {"pretty", // to generate reports
            "html:target/html/",
            "json:target/json/file.json","junit:target/junit_xml/cucumber_xml"
            },
        //publish=true,
        monochrome=true,
       // strict=true, //
        dryRun=true // to tell whether to test run(true) or actual run(false)
        )

public class TestRunner1 {

}
