package org.grife.importWizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

public class ImportGroovyExtension implements IWorkbenchWizard {
	
	ImportGroovyExtensionPage mainPage;
	List<IWizardPage> pages;
	IWizardContainer wizardContainer;
	
	public ImportGroovyExtension() {
		super();
	}

	@Override
	public boolean canFinish() {
		return mainPage.isPageComplete();
	}
	
	 
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		mainPage = new ImportGroovyExtensionPage("Import Groovy Extension File"); //NON-NLS-1
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages() {
    	pages = new ArrayList<IWizardPage>();
    	pages.add(mainPage);
    }

	@Override
	public void createPageControls(Composite pageContainer) {
		mainPage.createControl(pageContainer);		
	}

	@Override
	public void dispose() {
		mainPage.dispose();
	}

	@Override
	public IWizardContainer getContainer() {
		return wizardContainer;
	}

	@Override
	public Image getDefaultPageImage() {
		return null;
	}

	@Override
	public IDialogSettings getDialogSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		return null;
	}

	@Override
	public IWizardPage getPage(String pageName) {
		if (mainPage.getName() == pageName) {
			return mainPage;
		}
		return null;
	}

	@Override
	public int getPageCount() {
		return pages.size();
	}

	@Override
	public IWizardPage[] getPages() {
		return pages.toArray(new IWizardPage[pages.size()]);
	}

	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		return null;
	}

	@Override
	public IWizardPage getStartingPage() {
		return mainPage;
	}

	@Override
	public RGB getTitleBarColor() {
		return new RGB(255,0,0);
	}

	@Override
	public String getWindowTitle() {
		return "File Import Wizard";
	}

	@Override
	public boolean isHelpAvailable() {
		return false;
	}

	@Override
	public boolean needsPreviousAndNextButtons() {
		return false;
	}

	@Override
	public boolean needsProgressMonitor() {
		return false;
	}

	@Override
	public boolean performCancel() {
		return false;
	}

	@Override
	public boolean performFinish() {
		return mainPage.runScript();
	}

	@Override
	public void setContainer(IWizardContainer wizardContainer) {
		this.wizardContainer = wizardContainer;
	}

}
