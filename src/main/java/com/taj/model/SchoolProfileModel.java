package com.taj.model;

/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
public class SchoolProfileModel {


    private int school_id;
    private String school_name;
    private byte[] school_logo_image;
    private String school_address;
    private String school_service_desc;
    private String school_link_youtube;
    private String school_website_url;
    private float school_lng, school_lat;

    public SchoolProfileModel(int school_id, String school_name, byte[] school_logo_image, String school_address,
                              String school_service_desc, String school_link_youtube, String school_website_url, float school_lng, float school_lat) {
        this.school_id = school_id;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
        this.school_address = school_address;
        this.school_service_desc = school_service_desc;
        this.school_link_youtube = school_link_youtube;
        this.school_website_url = school_website_url;
        this.school_lng = school_lng;
        this.school_lat = school_lat;
    }

    public SchoolProfileModel() {
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public byte[] getSchool_logo_image() {
        return school_logo_image;
    }

    public void setSchool_logo_image(byte[] school_logo_image) {
        this.school_logo_image = school_logo_image;
    }

    public String getSchool_address() {
        return school_address;
    }

    public void setSchool_address(String school_address) {
        this.school_address = school_address;
    }

    public String getSchool_service_desc() {
        return school_service_desc;
    }

    public void setSchool_service_desc(String school_service_desc) {
        this.school_service_desc = school_service_desc;
    }

    public String getSchool_link_youtube() {
        return school_link_youtube;
    }

    public void setSchool_link_youtube(String school_link_youtube) {
        this.school_link_youtube = school_link_youtube;
    }

    public String getSchool_website_url() {
        return school_website_url;
    }

    public void setSchool_website_url(String school_website_url) {
        this.school_website_url = school_website_url;
    }

    public float getSchool_lng() {
        return school_lng;
    }

    public void setSchool_lng(float school_lng) {
        this.school_lng = school_lng;
    }

    public float getSchool_lat() {
        return school_lat;
    }

    public void setSchool_lat(float school_lat) {
        this.school_lat = school_lat;
    }
}
