/**
 * 
 */
package org.palladiosimulator.edp2.visualization.inputs;

import java.awt.Color;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.measure.Measure;
import javax.measure.unit.Unit;
import javax.measure.unit.UnitFormat;

import org.eclipse.ui.IMemento;
import org.jfree.chart.ChartColor;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.ui.TextAnchor;
import org.palladiosimulator.edp2.MeasurementsDao;
import org.palladiosimulator.edp2.datastream.AbstractDataSource;
import org.palladiosimulator.edp2.impl.MeasurementsUtility;
import org.palladiosimulator.edp2.impl.MetricDescriptionUtility;
import org.palladiosimulator.edp2.models.ExperimentData.DataSeries;
import org.palladiosimulator.edp2.visualization.datasource.ElementFactory;
import org.palladiosimulator.edp2.visualization.editors.JFreeChartVisualisationSingleDatastreamInput;
import org.palladiosimulator.edp2.visualization.util.DefaultUnitSwitch;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;

/**
 * A HistogramEditorInput displays the input data in a histogram either in
 * absolute or relative frequency. Options include the number of bins into which
 * the data is split and whether axis and values are labeled.
 * 
 * @author Dominik Ernst
 * 
 */
public class HistogramEditorInput extends
JFreeChartVisualisationSingleDatastreamInput<HistogramDataset> {

    /**
     * Name constant, which is used to identify this class in properties and
     * persistence.
     */
    private static final String ELEMENT_NAME = "HistogramEditorInput";

    /**
     * Keys for persistence of properties
     */
    public static final String DOMAIN_AXIS_LABEL_KEY = "domainAxisLabel";
    public static final String RANGE_AXIS_LABEL_KEY = "rangeAxisLabel";
    public static final String SHOW_RANGE_AXIS_LABEL_KEY = "showRangeAxisLabel";
    public static final String SHOW_DOMAIN_AXIS_LABEL_KEY = "showDomainAxisLabel";
    public static final String INCLUDE_ZERO_KEY = "includeZero";
    public final static String NUMBER_BINS_KEY = "numberOfBins";
    public final static String SHOW_ITEM_VALUES_KEY = "showItemValues";
    public final static String BAR_MARGIN_KEY = "barMargin";
    public final static String ABSOLUTE_FREQUENCY_KEY = "absoluteFrequency";
    public final static String UNIT_KEY = "unit";

    /**
     * Default value for <code>numberOfBins</code>
     */
    private final static int DEFAULT_NUMBER_BINS = 5;

    /**
     * Default value if no unit is specified.
     */
    private static final String NO_UNIT = "noUnit";

    /**
     * The number of bins, i.e. the number of intervals of equal length in which
     * the measurements are counted.
     */
    private int numberOfBins;

    /**
     * Option to show the value for each bar in the histogram, i.e. the absolute
     * or relative number of items contained in each bin.
     */
    private boolean showItemValues;

    /**
     * Whether to use absolute frequency or relative frequency for the chart.
     * NOTE: this does affect other input items as well, but is specific for
     * histograms, thus it is located here.
     */
    private boolean absoluteFrequency;

    /**
     * The width of the whitespace between the bars in percentage of each bar's
     * width.
     */
    private double barMargin;
    /**
     * The specific type of data provided by this {@link JFreeChartVisualisationSingleDatastreamInput}.
     */
    private double[] data;

    /**
     * Label for the number axis (= horizontal axis)
     */
    private String domainAxisLabel;

    /**
     * Label for the range axis (= vertical axis)
     */
    private String rangeAxisLabel;

    /**
     * The unit of the horizontal axis as a String.
     */
    private String unit;

    /**
     * Show different labels?
     */
    private boolean showRangeAxisLabel;
    private boolean showDomainAxisLabel;
    /**
     * Always include zero in the diagram?
     */
    private boolean includeZero;
    private Unit<?> jscienceUnit;
    /**
     * Logger for this class
     */
    private final static Logger logger = Logger
            .getLogger(HistogramEditorInput.class.getCanonicalName());

    /**
     * Empty constructor.
     */
    public HistogramEditorInput() {
        this(null);
    }

    /**
     * Constructor, with reference on the source. Automatically initiates an
     * update of the {@link #dataset}.
     * 
     * @param source
     */
    public HistogramEditorInput(final AbstractDataSource source) {
        super();
        setAbsoluteFrequency(true);
        setBarMargin(0.0);
        setNumberOfBins(DEFAULT_NUMBER_BINS);
        setShowItemValues(false);
        setAlpha(1.0f);
        setShowDomainAxisLabel(true);
        setShowRangeAxisLabel(true);
        setIncludeZero(false);
        setUnit(NO_UNIT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.palladiosimulator.edp2.visualization.IVisualizationInput#updateInputData()
     */
    @Override
    @SuppressWarnings("unchecked")
    public void updateInputData() {
        logger.log(Level.INFO, "Transformation : BEGIN");

        dataset = new HistogramDataset();

        final ArrayList<MeasurementsDao> listOfDaos = new ArrayList<MeasurementsDao>();
        final ArrayList<List<Measure>> listOfMeasures = new ArrayList<List<Measure>>();
        for (final DataSeries series : getSource().getOutput()) {
            listOfDaos.add(MeasurementsUtility
                    .getMeasurementsDao(series));
        }
        for (final MeasurementsDao dao : listOfDaos) {
            listOfMeasures.add(dao.getMeasurements());
        }

        // sorting seems to have no effect
        // Collections.sort(listOfMeasures.get(0));
        data = new double[listOfMeasures.get(0).size()];

        for (int i = 0; i < listOfMeasures.get(0).size(); i++) {
            data[i] = listOfMeasures.get(0).get(i).doubleValue(getUnit());

        }
        dataset.addSeries(getInputName(), data, getNumberOfBins());

        setChanged();
        notifyObservers();
        logger.log(Level.INFO, "Transformation : END");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.palladiosimulator.edp2.models.ExperimentData.presentation.IDataSink#canAccept
     * (org.palladiosimulator.edp2.models.ExperimentData.presentation.IDataSource)
     */
    @Override
    public boolean canAccept(final AbstractDataSource source) {
        boolean result = true;
        result = source.getOutput().size() < 1;
        result = (MetricDescriptionUtility.toBaseMetricDescriptions(source
                .getMeasurementsRange().getMeasurements().getMeasure()
                .getMetric())[0] instanceof NumericalBaseMetricDescription);
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.edp2.models.ExperimentData.presentation.IDataSink#
     * getMetricRoles()
     */
    @Override
    public ArrayList<MetricDescription> getMetricRoles() {
        throw new RuntimeException("Not implemented!");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPersistableElement#getFactoryId()
     */
    @Override
    public String getFactoryId() {
        return HistogramEditorInputFactory.getFactoryId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPersistable#saveState(org.eclipse.ui.IMemento)
     */
    @Override
    public void saveState(final IMemento memento) {
        HistogramEditorInputFactory.saveState(memento, this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.edp2.visualization.IDataFlow#getProperties()
     */
    @Override
    public HashMap<String, Object> getProperties() {
        properties.put(ElementFactory.ELEMENT_KEY, ELEMENT_NAME);
        properties.put(RANGE_AXIS_LABEL_KEY, getRangeAxisLabel());
        properties.put(DOMAIN_AXIS_LABEL_KEY, getDomainAxisLabel());
        properties.put(SHOW_DOMAIN_AXIS_LABEL_KEY,
                String.valueOf(isShowDomainAxisLabel()));
        properties.put(SHOW_RANGE_AXIS_LABEL_KEY,
                String.valueOf(isShowRangeAxisLabel()));
        properties.put(INCLUDE_ZERO_KEY, String.valueOf(isIncludeZero()));
        properties.put(NUMBER_BINS_KEY, getNumberOfBins());
        properties.put(COLOR_KEY, getColor());
        properties.put(INPUT_NAME_KEY, getInputName());
        properties.put(SHOW_ITEM_VALUES_KEY, isShowItemValues());
        properties.put(BAR_MARGIN_KEY, getBarMargin());
        properties.put(ABSOLUTE_FREQUENCY_KEY, isAbsoluteFrequency());
        properties.put(UNIT_KEY, getUnitAsString());
        return properties;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.palladiosimulator.edp2.visualization.IDataFlow#setProperties(java.util.HashMap
     * )
     */
    @Override
    public void setProperties(final HashMap<String, Object> newProperties) {
        if (newProperties.get(NUMBER_BINS_KEY) != null) {
            setNumberOfBins(Integer.parseInt(newProperties.get(NUMBER_BINS_KEY)
                    .toString()));
        }
        if (newProperties.get(COLOR_KEY) != null) {
            setColor(newProperties.get(COLOR_KEY).toString());
        }
        if (newProperties.get(SHOW_ITEM_VALUES_KEY) != null) {
            setShowItemValues(Boolean.parseBoolean(newProperties.get(
                    SHOW_ITEM_VALUES_KEY).toString()));
        }
        if (newProperties.get(BAR_MARGIN_KEY) != null) {
            setBarMargin(Double.parseDouble(newProperties.get(BAR_MARGIN_KEY)
                    .toString()));
        }
        if (newProperties.get(ABSOLUTE_FREQUENCY_KEY) != null) {
            setAbsoluteFrequency(Boolean.parseBoolean(newProperties.get(
                    ABSOLUTE_FREQUENCY_KEY).toString()));
        }
        if (newProperties.get(INPUT_NAME_KEY) != null) {
            setInputName(newProperties.get(INPUT_NAME_KEY).toString());
        }
        if (newProperties.get(RANGE_AXIS_LABEL_KEY) != null) {
            setRangeAxisLabel(newProperties.get(RANGE_AXIS_LABEL_KEY)
                    .toString());
        }
        if (newProperties.get(DOMAIN_AXIS_LABEL_KEY) != null) {
            setDomainAxisLabel(newProperties.get(DOMAIN_AXIS_LABEL_KEY)
                    .toString());
        }
        if (newProperties.get(SHOW_DOMAIN_AXIS_LABEL_KEY) != null) {
            setShowDomainAxisLabel(Boolean.parseBoolean(newProperties.get(
                    SHOW_DOMAIN_AXIS_LABEL_KEY).toString()));
        }
        if (newProperties.get(SHOW_RANGE_AXIS_LABEL_KEY) != null) {
            setShowRangeAxisLabel(Boolean.parseBoolean(newProperties.get(
                    SHOW_RANGE_AXIS_LABEL_KEY).toString()));
        }
        if (newProperties.get(INCLUDE_ZERO_KEY) != null) {
            setIncludeZero(Boolean.parseBoolean(newProperties.get(
                    INCLUDE_ZERO_KEY).toString()));
        }
        if (newProperties.get(UNIT_KEY) != null) {
            parseJScienceUnit(newProperties.get(UNIT_KEY).toString());
        }
    }

    public String getDomainAxisLabel() {
        if (domainAxisLabel == null) {
            return getDefaultDomainAxisLabel();
        }
        return domainAxisLabel;
    }

    public void setDomainAxisLabel(final String domainAxisLabel) {
        this.domainAxisLabel = domainAxisLabel;
    }

    public void setRangeAxisLabel(final String rangeAxisLabel) {
        this.rangeAxisLabel = rangeAxisLabel;
    }

    public String getRangeAxisLabel() {
        if (rangeAxisLabel == null) {
            return getDefaultRangeAxisLabel();
        }
        return rangeAxisLabel;
    }

    public boolean isShowRangeAxisLabel() {
        return showRangeAxisLabel;
    }

    public void setShowRangeAxisLabel(final boolean showRangeAxisLabel) {
        this.showRangeAxisLabel = showRangeAxisLabel;
    }

    public boolean isShowDomainAxisLabel() {
        return showDomainAxisLabel;
    }

    public void setShowDomainAxisLabel(final boolean showDomainAxisLabel) {
        this.showDomainAxisLabel = showDomainAxisLabel;
    }

    public boolean isIncludeZero() {
        return includeZero;
    }

    public void setIncludeZero(final boolean includeZero) {
        this.includeZero = includeZero;
    }

    private int getNumberOfBins() {
        if (numberOfBins != 0) {
            return numberOfBins;
        }
        return DEFAULT_NUMBER_BINS;
    }

    private void setNumberOfBins(final int numberOfBins) {
        this.numberOfBins = numberOfBins;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.palladiosimulator.edp2.visualization.editors.JFreeChartEditorInput#getChart
     * ()
     */
    @Override
    public JFreeChart getChart() {
        // create the axes for the chart; if an axis is not to be displayed, it
        // must be null
        XYPlot plot = null;
        XYBarRenderer renderer = null;
        final NumberAxis domainAxis = new NumberAxis(
                isShowDomainAxisLabel() ? getDomainAxisLabel() : null);
        domainAxis.setAutoRangeIncludesZero(isIncludeZero());
        final NumberAxis rangeAxis = new NumberAxis(
                isShowRangeAxisLabel() ? getRangeAxisLabel() : null);

        dataset = new HistogramDataset();
        // add all inputs anew
        // assume that if the getChart()-Method of this input is called, the
        // remaining inputs have the same type of data
        for (int i = 0; i < getHandle().getInputsSize(); i++) {
            dataset.addSeries(
                    getHandle().getInputs().get(i).getInputName(),
                    (double[]) getHandle().getInputs().get(i).getData(),
                    Integer.parseInt(getHandle().getInputProperties()[i].get(
                            "numberOfBins").toString()));
        }

        plot = new XYPlot();
        plot.setDataset(getDataset());

        // the renderer for the chart
        renderer = new XYBarRenderer();
        plot.setRenderer(renderer);
        plot.setRangeAxis(rangeAxis);
        plot.setDomainAxis(domainAxis);

        // modifiy the colors of the data series, if there are persisted color
        // properties
        for (int i = 0; i < getHandle().getInputsSize(); i++) {
            final float alpha = Float.parseFloat(getHandle().getInputProperties()[i]
                    .get(JFreeChartVisualisationSingleDatastreamInput.ALPHA_KEY).toString());
            if ((getHandle().getInputProperties()[i]
                    .get(JFreeChartVisualisationSingleDatastreamInput.COLOR_KEY) != null)
                    && !getHandle().getInputProperties()[i]
                            .get(JFreeChartVisualisationSingleDatastreamInput.COLOR_KEY).toString()
                            .equals(NO_COLOR)) {
                final Color opaque = Color.decode(getHandle().getInputProperties()[i]
                        .get(JFreeChartVisualisationSingleDatastreamInput.COLOR_KEY).toString());

                final float[] comp = opaque.getRGBColorComponents(null);
                final Color col = new Color(comp[0], comp[1], comp[2], alpha);
                renderer.setSeriesPaint(i, col);
            } else {
                final Color defaultColor = (Color) ChartColor
                        .createDefaultPaintArray()[i];
                final float[] comp = defaultColor.getRGBColorComponents(null);
                final Color col = new Color(comp[0], comp[1], comp[2], alpha);
                renderer.setSeriesPaint(i, col);
            }
        }

        // absolute or relative frequency
        getDataset().setType(
                isAbsoluteFrequency() ? HistogramType.FREQUENCY
                        : HistogramType.RELATIVE_FREQUENCY);
        // margin is the relative space of each bar, which remains uncolored
        // NOTE: this prevents a clear visibility of the bins' upper / lower
        // bounds
        renderer.setMargin(getBarMargin() / 100);

        // show values on each bar in the histogram if the property is set
        renderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        renderer.setBaseItemLabelPaint(Color.BLACK);
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition());
        renderer.setBaseNegativeItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER));
        renderer.setBaseItemLabelsVisible(isShowItemValues());

        // finally, create the chart using the plot
        final JFreeChart chart = new JFreeChart(
                getHandle().isShowTitle() ? getHandle().getTitle() : null,
                        JFreeChart.DEFAULT_TITLE_FONT, plot, getHandle().isShowLegend());
        return chart;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.edp2.visualization.IVisualizationInput#getData()
     */
    @Override
    public double[] getData() {
        return data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.palladiosimulator.edp2.visualization.IDataSink#createCopyForSource(de.uka
     * .ipd.sdq.edp2.visualization.AbstractDataSource)
     */
    @Override
    public HistogramEditorInput createCopyForSource(final AbstractDataSource source) {
        final HistogramEditorInput copy = new HistogramEditorInput(source);
        return copy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.edp2.visualization.IDataFlow#getName()
     */
    @Override
    public String getName() {
        return ELEMENT_NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.edp2.visualization.editors.JFreeChartEditorInput#
     * getDefaultTitle()
     */
    @Override
    public String getDefaultTitle() {
        return "Histogram";
    }

    public String getDefaultDomainAxisLabel() {
        if (getSource() != null) {
            final MetricDescription metric = MetricDescriptionUtility
                    .toBaseMetricDescriptions(getSource()
                            .getMeasurementsRange().getMeasurements()
                            .getMeasure().getMetric())[0];
            return metric.getName() + " [" + getUnitAsString() + "]";
        } else {
            return "noDefaultLabelAvailable";
        }
    }

    public String getDefaultRangeAxisLabel() {
        if (getSource() != null) {
            return "Frequency ["
                    + (isAbsoluteFrequency() ? "absolute" : "relative") + "]";
        } else {
            return "noDefaultLabelAvailable";
        }
    }

    private boolean isAbsoluteFrequency() {
        return absoluteFrequency;
    }

    private double getBarMargin() {
        return barMargin;
    }

    private boolean isShowItemValues() {
        return showItemValues;
    }

    private void setAbsoluteFrequency(final boolean value) {
        absoluteFrequency = value;
    }

    private void setBarMargin(final double percentage) {
        if (percentage < 0) {
            barMargin = 0.0;
        } else if (percentage > 100) {
            barMargin = 100.0;
        } else {
            barMargin = percentage;
        }
    }

    private void setShowItemValues(final boolean value) {
        showItemValues = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.palladiosimulator.edp2.visualization.IVisualizationInput#supportsMultipleInputs
     * ()
     */
    @Override
    public boolean supportsMultipleInputs() {
        return true;
    }

    private void parseJScienceUnit(final String unit) {
        final ParsePosition pos = new ParsePosition(0);
        Unit parsedUnit = null;
        try {
            parsedUnit = UnitFormat.getInstance().parseSingleUnit(unit, pos);
        } catch (final ParseException e) {
            logger.log(Level.INFO, "Could not parse specified Unit!");
        }
        if (parsedUnit != null) {
            this.jscienceUnit = parsedUnit;
            setUnit(parsedUnit.toString());
        }
    }

    public String getUnitAsString() {
        if (this.unit.equals(NO_UNIT) && getSource() != null) {
            this.unit = getUnit().toString();
        }
        return unit;
    }

    public Unit getUnit() {
        if (getSource() != null) {
            if (properties.containsKey(UNIT_KEY) == false || properties.get(UNIT_KEY).equals(NO_UNIT)) {
                final MetricDescription metric = MetricDescriptionUtility
                        .toBaseMetricDescriptions(getSource()
                                .getMeasurementsRange().getMeasurements()
                                .getMeasure().getMetric())[0];
                parseJScienceUnit(new DefaultUnitSwitch(metric)
                .doSwitch(metric));
            }
        }
        return jscienceUnit;
    }

    public void setUnit(final String unit) {
        this.unit = unit;
        // if unit is changed, reset label to default but with new unit
        setDomainAxisLabel(getDefaultDomainAxisLabel());
    }

}
