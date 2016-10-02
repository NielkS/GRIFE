package org.grife.framework;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;

public class GroovyLog implements IGroovyLog {
	GroovyLog(final String packageName, final ILog log) {
		this.packageName = packageName;
		this.log = log;
	}
	
	@Override
	public void debug(String string) {
		log.log( makeStatus(Status.OK,string) );
	}

	@Override
	public void info(String string) {
		log.log( makeStatus(Status.INFO,string) );
	}

	@Override
	public void warning(String string) {
		log.log( makeStatus(Status.WARNING,string) );
	}

	@Override
	public void error(String string) {
		log.log( makeStatus(Status.ERROR,string) );
	}
	
	protected Status makeStatus(final int level, final String message) {
		return new Status(level, packageName, message);
	}
	
	protected ILog log;
	protected final String packageName;

}
