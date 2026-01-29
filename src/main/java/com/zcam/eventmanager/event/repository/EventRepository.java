package com.zcam.eventmanager.event.repository;

import com.zcam.eventmanager.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = """
        SELECT EXISTS (
            SELECT 1
            FROM event e
            JOIN place p ON p.id = e.place_id
            WHERE p.code = :placeCode
              AND e.starts_at < :finishesAt
              AND e.finishes_at > :startsAt
        )
        """, nativeQuery = true)
    boolean existsPlaceConflict(
            @Param("placeCode") String placeCode,
            @Param("startsAt") LocalDateTime startsAt,
            @Param("finishesAt") LocalDateTime finishesAt
    );

    @Query(value = """
        SELECT EXISTS (
            SELECT 1
            FROM event e
            JOIN place p ON p.id = e.place_id
            WHERE p.code = :placeCode
              AND e.id <> :eventId
              AND e.starts_at < :finishesAt
              AND e.finishes_at > :startsAt
        )
        """, nativeQuery = true)
    boolean existsPlaceConflictExcludingEvent(
            @Param("placeCode") String placeCode,
            @Param("eventId") long eventId,
            @Param("startsAt") LocalDateTime startsAt,
            @Param("finishesAt") LocalDateTime finishesAt
    );
}
