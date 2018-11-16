package codeSpitters.programathon_2018.service.user.administrator;

import codeSpitters.programathon_2018.model.Administrator;
import codeSpitters.programathon_2018.model.User;

public interface AdministratorService {

    void createAdmininistrator(Administrator administrator);

    Administrator getAdministrator(String userSsn);

    void updateAdministrator(Administrator administrator);

}
