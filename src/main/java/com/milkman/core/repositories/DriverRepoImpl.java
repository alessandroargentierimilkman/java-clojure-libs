package com.milkman.core.repositories;

import clojure.lang.Keyword;
import clojure.lang.PersistentVector;
import com.milkman.core.dtos.DriverDto;
import com.milkman.core.entities.Driver;

import com.milkman.core.mappers.DriverMapper;
import datomic.*;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static datomic.Connection.TEMPIDS;
import static datomic.Peer.tempid;
import static java.lang.System.getenv;

public class DriverRepoImpl implements DriverRepo {

    private static final String FETCH_DRIVER_INFO_QUERY = "[:find  ?e ?firstName ?lastName ?email " +
                                                          " :in    $ ?territoryId " +
                                                          " :where [?e :driver/territoryId ?territoryId] " +
                                                          "        [?e :driver/firstName ?firstName] " +
                                                          "        [?e :driver/lastName ?lastName]" +
                                                          "        [?e :driver/email ?email]]";

    private static final Map<String, Integer> DRIVER_FETCH_POSIX_MAP;
    static {
        DRIVER_FETCH_POSIX_MAP = new HashMap<>();
        DRIVER_FETCH_POSIX_MAP.put(Driver.Attributes.ID, 0);
        DRIVER_FETCH_POSIX_MAP.put(Driver.Attributes.FIRST_NAME, 1);
        DRIVER_FETCH_POSIX_MAP.put(Driver.Attributes.LAST_NAME, 2);
        DRIVER_FETCH_POSIX_MAP.put(Driver.Attributes.EMAIL, 3);
    }

    private static final List<DriverDto> NO_FETCHED_DRIVER_INFO = new ArrayList<>();

    private Connection conn;

    /* You can pass an url which uses free protocol: when connection is not passed from outside */
    public DriverRepoImpl() {
        // Connect to transactor, get latest database value from storage.
        conn = Peer.connect(getenv("DB_URI"));
    }

    /* Uses the connection and the protocol passed from the caller */
    public DriverRepoImpl(final Connection conn) {
        this.conn = conn;
    }

    @Override
    public Driver pullDriverById(final Long id) {
        Map<Keyword, Object>  driverMap = conn.db().pull("[*]", id);
        return DriverMapper.toJavaObject(driverMap);
    }

    @Override
    public List<DriverDto> fetchDriverInfoByTerritoryId(final Long territoryId) {

        Collection<PersistentVector> results = Peer.query(FETCH_DRIVER_INFO_QUERY, conn.db(), territoryId);

        return results.isEmpty()
                  ? NO_FETCHED_DRIVER_INFO
                  : results.stream()
                           .map(this::convertFetchResult)
                           .collect(Collectors.toList());
    }

    private DriverDto convertFetchResult(final PersistentVector driverInfo) {
        return new DriverDto()
                .id((Long) driverInfo.get( DRIVER_FETCH_POSIX_MAP.get(Driver.Attributes.ID)))
                .firstName((String) driverInfo.get( DRIVER_FETCH_POSIX_MAP.get(Driver.Attributes.FIRST_NAME)))
                .lastName((String) driverInfo.get( DRIVER_FETCH_POSIX_MAP.get(Driver.Attributes.LAST_NAME)))
                .email((String) driverInfo.get( DRIVER_FETCH_POSIX_MAP.get(Driver.Attributes.EMAIL)));
    }

    @Override
    public Driver addDriver(Driver driver) throws ExecutionException, InterruptedException {
        // prepare the transaction
        List<Map<Keyword, Object>> tx = Arrays.asList(DriverMapper.toClojureMap(driver));
        // add the tempid to the transaction object
        Object driverTempId = tempid(Keyword.intern("milk", "data"));
        tx.get(0).put(Keyword.intern("db", "id"), driverTempId);
        // transact and get the result
        Map<Keyword, Object>  txResult = conn.transact(tx).get();
        // return the driver after having resolved the :db/id
        return driver.id(resolveTempId(txResult, driverTempId));
    }

    private Long resolveTempId(final Map<Keyword, Object> txResult, final Object driverTempId) {
       return (Long) Peer.resolveTempid(conn.db(), txResult.get(TEMPIDS),  driverTempId);
    }

}
