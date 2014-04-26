/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.edp2.models.ExperimentData.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.edp2.models.ExperimentData.Descriptions;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage;
import org.palladiosimulator.metricspec.Description;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Descriptions</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.edp2.models.ExperimentData.impl.DescriptionsImpl#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DescriptionsImpl extends IdentifierImpl implements Descriptions {
	/**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
	protected EList<Description> description;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected DescriptionsImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ExperimentDataPackage.Literals.DESCRIPTIONS;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Description> getDescription() {
        if (description == null) {
            description = new EObjectContainmentEList<Description>(Description.class, this, ExperimentDataPackage.DESCRIPTIONS__DESCRIPTION);
        }
        return description;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ExperimentDataPackage.DESCRIPTIONS__DESCRIPTION:
                return ((InternalEList<?>)getDescription()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ExperimentDataPackage.DESCRIPTIONS__DESCRIPTION:
                return getDescription();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ExperimentDataPackage.DESCRIPTIONS__DESCRIPTION:
                getDescription().clear();
                getDescription().addAll((Collection<? extends Description>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eUnset(int featureID) {
        switch (featureID) {
            case ExperimentDataPackage.DESCRIPTIONS__DESCRIPTION:
                getDescription().clear();
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ExperimentDataPackage.DESCRIPTIONS__DESCRIPTION:
                return description != null && !description.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //DescriptionsImpl
