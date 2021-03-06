package org.palladiosimulator.edp2.visualization.jfreechart.input;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.palladiosimulator.edp2.datastream.configurable.reflective.ConfigurationProperty;
import org.palladiosimulator.edp2.visualization.AbstractVisualizationSingleDatastreamConfiguration;

public class JFreeChartVisualizationSingleDatastreamConfiguration extends
AbstractVisualizationSingleDatastreamConfiguration {

    /**
     * Keys used for persistence of properties.
     */
    public static final String COLOR_KEY = "color";

    /**
     * Color for this {@link JFreeChartVisualizationSingleDatastreamInput}'s data in the graph.
     */
    @ConfigurationProperty(description = "Color of the dataseries", isUnsetable=true)
    private Color color;

    public Color getColor() {
        if (isPropertyNotSet(COLOR_KEY)) {
            throw new IllegalStateException("Tried to get an unset Color");
        }
        return color;
    }

    @Override
    public Map<String, Object> getDefaultConfiguration() {
        final Map<String, Object> result = new HashMap<String, Object>(super.getDefaultConfiguration());
        result.put(COLOR_KEY, getNotSetConstant());
        return result;
    }
}
