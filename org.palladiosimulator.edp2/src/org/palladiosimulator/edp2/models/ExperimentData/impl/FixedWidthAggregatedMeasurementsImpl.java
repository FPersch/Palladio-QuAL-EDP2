/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.edp2.models.ExperimentData.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.edp2.models.ExperimentData.DataSeries;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage;
import org.palladiosimulator.edp2.models.ExperimentData.FixedIntervals;
import org.palladiosimulator.edp2.models.ExperimentData.FixedWidthAggregatedMeasurements;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Fixed Width Aggregated Measurements</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.edp2.models.ExperimentData.impl.FixedWidthAggregatedMeasurementsImpl#getIntervals
 * <em>Intervals</em>}</li>
 * <li>
 * {@link org.palladiosimulator.edp2.models.ExperimentData.impl.FixedWidthAggregatedMeasurementsImpl#getDataSeries
 * <em>Data Series</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FixedWidthAggregatedMeasurementsImpl extends AggregatedMeasurementsImpl implements
FixedWidthAggregatedMeasurements {

    /**
     * The cached value of the '{@link #getIntervals() <em>Intervals</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIntervals()
     * @generated
     * @ordered
     */
    protected FixedIntervals intervals;

    /**
     * The cached value of the '{@link #getDataSeries() <em>Data Series</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDataSeries()
     * @generated
     * @ordered
     */
    protected EList<DataSeries> dataSeries;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected FixedWidthAggregatedMeasurementsImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ExperimentDataPackage.Literals.FIXED_WIDTH_AGGREGATED_MEASUREMENTS;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public FixedIntervals getIntervals() {
        return this.intervals;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetIntervals(final FixedIntervals newIntervals, NotificationChain msgs) {
        final FixedIntervals oldIntervals = this.intervals;
        this.intervals = newIntervals;
        if (this.eNotificationRequired()) {
            final ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__INTERVALS, oldIntervals, newIntervals);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setIntervals(final FixedIntervals newIntervals) {
        if (newIntervals != this.intervals) {
            NotificationChain msgs = null;
            if (this.intervals != null) {
                msgs = ((InternalEObject) this.intervals).eInverseRemove(this,
                        ExperimentDataPackage.FIXED_INTERVALS__AGGREGATED_MEASUREMENTS, FixedIntervals.class, msgs);
            }
            if (newIntervals != null) {
                msgs = ((InternalEObject) newIntervals).eInverseAdd(this,
                        ExperimentDataPackage.FIXED_INTERVALS__AGGREGATED_MEASUREMENTS, FixedIntervals.class, msgs);
            }
            msgs = this.basicSetIntervals(newIntervals, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__INTERVALS, newIntervals, newIntervals));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DataSeries> getDataSeries() {
        if (this.dataSeries == null) {
            this.dataSeries = new EObjectContainmentWithInverseEList<DataSeries>(DataSeries.class, this,
                    ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__DATA_SERIES,
                    ExperimentDataPackage.DATA_SERIES__AGGREGATED_MEASUREMENTS);
        }
        return this.dataSeries;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__INTERVALS:
            if (this.intervals != null) {
                msgs = ((InternalEObject) this.intervals).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__INTERVALS, null, msgs);
            }
            return this.basicSetIntervals((FixedIntervals) otherEnd, msgs);
        case ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__DATA_SERIES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getDataSeries()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__INTERVALS:
            return this.basicSetIntervals(null, msgs);
        case ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__DATA_SERIES:
            return ((InternalEList<?>) this.getDataSeries()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__INTERVALS:
            return this.getIntervals();
        case ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__DATA_SERIES:
            return this.getDataSeries();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__INTERVALS:
            this.setIntervals((FixedIntervals) newValue);
            return;
        case ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__DATA_SERIES:
            this.getDataSeries().clear();
            this.getDataSeries().addAll((Collection<? extends DataSeries>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__INTERVALS:
            this.setIntervals((FixedIntervals) null);
            return;
        case ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__DATA_SERIES:
            this.getDataSeries().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__INTERVALS:
            return this.intervals != null;
        case ExperimentDataPackage.FIXED_WIDTH_AGGREGATED_MEASUREMENTS__DATA_SERIES:
            return this.dataSeries != null && !this.dataSeries.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // FixedWidthAggregatedMeasurementsImpl
