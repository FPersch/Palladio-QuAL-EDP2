/**
 */
package org.palladiosimulator.edp2.models.measuringpoint;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assembly Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.palladiosimulator.edp2.models.measuringpoint.AssemblyReference#getAssembly <em>Assembly</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointPackage#getAssemblyReference()
 * @model abstract="true"
 * @generated
 */
public interface AssemblyReference extends EObject {
    /**
     * Returns the value of the '<em><b>Assembly</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Assembly</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Assembly</em>' reference.
     * @see #setAssembly(AssemblyContext)
     * @see org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointPackage#getAssemblyReference_Assembly()
     * @model required="true"
     * @generated
     */
    AssemblyContext getAssembly();

    /**
     * Sets the value of the '{@link org.palladiosimulator.edp2.models.measuringpoint.AssemblyReference#getAssembly <em>Assembly</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Assembly</em>' reference.
     * @see #getAssembly()
     * @generated
     */
    void setAssembly(AssemblyContext value);

} // AssemblyReference
