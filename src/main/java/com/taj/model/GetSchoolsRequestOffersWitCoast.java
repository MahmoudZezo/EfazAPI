package com.taj.model;

import java.util.List;

/**
 * Created by User on 8/1/2018.
 */
public class GetSchoolsRequestOffersWitCoast {

    private double offerCoast;
    private List<SchoolRequestOfferModel> allSchoolRequestOffer;

    public GetSchoolsRequestOffersWitCoast() {
    }

    public GetSchoolsRequestOffersWitCoast(double offerCoast, List<SchoolRequestOfferModel> allSchoolRequestOffer) {
        this.offerCoast = offerCoast;
        this.allSchoolRequestOffer = allSchoolRequestOffer;
    }

    public double getOfferCoast() {
        return offerCoast;
    }

    public void setOfferCoast(double offerCoast) {
        this.offerCoast = offerCoast;
    }

    public List<SchoolRequestOfferModel> getAllSchoolRequestOffer() {
        return allSchoolRequestOffer;
    }

    public void setAllSchoolRequestOffer(List<SchoolRequestOfferModel> allSchoolRequestOffer) {
        this.allSchoolRequestOffer = allSchoolRequestOffer;
    }
}
