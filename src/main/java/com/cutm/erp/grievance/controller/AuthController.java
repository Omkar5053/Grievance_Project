package com.cutm.erp.grievance.controller;

import com.cutm.erp.common.UserDto;
import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.entity.User;
import com.cutm.erp.grievance.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final String ERP_SESSION_COOKIE = "ERPUserId";

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public @ResponseBody
    UserDto login(@RequestParam("username") String userName, @RequestParam("password") String password,
                  HttpServletRequest request, HttpServletResponse response) throws GrievanceException {

        User user = userService.authenticate(userName, password);
        UserDto dto = new UserDto();
        dto.setFullName(user.getFirstName()+" "+user.getLastName());
        dto.setSessionId(String.valueOf(user.getUserId()));
        request.getSession().setAttribute(ERP_SESSION_COOKIE, user.getUserId());
        setSessionCookie(request, response, dto.getSessionId());
        return dto;
    }

    private void setSessionCookie(HttpServletRequest request, HttpServletResponse response, String cookieValue) {
        Cookie cookie = new Cookie(ERP_SESSION_COOKIE, cookieValue);
        cookie.setPath(request.getServerName());
        cookie.setMaxAge(2147483647);
        cookie.setDomain(request.getServerName());
        response.addCookie(cookie);
    }

    @PostMapping("/logout")
    public @ResponseBody UserDto logout(HttpServletRequest request, HttpServletResponse response) throws GrievanceException {
        request.getSession().invalidate();
        Cookie cookie = new Cookie(ERP_SESSION_COOKIE, "");
        cookie.setPath(request.getServerName());
        cookie.setMaxAge(0);
        cookie.setDomain(request.getServerName());
        response.addCookie(cookie);
        return new UserDto();
    }

    @PostMapping("/privileges")
    public @ResponseBody UserDto privileges() throws GrievanceException {
        return new UserDto();
    }

    @PostMapping("/currentUser")
    public @ResponseBody UserDto currentUser(
            HttpServletRequest request, HttpServletResponse response) throws GrievanceException {

        if (request.getCookies().length > 0) {
            Optional<Cookie> cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(ERP_SESSION_COOKIE)).findFirst();
            if (cookie.isPresent() && !cookie.get().getValue().isBlank()) {
                User user = userService.getUser(cookie.get().getValue());
                UserDto dto = new UserDto();
                dto.setFullName(user.getFirstName()+" "+user.getLastName());
                dto.setSessionId(String.valueOf(user.getUserId()));
                request.getSession().setAttribute(ERP_SESSION_COOKIE, user.getUserId());
                setSessionCookie(request, response, dto.getSessionId());
                return dto;
            }
        }
        throw new GrievanceException("Login required");
    }
}
