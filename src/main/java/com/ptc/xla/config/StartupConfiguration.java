package com.ptc.xla.config;

import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.ptc.xla.util.PropertiesFileFinder;

/**
 * This Configuration is for loading those properties that are needed very early
 * in the application initialization; often during the initialization of Spring
 * beans.
 */
public class StartupConfiguration extends PropertiesConfiguration {

	private final static Logger logger = Logger
			.getLogger(StartupConfiguration.class.getSimpleName());

	private static final String STARTUP_SETTINGS_ENV_VARIABLE = "XLA_STARTUP_SETTINGS";
	private static final String STARTUP_SETTINGS_JAVA_PROPERTY = "xlastartupsettings";
	private static final String STARTUP_SETTINGS_CLASSPATH_FILE_NAME = "xlastartupsettings.properties";

	public StartupConfiguration() throws ConfigurationException {
		super();
		URL settingsFileUrl = PropertiesFileFinder.locate(
				STARTUP_SETTINGS_ENV_VARIABLE, STARTUP_SETTINGS_JAVA_PROPERTY,
				STARTUP_SETTINGS_CLASSPATH_FILE_NAME);

		if (settingsFileUrl != null) {
			logger.info("Startup Setting file path: " + settingsFileUrl);
			setURL(settingsFileUrl);
			load();
		} else {
			logger.fatal("Startup configuration properties (i.e. database settings) for "
					+ "the XLA application are not intialiized. The application will not be able to "
					+ "function. Please see the adminstration manual for troubleshooting guidance.");
		}
	}
}