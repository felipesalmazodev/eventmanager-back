package com.zcam.eventmanager.event.model;

import com.zcam.eventmanager.place.model.Place;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private LocalDateTime startsAt;

    @NotNull
    private LocalDateTime finishesAt;

    @ManyToOne
    private Place place;

    private String description;
    private LocalDateTime updatedAt;

    private final LocalDateTime createdAt = LocalDateTime.now();

    public Event(String name, LocalDateTime startsAt, LocalDateTime finishesAt, Place place, String description) {
        this.name = name;
        this.startsAt = startsAt;
        this.finishesAt = finishesAt;
        this.place = place;
        this.description = description;
    }

    @Deprecated
    public Event() {
    }

    public void update(String name, LocalDateTime startsAt, LocalDateTime finishesAt, String description, Place place) {
        this.name = name;
        this.startsAt = startsAt;
        this.finishesAt = finishesAt;
        this.description = description;
        this.updatedAt = LocalDateTime.now();
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public LocalDateTime getFinishesAt() {
        return finishesAt;
    }

    public Place getPlace() {
        return place;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
