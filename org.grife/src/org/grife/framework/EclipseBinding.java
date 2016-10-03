package org.grife.framework;

public class EclipseBinding {
	public EclipseBinding(IGroovyLog log) {
		this.log = log;
	}
	
	
	public IGroovyLog getLog() {
		return log;
	}
	
	private IGroovyLog log;
}
