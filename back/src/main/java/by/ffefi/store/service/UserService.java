package by.ffefi.store.service;

import by.ffefi.store.dao.UserDao;
import by.ffefi.store.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean save(User user) {

        if (userDao.findByTelephone(user.getUsername()) != null) {
            return false;
        }

        userDao.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User loadUserById(Long id) {
        Optional<User> user = userDao.findById(id.toString());

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        return user.get();
    }

    public void updateUser(User user, User userFromDb) {
        if (
                !user.getEmail().isEmpty() &&
                        !user.getUsername().isEmpty() &&
                        !user.getPassword().isEmpty() &&
                        (user.getUsername().length() < 50 &&
                                user.getPassword().length() < 50 &&
                                user.getEmail().length() < 50)
        ) {
            userFromDb.setFirstName(user.getFirstName());
            userFromDb.setLastName(user.getLastName());
            userFromDb.setEmail(user.getEmail());
            userFromDb.setPassword(user.getPassword());
            userDao.save(userFromDb);
        }
    }
}
