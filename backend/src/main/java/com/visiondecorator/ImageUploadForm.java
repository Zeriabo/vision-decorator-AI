package com.visiondecorator;

import jakarta.ws.rs.FormParam;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public class ImageUploadForm {

    @FormParam("file")
    @PartType("image/jpeg")
    public FileUpload file;
}
