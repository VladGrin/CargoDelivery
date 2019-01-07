package com.cargodelivery.repository;

import com.cargodelivery.model.Company;

public interface CompanyRepository {

    Company findCompanyById(int id);
}
