package ru.job4j.pinger.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.pinger.models.User;
import ru.job4j.pinger.repositories.UsersRepository;

@Getter
@Setter
@NoArgsConstructor
@Component
public class UserDto {

    @Autowired
    private UsersRepository uuu;

    private String name;


    public User convert() {

        User result = uuu.findByName(this.getName());
        if (result == null) {
            result = new User();
            result.setName(this.getName());
            result.setPassword("");
            result.setEmail("empty@empty.ru");
        }
        return result;
    }
}
