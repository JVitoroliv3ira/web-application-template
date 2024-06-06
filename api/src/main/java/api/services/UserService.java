package api.services;

import api.exceptions.ApiException;
import api.models.Details;
import api.models.User;
import api.repositories.UserRepository;
import api.utils.EncoderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final EncoderUtil encoderUtil;
    private static final String EMAIL_NOT_UNIQUE_MESSAGE = "O e-mail informado já está sendo utilizado por outra conta.";
    private static final String USER_NOT_EXISTS_MESSAGE = "Usuário não encontrado na base de dados.";

    public User register(User user) {
        this.validateEmailUniqueness(user.getEmail());
        this.encodeUserPassword(user);
        return this.create(user);
    }

    public Boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public void validateEmailUniqueness(String email) throws ApiException {
        if (Boolean.TRUE.equals(this.existsByEmail(email))) {
            throw new ApiException(
                    EMAIL_NOT_UNIQUE_MESSAGE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    public void encodeUserPassword(User user) {
        user.setPassword(this.encoderUtil.encode(user.getPassword()));
    }

    public User create(User user) {
        user.setId(null);
        return this.userRepository.save(user);
    }

    @Override
    public Details loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException(
                        USER_NOT_EXISTS_MESSAGE,
                        HttpStatus.BAD_REQUEST
                ));
        return new Details(user);
    }
}
