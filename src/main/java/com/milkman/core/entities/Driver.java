package com.milkman.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.net.URI;
import java.util.UUID;

/**
 * {  :driver/installationId "add8fa33-b88b-419f-af63-b2f28f38b54d",
 *    :driver/deviceId "devId-7516-3",
 *    :driver/name "Bruce Wayne",
 *    :driver/firstName "Bruce",
 *    :fleet/fleetRefId #uuid "601c130a-6ce0-4152-beb0-44359118de01",
 *    :driver/isActive true,
 *    :driver/email "601c130a-c485-459d-9223-f5c39028945c@milk-test.com",
 *    :driver/lastName "Wayne",
 *    :driver/imageHappy #object[java.net.URI 0x27bc47e2 "https://cdn.integration.milkmantechnologies.com/files/1620/1620201/1620201676-driver.jpg"],
 *    :driver/password "12345678",
 *    :db/id 281474978733558,
 *    :driver/territoryId 1,
 *    :driver/code "89794",
 *    :driver/phone "18797868",
 *    :driver/imageSad #object[java.net.URI 0x6a17e486 "https://cdn.integration.milkmantechnologies.com/files/1620/1620201/1620201716-driver.jpg"]
 *  }
 */
@NoArgsConstructor @AllArgsConstructor
@Data @Accessors(fluent = true, chain = true)
@Builder
public class Driver {
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String code;
    private Long territoryId;
    private String installationId;
    private String deviceId;
    private Boolean isActive;
    private URI imageHappy;
    private URI imageSad;

    public interface Entity {
        String NAME = "driver";
    }

    public interface Attributes {
        String ID = "id";
        String NAME = "name";
        String FIRST_NAME = "firstName";
        String LAST_NAME = "lastName";
        String EMAIL = "email";
        String PASSWORD = "password";
        String PHONE = "phone";
        String CODE = "code";
        String TERRITORY_ID = "territoryId";
        String INSTALLATION_ID = "installationId";
        String DEVICE_ID = "deviceId";
        String IS_ACTIVE = "isActive";
        String IMAGE_HAPPY = "imageHappy";
        String IMAGE_SAD = "imageSad";
    }
}
