package Application.Services;

import Application.Models.Exceptions.UserNotFoundException;
import Application.Models.User;
import Application.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    public User findById(String username) {
        return this.userRepository.findById(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }
}
