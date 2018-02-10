package kursa4.Controllers;


import kursa4.DAO.ArticleDAO;
import kursa4.DAO.UserRolesDAO;
import kursa4.DAO.UsersDAO;
import kursa4.Entities.UserRolesEntity;
import kursa4.Entities.UsersEntity;

import javax.annotation.security.RolesAllowed;
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

    final String [] types = {"image/png" , "image/jpeg" , ".png" , ".jpg" , ".jpeg"};
    final String uploadPath = "/home/pavel/Software/glassfish5/glassfish/domains/domain1/docroot/";

    @EJB
    private UsersDAO service;

    @EJB
    private UserRolesDAO rolesService;

    @EJB
    private ArticleDAO articleService;


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
        UsersEntity existingUser = service.readByLogin(login);
        if(existingUser != null){
            existingUser.setPassword(null);
            return existingUser;
        } else {
            return null;
        }

    }

    @POST
    @Path("/author/{login}")
    @Produces(MediaType.TEXT_PLAIN)
    public Number countByAuthor(@PathParam("login") String login){

        return articleService.countByAuthor(login);
    }

    @GET
    @Path("/top/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsersEntity> getAllUsers(@PathParam("amount") int i){
        List<UsersEntity> list = service.Top(i);
        for(UsersEntity user : list){
            user.setPassword(null);
        }
        return list;
    }


    @POST
    @Path("/roles/{login}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getRolesByLogin(@PathParam("login") String login){
        return rolesService.readByLogin(login).size();
    }

    @POST
    @Path("/load/{login}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public int upload (
            @FormDataParam("file") InputStream uploadedInputStream ,
            @FormDataParam("file") FormDataContentDisposition fileDetail ,
            @PathParam("login") String login
    ) throws IOException {
        int t = 0;
        String type2 = "";
        String uploadedFileLocation = uploadPath + fileDetail.getFileName();
        String fileParsedType = fileDetail.getFileName().substring
                (fileDetail.getFileName().lastIndexOf(".") ,
                        fileDetail.getFileName().length());
        for(int i = 0 ; i < types.length ; i++){
            if(types[i].equals(fileParsedType)){
                t++;
                writeToFile(uploadedInputStream , uploadPath+login);
                File file = new File(uploadedFileLocation);
                type2 = Files.probeContentType(file.toPath());
                for(int j = 0 ; j < types.length ; j++) {
                    if (types[j].equals(type2)) {
                        t++;
                        break;
                    }
                }
                break;
            }
        }
        if(t < 2) return 0;
        else{
            UsersEntity usersEntity = service.readByLogin(login);
            usersEntity.setAvatarpath(uploadPath+login);
            service.update(usersEntity);
            return 1;
        }
    }

    private void writeToFile(InputStream uploadedInputStream,
                             String uploadedFileLocation) {

        try {
            OutputStream out;
            int read ;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (NullPointerException x){
            x.printStackTrace();
        }

    }

}
