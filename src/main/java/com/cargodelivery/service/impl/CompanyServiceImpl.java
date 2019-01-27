package com.cargodelivery.service.impl;

import com.cargodelivery.model.Company;
import com.cargodelivery.repository.CompanyRepository;
import com.cargodelivery.repository.impl.MySQLCompanyRepository;
import com.cargodelivery.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository = new MySQLCompanyRepository();

    @Override
    public Company getCompanyById(int id) {
        return companyRepository.findCompanyById(id);
    }
}
