package de.dpunkt.myaktion.resources;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.dpunkt.myaktion.Auth.LoginRequest;
import de.dpunkt.myaktion.services.OrganizerServiceBean;


@Path("/login")
public class LoginResource {

    @Inject
    OrganizerServiceBean organizerServiceBean;

    @POST
    public Response login(LoginRequest loginRequest) {
        
        if (organizerServiceBean.isValidUser(loginRequest.getEmail(), loginRequest.getPassword())) {
            System.out.println(loginRequest.getEmail()+ " hat sich angemeldet");
            String token = generateToken(loginRequest.getEmail());
            return Response.ok(token).build();
        } else {
            return Response.status(Status.UNAUTHORIZED).build();
        }
    }

    private String generateToken(String email) {
        String jwt = Jwt.issuer("http://localhost:3000") 
             .upn(email) 
             .groups(new HashSet<>(Arrays.asList("organizer", "Admin"))) 
             .claim(Claims.birthdate.name(), "2023-03-01")
           .sign();
        return jwt;
    }
}
