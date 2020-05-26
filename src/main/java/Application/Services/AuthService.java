package Application.Services;

import Application.Models.Book;
import Application.Models.User;
import Application.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private IUserRepository userRepository;

    public User getCurrentUser() {
        return this.userRepository.findById("dummy")
                .orElseGet(() -> {
                    User user = new User();
                    user.setUsername("dummy");
                    return this.userRepository.save(user);
                });
    }

    public String getCurrentUserId() {
        return this.getCurrentUser().getUsername();
    }
}
