package api.services;

import api.exceptions.ApiException;
import api.models.User;
import api.repositories.UserRepository;
import api.utils.EncoderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ActiveProfiles(profiles = "h2")
@SpringBootTest
class UserServiceTest {
    private UserService userService;

    @Mock
    private final UserRepository userRepository;
    @Mock
    private final EncoderUtil encoderUtil;

    private static final String EMAIL_NOT_UNIQUE_MESSAGE = "O e-mail informado já está sendo utilizado por outra conta.";

    @Autowired
    public UserServiceTest(UserRepository userRepository, EncoderUtil encoderUtil) {
        this.userRepository = userRepository;
        this.encoderUtil = encoderUtil;
    }

    @BeforeEach
    void setUp() {
        this.userService = new UserService(this.userRepository, this.encoderUtil);
    }

    public User buildUserPayload(Long id, String password) {
        return User
                .builder()
                .id(id)
                .name("payload")
                .email("payload@payload.com")
                .password(Optional.ofNullable(password).orElse("@payload"))
                .build();
    }

    @Test
    void create_should_call_save_method_of_user_repository() {
        var payload = this.buildUserPayload(null, null);

        this.userService.create(payload);

        verify(this.userRepository, times(1)).save(payload);
    }

    @Test
    void create_should_set_user_id_to_null_before_call_save_method_of_user_repository() {
        var payload = this.buildUserPayload(1L, null);

        this.userService.create(payload);

        verify(this.userRepository, times(1))
                .save(this.buildUserPayload(null, null));
    }

    @Test
    void create_should_return_created_user() {
        var payload = this.buildUserPayload(null, null);
        var expected = this.buildUserPayload(1L, null);

        when(this.userRepository.save(payload)).thenReturn(expected);

        var result = this.userService.create(payload);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void exists_by_email_should_call_exists_by_email_method_of_user_repository() {
        var payload = "payload@payload.com";

        this.userService.existsByEmail(payload);

        verify(this.userRepository, times(1)).existsByEmail(payload);
    }

    @Test
    void exists_by_email_should_return_true_when_payload_exists() {
        var payload = "payload@payload.com";

        when(this.userRepository.existsByEmail(payload)).thenReturn(true);

        var result = this.userService.existsByEmail(payload);

        Assertions.assertTrue(result);
    }

    @Test
    void exists_by_email_should_return_false_when_payload_does_not_exists() {
        var payload = "payload@payload.com";

        when(this.userRepository.existsByEmail(payload)).thenReturn(false);

        var result = this.userService.existsByEmail(payload);

        Assertions.assertFalse(result);
    }

    @Test
    void validate_email_uniqueness_should_call_exists_by_email_method_of_user_repository() {
        var payload = "payload@payload.com";

        this.userService.validateEmailUniqueness(payload);

        verify(this.userRepository, times(1)).existsByEmail(payload);
    }

    @Test
    void validate_email_uniqueness_should_throw_an_exception_when_email_is_already_in_use() {
        var payload = "payload@payload.com";

        when(this.userRepository.existsByEmail(payload)).thenReturn(true);

        assertThatThrownBy(() -> this.userService.validateEmailUniqueness(payload))
                .isInstanceOf(ApiException.class)
                .hasMessage(EMAIL_NOT_UNIQUE_MESSAGE);
    }

    @Test
    void encode_user_password_should_call_encode_method_of_encoder_util() {
        var payload = this.buildUserPayload(null, null);

        this.userService.encodeUserPassword(payload);

        verify(this.encoderUtil, times(1))
                .encode("@payload");
    }

    @Test
    void register_should_validate_email_uniqueness_encode_password_and_create_user() {
        var payload = this.buildUserPayload(null, null);
        var expected = this.buildUserPayload(1L, null);

        when(this.userRepository.save(payload)).thenReturn(expected);

        var result = this.userService.register(payload);

        verify(this.userRepository, times(1)).existsByEmail(payload.getEmail());
        verify(this.encoderUtil, times(1)).encode("@payload");
        verify(this.userRepository, times(1)).save(payload);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void register_should_throw_exception_when_email_is_not_unique() {
        var payload = this.buildUserPayload(null, null);

        when(this.userRepository.existsByEmail(payload.getEmail())).thenReturn(true);

        assertThatThrownBy(() -> this.userService.register(payload))
                .isInstanceOf(ApiException.class)
                .hasMessage(EMAIL_NOT_UNIQUE_MESSAGE);

        verify(this.userRepository, times(1)).existsByEmail(payload.getEmail());
        verify(this.encoderUtil, times(0)).encode("@payload");
        verify(this.userRepository, times(0)).save(payload);
    }
}
