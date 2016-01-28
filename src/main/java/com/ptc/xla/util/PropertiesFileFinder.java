package com.ptc.xla.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class will check for a settings file in three places in the following order
 *   1. At a location specified by an environment variable named FUSION_SETTINGS
 *   2. At a locations specified by the java paramater named fusionsettings (i.e. passed to java like -DfusionSettings=/some/path/fusionsettings.properties)
 *   3. On the classpath (this one is not recommended but is, in some cases necessary
 */
public class PropertiesFileFinder {

	public static URL locate(String envVariable, String javaProperty, String classpathFile){

		URL url = locateByEnvVariable(envVariable);
		if (url == null){
			url = locateByJavaProperty(javaProperty);
			if (url == null){
				url = locateByClasspathFile(classpathFile);
			}
		}

		return url;
	}

	public static URL locateByEnvVariable(String envVariable) {
		try {
			if (envVariable != null ){
				if (System.getenv(envVariable) != null){
					File file = new File(System.getenv(envVariable));
					if (file.exists()){
						return file.toURI().toURL();
					}
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static URL locateByJavaProperty(String javaProperty) {
		try {
			if (javaProperty != null ){
				if (System.getProperty(javaProperty) != null){
					File file = new File(System.getProperty(javaProperty));
					if (file.exists()){
						return file.toURI().toURL();
					}
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static URL locateByClasspathFile(String classpathFile) {
		return Thread.currentThread().getContextClassLoader().getResource(classpathFile);
	}


}