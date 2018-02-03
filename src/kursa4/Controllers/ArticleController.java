package kursa4.Controllers;

import kursa4.DAO.ArticleDAO;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/article")
public class ArticleController {

    @EJB
    private ArticleDAO service;

    @GET
    @Path("/byuser")
    @Produces(MediaType.TEXT_PLAIN)
    public String check(){
        return service.readByAuthor("user2").toString();
    }

    @POST
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String tt(){
        return service.readAll().toString();
    }
}
