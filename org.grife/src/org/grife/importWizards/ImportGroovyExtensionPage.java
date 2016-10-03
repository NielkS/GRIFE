package org.grife.importWizards;

import java.io.File;
import java.net.MalformedURLException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.grife.framework.ScriptWrapper;

import groovy.util.ResourceException;
import groovy.util.ScriptException;

public class ImportGroovyExtensionPage extends WizardPage {
	
	protected FileFieldEditor editor;

	public ImportGroovyExtensionPage(String pageName) {
		super(pageName);
		setTitle(pageName); //NON-NLS-1
		setDescription("Import a new groovy plugin"); //NON-NLS-1
		setPageComplete(false);
	}

	/*
		String[] extensions = new String[] { "*.groovy" }; //NON-NLS-1
		editor.setFileExtensions(extensions);
		fileSelectionArea.moveAbove(null);
*/
	protected IStatus validateLinkedResource() {
		return new Status(IStatus.OK, "org.grife", IStatus.OK, "", null); //NON-NLS-1 //NON-NLS-2
	}

	@Override
	public boolean isPageComplete() {
		return editor.isValid();
	}
	
	@Override
	public void createControl(Composite parent) {
		editor = new FileFieldEditor("Import groovy plugin file", "", parent);		
		editor.setFileExtensions( new String[] {"*.groovy" });
	}
	
	public	boolean runScript() {
		File file = new File(editor.getStringValue());
		return runScript(file);
	}
	
	boolean runScript(File file) {
	    if (file == null) {
	        return false;
	    } else {
	    	ScriptWrapper scriptWrapper;
			try {
				scriptWrapper = new ScriptWrapper(file);
	        	scriptWrapper.execute();
	        	return true;
			} catch (MalformedURLException | ResourceException | ScriptException e) {
				e.printStackTrace();
				return false;
			}
	    }
	}
}
