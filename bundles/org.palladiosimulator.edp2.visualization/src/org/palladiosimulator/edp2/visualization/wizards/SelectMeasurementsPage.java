package org.palladiosimulator.edp2.visualization.wizards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.edp2.visualization.IDerivedMetric;
import org.palladiosimulator.metricspec.MetricDescription;

public class SelectMeasurementsPage extends WizardPage implements ISelectionChangedListener {

    /**
     * The 'OK'-Status, which signalizes everything is fine.
     */
    private final IDerivedMetric derivedMetric;
    private final List<Measurement> measurements;
    private Map<String, Measurement> selectedMeasurements;
    private Map<String, Text> textFields;
    
	protected SelectMeasurementsPage(String pageName, IDerivedMetric derivedMetric, List<Measurement> measurements) {
		super(pageName);
        setDescription("Choose all required measurements to calculate the derived metric.");
        textFields = new HashMap<>();
        this.derivedMetric = derivedMetric;
        this.measurements = measurements;
        selectedMeasurements = new HashMap<>();
	}

	@Override
	public void createControl(Composite parent) {
		// create the composite to hold the widgets
        final Composite composite = new Composite(parent, SWT.NONE);

        // create the desired layout for this wizard page
        final GridLayout gl = new GridLayout();
        composite.setLayout(gl);

        final Label label = new Label(composite, SWT.NONE);
        label.setText("Required measurements:");

        final SashForm sashForm = new SashForm(composite, SWT.HORIZONTAL);
        final GridData gd = new GridData(GridData.FILL_BOTH);
        gd.widthHint = 200;
        sashForm.setLayoutData(gd);
		
        for (Entry<String, MetricDescription> entry : derivedMetric.getNeededMetrics().entrySet()) {
        	createSelection(composite, entry);
        }
        
        setControl(composite);
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO: implement according to SelectDerivedMetricPage
	}

	/**
     * Method which handles the status of the whole {@link WizardPage} based on the different states
     * resulting from inputs.
     *
     * @return the page Status
     */
    public boolean updatePageStatus() {
    	// TODO: implement possible changes according to SelectDerivedMetricPage
        ((SelectMeasurementsWizard) getWizard()).setFinishable(false);
        boolean missing = false;
        if (textFields.entrySet().size() != derivedMetric.getNeededMetrics().size()) {
        	return false;
        }
		for (Entry<String, Text> entry : textFields.entrySet()) {
        	if (entry.getValue().getData() == null) {
        		missing = true;
        		return false;
        	}
        }
		if (!missing) {
			((SelectMeasurementsWizard) getWizard()).setFinishable(true);
		}
        getContainer().updateButtons();
        return true;
    }
	
	public Map<String, Measurement> getSelectedMeasurements() {
		return this.selectedMeasurements;
	}
	
	private void createSelection(Composite composite, Entry<String, MetricDescription> entry) {
		Label nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		nameLabel.setText(entry.getKey());
		
		Text text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text.setEditable(false);
		textFields.put(entry.getKey(), text);
		Button browse = new Button(composite, SWT.NONE);
		browse.setText("Browse");
		browse.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	var lblProvider = new ILabelProvider() {
	
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
		                    final Measurement sequenceElement = (Measurement) element;
		                    return sequenceElement.getMeasuringType().getMeasuringPoint().getStringRepresentation();
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
		        };
		        
		    	ElementListSelectionDialog dialog = new ElementListSelectionDialog(composite.getShell(), lblProvider);
		    	List<Measurement> applicableMetrics = getApplicableMetrics(entry.getValue());
		    	dialog.setElements(applicableMetrics.toArray());
		    	dialog.setTitle("Select the " + entry.getKey());
		    	if (dialog.open() != Window.OK) {
		            
		    	}
		    	
		    	Object[] result = dialog.getResult();
		    	if (result != null) {
			    	text.setText(((Measurement) result[0]).getMeasuringType().getMeasuringPoint().getStringRepresentation());
			    	text.setData((Measurement) result[0]);
			    	selectedMeasurements.put(entry.getKey(), (Measurement) result[0]);
		    		updatePageStatus();
		    	}
		    }
		});
	}

	private List<Measurement> getApplicableMetrics(MetricDescription metricDesc) {
		List<Measurement> result = new ArrayList<>();
	    for (Measurement measurement : measurements) {
	    	if (MetricDescriptionUtility.metricDescriptionIdsEqual(metricDesc, measurement.getMeasuringType().getMetric())) {
	    		result.add(measurement);
	    	}
	    }
	    return result;
	}
	
}
