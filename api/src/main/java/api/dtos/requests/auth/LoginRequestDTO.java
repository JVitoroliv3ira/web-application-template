package api.dtos.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public record LoginRequestDTO(
        @NotNull(message = "Informe o seu e-mail.")
        @Size(min = 9, max = 90, message = "O e-mail deve conter entre {min} e {max} caracteres.")
        @Email(message = "O e-mail informado é inválido.")
        String email,
        @NotNull(message = "Informe a sua senha.")
        @Size(min = 6, max = 60, message = "A senha deve conter entre {min} e {max} caracteres.")
        String password
) {

    public Authentication convert() {
        return new UsernamePasswordAuthenticationToken(email(), password());
    }
}
