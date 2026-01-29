package com.zcam.eventmanager.place.repository;

import com.zcam.eventmanager.place.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, long id);
}
