package com.epam.esm.dto;

import com.epam.esm.domain.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

public class GiftCertificateDto {

    private int id;

    @Size(min=11 ,max = 100,message = "Certificate name should be between 11 and 100 characters")
    private String name;
    @Size(min = 20, max = 255, message = "Certificate description should be between 20 and 255 characters")
    private String description;
    @Positive
    private float price;
    @Positive
    private int duration;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createDate;
    private Set<Tag> tags;
    public GiftCertificateDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
