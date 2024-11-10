package ssu.opensource.dto.test;

import jakarta.validation.constraints.NotBlank;

public record TestInput(
        @NotBlank
        String name,
        String email
) {
}
