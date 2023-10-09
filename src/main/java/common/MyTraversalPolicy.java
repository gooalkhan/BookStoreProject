package common;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Arrays;

public class MyTraversalPolicy extends FocusTraversalPolicy {
	private Component[] focusOrder;

	public MyTraversalPolicy(Component[] focusOrder) {
		this.focusOrder = focusOrder;
	}

	@Override
	public Component getComponentAfter(Container aContainer, Component aComponent) {
		// TODO Auto-generated method stub
		int index = (Arrays.asList(focusOrder).indexOf(aComponent)+1) % focusOrder.length;
		return focusOrder[index];
	}

	@Override
	public Component getComponentBefore(Container aContainer, Component aComponent) {
		// TODO Auto-generated method stub
		 int index = Arrays.asList(focusOrder).indexOf(aComponent) - 1;
            if (index < 0) {
                index = focusOrder.length - 1;
            }
            return focusOrder[index];
	}

	@Override
	public Component getFirstComponent(Container aContainer) {
		
		// TODO Auto-generated method stub
		return focusOrder[0];
	}

	@Override
	public Component getLastComponent(Container aContainer) {
		// TODO Auto-generated method stub
		return focusOrder[focusOrder.length-1];
	}

	@Override
	public Component getDefaultComponent(Container aContainer) {
		// TODO Auto-generated method stub
		return focusOrder[0];
	}


}
