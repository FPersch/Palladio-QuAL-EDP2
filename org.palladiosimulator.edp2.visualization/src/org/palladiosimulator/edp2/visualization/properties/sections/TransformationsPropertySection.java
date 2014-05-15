package org.palladiosimulator.edp2.visualization.properties.sections;

import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Logger;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.palladiosimulator.edp2.datastream.AbstractDataSource;
import org.palladiosimulator.edp2.datastream.filter.AbstractAdapter;
import org.palladiosimulator.edp2.datastream.filter.AbstractFilter;
import org.palladiosimulator.edp2.visualization.AbstractVisualizationSingleDatastreamInput;
import org.palladiosimulator.edp2.visualization.Activator;
import org.palladiosimulator.edp2.visualization.IVisualisationSingleDatastreamInput;
import org.palladiosimulator.edp2.visualization.editors.AbstractEditor;
import org.palladiosimulator.edp2.visualization.wizards.AdapterWizard;
import org.palladiosimulator.edp2.visualization.wizards.FilterWizard;

/**
 * GUI controls for {@link AbstractTransformation}s. Used to display and edit
 * properties of applied data transformations in the Eclipse Properties View if
 * an {@link AbstractEditor} is the currently active editor.
 * 
 * @author Roland Richter, Dominik Ernst
 * 
 */
public class TransformationsPropertySection extends AbstractPropertySection
implements ISelectionChangedListener {
    /** logger */
    private final static Logger logger = Logger
            .getLogger(TransformationsPropertySection.class.getCanonicalName());

    /**
     * Key which must be the same as the key under which the ID's / names of
     * {@link AbstractTransformation}s are stored.
     */
    private final static String NAME_KEY = "elementName";

    /**
     * A tree, which contains the editor's inputs and their transformations (as
     * children)
     */
    private TreeViewer treeViewer;
    /**
     * The table for displaying attributes of a selected transformation.
     */
    private Table transformationTable;

    /**
     * Viewer for the table containing the attributes of a transformation.
     */
    private TableViewer transformationTableViewer;

    /**
     * The current editor which is an
     * {@link ITabbedPropertySheetPageContributor}.
     */
    private AbstractEditor editor;

    /**
     * Last, by the user selected {@link AbstractTransformation} in the
     * {@link #treeViewer}.
     */
    private AbstractAdapter selectedTransformation;

    /**
     * The last selected {@link AbstractVisualizationSingleDatastreamInput} in the {@link #treeViewer}.
     */
    private IVisualisationSingleDatastreamInput selectedInput;

    /**
     * The parent container.
     */
    private Composite container;

    /**
     * Create the look and items of the properties. It is called, if one of the
     * editors in the package visualization.editors is selected.
     */
    @Override
    public void createControls(final Composite parent,
            final TabbedPropertySheetPage aTabbedPropertySheetPage) {

        super.createControls(parent, aTabbedPropertySheetPage);

        container = getWidgetFactory().createFlatFormComposite(parent);
        container.setBackground(parent.getDisplay().getSystemColor(
                SWT.COLOR_WIDGET_BACKGROUND));
        final GridLayout layout = new GridLayout(1, false);
        layout.marginWidth = 2;
        layout.marginHeight = 2;
        container.setLayout(layout);
        // initialize the layout
        final Group group = new Group(container, SWT.NONE);
        group.setText("Editor Inputs");
        group.setLayout(new GridLayout(3, false));
        // create the tree viewer with empty input
        final InputSelectionTree tree = new InputSelectionTree(group, SWT.EMBEDDED,
                null);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
        tree.setLayoutData(gridData);
        treeViewer = tree.getTreeViewer();

        initTransformationTable(group);

        final Button buttonAdapter = new Button(group, SWT.PUSH);
        buttonAdapter.setText("Add new Adapter..");
        gridData = new GridData(SWT.FILL,
                SWT.FILL, false, false, 1, 1);
        gridData.widthHint = 120;
        buttonAdapter.setLayoutData(gridData);
        final Button buttonFilter = new Button(group, SWT.PUSH);
        buttonFilter.setText("Add new Filter..");
        buttonFilter.setLayoutData(gridData);

        final Listener btnListener = new Listener() {

            // TODO fix references on previous transformations etc.
            @Override
            public void handleEvent(final Event event) {
                if (selectedInput == null) {
                    MessageDialog.openError(Activator.getDefault()
                            .getWorkbench().getActiveWorkbenchWindow()
                            .getShell(), "No data series selected",
                            "To add a transformation, a data series, to which the transformation should "
                                    + "be added, must be selected first");
                } else {
                    if (event.widget == buttonAdapter) {
                        final AdapterWizard wizard = new AdapterWizard(
                                (AbstractDataSource) selectedInput.getDataSource());
                        AbstractAdapter adapter = null;
                        final WizardDialog wdialog = new WizardDialog(Activator
                                .getDefault().getWorkbench()
                                .getActiveWorkbenchWindow().getShell(), wizard);
                        wdialog.open();
                        if (wdialog.getReturnCode() == Window.OK) {
                            adapter = wizard.getAdapter();
                            handleSemanticChange(adapter);
                        }

                    } else if (event.widget == buttonFilter) {
                        final FilterWizard wizard = new FilterWizard(
                                (AbstractDataSource) selectedInput.getDataSource());
                        AbstractFilter filter = null;
                        final WizardDialog wdialog = new WizardDialog(Activator
                                .getDefault().getWorkbench()
                                .getActiveWorkbenchWindow().getShell(), wizard);
                        wdialog.open();
                        if (wdialog.getReturnCode() == Window.OK) {
                            filter = wizard.getFilter();
                            //TODO selectedInput.setSource(filter);
                            refresh();
                        }
                    }

                    ((AbstractEditor<AbstractVisualizationSingleDatastreamInput>) editor).updateEditorContents();
                }
            }
        };
        buttonAdapter.addListener(SWT.Selection, btnListener);
        buttonFilter.addListener(SWT.Selection, btnListener);

    }

    private void handleSemanticChange(final AbstractAdapter adapter) {
        //        // TODO perform actual check on compatible editors for new input.
        //        boolean adapterExistsAlready = false;
        //        for (final AbstractTransformation trafo : selectedInput
        //                .getListOfTransformations()) {
        //            if (trafo.getFactoryId().equals(adapter.getFactoryId())) {
        //                MessageDialog
        //                .openInformation(
        //                        Activator.getDefault().getWorkbench()
        //                        .getActiveWorkbenchWindow().getShell(),
        //                        "Adapter already exists",
        //                        "You tried to add an adapter, which already exists in the list"
        //                                + "of transformations for the selected data series. "
        //                                + "Please select a different series or change the settings of the existing adapter "
        //                                + "in the properties view.");
        //                adapterExistsAlready = true;
        //            }
        //        }
        //
        //        if (!adapterExistsAlready) {
        //            final boolean result = MessageDialog
        //                    .openQuestion(
        //                            Activator.getDefault().getWorkbench()
        //                            .getActiveWorkbenchWindow().getShell(),
        //                            "Semantics of Data Changed",
        //                            "The applied data transformation cannot be displayed in the current dataset."
        //                                    + "A new dataset in a new editor must be opened. Do you want to proceed?");
        //            if (result) {
        //
        //                final DefaultViewsWizard wizard = new DefaultViewsWizard(adapter);
        //                final WizardDialog wdialog = new WizardDialog(Activator.getDefault()
        //                        .getWorkbench().getActiveWorkbenchWindow().getShell(),
        //                        wizard);
        //                wdialog.open();
        //
        //                if (wdialog.getReturnCode() == Window.OK) {
        //                    final DefaultSequence selection = wizard.getSelectedDefault();
        //
        //                    if (selection.getSize() > 0) {
        //                        if (selection.getSequenceProperties().size() > 0) {
        //                            selection.getFirstSequenceElement().setProperties(
        //                                    selection.getSequenceProperties().get(0));
        //                        }
        //                        selection.getFirstSequenceElement().setSource(adapter);
        //                    }
        //                    for (int i = 1; i < selection.getSize(); i++) {
        //                        selection
        //                        .getSequenceElements()
        //                        .get(i)
        //                        .setProperties(
        //                                selection.getSequenceProperties()
        //                                .get(i));
        //                        selection
        //                        .getSequenceElements()
        //                        .get(i)
        //                        .setSource(
        //                                selection.getSequenceElements().get(
        //                                        i - 1));
        //                    }
        //
        //                    final IVisualisationSingleDatastreamInput visualization = selection
        //                            .getVisualization();
        //                    visualization.setProperties(selection
        //                            .getVisualizationProperties());
        //                    if (selection.getSize() > 0) {
        //                        visualization.setSource(selection
        //                                .getLastSequenceElement());
        //                    } else {
        //                        visualization.setSource(adapter);
        //                    }
        //                    final AbstractVisualizationInput<JFreeChartVisualisationSingleDatastreamInput<T>> input = new JFreeChartVisualisationInput(
        //                            (AbstractVisualizationSingleDatastreamInput) visualization);
        //
        //                    try {
        //                        final IWorkbenchPage page = Activator.getDefault()
        //                                .getWorkbench().getActiveWorkbenchWindow()
        //                                .getActivePage();
        //                        page.openEditor(input,
        //                                "org.palladiosimulator.edp2.visualization.editors.JFreeChartEditor");
        //                    } catch (final PartInitException e) {
        //                    }
        //
        //                }
        //            }
        //        }
        //        refresh();
    }

    /**
     * Creates and initializes the list of the applied filters and the
     * properties table. A selection listener looks if one entry in the filter
     * list is selected and if one is selected the
     * {@link TransformationsPropertySection#refreshPropertiesTable()} is
     * called. A listener look for the properties table and call
     * {@link TransformationsPropertySection#updateProperties(String, Object)}
     * of the value field is let out by pressing ENTER.
     * 
     * @param parentGroup
     *            the parent GUI Object
     */
    private void initTransformationTable(final Composite parent) {

        // initialize the table, which shows the properties of transformations
        transformationTable = new Table(parent, SWT.SINGLE | SWT.BORDER
                | SWT.V_SCROLL | SWT.FULL_SELECTION);

        transformationTable.setLinesVisible(true);
        transformationTable.setHeaderVisible(true);
        final GridData gridData = new GridData(GridData.FILL_HORIZONTAL,
                GridData.FILL_VERTICAL, false, true, 1, 1);
        gridData.heightHint = 220;
        gridData.widthHint = 180;

        transformationTable.setLayoutData(gridData);
        // set width and height of the table
        // transformationTable.setLayoutData(new RowData(250, 123));
        // set the weight of the table columns
        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(2));
        tableLayout.addColumnData(new ColumnWeightData(1));
        transformationTable.setLayout(tableLayout);

        transformationTableViewer = new TableViewer(transformationTable);
        final TableViewerColumn labelColumn = new TableViewerColumn(
                transformationTableViewer, SWT.NONE);
        labelColumn.getColumn().setText("Property");

        final TableViewerColumn valueColumn = new TableViewerColumn(
                transformationTableViewer, SWT.NONE);
        valueColumn.getColumn().setText("Value");

        // the editor for the cells
        final TableEditor editor = new TableEditor(transformationTable);
        editor.horizontalAlignment = SWT.LEFT;
        editor.grabHorizontal = true;
        transformationTable.addListener(SWT.MouseDown, new Listener() {
            @Override
            public void handleEvent(final Event event) {
                final Rectangle clientArea = transformationTable.getClientArea();
                final Point pt = new Point(event.x, event.y);
                int index = transformationTable.getTopIndex();
                while (index < transformationTable.getItemCount()) {
                    boolean visible = false;
                    final TableItem item = transformationTable.getItem(index);

                    // look if the mouse event is in an editable cell
                    final Rectangle rect = item.getBounds(1);
                    if (rect.contains(pt)) {
                        final int column = 1;
                        final Text text = new Text(transformationTable,
                                SWT.NONE);
                        final Listener textListener = new Listener() {
                            @Override
                            public void handleEvent(final Event e) {
                                switch (e.type) {
                                case SWT.FocusOut:
                                    item.setText(column, text.getText());
                                    text.dispose();
                                    break;
                                case SWT.Traverse:
                                    switch (e.detail) {
                                    case SWT.TRAVERSE_RETURN:
                                        item.setText(column, text.getText());
                                        updateProperties(
                                                // first column
                                                item.getText(0), text.getText());

                                    case SWT.TRAVERSE_ESCAPE:
                                        text.dispose();
                                        e.doit = false;
                                    }
                                    break;
                                }
                            }
                        };
                        text.addListener(SWT.FocusOut, textListener);
                        text.addListener(SWT.Traverse, textListener);
                        editor.setEditor(text, item, 1);
                        text.setText(item.getText(1));
                        text.selectAll();
                        text.setFocus();
                        return;
                    }
                    if (!visible && rect.intersects(clientArea)) {
                        visible = true;
                    }
                    // }
                    if (!visible) {
                        return;
                    }
                    index++;
                }
            }
        });

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput
     * (org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
    }

    private boolean editorExists() {
        final IWorkbenchWindow window = Activator.getDefault().getWorkbench()
                .getActiveWorkbenchWindow();
        if (window == null) {
            editor = null;
            return false;
        } else if (window.getActivePage() == null) {
            editor = null;
            return false;
        } else if (window.getActivePage().getActiveEditor() == null) {
            editor = null;
            return false;
        } else {
            editor = (AbstractEditor) window.getActivePage().getActiveEditor();
            return true;
        }
    }

    /**
     * Refresh the items in the filters properties table. It shows the
     * properties of the selected filter in the list.
     * 
     * @param abstractTransformation
     */
    private void refreshPropertiesTable() {

        transformationTable.clearAll();
        transformationTable.setItemCount(0);

        // TODO: Get properties of adapter
        final HashMap<String, Object> properties = (HashMap<String, Object>) Collections.EMPTY_MAP;

        for (final Object key : properties.keySet()) {
            final TableItem item = new TableItem(transformationTable, SWT.NONE);
            item.setText(0, String.valueOf(key));
            item.setText(1, String.valueOf(properties.get(key)));
        }
    }

    /**
     * Update the properties of the selected filter. It use the method
     * {@link AbstractTransformation#setProperties(HashMap)} to update the
     * properties from the Filter.
     * 
     * @param key
     *            the key as String.
     * @param value
     *            the value as an Object.
     */
    private void updateProperties(final String key, final Object value) {
        // TODO selectedTransformation.setProperties(newProperties);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
     */
    @Override
    public void refresh() {
        if (editorExists()) {
            treeViewer.setInput(editor.getEditorInput());
            treeViewer.refresh();
            treeViewer.addSelectionChangedListener(this);
        }
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        final ITreeSelection selection = (ITreeSelection) event
                .getSelectionProvider().getSelection();
        if (selection.getFirstElement() instanceof AbstractAdapter) {
            selectedTransformation = (AbstractAdapter) selection
                    .getFirstElement();
            refreshPropertiesTable();
        } else {
            selectedInput = (IVisualisationSingleDatastreamInput) selection.getFirstElement();
        }
    }

}
