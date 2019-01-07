package com.cargodelivery.service.impl;

import com.cargodelivery.model.Company;
import com.cargodelivery.repository.CompanyRepository;
import com.cargodelivery.repository.impl.CompanyRepositoryImpl;
import com.cargodelivery.service.CompanyService;

import java.sql.Connection;

public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(Connection connection) {
        this.companyRepository = new CompanyRepositoryImpl(connection);
    }

    @Override
    public Company getCompanyById(int id) {
        return companyRepository.findCompanyById(id);
    }
}
