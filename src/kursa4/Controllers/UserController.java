package kursa4.Controllers;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import kursa4.DAO.UserRolesDAO;
import kursa4.DAO.UsersDAO;
import kursa4.Entities.UserRolesEntity;
import kursa4.Entities.UsersEntity;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.util.Base64;
import kursa4.models.AuthorizationResponse;
import kursa4.models.RegistrationResponse;

import java.io.*;
import java.util.List;

@Path("/user")
public class UserController {

    @EJB
    private UsersDAO service;

    @EJB
    private UserRolesDAO rolesService;

//    @POST
//    @Path("/load")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response upload(
//            @FormDataParam("file") InputStream uploadedInputStream ,
//            @FormDataParam("file") FormDataContentDisposition fileDetail){
//        String uploadedFileLocation = "/home/andrew/Desktop/kursa4/web/resources/img"
//                + fileDetail.getFileName();
//        writeToFile(uploadedInputStream , uploadedFileLocation);
//        String resp = "uploaded to: "+uploadedFileLocation;
//        return Response.status(200).entity(resp).build();
//    }
//
//    private void writeToFile(InputStream uploadedInputStream,
//                             String uploadedFileLocation) {
//
//        try {
//            OutputStream out = new FileOutputStream(new File(
//                    uploadedFileLocation));
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            out = new FileOutputStream(new File(uploadedFileLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//
//    }




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
                    registrationResponse = new RegistrationResponse( true , "null");
                    return registrationResponse;

                } else  {
                    registrationResponse = new RegistrationResponse( false , "email exists");
                    return  registrationResponse;
                }
            } else {
                registrationResponse = new RegistrationResponse(false , "login exists");
                return registrationResponse;
            }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public AuthorizationResponse auth (String login, String password) throws Exception{
        AuthorizationResponse authorizationResponse;
        if(service.authorization(login,password)) {
            String token = new String( Base64.encode(login+":"+password) , "UTF8");
            authorizationResponse = new AuthorizationResponse(true , token);
        return authorizationResponse;
        } else{
            authorizationResponse = new AuthorizationResponse(false ,"null");
            return authorizationResponse;
        }

    }

    @GET
    @Path("/top10")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsersEntity> usersTopTen(){
        return service.topTen();
    }

    @POST
    @Path("roles")
    @Produces(MediaType.TEXT_PLAIN)
    public String userRoles(@FormParam("login") String login){
        String s="";
        for(UserRolesEntity rolesEntity : rolesService.readByLogin(login)){
            s +=rolesEntity.getRole();
        }
        return s;
    }


}
