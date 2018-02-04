package kursa4.Filters;


import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


@Provider
public class CorsFilter  implements ContainerResponseFilter{
    @Override
    public ContainerResponse filter(ContainerRequest req, ContainerResponse contResp) {

        Response.ResponseBuilder resp = Response.fromResponse(contResp.getResponse());
        resp.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");

        String reqHead = req.getHeaderValue("Access-Control-Request-Headers");

        if(null != reqHead && !reqHead.equals("")){
            resp.header("Access-Control-Allow-Headers", reqHead);
        }

        contResp.setResponse(resp.build());
        return contResp;
    }
}