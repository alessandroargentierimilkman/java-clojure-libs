package com.milkman.core.factories;

import com.milkman.core.services.BestFitService;
import com.milkman.core.services.IBestFit;

/**
 * Factory classes will ease the introduction of dependency injection in the future
 */
public class BestFitFactory {

    private static final BestFitService bestFitService = new BestFitService();

    public static IBestFit iBestFit() {
        return bestFitService;
    }

}
