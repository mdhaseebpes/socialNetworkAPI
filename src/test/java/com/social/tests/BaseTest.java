package com.social.tests;

import utils.FileLocations;
import utils.PropertyInit;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

abstract class BaseTest {
    public static Properties properties;

    @BeforeTest
    public void setUp()  {

        properties = PropertyInit.initProp(FileLocations.DATA_DIRECTORY);
    }

}
