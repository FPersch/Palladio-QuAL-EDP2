/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.edp2.models.Repository;

import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.edp2.dao.MetaDao;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroup;
import org.palladiosimulator.metricspec.Description;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Repository</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <p>
 * Unit of containment/storage for descriptions (metric and aggregation function) and experiment
 * groups. Depending on the actual type of storage where the data is stored different subclasses of
 * this class must be used to access the data itself.
 * </p>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.palladiosimulator.edp2.models.Repository.Repository#getRepositories <em>
 * Repositories</em>}</li>
 * <li>{@link org.palladiosimulator.edp2.models.Repository.Repository#getExperimentGroups <em>
 * Experiment Groups</em>}</li>
 * <li>{@link org.palladiosimulator.edp2.models.Repository.Repository#isReadOnly <em>Read Only</em>}
 * </li>
 * <li>{@link org.palladiosimulator.edp2.models.Repository.Repository#getDescriptions <em>
 * Descriptions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.edp2.models.Repository.RepositoryPackage#getRepository()
 * @model abstract="true" superTypes=
 *        "de.uka.ipd.sdq.identifier.Identifier org.palladiosimulator.edp2.models.Repository.MetaDao"
 * @generated
 */
public interface Repository extends Identifier, MetaDao {

    /**
     * Returns the value of the '<em><b>Repositories</b></em>' container reference. It is
     * bidirectional and its opposite is '
     * {@link org.palladiosimulator.edp2.models.Repository.Repositories#getAvailableRepositories
     * <em>Available Repositories</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Repositories</em>' container reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Repositories</em>' container reference.
     * @see #setRepositories(Repositories)
     * @see org.palladiosimulator.edp2.models.Repository.RepositoryPackage#getRepository_Repositories()
     * @see org.palladiosimulator.edp2.models.Repository.Repositories#getAvailableRepositories
     * @model opposite="availableRepositories" required="true" transient="false" ordered="false"
     * @generated
     */
    Repositories getRepositories();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.edp2.models.Repository.Repository#getRepositories
     * <em>Repositories</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Repositories</em>' container reference.
     * @see #getRepositories()
     * @generated
     */
    void setRepositories(Repositories value);

    /**
     * Returns the value of the '<em><b>Read Only</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Read Only</em>' attribute isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Read Only</em>' attribute.
     * @see #setReadOnly(boolean)
     * @see org.palladiosimulator.edp2.models.Repository.RepositoryPackage#getRepository_ReadOnly()
     * @model required="true" ordered="false"
     * @generated
     */
    boolean isReadOnly();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.edp2.models.Repository.Repository#isReadOnly <em>Read Only</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Read Only</em>' attribute.
     * @see #isReadOnly()
     * @generated
     */
    void setReadOnly(boolean value);

    /**
     * Returns the value of the '<em><b>Descriptions</b></em>' reference list. The list contents are
     * of type {@link org.palladiosimulator.metricspec.Description}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Descriptions</em>' reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Descriptions</em>' reference list.
     * @see org.palladiosimulator.edp2.models.Repository.RepositoryPackage#getRepository_Descriptions()
     * @model transient="true" ordered="false"
     * @generated
     */
    EList<Description> getDescriptions();

    /**
     * Returns the value of the '<em><b>Experiment Groups</b></em>' reference list. The list
     * contents are of type {@link org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroup}
     * . It is bidirectional and its opposite is '
     * {@link org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroup#getRepository
     * <em>Repository</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Experiment Groups</em>' reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Experiment Groups</em>' reference list.
     * @see org.palladiosimulator.edp2.models.Repository.RepositoryPackage#getRepository_ExperimentGroups()
     * @see org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroup#getRepository
     * @model opposite="repository" transient="true" ordered="false"
     * @generated
     */
    EList<ExperimentGroup> getExperimentGroups();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model
     * @generated
     */
    void resetExperimentGroups();

} // Repository
