package com.milkman.core.repositories;

import com.milkman.core.dtos.DriverDto;
import com.milkman.core.entities.Driver;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface DriverRepo {
    Driver pullDriverById(final Long id);
    List<DriverDto> fetchDriverInfoByTerritoryId(final Long territoryId);
    Driver addDriver(Driver driver) throws ExecutionException, InterruptedException;
}
