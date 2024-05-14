package api.services;

import api.exceptions.ApiException;
import api.models.User;
import api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private static final String EMAIL_NOT_UNIQUE_MESSAGE = "O e-mail informado já está sendo utilizado por outra conta.";

    public User create(User user) {
        user.setId(null);
        return this.userRepository.save(user);
    }

    public Boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public void validateEmailUniqueness(String email) throws ApiException {
        if (Boolean.FALSE.equals(this.existsByEmail(email))) {
            throw new ApiException(
                    EMAIL_NOT_UNIQUE_MESSAGE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
