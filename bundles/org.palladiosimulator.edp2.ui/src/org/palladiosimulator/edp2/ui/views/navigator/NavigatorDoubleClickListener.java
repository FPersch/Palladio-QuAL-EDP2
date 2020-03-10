package org.palladiosimulator.edp2.ui.views.navigator;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.palladiosimulator.edp2.datastream.IDataSource;
import org.palladiosimulator.edp2.datastream.chaindescription.ChainDescription;
import org.palladiosimulator.edp2.datastream.edp2source.Edp2DataTupleDataSource;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.models.ExperimentData.RawMeasurements;
import org.palladiosimulator.edp2.ui.EDP2UIPlugin;
import org.palladiosimulator.edp2.visualization.IVisualisationInput;
import org.palladiosimulator.edp2.visualization.wizards.DefaultViewsWizard;
import org.palladiosimulator.edp2.visualization.wizards.DerivedMetricWizard;
import org.palladiosimulator.edp2.visualization.wizards.SelectMeasurementsWizard;

/**
 * Listener for selections in the {@link Navigator}. Creates a new {@link EDP2Source}, which is
 * associated with the selected {@link RawMeasurements}. Upon Double-click on a
 * {@link RawMeasurements} -object, it opens a Dialog and offers possible combinations of
 * visualizations and transformations to display the data encapsulated by the object. All
 * combinations are objects of the Type {@link ChainDescription} and displayed in the
 * {@link DefaultViewsWizard}.
 *
 * TODO This is a copied version of /org.palladiosimulator.edp2.editor/src-man/de/uka
 * /ipd/sdq/edp2/models/ExperimentData/presentation/NavigatorDoubleClickListener.java. Get rid of
 * redundancies.
 *
 * @author Sebastian Lehrig
 *
 */
public class NavigatorDoubleClickListener implements IDoubleClickListener {

    @Override
    public void doubleClick(final DoubleClickEvent event) {
        Object selectedObject = null;
        if (event.getSelection() instanceof IStructuredSelection) {
            final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            selectedObject = selection.getFirstElement();
        }
        // check for the object to contain actual data
        if (selectedObject instanceof Measurement) {
            openChainSelectionDialog(selectedObject);
        }
        
        if (selectedObject instanceof ExperimentRun) {
        	openDerivedMetricDialog(selectedObject);
        }
    }

    /**
     * @param selectedObject
     */
    private void openChainSelectionDialog(final Object selectedObject) {
        final Measurement measurement = (Measurement) selectedObject;
        final RawMeasurements rawMeasurements = measurement.getMeasurementRanges().get(0).getRawMeasurements();

        if (rawMeasurements != null && !rawMeasurements.getDataSeries().isEmpty()) {
            final IDataSource edp2Source = new Edp2DataTupleDataSource(rawMeasurements);
            final int dataStreamSize = edp2Source.getDataStream().size();
            edp2Source.getDataStream().close();

            if (dataStreamSize > 0) {
                openWizard(edp2Source);
            }
        } else {
            throw new RuntimeException("Empty Measurements!");
        }
    }

 // open the wizard to select a derived metric
    private void openDerivedMetricDialog(final Object selectedObject) {
    	final ExperimentRun run = (ExperimentRun) selectedObject;
    	final DerivedMetricWizard wizard = new DerivedMetricWizard(run.getMeasurement());
    	final WizardDialog wdialog = new WizardDialog(EDP2UIPlugin.INSTANCE.getWorkbench().getActiveWorkbenchWindow()
                    .getShell(), wizard);
    	wdialog.open();

    	if (wdialog.getReturnCode() == Window.OK) {
    		openSelectMetricsDialog(wizard, run);
        }
    }
    
    // open the wizard to select the measurements from which the derived metric should be derived
    private void openSelectMetricsDialog(DerivedMetricWizard wizard, ExperimentRun run) {
    	final SelectMeasurementsWizard metricsWizard = new SelectMeasurementsWizard(wizard.getDerivedMetric(), run.getMeasurement());
       	final WizardDialog metricsWizarddialog = new WizardDialog(EDP2UIPlugin.INSTANCE.getWorkbench().getActiveWorkbenchWindow()
                .getShell(), metricsWizard);
       	metricsWizarddialog.open();
    }
    
    // open the wizard with reference to the selected source
    // it shows possible visualizations, which are instances of
    // DefaultSequence
    private void openWizard(final IDataSource edp2Source) {
        final DefaultViewsWizard wizard = new DefaultViewsWizard(edp2Source);
        final WizardDialog wdialog = new WizardDialog(EDP2UIPlugin.INSTANCE.getWorkbench().getActiveWorkbenchWindow()
                .getShell(), wizard);
        wdialog.open();

        if (wdialog.getReturnCode() == Window.OK) {
            openEditor(edp2Source, wizard);
        }
    }

    /**
     * @param edp2Source
     * @param wizard
     */
    private void openEditor(final IDataSource edp2Source, final DefaultViewsWizard wizard) {
        final ChainDescription chainDescription = wizard.getSelectedDefault();
        final IVisualisationInput input = (IVisualisationInput) chainDescription.getVisualizationInput();
        input.addInput(input.createNewInput(chainDescription.attachRootDataSource(edp2Source)));
        try {
            final IWorkbenchPage page = EDP2UIPlugin.INSTANCE.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            page.openEditor(input, "org.palladiosimulator.edp2.visualization.editors.JFreeChartEditor");
        } catch (final PartInitException e) {
            throw new RuntimeException(e);
        }
    }

}
