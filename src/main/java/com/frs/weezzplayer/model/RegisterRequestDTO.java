package com.frs.weezzplayer.model;

import com.frs.weezzplayer.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotNull
    @NotEmpty(message = "First name can not be empty")
    private String firstname;

    @NotNull
    @NotEmpty(message = "Last name can not be empty")
    private String lastname;
    @NotNull
    @NotEmpty(message = "Username can not be empty")
    private String username;

    @NotNull
    @NotEmpty(message = "Please provide a valid email ")
    private String email;
    @NotNull
    @NotEmpty(message = "password can not be empty")
    @Size(min = 6, max = 40)
    private String password;
    private String role;

    private Organization organization;




}
