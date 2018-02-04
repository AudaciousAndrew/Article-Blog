package kursa4.Controllers;

import kursa4.DAO.ArticleDAO;
import kursa4.DAO.UsersDAO;
import kursa4.Entities.ArticleEntity;
import kursa4.Entities.UsersEntity;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("secured")
public class SecuredController {

    @EJB
    private ArticleDAO articleService;

    @EJB
    private UsersDAO usersService;

    @GET
    @Path("test")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkRoles(){
        return "Hi admin";
    }

    @GET
    @Path("/user/{login}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public UsersEntity getUser(@PathParam("login") String login){
        return usersService.readByLogin(login);
    }

    @POST
    @Path("/article/add")
    @RolesAllowed({"USER" , "ADMIN" , "MODERATOR"})
    @Produces(MediaType.TEXT_PLAIN)
    public String addArticle(
            @FormParam("article_name") String article_name ,
            @FormParam("article_type") String article_type ,
            @FormParam("article_desc") String article_desc ,
            @FormParam("author") String login
    ){
        try{
        articleService.create(
                new ArticleEntity(article_name , article_type , article_desc ,
                       usersService.readByLogin(login) ));
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
    public String updateVerified(@FormParam("name") String name){
        try{
        articleService.updateVerified(name);
        return "true";
        }catch (Exception e){
            return "Error on server side";
        }

    }
}
