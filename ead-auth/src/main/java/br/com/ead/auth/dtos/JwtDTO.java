package br.com.ead.auth.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class JwtDTO {

    @NotBlank
    @NotNull
    private String token;

    private String type = "Bearer";

    public JwtDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
