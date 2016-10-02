package org.grife;

import java.net.MalformedURLException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.grife.framework.ScriptWrapper;
import org.osgi.framework.BundleContext;

import groovy.util.ResourceException;
import groovy.util.ScriptException;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.grife"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		getLog().log( new Status(Status.INFO,PLUGIN_ID, "Starting plugin " + PLUGIN_ID) );
		
		new Job("Hello groovy") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					new ScriptWrapper("C:/Users/Nico/Desktop/HelloWorld.groovy").execute();
					Status status = new Status(Status.INFO, PLUGIN_ID, "Test OK");
					getLog().log( status);
					return status;
				} catch (MalformedURLException | ResourceException | ScriptException e) {
					e.printStackTrace();
					Status status = new Status(Status.ERROR, PLUGIN_ID, "Test KO");
					getLog().log( status);
					return status;
				}
			}
		}.schedule(100);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		getLog().log( new Status(Status.INFO,PLUGIN_ID, "Stopping plugin " + PLUGIN_ID) );
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
