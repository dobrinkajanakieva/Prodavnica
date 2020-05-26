package Application.Services;

import Application.Models.User;

public interface IUserService {
    User findById(String username);
}
