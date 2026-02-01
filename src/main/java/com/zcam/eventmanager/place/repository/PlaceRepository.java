package com.zcam.eventmanager.place.repository;

import com.zcam.eventmanager.place.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, long id);

    Optional<Place> findByCode(String code);

    @Query(value = """
        SELECT *
        FROM place p
        WHERE NOT EXISTS (
            SELECT 1
            FROM event e
            WHERE e.place_id = p.id
              AND e.starts_at < :finishesAt
              AND e.finishes_at > :startsAt
        );
    """, nativeQuery = true)
    List<Place> getAvailablePlacesIn (LocalDateTime startsAt, LocalDateTime finishesAt);
}
