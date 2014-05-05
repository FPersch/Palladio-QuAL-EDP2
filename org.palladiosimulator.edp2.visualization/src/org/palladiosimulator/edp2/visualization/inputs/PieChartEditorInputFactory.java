package org.palladiosimulator.edp2.visualization.inputs;

import java.util.HashMap;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import org.palladiosimulator.edp2.datastream.AbstractDataSource;
import org.palladiosimulator.edp2.visualization.datasource.ElementFactory;

public class PieChartEditorInputFactory extends ElementFactory {
	
	@Override
	public IAdaptable createElement(IMemento memento) {
		PieChartEditorInput restoredElement = new PieChartEditorInput();
		
		HashMap<String, Object> restoredProperties = restoredElement.getProperties();
		memento = memento.getChild(restoredProperties.get(ELEMENT_KEY)
				.toString());
		//default properties are overridden with persisted properties from the memento
		overrideFromMemento(memento, restoredProperties);
		//properties are set for the restored element
		restoredElement.setProperties(restoredProperties);
		
		Object sourceFactory = FactoryConnector.instance.getAdapter(memento.getString(SOURCE_KEY),
				IElementFactory.class);
		
		AbstractDataSource createdSource = (AbstractDataSource) ((IElementFactory) sourceFactory)
		.createElement(memento);
		
		restoredElement.setSource(createdSource);
		
		return restoredElement;
	}

}
