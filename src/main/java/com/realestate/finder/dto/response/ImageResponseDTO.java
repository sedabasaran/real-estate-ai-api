package com.realestate.finder.dto.response;

import java.time.LocalDateTime;

public class ImageResponseDTO {

	private Long id;
    private String url;
    private LocalDateTime uploadedAt;

    public ImageResponseDTO() {}

    public ImageResponseDTO(Long id, String url, LocalDateTime uploadedAt) {
        this.id = id;
        this.url = url;
        this.uploadedAt = uploadedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
