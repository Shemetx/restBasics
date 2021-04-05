package com.epam.esm.domain;



public class Tag implements BaseEntity {

    private int id;
    private String name;

    public Tag() {}

    public Tag(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
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

    public static class Builder {
        private int id;
        private String name;
        public static Tag.Builder newInstance() {
            return new Tag.Builder();
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
        public Tag build() {
            return new Tag(this);
        }
    }
}
