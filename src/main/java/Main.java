import http.GhCleaner;
import io.github.cdimascio.dotenv.Dotenv;
import model.GhCleanerConfig;

import java.util.List;

public class Main {
    private static final Dotenv DOTENV = Dotenv.load();

    public static void main(String[] args) {
        var repos = List.of(
                "js-fundamentals-arrays",
                "js-fundamentals-variables",
                "java-tdd-oop-bank-challenge",
                "java-tdd-oop-bobs-bagels",
                "java-tdd-oop-inheritance",
                "java-tdd-opp-abstraction",
                "java-tdd-oop-encapsulation",
                "java-tdd-bobs-bagels",
                "java-tdd-todo-list",
                "java-scrabble-challenge",
                "java-fundamentals-maps",
                "java-fundamentals-lists",
                "java-fundamentals-loops",
                "java-fundamentals-strings",
                "java-fundamentals-control-flow",
                "java-fundamentals-methods",
                "java-fundamentals-constructors-overloading",
                "java-fundamentals-class-members",
                "java-fundamentals-primitive-types",
                "angular-greengrocers",
                "angular-todo-list",
                "angular-address-book",
                "angular-routing-workshop",
                "angular-friends-list",
                "ood-bank-challenge",
                "tdd-bobs-bagels",
                "ts-oop-polymorphism",
                "js-poker-challenge",
                "js-fundamentals-functions-3",
                "js-fundamentals-functions-2",
                "js-fundamentals-functions-1",
                "js-fundamentals-loops",
                "js-fundamentals-objects "

        );
        var owner = GhCleanerConfig.builder()
                .username(DOTENV.get("GH_USERNAME"))
                .authToken(DOTENV.get("GH_AUTH_TOKEN"))
                .repos(repos)
                .build();
        var ghCleaner = new GhCleaner(owner);
        ghCleaner.clean();
    }
}
