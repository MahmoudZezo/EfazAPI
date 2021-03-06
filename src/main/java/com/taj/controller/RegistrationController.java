package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.RegistrationModel;
import com.taj.model.UserType;
import com.taj.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by MahmoudAhmed on 5/30/2018.
 */
@RequestMapping("/register")
@RestController
@CrossOrigin
public class RegistrationController {

    @Autowired
    RegistrationRepo registrationRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * register company
     *
     * @param registrationModel
     */


    @Autowired
    ObjectMapper mapper;
//    @GetMapping("/gets/{email}")
//    public boolean checkIfEmailExist(@PathVariable String email){
//        return  registrationRepo.checkIfEmailExist(email);
//    }


    @GetMapping("ss")
    public ResponseEntity get(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ObjectNode> addUserRegistration(@Valid @RequestBody RegistrationModel registrationModel, Errors errors) {

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }else if (registrationRepo.checkIfEmailExist(registrationModel.getRegisteration_email(), registrationModel.getRegistration_role())){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Email is exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        if (registrationModel.getRegistration_role().trim().compareTo(UserType.admin.toString())==0 ||
                registrationModel.getRegistration_role().trim().compareTo(UserType.school.toString())==0 ||
                registrationModel.getRegistration_role().trim().compareTo(UserType.company.toString())==0) {

            String encodedPassword = bCryptPasswordEncoder.encode(registrationModel.getRegisteration_password());
            RegistrationModel model = registrationRepo.addUser(registrationModel.getRegisteration_email(), encodedPassword,
                    registrationModel.getRegisteration_username(), registrationModel.getRegisteration_phone_number(),
                    registrationModel.getRegistration_organization_name(), registrationModel.getRegistration_address_desc(),
                    registrationModel.getRegistration_website_url(),
                    registrationModel.getRegistration_isActive(), registrationModel.getRegistration_role(), new Timestamp(System.currentTimeMillis()).getTime());
            if (model != null) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("states", 201);
                objectNode.put("registeration_email", registrationModel.getRegisteration_email());
                objectNode.put("registeration_password", registrationModel.getRegisteration_password());
                objectNode.put("registeration_username", registrationModel.getRegisteration_username());
                objectNode.put("registeration_phone_number", registrationModel.getRegisteration_phone_number());
                objectNode.put("registration_organization_name", registrationModel.getRegistration_organization_name());
                objectNode.put("registration_address_desc", registrationModel.getRegistration_address_desc());
                objectNode.put("registration_website_url", registrationModel.getRegistration_website_url());
                objectNode.put("registration_isActive", registrationModel.getRegistration_isActive());
                objectNode.put("registration_role", registrationModel.getRegistration_role());
                objectNode.put("registration_date", new Timestamp(System.currentTimeMillis()).getTime());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("states", 400);
                objectNode.put("message", "sign up error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        }else {

            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("states", 400);
            objectNode.put("message", "not valid role");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);

        }


    }

    /**
     * get all registered companies data
     *
     * @return List of registered company data
     */

    //@PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/getAll")
    public List<RegistrationModel> getAllUsers() {
        return registrationRepo.getUsers();
    }

    /**
     * get register company data by id
     *
     * @param id
     * @return
     */

    //@PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/get/{id}")
    public RegistrationModel getUser(@PathVariable int id) {
        return registrationRepo.getUser(id);
    }

    /**
     * update registered data
     *
     * @param registrationModel
     * @return
     */

    @PutMapping("/update")
    public ResponseEntity<ObjectNode> updateUser(@Valid @RequestBody RegistrationModel registrationModel, Errors errors2) {

        if (errors2.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors2.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        int res = registrationRepo.updateUser(registrationModel.getRegistration_id(), registrationModel.getRegisteration_email(), registrationModel.getRegisteration_password(),
                registrationModel.getRegisteration_username(), registrationModel.getRegisteration_phone_number(), registrationModel.getRegistration_organization_name(), registrationModel.getRegistration_address_desc(),
                registrationModel.getRegistration_website_url(), registrationModel.getRegistration_isActive(), registrationModel.getRegistration_role());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("registration_id", registrationModel.getRegistration_id());
            objectNode.put("registeration_email", registrationModel.getRegisteration_email());
            objectNode.put("registeration_password", registrationModel.getRegisteration_password());
            objectNode.put("registeration_username", registrationModel.getRegisteration_username());
            objectNode.put("registeration_phone_number", registrationModel.getRegisteration_phone_number());
            objectNode.put("registration_organization_name", registrationModel.getRegistration_organization_name());
            objectNode.put("registration_address_desc", registrationModel.getRegistration_address_desc());
            objectNode.put("registration_website_url", registrationModel.getRegistration_website_url());
            objectNode.put("registration_isActive", registrationModel.getRegistration_isActive());
            objectNode.put("registration_role", registrationModel.getRegistration_role());
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }

    /**
     * delete registered company by register id
     *
     * @param id
     * @return
     */
    //@PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @PutMapping("/delete/{id}")
    public ResponseEntity<ObjectNode> deleteUser(@PathVariable int id) {
        int res = registrationRepo.deleteUser(id);

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }


    /**
     * get list of all inactive companies
     *
     * @return
     */
    //@PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/getInActive")
    public List<RegistrationModel> getInActiveCompaines() {
        return registrationRepo.getInActiveCompanies();
    }

//    /**
//     * active user by admin and send mail to him
//     *
//     * @param id
//     */
//    @PutMapping("/activeCompanyUser/{id}")
//    public void activeCompanyAccount(@PathVariable int id) {
//        registrationRepo.activeCompanyAccount(id);
//    }

    /**
     * get list of all active companies
     *
     * @return
     */

    //@PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/getActive")
    public List<RegistrationModel> getActiveCompaines() {
        return registrationRepo.getActiveCompanies();
    }

//    /**
//     * inactive user by admin and send mail to him
//     *
//     * @param id
//     */
//    @PutMapping("/inActiveCompanyUser/{id}")
//    public void inActiveCompanyAccount(@PathVariable int id) {
//        registrationRepo.inActiveCompanyAccount(id);
//    }

    /**
     * confirm company account and send mail to user to inform him
     *
     * @param id
     */
    //@PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/confirm/{id}")
    public ObjectNode confirmEmail(@PathVariable int id) {
        int res = registrationRepo.confirmEmail(id);

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }


}
