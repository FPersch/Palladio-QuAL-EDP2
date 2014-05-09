/**
 */
package org.palladiosimulator.edp2.models.measuringpoint;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.palladiosimulator.edp2.models.measuringpoint.SystemReference#getSystem <em>System</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointPackage#getSystemReference()
 * @model abstract="true"
 * @generated
 */
public interface SystemReference extends EObject {
    /**
     * Returns the value of the '<em><b>System</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>System</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>System</em>' reference.
     * @see #setSystem(de.uka.ipd.sdq.pcm.system.System)
     * @see org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointPackage#getSystemReference_System()
     * @model required="true"
     * @generated
     */
    de.uka.ipd.sdq.pcm.system.System getSystem();

    /**
     * Sets the value of the '{@link org.palladiosimulator.edp2.models.measuringpoint.SystemReference#getSystem <em>System</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>System</em>' reference.
     * @see #getSystem()
     * @generated
     */
    void setSystem(de.uka.ipd.sdq.pcm.system.System value);

} // SystemReference
