package org.palladiosimulator.edp2.visualization.wizards;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.visualization.IDerivedMetric;

public class DerivedMetricWizard extends Wizard implements INewWizard {

	
	/**
	 * The default-combination, which was selected by the user.
	 */
	IDerivedMetric derivedMetric;
	
	/**
	 * Variable to indicate, if the user is allowed to finish the Wizard.
	 */
	boolean finishable;
	
	private SelectDerivedMetricPage selectDerivedMetricPage;
	final List<Measurement> measurements;
	
	public DerivedMetricWizard(List<Measurement> measurements) {
		setWindowTitle("Select a derived Metric");
		this.finishable = false;
		this.measurements = measurements;
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	@Override
	public boolean performFinish() {
		if (finishable) {
			return true;
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		selectDerivedMetricPage = new SelectDerivedMetricPage("Select a derived Metric");
		addPage(selectDerivedMetricPage);
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
	
	/**
	 * @param selectedDefault
	 *            the selectedDefault to set
	 */
	public void setDerivedMetric(final IDerivedMetric derivedMetric) {
		this.derivedMetric = derivedMetric;
	}
	
	/**
	 * 
	 * @return the metric selected
	 */
	public IDerivedMetric getDerivedMetric() {
		return this.derivedMetric;
	}
	
}
