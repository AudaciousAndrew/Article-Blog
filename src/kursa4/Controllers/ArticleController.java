package kursa4.Controllers;

import kursa4.DAO.ArticleDAO;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/article")
public class ArticleController {

    @EJB
    private ArticleDAO service;

    @POST
    @Path("/byuser")
    @Produces(MediaType.TEXT_PLAIN)
    public String check(@FormParam("login") String login) {
        return service.readByAuthor(login).toString();
    }

    @POST
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String tt(){
        return service.readAll().toString();
    }
}
