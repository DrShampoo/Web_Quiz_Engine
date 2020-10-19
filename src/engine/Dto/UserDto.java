package engine.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
    @JsonProperty
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid Email")
    private String email;

    @JsonProperty
    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password should contain at least 5 characters")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
