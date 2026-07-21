package com.smartmatch.backend.service;

import com.smartmatch.backend.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Company createCompany(Company company);

    List<Company> getAllCompanies();

    Optional<Company> getCompanyById(Long id);

    Company updateCompany(Long id, Company company);

    void deleteCompany(Long id);

}