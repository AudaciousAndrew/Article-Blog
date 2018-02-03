package kursa4.Controllers;

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
import com.sun.jersey.core.util.Base64;

@Path("/user")
public class UserController {

    @EJB
    private UsersDAO service;

    @EJB
    private UserRolesDAO rolesService;

    @POST
    @Path("/register")
    @Produces(MediaType.TEXT_PLAIN)
    public String register(@FormParam("login") String login
                        , @FormParam("password") String password
                        , @FormParam("email") String email){
        try {
            UsersEntity user = new UsersEntity(login, password, email);
            UserRolesEntity userRolesEntity = new UserRolesEntity(user , "USER");
            if(!service.existsByLogin(login)){
                if(!service.existsByEmail(email)){
                    service.create(user);
                    rolesService.create(userRolesEntity);
                    return "user "+login+" created";
                } else  return "email exists";
            } else return "login exists";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "smth wrong";
    }

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public String auth (@FormParam("login") String login, @FormParam("password") String password) throws Exception{
      if(service.authorization(login,password)) {
        String str = new String( Base64.encode(login+":"+password) , "UTF8");
      return"{\"Authorization\":\"success\",\"Token\":\""+str+"\"}";
      } else
          return "{\"Authorization\":\"failed\",\"Token\":\"null\"}";
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
