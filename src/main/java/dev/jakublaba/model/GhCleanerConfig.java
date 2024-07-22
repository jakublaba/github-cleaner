package dev.jakublaba.model;

import lombok.Builder;

import java.util.List;

@Builder
public record GhCleanerConfig(
        String username,
        String authToken,
        List<String> repos
) {
}
