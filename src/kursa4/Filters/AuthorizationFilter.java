package kursa4.Filters;



import com.sun.jersey.core.util.Base64;
import kursa4.DAO.UserRolesDAO;
import kursa4.DAO.UsersDAO;
import kursa4.Entities.UserRolesEntity;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.StringTokenizer;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic";
    private static final String SECURED_URL_PREFIX = "secured";

    @Context
    private ResourceInfo resourceInfo;

    @EJB
    private UsersDAO service;

    @EJB
    private UserRolesDAO rolesService;

    private String login;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException{
        if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {


            Method method = resourceInfo.getResourceMethod();

            if (method.isAnnotationPresent(DenyAll.class)) {
                refuseRequest(requestContext);
            }

            RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
            if (rolesAllowed != null) {
                performAuthorization(rolesAllowed.value(), requestContext);
            }

            if (method.isAnnotationPresent(PermitAll.class)) {
                return;
            }

            rolesAllowed =
                    resourceInfo.getResourceClass().getAnnotation(RolesAllowed.class);

            if (rolesAllowed != null) {
                performAuthorization(rolesAllowed.value(), requestContext);
            }

            if (resourceInfo.getResourceClass().isAnnotationPresent(PermitAll.class)) {
                return;
            }

            if (!isAuthenticated(requestContext)) {
                refuseRequest(requestContext);
            }
        }
    }

    private void performAuthorization(String[] rolesAllowed  ,
                                      ContainerRequestContext requestContext)
                                                    throws AccessDeniedException , UnsupportedEncodingException{
     if (rolesAllowed.length > 0 && !isAuthenticated(requestContext)){
         refuseRequest(requestContext);
     }
    //TODO CHECK ROLE
     for(String role : rolesAllowed){
         for(UserRolesEntity entity: rolesService.readByLogin(login)){
             if(entity.getRole().equals(role)){
                 return;
             }
         }
     }
        refuseRequest(requestContext);
    }

    private boolean isAuthenticated(final ContainerRequestContext   requestContext) throws UnsupportedEncodingException{
        List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);

        if(authHeader != null && authHeader.size() > 0){
            String authToken = authHeader.get(0);
            authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX , "");
            String decodedString = new String(Base64.decode(authToken) , "UTF8");
            StringTokenizer tokenizer = new StringTokenizer(decodedString , ":");
            login = tokenizer.nextToken();
            String password = tokenizer.nextToken();
            return service.authorization(login,password);
        } else {
            refuseRequest(requestContext);
            return false;
        }


    }

    private void refuseRequest(final ContainerRequestContext requestContext) {
        Response unauthorizedStatus = Response
                                        .status(Response.Status.UNAUTHORIZED)
                                        .entity("Permission denied")
                                        .build();
        requestContext.abortWith(unauthorizedStatus);

    }

}
