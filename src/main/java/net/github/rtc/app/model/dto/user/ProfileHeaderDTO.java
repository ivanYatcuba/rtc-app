package net.github.rtc.app.model.dto.user;

public class ProfileHeaderDTO {
    private String name;
    private String imageId;

    public ProfileHeaderDTO() { }

    public ProfileHeaderDTO(String name, String imageUrl) {
        this.name = name;
        this.imageId = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
