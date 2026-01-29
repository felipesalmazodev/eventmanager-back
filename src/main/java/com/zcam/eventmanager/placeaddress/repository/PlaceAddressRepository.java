package com.zcam.eventmanager.placeaddress.repository;

import com.zcam.eventmanager.placeaddress.document.PlaceAddress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceAddressRepository extends MongoRepository<PlaceAddress, String> {
    Optional<PlaceAddress> findByCep(String normalizedCep);
}
