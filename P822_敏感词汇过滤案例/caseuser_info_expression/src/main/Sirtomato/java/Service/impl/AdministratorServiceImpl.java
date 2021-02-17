package Service.impl;

import Dao.impl.AdministratorDaoImpl;
import Domain.Administrator;
import Service.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService {
    @Override
    public Administrator login(Administrator loginAdministrator) {
        AdministratorDaoImpl administratorDaoImpl = new AdministratorDaoImpl();
        return administratorDaoImpl.findUserByUsernameAndPasword(loginAdministrator);
    }
}
