/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.edp2.models.ExperimentData;

import org.palladiosimulator.metricspec.AggregationFunctionDescription;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Aggregated Measurements</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements#getMeasurementRange
 * <em>Measurement Range</em>}</li>
 * <li>{@link org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements#isValid <em>
 * Valid</em>}</li>
 * <li>{@link org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements#getFunction
 * <em>Function</em>}</li>
 * <li>
 * {@link org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements#getAggregationStatistics
 * <em>Aggregation Statistics</em>}</li>
 * <li>
 * {@link org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements#getAggregationOn
 * <em>Aggregation On</em>}</li>
 * <li>{@link org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements#getMetric <em>
 * Metric</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage#getAggregatedMeasurements()
 * @model abstract="true"
 * @generated
 */
public interface AggregatedMeasurements extends Identifier {

    /**
     * Returns the value of the '<em><b>Measurement Range</b></em>' container reference. It is
     * bidirectional and its opposite is '
     * {@link org.palladiosimulator.edp2.models.ExperimentData.MeasurementRange#getAggregatedMeasurements
     * <em>Aggregated Measurements</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Measurement Range</em>' container reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Measurement Range</em>' container reference.
     * @see #setMeasurementRange(MeasurementRange)
     * @see org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage#getAggregatedMeasurements_MeasurementRange()
     * @see org.palladiosimulator.edp2.models.ExperimentData.MeasurementRange#getAggregatedMeasurements
     * @model opposite="aggregatedMeasurements" required="true" transient="false" ordered="false"
     * @generated
     */
    MeasurementRange getMeasurementRange();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements#getMeasurementRange
     * <em>Measurement Range</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Measurement Range</em>' container reference.
     * @see #getMeasurementRange()
     * @generated
     */
    void setMeasurementRange(MeasurementRange value);

    /**
     * Returns the value of the '<em><b>Valid</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Marker if the aggregated values are valid or if
     * they are not consistent with the raw measurements. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Valid</em>' attribute.
     * @see #setValid(boolean)
     * @see org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage#getAggregatedMeasurements_Valid()
     * @model required="true" ordered="false"
     * @generated
     */
    boolean isValid();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements#isValid
     * <em>Valid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Valid</em>' attribute.
     * @see #isValid()
     * @generated
     */
    void setValid(boolean value);

    /**
     * Returns the value of the '<em><b>Function</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Function</em>' reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Function</em>' reference.
     * @see #setFunction(AggregationFunctionDescription)
     * @see org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage#getAggregatedMeasurements_Function()
     * @model required="true" ordered="false"
     * @generated
     */
    AggregationFunctionDescription getFunction();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements#getFunction
     * <em>Function</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Function</em>' reference.
     * @see #getFunction()
     * @generated
     */
    void setFunction(AggregationFunctionDescription value);

    /**
     * Returns the value of the '<em><b>Aggregation Statistics</b></em>' containment reference. It
     * is bidirectional and its opposite is '
     * {@link org.palladiosimulator.edp2.models.ExperimentData.AggregationStatistics#getAggregatedMeasurements
     * <em>Aggregated Measurements</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Aggregation Statistics</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Aggregation Statistics</em>' containment reference.
     * @see #setAggregationStatistics(AggregationStatistics)
     * @see org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage#getAggregatedMeasurements_AggregationStatistics()
     * @see org.palladiosimulator.edp2.models.ExperimentData.AggregationStatistics#getAggregatedMeasurements
     * @model opposite="aggregatedMeasurements" containment="true" ordered="false"
     * @generated
     */
    AggregationStatistics getAggregationStatistics();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements#getAggregationStatistics
     * <em>Aggregation Statistics</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Aggregation Statistics</em>' containment reference.
     * @see #getAggregationStatistics()
     * @generated
     */
    void setAggregationStatistics(AggregationStatistics value);

    /**
     * Returns the value of the '<em><b>Aggregation On</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> Points to the base metric within the measure
     * definition associated with the measurement of an AggregatedMeasurements instance. The data is
     * aggregated on this base metric. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Aggregation On</em>' reference.
     * @see #setAggregationOn(BaseMetricDescription)
     * @see org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage#getAggregatedMeasurements_AggregationOn()
     * @model required="true" ordered="false"
     * @generated
     */
    BaseMetricDescription getAggregationOn();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements#getAggregationOn
     * <em>Aggregation On</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Aggregation On</em>' reference.
     * @see #getAggregationOn()
     * @generated
     */
    void setAggregationOn(BaseMetricDescription value);

    /**
     * Returns the value of the '<em><b>Metric</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Description of the metric used to express the
     * aggregated data. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Metric</em>' reference.
     * @see #setMetric(MetricDescription)
     * @see org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage#getAggregatedMeasurements_Metric()
     * @model required="true" ordered="false"
     * @generated
     */
    MetricDescription getMetric();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements#getMetric
     * <em>Metric</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Metric</em>' reference.
     * @see #getMetric()
     * @generated
     */
    void setMetric(MetricDescription value);

} // AggregatedMeasurements
