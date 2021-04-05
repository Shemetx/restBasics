package com.epam.esm.domain;


public class CertificatesTags implements BaseEntity {

    private int certificateId;
    private int tagId;

    public CertificatesTags(Builder builder) {
        this.certificateId = builder.certificateId;
        this.tagId = builder.tagId;
    }

    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public static class Builder {
        private int certificateId;
        private int tagId;

        public static CertificatesTags.Builder newInstance() {
            return new CertificatesTags.Builder();
        }

        private Builder() {}

        public Builder setCertificateId(int certificateId) {
            this.certificateId = certificateId;
            return this;
        }
        public Builder setTagId(int tagId) {
            this.tagId = tagId;
            return this;
        }
        public CertificatesTags build() {
            return new CertificatesTags(this);
        }
    }
}
