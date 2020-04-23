package com.intro.api.model;

import lombok.*;
import io.swagger.annotations.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ProfileReply {
    private String username;
}