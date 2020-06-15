package com.peterabyte.hellospring.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordRequest
{
    @NotNull
    @Size(min = 3)
    private String password;

    public PasswordRequest() {
        this("");
    }

    public PasswordRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
