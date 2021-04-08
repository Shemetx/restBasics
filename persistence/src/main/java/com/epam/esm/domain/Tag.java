package com.epam.esm.domain;


/**
 * The type Tag.
 */
public class Tag implements BaseEntity {

    private int id;
    private String name;

    /**
     * Instantiates a new Tag.
     */
    public Tag() {
    }

    /**
     * Instantiates a new Tag.
     *
     * @param builder the builder
     */
    public Tag(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
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
     * The type Builder.
     */
    public static class Builder {
        private int id;
        private String name;

        /**
         * New instance tag . builder.
         *
         * @return the tag . builder
         */
        public static Tag.Builder newInstance() {
            return new Tag.Builder();
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
         * Build tag.
         *
         * @return the tag
         */
        public Tag build() {
            return new Tag(this);
        }
    }
}
