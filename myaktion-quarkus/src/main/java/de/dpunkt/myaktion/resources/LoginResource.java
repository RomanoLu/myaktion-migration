package de.dpunkt.myaktion.resources;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import de.dpunkt.myaktion.services.OrganizerServiceBean;


@Path("/login")
public class LoginResource {

    @Inject
    OrganizerServiceBean organizerServiceBean;

    @POST
    public Response login(@FormParam("email") String email, 
                          @FormParam("password") String password) {
        
        if (organizerServiceBean.isValidUser(email, password)) {
            String token = generateToken(email);
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
