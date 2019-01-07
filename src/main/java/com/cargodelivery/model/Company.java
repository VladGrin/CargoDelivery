package com.cargodelivery.model;

import lombok.Data;

@Data
public class Company {
    private Integer id;
    private String name;
    private String address;
    private String codEDRPOU;
    private String codINN;
    private String bank;
    private String mfo;
    private String account;

    public Company() {
    }

    public Company(Integer id, String name, String address, String codEDRPOU,
                   String codINN, String bank, String mfo, String account) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.codEDRPOU = codEDRPOU;
        this.codINN = codINN;
        this.bank = bank;
        this.mfo = mfo;
        this.account = account;
    }
}
