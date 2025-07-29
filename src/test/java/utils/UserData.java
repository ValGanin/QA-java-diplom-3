package utils;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private String email, password, name;
    public static UserData random() {
        return new UserData(
                UUID.randomUUID().toString().substring(0, 8) + "@yandex.ru",
                "pass_" + UUID.randomUUID().toString().substring(0, 8),
                "name_" + UUID.randomUUID().toString().substring(0, 5)
        );
    }
}