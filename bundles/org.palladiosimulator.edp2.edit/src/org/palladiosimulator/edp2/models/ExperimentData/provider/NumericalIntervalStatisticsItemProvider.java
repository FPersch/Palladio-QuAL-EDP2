/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.edp2.models.ExperimentData.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage;
import org.palladiosimulator.edp2.models.ExperimentData.NumericalIntervalStatistics;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.edp2.models.ExperimentData.NumericalIntervalStatistics} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class NumericalIntervalStatisticsItemProvider extends NumericalOrdinalStatisticsItemProvider {

    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public NumericalIntervalStatisticsItemProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(final Object object) {
        if (this.itemPropertyDescriptors == null)
        {
            super.getPropertyDescriptors(object);

            this.addArithmethicMeanPropertyDescriptor(object);
            this.addVariancePropertyDescriptor(object);
            this.addStandardDeviationPropertyDescriptor(object);
            this.addSumPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Arithmethic Mean feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addArithmethicMeanPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
                (this.createItemPropertyDescriptor
                (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                        this.getResourceLocator(),
                        this.getString("_UI_NumericalIntervalStatistics_arithmethicMean_feature"),
                        this.getString("_UI_PropertyDescriptor_description",
                                "_UI_NumericalIntervalStatistics_arithmethicMean_feature",
                                "_UI_NumericalIntervalStatistics_type"),
                                ExperimentDataPackage.Literals.NUMERICAL_INTERVAL_STATISTICS__ARITHMETHIC_MEAN,
                                true,
                                false,
                                false,
                                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Variance feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected void addVariancePropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
                (this.createItemPropertyDescriptor
                (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                        this.getResourceLocator(),
                        this.getString("_UI_NumericalIntervalStatistics_variance_feature"),
                        this.getString("_UI_PropertyDescriptor_description",
                                "_UI_NumericalIntervalStatistics_variance_feature",
                                "_UI_NumericalIntervalStatistics_type"),
                                ExperimentDataPackage.Literals.NUMERICAL_INTERVAL_STATISTICS__VARIANCE,
                                true,
                                false,
                                false,
                                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Standard Deviation feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addStandardDeviationPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
                (this.createItemPropertyDescriptor
                (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                        this.getResourceLocator(),
                        this.getString("_UI_NumericalIntervalStatistics_standardDeviation_feature"),
                        this.getString("_UI_PropertyDescriptor_description",
                                "_UI_NumericalIntervalStatistics_standardDeviation_feature",
                                "_UI_NumericalIntervalStatistics_type"),
                                ExperimentDataPackage.Literals.NUMERICAL_INTERVAL_STATISTICS__STANDARD_DEVIATION,
                                true,
                                false,
                                false,
                                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Sum feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected void addSumPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
                (this.createItemPropertyDescriptor
                (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                        this.getResourceLocator(),
                        this.getString("_UI_NumericalIntervalStatistics_sum_feature"),
                        this.getString("_UI_PropertyDescriptor_description",
                                "_UI_NumericalIntervalStatistics_sum_feature",
                                "_UI_NumericalIntervalStatistics_type"),
                                ExperimentDataPackage.Literals.NUMERICAL_INTERVAL_STATISTICS__SUM,
                                true,
                                false,
                                false,
                                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                                null,
                                null));
    }

    /**
     * This returns NumericalIntervalStatistics.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/NumericalIntervalStatistics"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((NumericalIntervalStatistics) object).getId();
        return label == null || label.length() == 0 ?
                this.getString("_UI_NumericalIntervalStatistics_type") :
                this.getString("_UI_NumericalIntervalStatistics_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}
     * . <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void notifyChanged(final Notification notification) {
        this.updateChildren(notification);

        switch (notification.getFeatureID(NumericalIntervalStatistics.class))
        {
        case ExperimentDataPackage.NUMERICAL_INTERVAL_STATISTICS__ARITHMETHIC_MEAN:
        case ExperimentDataPackage.NUMERICAL_INTERVAL_STATISTICS__VARIANCE:
        case ExperimentDataPackage.NUMERICAL_INTERVAL_STATISTICS__STANDARD_DEVIATION:
        case ExperimentDataPackage.NUMERICAL_INTERVAL_STATISTICS__SUM:
            this.fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that
     * can be created under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(final Collection<Object> newChildDescriptors, final Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

}
