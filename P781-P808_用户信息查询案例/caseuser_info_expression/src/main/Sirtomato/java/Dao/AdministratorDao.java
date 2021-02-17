package Dao;

import Domain.Administrator;

public interface AdministratorDao {
    public Administrator findUserByUsernameAndPasword(Administrator loginAdministrator);
}
