package org.superbank.rate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

import java.security.Principal;

import static org.superbank.rate.RateApplication.API_VERSION;

@RestController
@RequestMapping(path = API_VERSION +"rate/")
public class RateUserController {

    @GetMapping(path = "/user")
    @RolesAllowed("rate-write")
    public String user(final Principal principal) {
        return "Response from rate-service, userId: " + principal.getName();
    }
}
