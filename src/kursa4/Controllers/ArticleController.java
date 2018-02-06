package kursa4.Controllers;

import kursa4.DAO.ArticleDAO;
import kursa4.DAO.UserArticleDAO;
import kursa4.Entities.ArticleEntity;
import kursa4.Entities.UserArticleEntity;
import kursa4.models.articleName;
import kursa4.models.voteResponse;
import org.glassfish.jersey.server.JSONP;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Path("/article")
public class ArticleController {

    @EJB
    private ArticleDAO service;

    @EJB
    private UserArticleDAO voteService;

    @POST
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    public ArticleEntity articleByName(articleName name){
        return service.readByName(name.getName());
    }

    @POST
    @Path("/author/{author}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArticleEntity articleByAuthor(@PathParam("author") String login){
        return service.readByAuthor(login);
    }

    @POST
    @Path("/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleEntity> articleByType(@PathParam("type") String type){
        return service.readByType(type);
    }


    @GET
    @Path("/all/verified")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleEntity> articleVerified(){
        return service.readVerified();
    }

    @GET
    @Path("/top10")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleEntity> articleTopByRating(){
        return service.topTen();
    }

    @POST
    @Path("/vote/plus/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public voteResponse articlePlusVote(@PathParam("login") String login , articleName name){
        voteResponse voteResponse;
        if(!voteService.ExistsByAuthorAndName(login,name)) {
            ArticleEntity articleEntity = service.readByName(name.getName());
            articleEntity.setRating(articleEntity.getRating() + 1);
            UserArticleEntity userArticleEntity = new UserArticleEntity(login, name.getName());
            voteService.create(userArticleEntity);
            voteResponse = new voteResponse("true" , "null");
            return voteResponse;
        } else {
            voteResponse = new voteResponse("false" , "alrdy voted");
            return voteResponse;
        }

    }

    @POST
    @Path("/vote/minus/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public voteResponse articleMinusVote(@PathParam("login") String login , articleName name){
        voteResponse voteResponse;
        if(!voteService.ExistsByAuthorAndName(login,name)) {
            ArticleEntity articleEntity = service.readByName(name.getName());
            articleEntity.setRating(articleEntity.getRating() - 1);
            UserArticleEntity userArticleEntity = new UserArticleEntity(login, name.getName());
            voteService.create(userArticleEntity);
            voteResponse = new voteResponse("true" , "null");
            return voteResponse;
        } else {
            voteResponse = new voteResponse("false" , "alrdy voted");
            return voteResponse;
        }

    }

}
