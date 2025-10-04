package ru.stellarburgers.api;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;

    public static User getRandomUser() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(6, 12);
        String name = faker.name().firstName();
        return new User(email, password, name);
    }
}