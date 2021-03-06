package de.fhdw.tm.des.modelling;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ModelProcessExecutor {

	private Method operation;
	private Method delay;

	public ModelProcessExecutor(Method operation, Method delay) {
		this.operation = operation;
		this.delay = delay;
	}
	
	public void process(Object process) {
		try {
			operation.invoke(process);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ModelException("Invalid process operation methode signatur: " + operation.toGenericString());
		}
	}

	public long getDelay(Object process) {
		try {
			long nextDelay = (long) delay.invoke(process);
			if (nextDelay < 0) {
				throw new ModelException("Delay must not be negative");
			}
			return nextDelay;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ModelException("Invalid process delay methode signatur: " + delay.toGenericString());
		}
	}
}
