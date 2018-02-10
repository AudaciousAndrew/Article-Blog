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
        ArticleEntity articleEntity = service.readByName(name.getName());
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

    @POST
    @Path("/{login}/all/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleEntity> articleByUser(@PathParam("login") String login){
        return service.readByAuthor(login);
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
    @Path("/vote/{type}/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public voteResponse articlePlusVote(@PathParam("type") String type , @PathParam("login") String login , articleName name){
        voteResponse voteResponse;
        if(!voteService.ExistsByAuthorAndName(login,name)) {
            ArticleEntity articleEntity = service.readByName(name.getName());
            if(type.equals("plus")) {
                articleEntity.setRating(articleEntity.getRating() + 1);
            } else
                if(type.equals("minus")){
                    articleEntity.setRating(articleEntity.getRating() - 1);
                } else {
                    voteResponse = new voteResponse("false" , "wrong url");
                    return voteResponse;
                }
            UserArticleEntity userArticleEntity = new UserArticleEntity(login, name.getName());
            voteService.create(userArticleEntity);
            voteResponse = new voteResponse("true" , "null");
            return voteResponse;
        } else {
            voteResponse = new voteResponse("false" , "alrdy voted");
            return voteResponse;
        }

    }


//    @POST
//    @Path("/vote/minus/{login}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public voteResponse articleMinusVote(@PathParam("login") String login , articleName name){
//        voteResponse voteResponse;
//        if(!voteService.ExistsByAuthorAndName(login,name)) {
//            ArticleEntity articleEntity = service.readByName(name.getName());
//            articleEntity.setRating(articleEntity.getRating() - 1);
//            UserArticleEntity userArticleEntity = new UserArticleEntity(login, name.getName());
//            voteService.create(userArticleEntity);
//            voteResponse = new voteResponse("true" , "null");
//            return voteResponse;
//        } else {
//            voteResponse = new voteResponse("false" , "alrdy voted");
//            return voteResponse;
//        }
//
//    }

}
