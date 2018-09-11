package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.*;
import com.taj.repository.NewProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 9/11/2018.
 */
@RequestMapping("/company/profil")
@RestController
@CrossOrigin
public class NewProfileController {

    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    @Autowired
    NewProfileRepo repo;
    @Autowired
    ObjectMapper mapper;

    @PostMapping("/")
    public ResponseEntity<ObjectNode> addProfileWithCategories(@RequestBody @Valid MultiCategoryProfileModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, 400);
            objectNode.put(MESSAGE, "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (repo.isExist(model.getCompany_id())) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, 400);
            objectNode.put(MESSAGE, "already has profile in this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            repo.addProfileWithCategories(model.getCompany_id(), model.getCompany_name(), model.getCompany_logo_image(),
                    model.getCompany_address(), model.getCompany_link_youtube(),
                    model.getCompany_website_url(), model.getCompany_lng(), model.getCompany_lat(),
                    model.getCompany_cover_image(), model.getCompany_phone_number(), model.getCompany_desc(), model.getCategory());

            ArrayNode category = mapper.createArrayNode();

            for (int i = 0; i < model.getCategory().size(); i++) {
                ObjectNode node = mapper.createObjectNode();
                node.put("category_name", model.getCategory().get(i).getCategory_name());
                category.add(node);
            }
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, 200);
            objectNode.put("company_id", model.getCompany_id());
            objectNode.put("company_name", model.getCompany_name());
            objectNode.put("company_logo_image", model.getCompany_logo_image());
            objectNode.put("company_address", model.getCompany_address());
            objectNode.put("company_link_youtube", model.getCompany_link_youtube());
            objectNode.put("company_website_url", model.getCompany_website_url());
            objectNode.put("company_lng", model.getCompany_lng());
            objectNode.put("company_lat", model.getCompany_lat());
            objectNode.put("company_cover_image", model.getCompany_cover_image());
            objectNode.put("company_phone_number", model.getCompany_phone_number());
            objectNode.put("company_desc", model.getCompany_desc());
            objectNode.set("categories", category);
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);


        }
    }

    @GetMapping("/")
    public List<NewProfileModel> getProfiles() {
        return repo.getProfiles();
    }

    @GetMapping("/{id}")
    public NewProfileDto3 getProfile(@PathVariable int id) {
        List<TakatfTenderCategoryPOJO> category = new ArrayList<>();
        List<NewProfileDto> allData = repo.getProfile(id);
        for (int i = 0; i < allData.size(); i++) {
            TakatfTenderCategoryPOJO pojo = new TakatfTenderCategoryPOJO(allData.get(i).getCompanyCatName());
            category.add(pojo);
        }
        NewProfileDto3 result = new NewProfileDto3(allData.get(0).getCompanyId(), allData.get(0).getCompanyName(),
                allData.get(0).getCompanyLogoImage(), allData.get(0).getCompanyAddress(),
                allData.get(0).getCompanyLinkYoutube(), allData.get(0).getCompanyWebsiteUrl(), allData.get(0).getCompanyLng(),
                allData.get(0).getCompanyLat(), allData.get(0).getCompanyCoverImage(), allData.get(0).getCompanyPhoneNumber(),
                allData.get(0).getFollowerCount(), allData.get(0).getOfferCount(), allData.get(0).getCompanyDesc(),
                allData.get(0).getCity(), allData.get(0).getArea(), category);
        return result;
    }


    @PutMapping("/")
    public ResponseEntity<ObjectNode> updateProfile(@RequestBody @Valid NewProfileDto3 model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, 400);
            objectNode.put(MESSAGE, "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (repo.isExist(model.getCompanyId())) {


            int res = repo.updateProfile(model.getCompanyId(), model.getCompanyName(), model.getCompanyLogoImage(), model.getCompanyAddress(),
                    model.getCompanyLinkYoutube(), model.getCompanyWebsiteUrl(), model.getCompanyLng(),
                    model.getCompanyLat(), model.getCompanyCoverImage(), model.getCompanyPhoneNumber(), model.getCompanyDesc(),
                    model.getCity(), model.getArea(),model.getCategory());
            if (res == 1) {
                ArrayNode category = mapper.createArrayNode();

                for (int i = 0; i < model.getCategory().size(); i++) {
                    ObjectNode node = mapper.createObjectNode();
                    node.put("category_name", model.getCategory().get(i).getCategory_name());
                    category.add(node);
                }
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put(STATUS, 200);
                objectNode.put("company_id", model.getCompanyId());
                objectNode.put("company_name", model.getCompanyName());
                objectNode.put("company_logo_image", model.getCompanyLogoImage());
                objectNode.put("company_address", model.getCompanyAddress());
                objectNode.put("company_link_youtube", model.getCompanyLinkYoutube());
                objectNode.put("company_website_url", model.getCompanyWebsiteUrl());
                objectNode.put("company_lng", model.getCompanyLng());
                objectNode.put("company_lat", model.getCompanyLat());
                objectNode.put("company_cover_image", model.getCompanyCoverImage());
                objectNode.put("company_phone_number", model.getCompanyPhoneNumber());
                objectNode.put("company_desc", model.getCompanyDesc());
                objectNode.put("city", model.getCity());
                objectNode.put("area", model.getArea());
                objectNode.set("categories", category);
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);


            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put(STATUS, 400);
                objectNode.put(MESSAGE, "failed");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }


        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, 400);
            objectNode.put(MESSAGE, "already has profile in this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<NewProfileDto4> getProfileByCategoryAndroid(@PathVariable String id) {
        List<NewProfileDto2> model = repo.getProfileByCategory(id);
        if (model.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new NewProfileDto4("200", model));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new NewProfileDto4("400", null));
        }
    }


}