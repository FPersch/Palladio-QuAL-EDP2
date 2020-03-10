package org.palladiosimulator.edp2.visualization.wizards;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.visualization.IDerivedMetric;

public class SelectMeasurementsWizard extends Wizard implements INewWizard {

	private final IDerivedMetric derivedMetric;
	private boolean finishable;
	private SelectMeasurementsPage selectMetricsPage;
	private List<Measurement> measurements;
	private List<Measurement> selectedMetrics;
	
	public SelectMeasurementsWizard(IDerivedMetric derivedMetric, List<Measurement> measurements) {
		setWindowTitle("Select all needed measurements");
		this.derivedMetric = derivedMetric;
		this.measurements = measurements;
		this.finishable = false;
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		selectMetricsPage = new SelectMeasurementsPage("Select all required measurements", derivedMetric, measurements);
		addPage(selectMetricsPage);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#needsPreviousAndNextButtons()
	 */
	@Override
	public boolean needsPreviousAndNextButtons() {
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.wizard.Wizard#createPageControls(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createPageControls(final Composite pageContainer) {
		super.createPageControls(pageContainer);
	}

	@Override
	public boolean performFinish() {
		if (finishable) {
			derivedMetric.calculate(selectMetricsPage.getSelectedMeasurements());
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	@Override
	public boolean canFinish() {
		return finishable;
	}

	/**
	 * Sets the <finishable> attribute to the specified value.
	 * 
	 * @param finishable
	 *            a boolean value
	 */
	public void setFinishable(final boolean finishable) {
		this.finishable = finishable;
	}

	public List<Measurement> getSelectedMetrics() {
		return this.selectedMetrics;
	}
	
}
