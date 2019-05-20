package ojles.coursework.user.dao;

import ojles.coursework.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsernameOrEmail(String username, String email);
}

