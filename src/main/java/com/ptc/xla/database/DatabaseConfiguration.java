package com.ptc.xla.database;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.configuration.ConfigurationException;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ptc.xla.config.StartupConfiguration;
import com.ptc.xla.config.StartupSettings;
import com.ptc.xla.config.StartupSettingsImpl;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

  @Bean(name = "startupSettings")
  public StartupSettings startupSettings() throws ConfigurationException {
    return new StartupSettingsImpl(new StartupConfiguration());
  }

  @Bean(name = "xlaDataSource")
  public DataSource xlaDataSource(StartupSettings startupSettings) {
    return new XlaDbcpDataSource(startupSettings);
  }

  @Bean(name = "sessionFactory")
  public LocalSessionFactoryBean sessionFactory(DataSource xlaDataSource, StartupSettings startupSettings) {
    XlaSessionFactoryBean sessionFactory = new XlaSessionFactoryBean();
    sessionFactory.setDataSource(xlaDataSource);
    sessionFactory.setPackagesToScan("com.ptc.xla.model");
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", startupSettings.getHibernateDialect());
    sessionFactory.setHibernateProperties(properties);
    return sessionFactory;
  }

  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager(SessionFactory factory) {
      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
      transactionManager.setSessionFactory(factory);
      return transactionManager;
  }
}
