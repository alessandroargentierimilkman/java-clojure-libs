package com.milkman.core.factories;

import com.milkman.core.repositories.DriverRepo;
import com.milkman.core.repositories.DriverRepoImpl;
import datomic.Connection;

/**
 * This factory class is meant to always provide the same instance class of the DriverRepo.
 * Avoiding to implement the singleton pattern directly into the Repo class allows to throw this factory class away and annotate the Repo
 * with @Repository JPA annotation to let Spring Framework (for example) instantiate for us, as soon as the whole project is made in Java.
 */
public class DriverRepoFactory {

    private static DriverRepo driverRepo;

    public static DriverRepo inject() {
        if (driverRepo == null) {
            driverRepo = new DriverRepoImpl();
        }
        return driverRepo;
    }

    public static DriverRepo inject(final Connection conn) {
        if (driverRepo == null) {
            driverRepo = new DriverRepoImpl(conn);
        }
        return driverRepo;
    }
}
