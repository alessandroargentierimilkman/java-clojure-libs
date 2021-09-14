package com.milkman.core.services;

import java.util.*;

public class BestFitService implements IBestFit {

    @Override
    public List<?> findDuplicates(List<?> list) {
        Set<Object> result = new LinkedHashSet<>();
        Set<Object> distinct = new HashSet<>();
        for (Object o : list) {
            if (!distinct.add(o)) {
                result.add(o);
            }
        }
        return new ArrayList<>(result);
    }

}
