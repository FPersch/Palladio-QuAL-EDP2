/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.edp2.models.ExperimentData;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Propertyable</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.edp2.models.ExperimentData.Propertyable#getAdditionalInformation
 * <em>Additional Information</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage#getPropertyable()
 * @model abstract="true"
 * @extends CDOObject
 * @generated
 */
public interface Propertyable extends CDOObject {

    /**
     * Returns the value of the '<em><b>Additional Information</b></em>' map. The key is of type
     * {@link java.lang.String}, and the value is of type {@link java.lang.Object}, <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Additional Information</em>' map isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Additional Information</em>' map.
     * @see org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage#getPropertyable_AdditionalInformation()
     * @model mapType=
     *        "org.palladiosimulator.edp2.models.ExperimentData.EStringtoEObjectMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EJavaObject>"
     * @generated
     */
    EMap<String, Object> getAdditionalInformation();

} // Propertyable
