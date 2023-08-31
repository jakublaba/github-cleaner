import com.fasterxml.jackson.databind.ObjectMapper;
import http.GhCleaner;
import io.github.cdimascio.dotenv.Dotenv;
import model.GhCleanerConfig;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static final Dotenv DOTENV = Dotenv.load();
    private static final ObjectMapper objMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        var repos = objMapper.readValue(Main.class.getClassLoader().getResourceAsStream("repos.json"), String[].class);
        var owner = GhCleanerConfig.builder()
                .username(DOTENV.get("GH_USERNAME"))
                .authToken(DOTENV.get("GH_AUTH_TOKEN"))
                .repos(Arrays.asList(repos))
                .build();
        var ghCleaner = new GhCleaner(owner);
        ghCleaner.clean();
    }
}
