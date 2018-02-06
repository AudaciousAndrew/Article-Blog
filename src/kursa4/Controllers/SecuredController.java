package kursa4.Controllers;

import kursa4.DAO.ArticleDAO;
import kursa4.DAO.UsersDAO;
import kursa4.Entities.ArticleEntity;
import kursa4.Entities.UsersEntity;
import kursa4.response_models.articleName;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

@Path("secured")
public class SecuredController {

    final String [] types = {"image/png" , "image/jpeg" , ".png" , ".jpg" , ".jpeg"};
    final String uploadPath = "/home/andrew/Desktop/kursa4/web/resources/img/";

    @EJB
    private ArticleDAO articleService;

    @EJB
    private UsersDAO usersService;


    @POST
    @Path("/article/add")
    @RolesAllowed({"USER" , "ADMIN" , "MODERATOR"})
    @Produces(MediaType.TEXT_PLAIN)
    public String addArticle(ArticleEntity article){
        try{
//        articleService.create(
//                new ArticleEntity(article_name , article_type , article_desc ,
//                       usersService.readByLogin(login) ));
            articleService.create(article);
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
    public String updateVerified(articleName name){
        try{
        articleService.updateVerified(name.getName());
        return "true";
        }catch (Exception e){
            return "Error on server side";
        }

    }

    @POST
    @Path("/user/update")
    @RolesAllowed({"USER" , "MODERATOR" , "ADMIN" })
    @Produces(MediaType.TEXT_PLAIN)
    public String updateUserInfo(UsersEntity usersEntity){
        usersService.readByLogin(usersEntity.getLogin());
        usersService.update(usersEntity);
        return "true";
    }

    @POST
    @Path("/load/{login}")
    @RolesAllowed("USER")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload (
            @FormDataParam("file") InputStream uploadedInputStream ,
            @FormDataParam("file") FormDataContentDisposition fileDetail ,
            @PathParam("login") String login
    ) throws IOException {
        int t = 0;
        String type2 = "";
        String uploadedFileLocation = uploadPath + fileDetail.getFileName();
        String fileParsedType = fileDetail.getFileName().substring
                (fileDetail.getFileName().lastIndexOf(".") ,
                        fileDetail.getFileName().length());
        for(int i = 0 ; i < types.length ; i++){
            if(types[i].equals(fileParsedType)){
                t++;
                writeToFile(resizeImage(uploadedInputStream) , uploadPath+login);
                File file = new File(uploadedFileLocation);
                type2 = Files.probeContentType(file.toPath());
                for(int j = 0 ; j < types.length ; j++) {
                    if (types[j].equals(type2)) {
                        t++;
                        break;
                    }
                }
                break;
            }
        }
        if(t < 2) return Response.status(200).entity("unsuccess t: "+t+" type1: "+fileParsedType+" type2: "+type2).build();
        else{
            String resp = "successfully uploaded to: "+uploadedFileLocation +"\n "
                    +fileParsedType+ "   " + type2 + "   t:"+t;

            UsersEntity usersEntity = usersService.readByLogin(login);
            usersEntity.setAvatarpath(uploadPath+login);
            usersService.update(usersEntity);
            return Response.status(200).entity(resp).build();
        }
    }



    public static InputStream resizeImage(InputStream inputStream) throws IOException {
        BufferedImage sourceImage = ImageIO.read(inputStream);
        Image thumbnail = sourceImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        BufferedImage bufferedThumbnail = new BufferedImage(thumbnail.getWidth(null),
                thumbnail.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        bufferedThumbnail.getGraphics().drawImage(thumbnail, 0, 0, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedThumbnail, "png", baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }

    private void writeToFile(InputStream uploadedInputStream,
                             String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(
                    uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (NullPointerException x){
            x.printStackTrace();
        }

    }

}
