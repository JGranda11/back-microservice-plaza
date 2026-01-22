package com.pragma.challenge.msvc_plaza.domain.model;

public class Restaurant {

    private Long id;
    private String nit;
    private Long ownerId;
    private String name;
    private String address;
    private String phone;
    private String logoUrl;

    public Restaurant() {
    }

    public Restaurant(Long id,
                      String nit,
                      Long ownerId,
                      String name,
                      String address,
                      String phone,
                      String logoUrl) {
        this.id = id;
        this.nit = nit;
        this.ownerId = ownerId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.logoUrl = logoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    //Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private Long id;
        private String nit;
        private Long ownerId;
        private String name;
        private String address;
        private String phone;
        private String logoUrl;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nit(String nit) {
            this.nit = nit;
            return this;
        }

        public Builder ownerId(Long ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder logoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(
                    id,
                    nit,
                    ownerId,
                    name,
                    address,
                    phone,
                    logoUrl
            );
        }
    }
}
