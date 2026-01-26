package com.zcam.eventmanager.place.model;

import jakarta.persistence.*;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String code;
    private int capacity;
    private boolean available;

    public Place(String name, String code, int capacity) {
        this.name = name;
        this.code = code;
        this.capacity = capacity;
        this.available = true;
    }

    @Deprecated
    public Place() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }
}
