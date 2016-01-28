package com.ptc.xla.config;

/**
 * @author tbhasme
 */
public interface StartupSettings {

	public String getUrl();

	public String getUsername();

	public String getPassword();

	public String getDriver();

	public String getHibernateDialect();
}