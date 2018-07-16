package com.epam.chatbotserver.models;

import com.epam.chatbotserver.dto.UserDto;
import com.epam.chatbotserver.utility.IntentTypes;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String idInBotSystem;
    private String username;
    private String password;
    private String firstName;
    private String lastName;


    @Transient
    private IntentTypes intentType;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.lastName = userDto.getLastName();
        this.firstName = userDto.getFirstName();
    }


    public String getUsername() {
        return username;
    }

    public IntentTypes getIntentType() {
        return intentType;
    }

    public void setIntentType(IntentTypes intentType) {
        this.intentType = intentType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdInBotSystem() {
        return idInBotSystem;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIdInBotSystem(String idInBotSystem) {
        this.idInBotSystem = idInBotSystem;
    }
}
