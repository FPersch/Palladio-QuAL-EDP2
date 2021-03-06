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
import org.palladiosimulator.edp2.models.ExperimentData.NumericalRatioStatistics;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.edp2.models.ExperimentData.NumericalRatioStatistics} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class NumericalRatioStatisticsItemProvider extends NumericalIntervalStatisticsItemProvider {

    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public NumericalRatioStatisticsItemProvider(final AdapterFactory adapterFactory) {
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

            this.addCoefficientOfVariationPropertyDescriptor(object);
            this.addGeometricMeanPropertyDescriptor(object);
            this.addHarmonicMeanPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Coefficient Of Variation feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addCoefficientOfVariationPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
                (this.createItemPropertyDescriptor
                (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                        this.getResourceLocator(),
                        this.getString("_UI_NumericalRatioStatistics_coefficientOfVariation_feature"),
                        this.getString("_UI_PropertyDescriptor_description",
                                "_UI_NumericalRatioStatistics_coefficientOfVariation_feature",
                                "_UI_NumericalRatioStatistics_type"),
                                ExperimentDataPackage.Literals.NUMERICAL_RATIO_STATISTICS__COEFFICIENT_OF_VARIATION,
                                true,
                                false,
                                false,
                                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Geometric Mean feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected void addGeometricMeanPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
                (this.createItemPropertyDescriptor
                (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                        this.getResourceLocator(),
                        this.getString("_UI_NumericalRatioStatistics_geometricMean_feature"),
                        this.getString("_UI_PropertyDescriptor_description",
                                "_UI_NumericalRatioStatistics_geometricMean_feature",
                                "_UI_NumericalRatioStatistics_type"),
                                ExperimentDataPackage.Literals.NUMERICAL_RATIO_STATISTICS__GEOMETRIC_MEAN,
                                true,
                                false,
                                false,
                                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Harmonic Mean feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected void addHarmonicMeanPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
                (this.createItemPropertyDescriptor
                (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                        this.getResourceLocator(),
                        this.getString("_UI_NumericalRatioStatistics_harmonicMean_feature"),
                        this.getString("_UI_PropertyDescriptor_description",
                                "_UI_NumericalRatioStatistics_harmonicMean_feature",
                                "_UI_NumericalRatioStatistics_type"),
                                ExperimentDataPackage.Literals.NUMERICAL_RATIO_STATISTICS__HARMONIC_MEAN,
                                true,
                                false,
                                false,
                                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                                null,
                                null));
    }

    /**
     * This returns NumericalRatioStatistics.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/NumericalRatioStatistics"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((NumericalRatioStatistics) object).getId();
        return label == null || label.length() == 0 ?
                this.getString("_UI_NumericalRatioStatistics_type") :
                this.getString("_UI_NumericalRatioStatistics_type") + " " + label;
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

        switch (notification.getFeatureID(NumericalRatioStatistics.class))
        {
        case ExperimentDataPackage.NUMERICAL_RATIO_STATISTICS__COEFFICIENT_OF_VARIATION:
        case ExperimentDataPackage.NUMERICAL_RATIO_STATISTICS__GEOMETRIC_MEAN:
        case ExperimentDataPackage.NUMERICAL_RATIO_STATISTICS__HARMONIC_MEAN:
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
