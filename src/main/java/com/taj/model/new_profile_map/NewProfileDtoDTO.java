package com.taj.model.new_profile_map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 9/30/2018.
 */
public class NewProfileDtoDTO {

    private int companyId;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "company_name should have at least 1 characters")
    private String companyName;
    @NotNull
    private byte[] companyLogoImage;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "address should have at least 1 characters")
    private String companyAddress;


    //    @NotNull
//    @NotBlank
//    @NotEmpty
//    @Size(max = 450, min = 1, message="youtube should have at least 1 characters")
    private String companyLinkYoutube;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "website should have at least 1 characters")
    private String companyWebsiteUrl;

    private float companyLng;
    private float companyLat;
    @NotNull
    private byte[] companyCoverImage;
    private String companyPhoneNumber;

    private int followerCount;
    private int offerCount;
    private String companyDesc;
    private int companyCatId;
    private String companyCatName;
    private String city;
    private String area;
    private float lng;
    private float lat;

    public NewProfileDtoDTO(int companyId, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "company_name should have at least 1 characters") String companyName, @NotNull byte[] companyLogoImage, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "address should have at least 1 characters") String companyAddress, String companyLinkYoutube, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "website should have at least 1 characters") String companyWebsiteUrl, float companyLng, float companyLat, @NotNull byte[] companyCoverImage, String companyPhoneNumber,
                            int followerCount, int offerCount, String companyDesc, int companyCatId, String companyCatName, String city, String area, float lng, float lat) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyLogoImage = companyLogoImage;
        this.companyAddress = companyAddress;
        this.companyLinkYoutube = companyLinkYoutube;
        this.companyWebsiteUrl = companyWebsiteUrl;
        this.companyLng = companyLng;
        this.companyLat = companyLat;
        this.companyCoverImage = companyCoverImage;
        this.companyPhoneNumber = companyPhoneNumber;
        this.followerCount = followerCount;
        this.offerCount = offerCount;
        this.companyDesc = companyDesc;
        this.companyCatId = companyCatId;
        this.companyCatName = companyCatName;
        this.city = city;
        this.area = area;
        this.lng = lng;
        this.lat = lat;
    }

    public NewProfileDtoDTO() {
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public byte[] getCompanyLogoImage() {
        return companyLogoImage;
    }

    public void setCompanyLogoImage(byte[] companyLogoImage) {
        this.companyLogoImage = companyLogoImage;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyLinkYoutube() {
        return companyLinkYoutube;
    }

    public void setCompanyLinkYoutube(String companyLinkYoutube) {
        this.companyLinkYoutube = companyLinkYoutube;
    }

    public String getCompanyWebsiteUrl() {
        return companyWebsiteUrl;
    }

    public void setCompanyWebsiteUrl(String companyWebsiteUrl) {
        this.companyWebsiteUrl = companyWebsiteUrl;
    }

    public float getCompanyLng() {
        return companyLng;
    }

    public void setCompanyLng(float companyLng) {
        this.companyLng = companyLng;
    }

    public float getCompanyLat() {
        return companyLat;
    }

    public void setCompanyLat(float companyLat) {
        this.companyLat = companyLat;
    }

    public byte[] getCompanyCoverImage() {
        return companyCoverImage;
    }

    public void setCompanyCoverImage(byte[] companyCoverImage) {
        this.companyCoverImage = companyCoverImage;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(int offerCount) {
        this.offerCount = offerCount;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public int getCompanyCatId() {
        return companyCatId;
    }

    public void setCompanyCatId(int companyCatId) {
        this.companyCatId = companyCatId;
    }

    public String getCompanyCatName() {
        return companyCatName;
    }

    public void setCompanyCatName(String companyCatName) {
        this.companyCatName = companyCatName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
