package kursa4.Controllers;

import kursa4.DAO.ArticleDAO;
import kursa4.DAO.UserArticleDAO;
import kursa4.Entities.ArticleEntity;
import kursa4.Entities.UserArticleEntity;
import kursa4.response_models.articleName;
import kursa4.response_models.voteResponse;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
        ArticleEntity articleEntity = service.readByName(name.getName() , true);
        if(articleEntity == null) return null;
        else return articleEntity;
    }

    @POST
    @Path("/search/name")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleEntity> search(articleName name){
        List<ArticleEntity> list = service.searchByName(name.getName());
        if(list == null) return null;
        else return list;
    }

    @POST
    @Path("/type/{type}/{offset}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleEntity> articleByType(@PathParam("type") String type , @PathParam("offset") int offset){
        return service.readByTypeAndOffset(type , offset);
    }

    @POST
    @Path("/number/{type}")
    @Produces(MediaType.TEXT_PLAIN)
    public Number articlesCount(@PathParam("type") String type){
        return service.countByType(type);
    }

    @GET
    @Path("/{login}/all/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleEntity> articleByUser(@PathParam("login") String login){
        return service.readByAuthor(login , true);
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


}
