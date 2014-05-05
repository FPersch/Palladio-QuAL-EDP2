package org.palladiosimulator.edp2.visualization.properties.sections;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.palladiosimulator.edp2.datastream.IDataSink;
import org.palladiosimulator.edp2.visualization.IVisualisationInput;
import org.palladiosimulator.edp2.visualization.IVisualisationSingleDatastreamInput;

public class InputSelectionTree extends Composite {

	private TreeViewer treeViewer;

	/**
	 * @return the treeViewer
	 */
	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}

		public String getText(Object element) {
			if (element instanceof IVisualisationSingleDatastreamInput)
				return ((IVisualisationSingleDatastreamInput) element).getInputName();
			else if (element instanceof IDataSink)
				return ((IDataSink) element).getName();
			return super.getText(element);
		}
	}

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public InputSelectionTree(Composite parent, int style,
			IVisualisationInput inputHandle) {
		super(parent, SWT.EMBEDDED);
		setLayout(new GridLayout(1, false));

		Label lblCurrentEditorInputs = new Label(this, SWT.NONE);
		lblCurrentEditorInputs.setText("Current Editor Inputs:");

		treeViewer = new TreeViewer(this, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		GridData gd_tree = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1);
		gd_tree.heightHint = 168;
		gd_tree.widthHint = 257;
		tree.setLayoutData(gd_tree);
		treeViewer.setLabelProvider(new ViewerLabelProvider());
		treeViewer.setContentProvider(new InputSelectionContentProvider());
		treeViewer.setInput(inputHandle);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
