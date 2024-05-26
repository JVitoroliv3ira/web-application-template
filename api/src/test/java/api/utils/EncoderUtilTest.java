package api.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles(profiles = "h2")
@SpringBootTest
class EncoderUtilTest {
    private EncoderUtil encoderUtil;
    @Mock
    private final PasswordEncoder encoder;

    @Autowired
    public EncoderUtilTest(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @BeforeEach
    void setUp() {
        this.encoderUtil = new EncoderUtil(this.encoder);
    }

    @Test
    void encode_should_call_encode_method_of_password_encoder() {
        var payload = "@payload";

        this.encoderUtil.encode(payload);

        verify(this.encoder, times(1)).encode(payload);
    }

    @Test
    void encode_should_return_encoded_value() {
        var payload = "@payload";
        var expected = "payload@";

        when(this.encoder.encode(payload)).thenReturn(expected);

        var result = this.encoderUtil.encode(payload);

        assertEquals(expected, result);
    }

    @Test
    void matches_should_call_matches_method_of_password_encoder() {
        var rawPayload = "@payload";
        var encodedPayload = "payload@";

        this.encoderUtil.matches(rawPayload, encodedPayload);

        verify(this.encoder, times(1)).matches(rawPayload, encodedPayload);
    }

    @Test
    void matches_should_return_true_when_values_matches() {
        var rawPayload = "@payload";
        var encodedPayload = "payload@";

        when(this.encoder.matches(rawPayload, encodedPayload)).thenReturn(true);

        var result = this.encoderUtil.matches(rawPayload, encodedPayload);

        assertTrue(result);
    }

    @Test
    void matches_should_return_false_when_values_does_not_matches() {
        var rawPayload = "@payload";
        var encodedPayload = "payload@";

        when(this.encoder.matches(rawPayload, encodedPayload)).thenReturn(false);

        var result = this.encoderUtil.matches(rawPayload, encodedPayload);

        assertFalse(result);
    }
}