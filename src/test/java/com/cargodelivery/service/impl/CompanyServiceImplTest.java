package com.cargodelivery.service.impl;

import com.cargodelivery.model.Company;
import com.cargodelivery.repository.CompanyRepository;
import com.cargodelivery.repository.impl.MySQLCompanyRepository;
import com.cargodelivery.service.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class CompanyServiceImplTest {

    private CompanyService companyService;

    private CompanyRepository companyRepository;

    @Before
    public void before() {
        companyRepository = Mockito.mock(MySQLCompanyRepository.class);
        companyService = new CompanyServiceImpl(companyRepository);
    }

    @Test
    public void getCompanyByIdTest() {

        int companyId = 1;
        Company company = new Company();

        Mockito.when(companyRepository.findCompanyById(companyId)).thenReturn(company);

        Company result = companyService.getCompanyById(companyId);

        assertEquals(company, result);
    }

}
