package api.dtos.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotNull(message = "Informe o seu nome.")
        @Size(min = 5, max = 80, message = "O nome deve conter entre {min} e {max} caracteres.")
        String name,
        @NotNull(message = "Informe o seu e-mail.")
        @Size(min = 9, max = 90, message = "O e-mail deve conter entre {min} e {max} caracteres.")
        @Email(message = "O e-mail informado é inválido.")
        String email,
        @NotNull(message = "Informe a sua senha.")
        @Size(min = 6, max = 60, message = "A senha deve conter entre {min} e {max} caracteres.")
        String password
) {
}
