package com.epam.chatbotserver.models;

import com.epam.chatbotserver.dto.UserDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static User entityToUser(UserDto userDto){
        return new User(userDto.getUsername(), userDto.getPassword(), userDto.getFirstName(), userDto.getLastName());
    }
}
