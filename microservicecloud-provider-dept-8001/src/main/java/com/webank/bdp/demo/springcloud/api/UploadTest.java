package com.webank.bdp.demo.springcloud.api;


import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.*;

@Component
@Path("/api")
public class UploadTest {

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(){
        return "hello";
    }


    @POST
    @Path("/upload")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploda(@FormDataParam("file") InputStream fis,
                         @FormDataParam("file") FormDataContentDisposition fileDisposition, @Context HttpHeaders head) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("/opt/tmp/"+fileDisposition.getFileName());
        IOUtils.copy(fis,fileOutputStream);
        IOUtils.closeQuietly(fileOutputStream);
        IOUtils.closeQuietly(fis);
        return "ok";
    }
}
