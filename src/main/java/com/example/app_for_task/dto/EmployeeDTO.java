package com.example.app_for_task.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeDTO {
    @NotNull(message = "Id не может быть пустым")
    private int id;
    @NotNull(message = "Имя не может быть пустым")
    private String firstName;
    @NotNull(message = "Фамилия не может быть пустой")
    private String lastName;
    @NotNull(message = "Пароль не может быть пустым")
    private String  password;
    @NotNull(message = "Повтор пароля не может быть пустым")
    private String  passwordConf;

}
