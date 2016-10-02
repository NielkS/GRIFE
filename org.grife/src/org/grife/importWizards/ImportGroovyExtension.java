package org.grife.importWizards;

import java.net.MalformedURLException;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.grife.framework.ScriptWrapper;

import groovy.util.ResourceException;
import groovy.util.ScriptException;

public class ImportGroovyExtension extends Wizard implements IImportWizard {
	
	ImportGroovyExtensionPage mainPage;

	public ImportGroovyExtension() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		IFile file = mainPage.createNewFile();
        if (file == null) {
            return false;
        } else {
        	ScriptWrapper scriptWrapper;
			try {
				scriptWrapper = new ScriptWrapper(file.getName());
	        	scriptWrapper.execute();
			} catch (MalformedURLException | ResourceException | ScriptException e) {
				e.printStackTrace();
			}
        }
        return true;
	}
	 
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("File Import Wizard"); //NON-NLS-1
		setNeedsProgressMonitor(true);
		mainPage = new ImportGroovyExtensionPage("Import Groovy Extension File",selection); //NON-NLS-1
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages() {
        super.addPages(); 
        addPage(mainPage);        
    }

}
