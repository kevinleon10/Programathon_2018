package codeSpitters.programathon_2018.service.user;

import codeSpitters.programathon_2018.model.User;

public interface UserService {

    void createUser(String id, String name, String email, String password, int phone);

    User getUser(String email);

    User getUserBySsn(String userSsn);

    void updateUser(User user);

}
