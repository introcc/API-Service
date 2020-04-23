package com.intro.api.model;

import javax.validation.constraints.*;
import lombok.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class LoginRequest {

    @NotNull
    @Size(max = 256)
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    @ApiModelProperty(value = "Email", required = true)
    private String email;

    @NotNull
    @Size(max = 32)
    @ApiModelProperty(value = "Password", required = true)
    private String password;
}