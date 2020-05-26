package Application.Services;

import Application.Models.User;

public interface IAuthService {
    User getCurrentUser();
    String getCurrentUserId();
}
