package com.zcam.eventmanager.place.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private String cep;

    @NotNull
    private int capacity;

    @NotNull
    private int number;

    private String complement;
    private String reference;
    private boolean active;

    public Place(String name, String code, int capacity, String cep, int number, String complement, String reference) {
        this.name = name;
        this.code = code;
        this.capacity = capacity;
        this.cep = cep;
        this.number = number;
        this.complement = complement;
        this.reference = reference;
        this.active = true;
    }

    @Deprecated
    public Place() {
    }

    public void update(String name, String code, int capacity, String cep, int number, String complement, String reference) {
        this.name = name;
        this.code = code;
        this.capacity = capacity;
        this.cep = cep;
        this.number = number;
        this.complement = complement;
        this.reference = reference;
    }

    public void inactivate() {
        this.active = false;
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

    public String getCep() {
        return cep;
    }

    public int getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getReference() {
        return reference;
    }

    public boolean isActive() {
        return active;
    }
}
