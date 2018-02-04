package kursa4.Filters;


import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;


import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;


@Provider
@PreMatching
public class CorsFilter  implements ContainerResponseFilter {

//        @Override
//        public ContainerResponse filter(ContainerRequest request,
//            ContainerResponse response) {
//
//        response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
//        response.getHttpHeaders().add("Access-Control-Allow-Headers",
//        "origin, content-type, accept, authorization");
//        response.getHttpHeaders().add("Access-Control-Allow-Credentials","true");
//        response.getHttpHeaders().add("Access-Control-Allow-Methods",
//        "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//
//        return response;
//        }

//    @Override
//    public void filter(ContainerRequestContext requestContext,
//                       ContainerResponseContext responseContext) throws IOException {
//        responseContext.getHeaders().add(
//                "Access-Control-Allow-Origin", "*");
//        responseContext.getHeaders().add(
//                "Access-Control-Allow-Credentials", "true");
//        responseContext.getHeaders().add(
//                "Access-Control-Allow-Headers",
//                "origin, content-type, accept, authorization");
//        responseContext.getHeaders().add(
//                "Access-Control-Allow-Methods",
//                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//    }
//private final static Logger log = Logger.getLogger( CorsFilter.class.getName() );
//
//    @Override
//    public void filter( ContainerRequestContext requestCtx, ContainerResponseContext responseCtx ) throws IOException {
//        log.info( "Executing REST response filter" );
//
//        responseCtx.getHeaders().add( "Access-Control-Allow-Origin", "*" );
//        responseCtx.getHeaders().add( "Access-Control-Allow-Credentials", "true" );
//        responseCtx.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
//        responseCtx.getHeaders().add( "Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
//    }
public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN_VALUE = "*";

    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE = "true";

    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS_VALUE = "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With, Accept";

    public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    public static final String ACCESS_CONTROL_ALLOW_METHODS_VALUE = "GET, POST, PUT, DELETE, OPTIONS, HEAD";

    public static final String[] ALL_HEADERs = {
            ACCESS_CONTROL_ALLOW_ORIGIN,
            ACCESS_CONTROL_ALLOW_CREDENTIALS,
            ACCESS_CONTROL_ALLOW_HEADERS,
            ACCESS_CONTROL_ALLOW_METHODS
    };
    public static final String[] ALL_HEADER_VALUEs = {
            ACCESS_CONTROL_ALLOW_ORIGIN_VALUE,
            ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE,
            ACCESS_CONTROL_ALLOW_HEADERS_VALUE,
            ACCESS_CONTROL_ALLOW_METHODS_VALUE
    };
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) {
        for (int i = 0; i < ALL_HEADERs.length; i++) {
            ArrayList<Object> value = new ArrayList<>();
            value.add(ALL_HEADER_VALUEs[i]);
            response.getHeaders().put(ALL_HEADERs[i], value); //using put method
        }

    }
}


