package com.epam.esm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.Period;

public class GiftCertificate implements BaseEntity{
    private int id;
    private String name;
    private String description;
    private float price;

    private int duration;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdateDate;

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public GiftCertificate() {

    }

    public GiftCertificate(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.duration = builder.duration;
        this.createDate = builder.createDate;
        this.lastUpdateDate = builder.lastUpdateDate;
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



    public static class Builder {
        private int id;
        private String name;
        private String description;
        private float price;
        private int duration;
        private LocalDateTime createDate;
        private LocalDateTime lastUpdateDate;

        public static GiftCertificate.Builder newInstance() {
            return new GiftCertificate.Builder();
        }

        private Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(float price) {
            this.price = price;
            return this;
        }
        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setCreateDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public Builder setLastUpdateDate(LocalDateTime lastUpdateDate) {
            this.lastUpdateDate = lastUpdateDate;
            return this;
        }

        public GiftCertificate build() {
            return new GiftCertificate(this);
        }
    }

    @Override
    public String toString() {

        return "GiftCertificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
