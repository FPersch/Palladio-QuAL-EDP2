package org.palladiosimulator.edp2.visualization.wizards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.palladiosimulator.edp2.visualization.IDerivedMetric;

public class SelectDerivedMetricPage extends WizardPage implements ISelectionChangedListener {

	/**
     * The current {@link IStatus} based on the selection in the <choiceViewer>
     */
    private IStatus selectionStatus;

    /**
     * The 'OK'-Status, which signalizes everything is fine.
     */
    private final Status statusOK;
    
    /**
     * Viewer for the possible choices of Filter/Adapter/Chart combinations.
     */
    private TableViewer choiceViewer;
    
    private static final Logger LOGGER = Logger.getLogger(SelectDerivedMetricPage.class.getCanonicalName());
    
    private final List<IDerivedMetric> applicableDerivedMetrics;
    private static final String DERIVED_METRIC_EXTENSION_POINT_ID = "org.palladiosimulator.edp2.visualization.derivedmetric";
    
	protected SelectDerivedMetricPage(final String pageName) {
		super(pageName);
	    setDescription("Choose a derived Metric to calculate.");
	    statusOK = new Status(IStatus.OK, "not_used", 0, "", null);
	    selectionStatus = new Status(IStatus.INFO, "not_used", 0, "Please select a derived Metric to proceed.", null);
	    this.applicableDerivedMetrics = getApplicableDerivedMetrics();
	}

	@Override
	public void createControl(Composite parent) {
		// create the composite to hold the widgets
        final Composite composite = new Composite(parent, SWT.NONE);

        // create the desired layout for this wizard page
        final GridLayout gl = new GridLayout();
        composite.setLayout(gl);

        final Label label = new Label(composite, SWT.NONE);
        label.setText("Available Choices:");

        final SashForm sashForm = new SashForm(composite, SWT.HORIZONTAL);
        final GridData gd = new GridData(GridData.FILL_BOTH);
        gd.widthHint = 200;
        sashForm.setLayoutData(gd);

        this.choiceViewer = new TableViewer(sashForm, SWT.BORDER);
        initializeChoiceViewer();
        
        // set the composite as the control for this page
        setControl(composite);
	}

	
	/*
     * (non-Javadoc)
     *
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(
     * org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        selectionStatus = statusOK;
        final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
        if (selection.isEmpty()) {
            selectionStatus = new Status(IStatus.ERROR, "not_used", 0, "Please select a derived Metric to proceed.",
                    null);
        } else {
            final int index = choiceViewer.getTable().getSelectionIndex();
            setDerivedMetric(this.applicableDerivedMetrics.get(index));
        }

        updatePageStatus();
    }
	
	/**
	 * Forwards the chosen variant to the wizard.
	 *
	 * @param selection
	 */
	public void setDerivedMetric(final IDerivedMetric selection) {
	    ((DerivedMetricWizard) getWizard()).setDerivedMetric(selection);
	}

	/**
	 * Method which handles the status of the whole {@link WizardPage} based on the different states
	 * resulting from inputs.
	 *
	 * @return the page Status
	 */
	public IStatus updatePageStatus() {
	    IStatus pageStatus = statusOK;
	    ((DerivedMetricWizard) getWizard()).setFinishable(false);
	    switch (selectionStatus.getSeverity()) {
	    case IStatus.OK:
	        setErrorMessage(null);
	        setMessage(selectionStatus.getMessage());
	        pageStatus = statusOK;
	        ((DerivedMetricWizard) getWizard()).setFinishable(true);
	        break;
	    case IStatus.WARNING:
	        setErrorMessage(null);
	        setMessage(selectionStatus.getMessage(), WizardPage.WARNING);
	        pageStatus = selectionStatus;
	        break;
	    case IStatus.INFO:
	        setErrorMessage(null);
	        setMessage(selectionStatus.getMessage(), WizardPage.INFORMATION);
	        pageStatus = selectionStatus;
	        break;
	    default:
	        setErrorMessage(selectionStatus.getMessage());
	        setMessage(null);
	        pageStatus = selectionStatus;
	        break;
	    }
	    getContainer().updateButtons();
	    return pageStatus;
	}

	private void initializeChoiceViewer() {
	    this.choiceViewer.setContentProvider(new IStructuredContentProvider() {
	        @Override
	        public void dispose() {
	            // do nothing here
	        }
	
	        @Override
	        public Object[] getElements(final Object inputElement) {
	            return SelectDerivedMetricPage.this.applicableDerivedMetrics.toArray();
	        }
	
	        @Override
	        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
	            // do nothing here
	        }
	    });
	    this.choiceViewer.setLabelProvider(new ILabelProvider() {
	
	        @Override
	        public void addListener(final ILabelProviderListener listener) {
	            // do nothing here
	        }
	
	        @Override
	        public void dispose() {
	            // do nothing here
	        }
	
	        @Override
	        public Image getImage(final Object element) {
	            return null;
	        }
	
	        @Override
	        public String getText(final Object element) {
	            if (element != null) {
	                final IDerivedMetric sequenceElement = (IDerivedMetric) element;
	                return sequenceElement.getName();
	            }
	            return null;
	        }
	
	        @Override
	        public boolean isLabelProperty(final Object element, final String property) {
	            // not used
	            return false;
	        }
	
	        @Override
	        public void removeListener(final ILabelProviderListener listener) {
	            // not used
	        }
	    });
	
	    this.choiceViewer.setInput(this.applicableDerivedMetrics);
	    this.choiceViewer.addSelectionChangedListener(this);
	}

	private Map<String, IDerivedMetric> getRegisteredDerivedMetrics() {
        final Map<String, IDerivedMetric> result = new HashMap<String, IDerivedMetric>();
        final IConfigurationElement[] derivedMetricExtensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(DERIVED_METRIC_EXTENSION_POINT_ID);
        for (final IConfigurationElement e : derivedMetricExtensions) {
            try {
                final String id = e.getAttribute("id");
                final IDerivedMetric derivedMetric = (IDerivedMetric) e
                        .createExecutableExtension("class");
                result.put(id, derivedMetric);
            } catch (final CoreException e1) {
                LOGGER.log(Level.SEVERE,
                        "Error in creating a derived Metric referenced in an extension: Respective Id is "
                                + e.getAttribute("id") + ".");
                LOGGER.log(Level.SEVERE, e1.getMessage());
                throw new RuntimeException();
            }
        }
        return result;
    }
	
    private List<IDerivedMetric> getApplicableDerivedMetrics() {
    	 final List<IDerivedMetric> result = new ArrayList<>();
         result.addAll(getRegisteredDerivedMetrics().values());
         return result;
    }
	
}
