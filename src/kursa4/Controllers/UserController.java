package kursa4.Controllers;


import kursa4.DAO.UserRolesDAO;
import kursa4.DAO.UsersDAO;
import kursa4.Entities.UserRolesEntity;
import kursa4.Entities.UsersEntity;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.core.util.Base64;
import kursa4.response_models.AuthorizationResponse;
import kursa4.response_models.Credentials;
import kursa4.response_models.RegistrationResponse;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

@Path("/user")
public class UserController {


    @EJB
    private UsersDAO service;

    @EJB
    private UserRolesDAO rolesService;



    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public RegistrationResponse register(UsersEntity user){
            UserRolesEntity userRolesEntity = new UserRolesEntity(user , "USER");
            RegistrationResponse registrationResponse;
            if(!service.existsByLogin(user.getLogin())){
                if(!service.existsByEmail(user.getEmail())){
                    service.create(user);
                    rolesService.create(userRolesEntity);
                    registrationResponse = new RegistrationResponse( 1 ,user.getLogin(), "null");
                    return registrationResponse;

                } else  {
                    registrationResponse = new RegistrationResponse( 0 ,user.getLogin(), "email exists");
                    return  registrationResponse;
                }
            } else {
                registrationResponse = new RegistrationResponse(0 ,user.getLogin(), "login exists");
                return registrationResponse;
            }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public AuthorizationResponse auth (Credentials credentials) throws Exception{
        AuthorizationResponse authorizationResponse;
        if(service.authorization(credentials.getLogin(),credentials.getPassword())) {
            String token = new String( Base64.encode(credentials.getLogin()+":"+credentials.getPassword()) , "UTF8");
            authorizationResponse = new AuthorizationResponse(1 , credentials.getLogin(), token);
        return authorizationResponse;
        } else{
            authorizationResponse = new AuthorizationResponse(0 ,credentials.getLogin(),"null");
            return authorizationResponse;
        }

    }

    @GET
    @Path("/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsersEntity getUser(@PathParam("login") String login){
        return service.readByLogin(login);
    }

    @GET
    @Path("/top10")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsersEntity> usersTopTen(){
        return service.topTen();
    }

}
