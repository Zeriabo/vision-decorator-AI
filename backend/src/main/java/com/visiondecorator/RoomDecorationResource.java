package com.visiondecorator;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/decorate-room")
public class RoomDecorationResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response decorateRoom(RoomDecorationRequest request) {
        // 1. Extract base64 image & style
        String base64Image = request.getImageBase64();
        String style = request.getStyle();

        // 2. Call AI service to process image (mock here)
        String decoratedImageUrl = callAIModel(base64Image, style);

        // 3. Return result
        return Response.ok(new RoomDecorationResponse(decoratedImageUrl)).build();
    }

    private String callAIModel(String base64Image, String style) {
        // TODO: Integrate AI model or external API here.
        // For demo, return a placeholder image URL.
        return "https://example.com/decorated-room-image.jpg";
    }
}

class RoomDecorationRequest {
    private String imageBase64;
    private String style;

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}

class RoomDecorationResponse {
    private String decoratedImageUrl;

    public RoomDecorationResponse(String url) {
        this.decoratedImageUrl = url;
    }

    public String getDecoratedImageUrl() {
        return decoratedImageUrl;
    }
}
