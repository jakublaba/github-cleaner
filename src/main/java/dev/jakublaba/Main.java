package dev.jakublaba;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jakublaba.http.GhCleaner;
import io.github.cdimascio.dotenv.Dotenv;
import dev.jakublaba.model.GhCleanerConfig;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static final Dotenv DOTENV = Dotenv.load();
    private static final ObjectMapper OBJ_MAPPER = new ObjectMapper();
    private static final ClassLoader CLASS_LOADER = Main.class.getClassLoader();

    public static void main(String[] args) throws IOException {
        var repos = OBJ_MAPPER.readValue(CLASS_LOADER.getResourceAsStream("repos.json"), String[].class);
        var config = GhCleanerConfig.builder()
                .username(DOTENV.get("GH_USERNAME"))
                .authToken(DOTENV.get("GH_AUTH_TOKEN"))
                .repos(Arrays.asList(repos))
                .build();
        var ghCleaner = new GhCleaner(config);
        ghCleaner.clean();
    }
}
