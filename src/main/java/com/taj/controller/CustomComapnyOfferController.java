package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.CustomCompanyOfferModel;
import com.taj.model.CustomeCompanyOfferModel2;
import com.taj.model.getCustomeCompanyOffer;
import com.taj.model.new_company_history.CompanyHistoryDto;
import com.taj.model.new_company_history.CompanyHistoryDto2;
import com.taj.model.offer_description.CustomCompanyModelWithViewAndDescRes;
import com.taj.model.offer_description.getCustomeOffer2;
import com.taj.repository.CustomCompanyOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 8/5/2018.
 */
@RequestMapping("/company/offer")
@RestController
@CrossOrigin
public class CustomComapnyOfferController {
    @Autowired
    CustomCompanyOfferRepo repo;
    @Autowired
    ObjectMapper mapper;

    /**
     * add offer from company
     *
     * @param model
     * @return 1 if success or 0 if failed
     */

    @PostMapping("/")
    public ResponseEntity<ObjectNode> addCompanyOffers(@Valid @RequestBody CustomCompanyOfferModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            int res = repo.addOfferEdeited(model.getImage_one(), model.getImage_two(), model.getImage_third(), model.getImage_four(), model.getOffer_title(), model.getOffer_explaination(),
                    model.getOffer_cost(), model.getOffer_display_date(), model.getOffer_expired_date(), model.getOffer_deliver_date(),
                    model.getCompany_id(), model.getOffer_count(), model.getCity(), model.getArea(), model.getLng(), model.getLat());
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 200);
                //objectNode.put("offer_images_id", model.getOffer_images_id());
                objectNode.put("offer_title", model.getOffer_title());
                objectNode.put("offer_explaination", model.getOffer_explaination());
                objectNode.put("offer_cost", model.getOffer_cost());
                objectNode.put("offer_display_date", model.getOffer_display_date().toString());
                objectNode.put("offer_expired_date", model.getOffer_expired_date().toString());
                objectNode.put("offer_deliver_date", model.getOffer_deliver_date().toString());
                objectNode.put("company_id", model.getCompany_id());
                objectNode.put("offer_count", model.getOffer_count());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        }

    }

    @GetMapping("/")
    public List<CustomCompanyOfferModel> getCompanyOffers() {
        return repo.getAllOffers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<getCustomeOffer2> getCompanyOffer(@PathVariable int id) {
        if (repo.checkIfExist(id)) {
            CustomCompanyModelWithViewAndDescRes model = repo.getCompanyOfferWithDesc(id);

            return ResponseEntity.status(HttpStatus.OK).body(new getCustomeOffer2("200", model));

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getCustomeOffer2("400", null));
        }

    }


    @GetMapping("/{id}/company")
    public ResponseEntity<getCustomeCompanyOffer> getSingleCompanyOffer(@PathVariable int id) {
        List<CustomeCompanyOfferModel2> offers = repo.getCompanyOffers(id);
        if (offers != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new getCustomeCompanyOffer("200", offers));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getCustomeCompanyOffer("400", offers));
        }

    }


    @GetMapping("/get/{id}")
    public ResponseEntity<List<CustomeCompanyOfferModel2>> getSingleCompanyOffers(@PathVariable int id) {
        List<CustomeCompanyOfferModel2> offers = repo.getCompanyOffers(id);
        return ResponseEntity.status(HttpStatus.OK).body(offers);
    }


    /**
     * update offer of company
     *
     * @param model
     * @return 1 if success or 0 if failed
     */
    @PutMapping("/")
    public ResponseEntity<JsonNode> updateCompanyOfferWithImage(@Valid @RequestBody CustomCompanyOfferModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            //objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (repo.checkIfExist(model.getOffer_id())) {
            int res = repo.updateCompanyOfferWithImages(model.getOffer_id(), model.getImage_one(), model.getImage_two(), model.getImage_third(), model.getImage_four(),
                    model.getOffer_title(), model.getOffer_explaination(), model.getOffer_cost(), model.getOffer_display_date(),
                    model.getOffer_expired_date(), model.getOffer_deliver_date(), model.getCompany_id(), model.getOffer_count(), model.getCity(), model.getArea(), model.getLng(), model.getLat());

            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 200);
                objectNode.put("offer_id", model.getOffer_id());
                objectNode.put("image_one", model.getImage_one());
                objectNode.put("image_two", model.getImage_two());
                objectNode.put("image_three", model.getImage_third());
                objectNode.put("image_four", model.getImage_four());
                objectNode.put("offer_title", model.getOffer_title());
                objectNode.put("offer_explaination", model.getOffer_explaination());
                objectNode.put("offer_cost", model.getOffer_cost());
                objectNode.put("offer_display_date", model.getOffer_display_date());
                objectNode.put("offer_expired_date", model.getOffer_expired_date());
                objectNode.put("offer_deliver_date", model.getOffer_deliver_date());
                objectNode.put("company_id", model.getCompany_id());
                objectNode.put("offer_count", model.getOffer_count());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not exist");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }


    /**
     * delete offer by offer id
     *
     * @param id
     * @return 1 if success or 0 if failed
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectNode> deleteCompanyOffer(@PathVariable int id) {

        if (repo.checkIfExist(id)) {
            int res = repo.deleteCompanyOffer(id);
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "success");

                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    @GetMapping("/history/{companyId}")
    public List<CompanyHistoryDto> getCompanyHistory(@PathVariable int companyId) {
        return repo.getCompanyHistory(companyId);
    }

    @GetMapping("/history2/{companyId}")
    public List<CompanyHistoryDto2> getCompanyHistory2(@PathVariable int companyId) {
        return repo.getCompanyHistory2(companyId);
    }


    @GetMapping("/{id}/count")
    public int getCompanyOfferCount(@PathVariable int id) {
        return repo.getCompanyOfferCount(id);
    }


}
