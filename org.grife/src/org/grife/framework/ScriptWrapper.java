package org.grife.framework;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.grife.Activator;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

public class ScriptWrapper {

	public ScriptWrapper(final String strFile) throws MalformedURLException {
		scriptFile = new File(strFile);
		groovyLog = new GroovyLog(scriptFile.getName(), Activator.getDefault().getLog());
		/*rc = new ResourceConnector() {
			@Override
			public URLConnection getResourceConnection(String arg0) throws ResourceException {
				getClass().getClassLoader().
				groovyLog.debug("Looking for '" + arg0 + "'");
				// TODO add groovypath entries to class loader
				return new URLConnection(scriptFile.toURL());
			}
		};
		*/
		List<URL> urls = new ArrayList<URL>();
		urls.add( new File(scriptFile.getPath()).toURL());
		URL[] urlArray = urls.toArray( new URL[urls.size()] );
		gse = new groovy.util.GroovyScriptEngine( urlArray, getClass().getClassLoader()); //< pass current class loader for eclipse resources
		binding = new Binding();
	}
	
	public void execute() throws ResourceException, ScriptException {
		gse.run(scriptFile.getName().toString(), binding);
	}
	
	public void executeSafe() {
		try {
			execute();
		} catch (ResourceException | ScriptException e) {
			//TODO extract groovy stack
			groovyLog.error( e.getMessage() );
			e.printStackTrace();
		}
		
	}
	
	private IGroovyLog groovyLog;
	private File scriptFile;
	//private ResourceConnector rc;
	private GroovyScriptEngine gse;
	private Binding binding;
}
