package com.epam.esm.domain;


/**
 * The type Certificates tags.
 */
public class CertificatesTags  {

    private int certificateId;
    private int tagId;

    /**
     * Instantiates a new Certificates tags.
     *
     * @param builder the builder
     */
    public CertificatesTags(Builder builder) {
        this.certificateId = builder.certificateId;
        this.tagId = builder.tagId;
    }

    /**
     * Gets certificate id.
     *
     * @return the certificate id
     */
    public int getCertificateId() {
        return certificateId;
    }

    /**
     * Sets certificate id.
     *
     * @param certificateId the certificate id
     */
    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    /**
     * Gets tag id.
     *
     * @return the tag id
     */
    public int getTagId() {
        return tagId;
    }

    /**
     * Sets tag id.
     *
     * @param tagId the tag id
     */
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private int certificateId;
        private int tagId;

        /**
         * New instance certificates tags . builder.
         *
         * @return the certificates tags . builder
         */
        public static CertificatesTags.Builder newInstance() {
            return new CertificatesTags.Builder();
        }

        private Builder() {
        }

        /**
         * Sets certificate id.
         *
         * @param certificateId the certificate id
         * @return the certificate id
         */
        public Builder setCertificateId(int certificateId) {
            this.certificateId = certificateId;
            return this;
        }

        /**
         * Sets tag id.
         *
         * @param tagId the tag id
         * @return the tag id
         */
        public Builder setTagId(int tagId) {
            this.tagId = tagId;
            return this;
        }

        /**
         * Build certificates tags.
         *
         * @return the certificates tags
         */
        public CertificatesTags build() {
            return new CertificatesTags(this);
        }
    }
}
