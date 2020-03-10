package org.palladiosimulator.edp2.visualization;

import java.util.Map;

import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.metricspec.MetricDescription;

public interface IDerivedMetric {

	public Map<String, MetricDescription> getNeededMetrics();
	
	public void calculate(Map<String, Measurement> measurements);
	
	public String getName();
}
