package com.ptc.xla.config;

import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author tbhasme
 */
public class StartupSettingsImpl implements StartupSettings {

	private static final String STARTUP_DRIVER = "database.driver";
	private static final String STARTUP_URL = "database.url";
	private static final String STARTUP_USERNAME = "database.username";
	private static final String STARTUP_PASSWORD = "database.password";
	private static final String STARTUP_HIBERNATE_DIALECT = "database.hibernate.dialect";

	private PropertiesConfiguration startupConfiguration;

	public StartupSettingsImpl(PropertiesConfiguration startupConfig) {
		this.startupConfiguration = startupConfig;
	}

	@Override
	public String getUrl() {
		return startupConfiguration.getString(STARTUP_URL);
	}

	@Override
	public String getUsername() {
		return startupConfiguration.getString(STARTUP_USERNAME);
	}

	@Override
	public String getPassword() {
		return startupConfiguration.getString(STARTUP_PASSWORD);
	}

	@Override
	public String getDriver() {
		return startupConfiguration.getString(STARTUP_DRIVER);
	}

	@Override
	public String getHibernateDialect() {
		return startupConfiguration.getString(STARTUP_HIBERNATE_DIALECT);
	}
}
