package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 8/27/2018.
 */
public class TakatafMyTenderPageDTO {
    private int tender_id;
    private byte[] tender_logo;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "title should have at least 1 characters")
    private String tender_title;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "explain should have at least 1 characters")
    private String tender_explain;

    private @NotNull long tender_company_display_date;
    @NotNull
    private long tender_company_expired_date;

    private @NotNull long tender_display_date;
    @NotNull
    private long tender_expire_date;

    private int response_count;
    private int cat_num;

    public TakatafMyTenderPageDTO(int tender_id,byte[] tender_logo,
                                  @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String tender_title,
                                  @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String tender_explain,
                                  @NotNull long tender_display_date, @NotNull long tender_expire_date, long tender_company_display_date, long tender_company_expired_date,
                                  int response_count, int cat_num) {
        this.tender_id = tender_id;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.response_count = response_count;
        this.cat_num = cat_num;
        this.tender_company_display_date = tender_company_display_date;
        this.tender_company_expired_date = tender_company_expired_date;
        this.tender_logo = tender_logo;
    }

    public TakatafMyTenderPageDTO(byte[] tender_logo,
            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String tender_title,
            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String tender_explain,
            @NotNull long tender_display_date, @NotNull long tender_expire_date, long tender_company_display_date, long tender_company_expired_date,
            int response_count, int cat_num) {
        this.tender_id = tender_id;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.response_count = response_count;
        this.cat_num = cat_num;
        this.tender_company_display_date = tender_company_display_date;
        this.tender_company_expired_date = tender_company_expired_date;
    }

    public TakatafMyTenderPageDTO() {
    }

    public int getTender_id() {
        return tender_id;
    }

    public void setTender_id(int tender_id) {
        this.tender_id = tender_id;
    }


    public String getTender_title() {
        return tender_title;
    }

    public void setTender_title(String tender_title) {
        this.tender_title = tender_title;
    }

    public String getTender_explain() {
        return tender_explain;
    }

    public void setTender_explain(String tender_explain) {
        this.tender_explain = tender_explain;
    }

    public long getTender_display_date() {
        return tender_display_date;
    }

    public void setTender_display_date(long tender_display_date) {
        this.tender_display_date = tender_display_date;
    }

    public long getTender_expire_date() {
        return tender_expire_date;
    }

    public void setTender_expire_date(long tender_expire_date) {
        this.tender_expire_date = tender_expire_date;
    }

    public int getResponse_count() {
        return response_count;
    }

    public void setResponse_count(int response_count) {
        this.response_count = response_count;
    }

    public int getCat_num() {
        return cat_num;
    }

    public void setCat_num(int cat_num) {
        this.cat_num = cat_num;
    }

    public long getTender_company_display_date() {
        return tender_company_display_date;
    }

    public void setTender_company_display_date(long tender_company_display_date) {
        this.tender_company_display_date = tender_company_display_date;
    }

    public long getTender_company_expired_date() {
        return tender_company_expired_date;
    }

    public void setTender_company_expired_date(long tender_company_expired_date) {
        this.tender_company_expired_date = tender_company_expired_date;
    }

    public byte[] getTender_logo() {
        return tender_logo;
    }

    public void setTender_logo(byte[] tender_logo) {
        this.tender_logo = tender_logo;
    }
}
