package ojles.coursework.user.service;

import lombok.RequiredArgsConstructor;
import ojles.coursework.user.dao.UserDao;
import ojles.coursework.user.entity.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public User getById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    public User findByUsernameOrEmail(String usernameOrEmail) {
        return userDao.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    }
}
