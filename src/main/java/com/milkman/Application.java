package com.milkman;

import com.milkman.core.dtos.DriverDto;
import com.milkman.core.entities.Driver;
import com.milkman.core.factories.DriverRepoFactory;
import com.milkman.core.repositories.DriverRepo;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Application {

   public static void main(String args[]) throws ExecutionException, InterruptedException {

        DriverRepo driverRepo = DriverRepoFactory.inject();

        Driver driver = new Driver()
                .name("Juan Quan")
                .firstName("Juan")
                .lastName("Quan")
                .email("juan.quan@milkmanmail.com")
                .password("ABC1234")
                .phone("333333333333")
                .code("JUAN_QUAN_CODE")
                .deviceId("DEVICE_ID")
                .territoryId(1L)
                .installationId("INSTALLATION_ID")
                .isActive(true)
                .imageHappy(URI.create("https://via.placeholder.com/150"))
                .imageSad(URI.create("https://via.placeholder.com/150"));

        Driver saved = driverRepo.addDriver(driver);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Driver added correctly: " + saved);
        System.out.println();



        Driver pulled = driverRepo.pullDriverById(281474978235426L);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Driver pulled correctly: " + pulled);
        System.out.println();

        List<DriverDto> fetched = driverRepo.fetchDriverInfoByTerritoryId(1L);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Fetched Driver info for territoryId = 1");
        System.out.println();
        fetched.forEach(System.out::println);
    }
}
