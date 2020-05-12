package com.intro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;
import net.devh.boot.grpc.client.inject.GrpcClient;

import com.intro.api.model.*;
import com.intro.proto.UserServiceGrpc.UserServiceBlockingStub;
import com.intro.proto.UserSrv.GetProfileReply;
import com.intro.proto.UserSrv.GetProfileRequest;

@RestController
@RequestMapping("user")
@Api(tags = "User", description = "User Service")
public class UserController {
    @Autowired
    private UserContext userContext;

    @GrpcClient("user-service")
    private UserServiceBlockingStub userService;

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    @ApiOperation(value = "Profile", nickname = "user_profile", notes = "User profile", produces = "application/json")
    @Authorization("Authorization")
    @ResponseHeader
    public ProfileReply profile() {
        GetProfileRequest req = GetProfileRequest.newBuilder().setId(this.userContext.getUserId()).build();
        GetProfileReply reply = this.userService.getProfile(req);
        ProfileReply profile = new ProfileReply();
        profile.setUsername(reply.getUsername());
        return profile;
    }
}