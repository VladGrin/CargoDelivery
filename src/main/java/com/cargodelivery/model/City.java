package com.cargodelivery.model;

import lombok.Data;

@Data
public class City implements Comparable<City>{
    private Integer id;
    private String name;

    public City() {
    }

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(City o) {
        return name.compareTo(o.name);
    }
}
