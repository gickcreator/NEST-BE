package ssu.opensource.dto.test;


import lombok.Builder;

@Builder
public record TestDto(
        String content
) {
}