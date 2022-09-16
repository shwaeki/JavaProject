package org.shwaeki.dao;

import org.shwaeki.models.Company;

import java.util.ArrayList;

public interface CompaniesDAO {
    boolean isCompanyExists(String email, String password);

    void addCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(int companyID);

    ArrayList<Company> gatAllCompany( );

    Company gatOneCompany(int companyID);

}
