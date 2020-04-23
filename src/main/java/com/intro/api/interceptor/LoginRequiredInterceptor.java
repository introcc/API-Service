package com.intro.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intro.api.model.UserContext;
import com.intro.proto.UserSrv.VerifyTokenReply;
import com.intro.proto.UserSrv.VerifyTokenRequest;
import com.intro.proto.UserServiceGrpc.UserServiceBlockingStub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Component
@Slf4j
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Autowired
    private UserContext userContext;

    @GrpcClient("user-service")
    private UserServiceBlockingStub userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        log.info("{} {} {}", request.getRequestURI(), response.getStatus(), o);
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            response.sendError(401);
            return false;
        }
        String token = header.substring(7);
        try {
            VerifyTokenRequest req = VerifyTokenRequest.newBuilder().setToken(token).build();
            VerifyTokenReply reply = userService.verifyToken(req);
            userContext.setUserId(reply.getId());
            return true;
        } catch (StatusRuntimeException e) {
            if (e.getStatus() == Status.UNAUTHENTICATED) {
                response.sendError(500);
                return false;
            }
            response.sendError(500);
            return false;
        } catch (Exception e) {
            response.sendError(500);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o,
            ModelAndView modelAndView) throws Exception {
        log.info("{} {} {}", request.getRequestURI(), response.getStatus(), o);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e)
            throws Exception {
        log.info("{} {} {}", request.getRequestURI(), response.getStatus(), o);
    }
}
