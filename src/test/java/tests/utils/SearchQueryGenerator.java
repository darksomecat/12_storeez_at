package tests.utils;

import com.github.javafaker.Faker;

public class SearchQueryGenerator {

    private static final Faker faker = new Faker();

    private static final String[] SEARCH_WORDS = {
            "Пальто",
            "Джинсы",
            "Ботинки",
            "Туфли"
    };

    public static String randomSearchWord() {
        return faker.options().option(SEARCH_WORDS);
    }
}
