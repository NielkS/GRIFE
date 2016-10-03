package org.grife.handlers;

import java.io.File;
import java.net.MalformedURLException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.grife.Activator;
import org.grife.framework.ScriptWrapper;

import groovy.util.ResourceException;
import groovy.util.ScriptException;

import org.eclipse.swt.widgets.FileDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class RunGroovyScriptCommandHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		//MessageDialog.openInformation(window.getShell(),"Grife", "Hello, Eclipse world");

		
		FileDialog dialog = new FileDialog(window.getShell() );
		dialog.setFilterExtensions( new String[] {"*.groovy"});
		final String strFile = dialog.open();
		if (null != strFile) {
			new Job("Run groovy script - " + strFile) {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					Status status;
					try {
						final File file = new File(strFile);
						new ScriptWrapper(file).execute();
						status = new Status(Status.INFO, Activator.PLUGIN_ID, "Script OK");
					} catch (MalformedURLException | ResourceException | ScriptException e) {
						e.printStackTrace();
						status = new Status(Status.ERROR, Activator.PLUGIN_ID, "Script KO");
					}
					Activator.getDefault().getLog().log( status);
					return status;
				}
			}.schedule();
		}
		return null;
	}
}
