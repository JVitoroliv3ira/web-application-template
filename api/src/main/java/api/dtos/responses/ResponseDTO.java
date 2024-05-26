package api.dtos.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
import java.util.Objects;

public record ResponseDTO<C>(
        String message,
        C content,
        List<String> errors
) {
    @JsonSerialize
    public boolean hasErrors() {
        return Objects.nonNull(errors()) && !errors().isEmpty();
    }
}
