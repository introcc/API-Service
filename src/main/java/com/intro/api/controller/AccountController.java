package com.intro.api.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

import com.intro.proto.UserSrv.AuthenticateReply;
import com.intro.proto.UserSrv.AuthenticateRequest;
import com.intro.proto.UserSrv.RegisterReply;
import com.intro.proto.UserSrv.RegisterRequest;
import com.intro.proto.UserServiceGrpc.UserServiceBlockingStub;
import net.devh.boot.grpc.client.inject.GrpcClient;

@RestController
@RequestMapping("account")
@Api(tags = "Account", description = "Account Service")
public class AccountController {

    @GrpcClient("user-service")
    private UserServiceBlockingStub userService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ApiOperation(value = "Register", notes = "Reigster new user", produces = "application/json")
    public String register(@RequestBody(required = true) @Valid com.intro.api.model.RegisterRequest params)
            throws Exception {
        RegisterRequest req = RegisterRequest.newBuilder().setUsername(params.getUsername()).setEmail(params.getEmail())
                .setPassword(params.getPassword()).build();
        final RegisterReply reply = this.userService.register(req);
        return reply.getToken();
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ApiOperation(value = "Login", notes = "Login", produces = "application/json")
    public String login(@RequestBody(required = true) @Valid com.intro.api.model.LoginRequest params) {
        AuthenticateRequest req = AuthenticateRequest.newBuilder().setUsername(params.getEmail())
                .setPassword(params.getPassword()).build();
        final AuthenticateReply reply = this.userService.authenticate(req);
        return reply.getToken();
    }
}