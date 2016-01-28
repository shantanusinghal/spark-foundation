package com.ptc.xla.database;

import org.apache.commons.dbcp2.BasicDataSource;

import com.ptc.xla.config.StartupSettings;

public class XlaDbcpDataSource extends BasicDataSource {

	public XlaDbcpDataSource(StartupSettings startupSettings) {
		setUrl(startupSettings.getUrl());
		setUsername(startupSettings.getUsername());
		setPassword(startupSettings.getPassword());
		setDriverClassName(startupSettings.getDriver());
	}
}
