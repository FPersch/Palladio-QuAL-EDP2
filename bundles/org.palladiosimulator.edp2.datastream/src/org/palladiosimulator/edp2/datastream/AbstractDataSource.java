package org.palladiosimulator.edp2.datastream;

import java.util.Map;
import java.util.Set;

import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.edp2.datastream.configurable.IPropertyConfigurable;
import org.palladiosimulator.edp2.datastream.configurable.IPropertyListener;
import org.palladiosimulator.edp2.datastream.configurable.PropertyConfigurable;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.metricentity.MetricEntity;

public abstract class AbstractDataSource extends MetricEntity implements IDataSource, IPropertyConfigurable {

    private final PropertyConfigurable myProperties;
    protected final AbstractObservable<IDataSourceListener> datasourceChangedListener = new AbstractObservable<IDataSourceListener>() {
    };

    public AbstractDataSource() {
        super();
        myProperties = setupProperties();
    }

    public AbstractDataSource(final MetricDescription metricDescription) {
        super(metricDescription);
        myProperties = setupProperties();
    }

    protected abstract PropertyConfigurable createProperties();

    /**
     * @return
     *
     */
    private PropertyConfigurable setupProperties() {
        final PropertyConfigurable myProperties = createProperties();
        myProperties.addObserver(new IPropertyListener() {

            @Override
            public void propertyChanged(final String key, final Object oldValue, final Object newValue) {
            }

            @Override
            public void propertyChangeCompleted() {
                AbstractDataSource.this.datasourceChangedListener.getEventDispatcher().datasourceUpdated();
            }
        });

        return myProperties;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.palladiosimulator.edp2.datastream.IDataSource#getConfiguration()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <G extends IPropertyConfigurable> G getConfiguration() {
        return (G) myProperties;
    }

    /**
     * @return
     * @see org.palladiosimulator.edp2.datastream.configurable.PropertyConfigurable#getDefaultConfiguration()
     */
    @Override
    public Map<? extends String, ? extends Object> getDefaultConfiguration() {
        return myProperties.getDefaultConfiguration();
    }

    /**
     * @return
     * @see org.palladiosimulator.edp2.datastream.configurable.PropertyConfigurable#getKeys()
     */
    @Override
    public Set<String> getKeys() {
        return myProperties.getKeys();
    }

    /**
     * @return
     * @see org.palladiosimulator.edp2.datastream.configurable.PropertyConfigurable#getProperties()
     */
    @Override
    public Map<String, Object> getProperties() {
        return myProperties.getProperties();
    }

    /**
     * @param newProperties
     * @see org.palladiosimulator.edp2.datastream.configurable.PropertyConfigurable#setProperties(java.util.Map)
     */
    @Override
    public void setProperties(final Map<String, Object> newProperties) {
        myProperties.setProperties(newProperties);
    }

    /**
     * @param adapter
     * @return
     * @see org.palladiosimulator.edp2.datastream.configurable.PropertyConfigurable#getAdapter(java.lang.Class)
     */
    @Override
    @SuppressWarnings("rawtypes")
    public Object getAdapter(final Class adapter) {
        if (adapter.isInstance(this)) {
            return this;
        }
        return myProperties.getAdapter(adapter);
    }

    /**
     * @param observer
     * @see org.palladiosimulator.commons.designpatterns.AbstractObservable#addObserver(java.lang.Object)
     */
    @Override
    public void addObserver(final IDataSourceListener observer) {
        datasourceChangedListener.addObserver(observer);
    }

    /**
     * @param observer
     * @see org.palladiosimulator.commons.designpatterns.AbstractObservable#removeObserver(java.lang.Object)
     */
    @Override
    public void removeObserver(final IDataSourceListener observer) {
        datasourceChangedListener.removeObserver(observer);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.palladiosimulator.edp2.datastream.configurable.IPropertyConfigurable#getPropertyType(
     * java.lang.String)
     */
    @Override
    public Class<?> getPropertyType(final String key) {
        return myProperties.getPropertyType(key);
    }

    /**
     * @param key
     * @return
     * @see org.palladiosimulator.edp2.datastream.configurable.PropertyConfigurable#isPropertyNotSet(java.lang.String)
     */
    @Override
    public boolean isPropertyNotSet(final String key) {
        return myProperties.isPropertyNotSet(key);
    }

    /**
     * @param key
     * @see org.palladiosimulator.edp2.datastream.configurable.PropertyConfigurable#unsetProperty(java.lang.String)
     */
    @Override
    public void unsetProperty(final String key) {
        myProperties.unsetProperty(key);
    }

}
