package org.shwaeki;


import org.shwaeki.dao.CompaniesDBDAO;
import org.shwaeki.models.Company;

public class Main {
    public static void main(String[] args) {

        boolean status = new CompaniesDBDAO().isCompanyExists("test@example.com", "asdasdasd");
        new CompaniesDBDAO().deleteCompany(22);

    }
}