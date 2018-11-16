package codeSpitters.programathon_2018.service.user.responsible;

import codeSpitters.programathon_2018.model.Responsible;
import codeSpitters.programathon_2018.model.User;

public interface ResponsibleService {

    void createResponsible(User user);

    Responsible getResponsible(String userSsn);

    void updateResponsible(Responsible responsible);
}
