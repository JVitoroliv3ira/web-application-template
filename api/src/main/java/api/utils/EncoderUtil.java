package api.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class EncoderUtil {
    private final PasswordEncoder encoder;

    public String encode(String value) {
        return this.encoder.encode(value);
    }

    public Boolean matches(String rawValue, String encodedValue) {
        return this.encoder.matches(rawValue, encodedValue);
    }
}
