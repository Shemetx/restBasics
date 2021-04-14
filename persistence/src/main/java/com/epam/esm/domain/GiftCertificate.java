package com.epam.esm.domain;

import java.time.LocalDateTime;

/**
 * The type Gift certificate.
 */
public class GiftCertificate {
    private int id;
    private String name;
    private String description;
    private float price;

    private int duration;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdateDate;

    /**
     * Gets last update date.
     *
     * @return the last update date
     */
    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Sets last update date.
     *
     * @param lastUpdateDate the last update date
     */
    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * Instantiates a new Gift certificate.
     */
    public GiftCertificate() {

    }

    /**
     * Instantiates a new Gift certificate.
     *
     * @param builder the builder
     */
    public GiftCertificate(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.duration = builder.duration;
        this.createDate = builder.createDate;
        this.lastUpdateDate = builder.lastUpdateDate;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }


    /**
     * The type Builder.
     */
    public static class Builder {
        private int id;
        private String name;
        private String description;
        private float price;
        private int duration;
        private LocalDateTime createDate;
        private LocalDateTime lastUpdateDate;

        /**
         * New instance gift certificate . builder.
         *
         * @return the gift certificate . builder
         */
        public static GiftCertificate.Builder newInstance() {
            return new GiftCertificate.Builder();
        }

        private Builder() {
        }

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets description.
         *
         * @param description the description
         * @return the description
         */
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets price.
         *
         * @param price the price
         * @return the price
         */
        public Builder setPrice(float price) {
            this.price = price;
            return this;
        }

        /**
         * Sets duration.
         *
         * @param duration the duration
         * @return the duration
         */
        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        /**
         * Sets create date.
         *
         * @param createDate the create date
         * @return the create date
         */
        public Builder setCreateDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        /**
         * Sets last update date.
         *
         * @param lastUpdateDate the last update date
         * @return the last update date
         */
        public Builder setLastUpdateDate(LocalDateTime lastUpdateDate) {
            this.lastUpdateDate = lastUpdateDate;
            return this;
        }

        /**
         * Build gift certificate.
         *
         * @return the gift certificate
         */
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
