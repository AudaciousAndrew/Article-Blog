package kursa4.Controllers;

import kursa4.DAO.ArticleDAO;
import kursa4.DAO.UsersDAO;
import kursa4.Entities.ArticleEntity;
import kursa4.Entities.UsersEntity;
import kursa4.Jabber.Jabber;
import kursa4.response_models.articleName;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

@Path("secured")
public class SecuredController {


    @EJB
    private ArticleDAO articleService;

    @EJB
    private UsersDAO usersService;


    @POST
    @Path("/article/add")
    @RolesAllowed({"USER" , "ADMIN" , "MODERATOR"})
    @Produces(MediaType.TEXT_PLAIN)
    public String addArticle(ArticleEntity article){
        try{
            articleService.create(article);
        return "true";
        }catch (Exception e){
            return "false";
        }

    }

    @GET
    @Path("/article/unverified")
    @RolesAllowed({"MODERATOR" ,"ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleEntity> articleUnverified(){
        return articleService.readUnverified();
    }

    @GET
    @Path("/article/all")
    @RolesAllowed({"MODERATOR" , "ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleEntity> articleAll(){
        return articleService.readAll();
    }

    @POST
    @Path("/article/update")
    @RolesAllowed({"MODERATOR" , "ADMIN"})
    @Produces(MediaType.TEXT_PLAIN)
    public String updateVerified(articleName name){
        try{
            articleService.updateVerified(name.getName());
            Jabber jabber = new Jabber();
            jabber.sendNotification(
                    usersService.readByLogin(
                            articleService.readByName(name.getName()).getAuthor()).getJabber() ,
            name.getName());
            return "true";
        }catch (Exception e){
            return "Error on server side";
        }
    }

    @POST
    @Path("/user/update")
    @RolesAllowed({"USER" , "MODERATOR" , "ADMIN" })
    @Produces(MediaType.TEXT_PLAIN)
    public String updateUserInfo(UsersEntity usersEntity){
        if(usersEntity.getFirstname() != null)
            usersService.updateFirstName(usersEntity.getLogin(), usersEntity.getFirstname());
        if(usersEntity.getLastname() != null)
            usersService.updateLastName(usersEntity.getLogin() , usersEntity.getLastname());
        if(usersEntity.getPassword() != null)
            usersService.updatePassword(usersEntity.getLogin() , usersEntity.getPassword());
        if(usersEntity.getEmail() != null){
            if(!usersService.existsByEmail(usersEntity.getEmail()))
            usersService.updateEmail(usersEntity.getLogin() , usersEntity.getEmail());
            else return "email exists";
        }
        if(usersEntity.getDescription() != null)
            usersService.updateDesc(usersEntity.getLogin() , usersEntity.getDescription());
        if(usersEntity.getJabber() != null)
            usersService.updateJabber(usersEntity.getLogin() , usersEntity.getJabber());
        return "updated";
    }



}
