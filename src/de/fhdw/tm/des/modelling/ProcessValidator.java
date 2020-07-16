package de.fhdw.tm.des.modelling;

import java.lang.reflect.Method;
import java.util.HashMap;

public class ProcessValidator {
    public static void validate(HashMap<Integer, Method> steps, HashMap<Integer, Method> delays) throws ModelException{
        if (steps.isEmpty()) {
            throw new ModelException("Process must have at least one step");
        }
        if (delays.isEmpty()) {
            throw new ModelException("Process must have at least one delay");
        }
        if (steps.size() != delays.size()) {
            throw new ModelException("Number of Process Steps must be equal to number of Delays");
        }
        for (Integer i : steps.keySet()) {
            if (!delays.containsKey(i)) {
                throw new ModelException("Numeration of Process Steps must be equal to numeration of Delays");
            }
        }

        validateStepMethods(steps);
        validateDelayMethods(delays);
    }

    private static void validateStepMethods(HashMap<Integer, Method> steps) throws ModelException {
        for (Method step: steps.values()) {
            if (!step.getReturnType().equals(void.class)) {
                throw new ModelException("Return Type of Step must be void");
            }
            if (step.getParameterCount() != 0) {
                throw new ModelException("Step may not have any Parameters");
            }
        }
    }

    private static void validateDelayMethods(HashMap<Integer, Method> delays) throws ModelException {
        for (Method delay : delays.values()) {
            if (!delay.getReturnType().equals(long.class)) {
                throw new ModelException("Return Type of Delay must be void");
            }
            if (delay.getParameterCount() != 0) {
                throw new ModelException("Delay may not have any Parameters");
            }
        }
    }
}
