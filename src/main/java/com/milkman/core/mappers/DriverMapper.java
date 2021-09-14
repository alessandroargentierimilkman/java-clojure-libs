package com.milkman.core.mappers;

import clojure.lang.Keyword;
import com.milkman.core.Constants;
import com.milkman.core.Utils;
import com.milkman.core.entities.Driver;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is meant to provide some utilities to convert a Clojure Driver Object into a Java Driver Object.
 * Keeping this logic separated from the Driver itself allows to throw it away as soon as the whole project is developed in Java.
 */
public class DriverMapper {

    public static Driver toJavaObject(final Map<Keyword, Object> driverMap) {

       if(driverMap == null || driverMap.isEmpty()) {
           return null;
       }

       Map<String, Object> map = Utils.unkeywordizeMap(driverMap);

       return Driver.builder()
               .id((Long) map.get(Driver.Attributes.ID))
               .name((String) map.get(Driver.Attributes.NAME))
               .firstName((String) map.get(Driver.Attributes.FIRST_NAME))
               .lastName((String) map.get(Driver.Attributes.LAST_NAME))
               .email((String) map.get(Driver.Attributes.EMAIL))
               .password((String) map.get(Driver.Attributes.PASSWORD))
               .phone((String) map.get(Driver.Attributes.PHONE))
               .code((String) map.get(Driver.Attributes.CODE))
               .territoryId((Long) map.get(Driver.Attributes.TERRITORY_ID))
               .installationId((String) map.get(Driver.Attributes.INSTALLATION_ID))
               .deviceId((String) map.get(Driver.Attributes.DEVICE_ID))
               .isActive((Boolean) map.get(Driver.Attributes.IS_ACTIVE))
               .imageHappy((URI) map.get(Driver.Attributes.IMAGE_HAPPY))
               .imageSad((URI) map.get(Driver.Attributes.IMAGE_SAD))
               .build();
    }

    public static Map<Keyword, Object> toClojureMap(final Driver driver) {

        if (driver == null) {
            return null;
        }

        Map<Keyword, Object> driverMap = new HashMap<>();

        try {
            driverMap.put(Keyword.intern(Constants.DB, Driver.Attributes.ID), driver.id());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.NAME), driver.name());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.FIRST_NAME), driver.firstName());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.LAST_NAME), driver.lastName());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.EMAIL), driver.email());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.PASSWORD), driver.password());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.PHONE), driver.phone());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.CODE), driver.code());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.TERRITORY_ID), driver.territoryId());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.INSTALLATION_ID), driver.installationId());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.DEVICE_ID), driver.deviceId());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.IS_ACTIVE), driver.isActive());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.IMAGE_HAPPY), driver.imageHappy());
            driverMap.put(Keyword.intern(Driver.Entity.NAME, Driver.Attributes.IMAGE_SAD), driver.imageSad());
            // let's remove null values because Datomic doesn't accept them
            driverMap.values().removeAll(Collections.singleton(null));
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + e.getCause());
        }

        return driverMap;

    }


}
