package com.taj.repository;

import com.taj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 8/15/2018.
 */
@Repository
public class SchoolRequestNewRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addRequest(String request_title, String request_explaination,
                          long request_display_date, long request_expired_date, int school_id,
                          String request_category_id) {

//        int category = jdbcTemplate.queryForObject("SELECT request_category_id  FROM efaz_company.efaz_school_request_category WHERE  request_category_name=?;",
//                Integer.class, request_category_id);

        int category = jdbcTemplate.queryForObject("SELECT request_category_id  FROM efaz_company.efaz_school_request_category WHERE  request_category_name LIKE ?;",
                Integer.class, "%" + request_category_id + "%");

        return jdbcTemplate.update("INSERT INTO efaz_school_tender VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", null, null, null, request_title,
                request_explaination, new Timestamp(request_display_date), new Timestamp(request_expired_date), null, null, null,
                null, school_id, category, null, null, null);
    }

    public List<SchoolRequestsDTO> getRequests() {

        String sql = "SELECT " +
                "request_id, request_title, request_explaination, request_display_date, " +
                "    request_expired_date, school_id," +
                "    request_category_name" +
                " FROM efaz_school_tender AS tender INNER JOIN" +
                "                        efaz_company.efaz_school_request_category AS cat" +
                "                         ON" +
                "                         tender.requests_category_id = cat.request_category_id;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new SchoolRequestsDTO(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime()
                        , resultSet.getInt(6), resultSet.getString(7)));
    }


    public List<SchoolRequestNewDto> getRequestsAll() {

        String sql = "SELECT " +
                "request_id, request_title, request_explaination, request_display_date, " +
                "    request_expired_date, school_id," +
                "    request_category_name, " +
                "    count(distinct responsed_request_id) AS response_count" +
                " FROM efaz_school_tender AS tender INNER JOIN" +
                "                        efaz_company.efaz_school_request_category AS cat" +
                "                         ON" +
                "                         tender.requests_category_id = cat.request_category_id" +
                "                         LEFT JOIN efaz_company.efaz_company_response_school_request AS req" +
                "                         ON tender.request_id = req.responsed_request_id" +
                "                         GROUP BY request_id;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new SchoolRequestNewDto(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime()
                        , resultSet.getInt(6), resultSet.getString(7), resultSet.getInt(8)));
    }


    public SchoolRequestNewDtoById getRequestByID(int id) {


        String sql = "SELECT \n" +
                "                tender.request_id, request_title, request_explaination, request_display_date, \n" +
                "                    request_expired_date, tender.school_id, school_name, school_logo_image, \n" +
                "                    request_category_name, \n" +
                "                    count( responsed_request_id) AS response_count, count(seen_id)AS views_count\n" +
                "                 FROM efaz_school_tender AS tender INNER JOIN\n" +
                "                                        efaz_company.efaz_school_request_category AS cat\n" +
                "                                         ON\n" +
                "                                         tender.requests_category_id = cat.request_category_id\n" +
                "                                        LEFT JOIN efaz_company.efaz_company_response_school_request AS req\n" +
                "                                         ON tender.request_id = req.responsed_request_id\n" +
                "                                         LEFT JOIN efaz_company.efaz_school_profile AS sp ON tender.school_id = sp.school_id\n" +
                "                                         Left Join efaz_company.efaz_company_see_request AS reqst ON  tender.request_id = reqst.request_id \n" +
                "                                       WHERE  tender.request_id=? \n" +
                "                 GROUP BY tender.request_id, request_title, request_explaination, request_display_date, \n" +
                "                    request_expired_date, tender.school_id, school_name, school_logo_image, \n" +
                "                    request_category_name;";


//        String sql = "SELECT " +
//                "request_id, request_title, request_explaination, request_display_date, " +
//                "    request_expired_date, school_id," +
//                "    request_category_name" +
//                " FROM efaz_school_tender AS tender INNER JOIN" +
//                "                        efaz_company.efaz_school_request_category AS cat" +
//                "                         ON" +
//                "                         tender.request_category_id = cat.request_category_id"+
//                " WHERE  request_id=?;";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id}, (resultSet, i) -> new SchoolRequestNewDtoById(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime()
                        , resultSet.getInt(6), resultSet.getString(7), resultSet.getBytes(8), resultSet.getString(9),
                        resultSet.getInt(10), resultSet.getInt(11)));
    }


    public List<SchoolRequestNewDto> getRequestsBySchoolID(int id) {


        String sql = "SELECT " +
                "                request_id, request_title, request_explaination, request_display_date, " +
                "                    request_expired_date, school_id, " +
                "                    request_category_name,  " +
               // " req.responsed_company_id, " +
                "                    count( responsed_request_id) AS response_count, company_name, company_logo_image, company_category_id" +
                "                 ,responsed_cost, response_date " +
                "                 FROM efaz_school_tender AS tender INNER JOIN " +
                "                                        efaz_company.efaz_school_request_category AS cat " +
                "                                         ON " +
                " tender.requests_category_id = cat.request_category_id " +
                "                                         LEFT JOIN efaz_company.efaz_company_response_school_request AS req " +
                "                                        ON tender.request_id = req.responsed_request_id " +
                "                                        Left JOIN efaz_company.efaz_company_profile AS p ON req.responsed_company_id = p.company_id " +
                "                                       WHERE  tender.school_id=?  " +
                "                  GROUP BY request_id,request_title, request_explaination, request_display_date,request_expired_date, school_id, " +
                " request_category_name,company_name, company_logo_image, company_category_id,responsed_cost, response_date;";


        return jdbcTemplate.query(sql,
                new Object[]{id}, (resultSet, i) -> new SchoolRequestNewDto(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime()
                        , resultSet.getInt(6), resultSet.getString(7), resultSet.getInt(8)));
    }

    public List<getSchoolCustomRequestById> getRequestOfSchoolByID(int id) {


        String sql = "SELECT  \n" +
                "                               request_id, request_title, request_explaination, request_display_date, \n" +
                "                request_expired_date, school_id, \n" +
                "                                request_category_name,  \n" +
                "                                count( responsed_request_id) AS response_count, ifnull(company_name,0) as company_name,\n" +
                "                                ifnull(company_logo_image,0)as company_name,\n" +
                "                              ifnull(category_name,0) as category_name\n" +
                "                                 ,ifnull(responsed_cost,0)as responsed_cost, ifnull(response_date,NOW()) \n" +
                "                                 as  response_date, ifnull(response_id,0) AS response_id, ifnull(responsed_company_id,0) AS responsed_company_id \n" +
                "                                 FROM efaz_school_tender AS tender  \n" +
                "\t\t\t\t\t\t\t\tLEFT JOIN efaz_company.efaz_company_response_school_request AS req \n" +
                "                                ON tender.request_id = req.responsed_request_id \n" +
                "                                Left JOIN efaz_company.efaz_company_profile as cp ON req.responsed_company_id = cp.company_id \n" +
                "                                Left JOIN \n" +
                "                                efaz_company.efaz_school_request_category AS cat\n" +
                "                                 ON \n" +
                "                                 tender.requests_category_id = cat.request_category_id \n" +
                "                                  Left JOIN \n" +
                "                                efaz_company.efaz_company_category AS cc \n" +
                "                                ON \n" +
                "                                 cp.company_category_id = cc.category_id \n" +
                "                                WHERE  request_id=?\n" +
                "                                  GROUP BY request_id, request_title, request_explaination, request_display_date,request_expired_date, school_id," +
                "                                 request_category_name,company_name, company_logo_image, category_name,responsed_cost, response_date, response_id, responsed_company_id;";

        String sql1 = "SELECT  " +
                "                request_id, request_title, request_explaination, request_display_date, " +
                " request_expired_date, school_id, " +
                "                request_category_name,  " +
                "                count( responsed_request_id) AS response_count, company_name, company_logo_image, category_name " +
                "                 ,responsed_cost, response_date  " +
                "                 FROM efaz_school_tender AS tender  " +
                "                 LEFT JOIN efaz_company.efaz_company_response_school_request AS req " +
                "                ON tender.request_id = req.responsed_request_id " +
                "                Left JOIN efaz_company.efaz_company_profile as cp ON req.responsed_company_id = cp.company_id " +
                "                Left JOIN " +
                "                efaz_company.efaz_school_request_category AS cat " +
                "                 ON " +
                "                 tender.requests_category_id = cat.request_category_id " +
                "                  Left JOIN " +
                "                efaz_company.efaz_company_category AS cc " +
                "                 ON " +
                "                 cp.company_category_id = cc.category_id " +
                "                WHERE  request_id=?" +
                "                  GROUP BY request_id, request_title, request_explaination, request_display_date,request_expired_date, school_id," +
                "                 request_category_name,company_name, company_logo_image, category_name,responsed_cost, response_date;";


        return jdbcTemplate.query(sql,
                new Object[]{id}, (resultSet, i) -> new getSchoolCustomRequestById(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime()
                        , resultSet.getInt(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getBytes(10),
                        resultSet.getString(11), resultSet.getDouble(12), resultSet.getTimestamp(13).getTime(), resultSet.getInt(14), resultSet.getInt(15)));
    }



    public List<SchoolRequestNewDto> getRequestsByCategoryID(String id) {


        int category = jdbcTemplate.queryForObject("SELECT request_category_id  FROM efaz_company.efaz_school_request_category WHERE  request_category_name LIKE ?;",
                Integer.class, "%" + id + "%");


        String sql = "SELECT " +
                "request_id, request_title, request_explaination, request_display_date, " +
                "    request_expired_date, school_id," +
                "    request_category_name, " +
                "    count( responsed_request_id) AS response_count" +
                " FROM efaz_school_tender AS tender INNER JOIN" +
                "                        efaz_company.efaz_school_request_category AS cat" +
                "                         ON" +
                "                         tender.requests_category_id = cat.request_category_id" +
                "                         LEFT JOIN efaz_company.efaz_company_response_school_request AS req" +
                "                         ON tender.request_id = req.responsed_request_id" +
                "                      WHERE  tender.requests_category_id =?   " +
                "  GROUP BY request_id;";


//        String sql = "SELECT " +
//                "request_id, request_title, request_explaination, request_display_date, " +
//                "    request_expired_date, school_id," +
//                "    request_category_name" +
//                " FROM efaz_school_tender AS tender INNER JOIN" +
//                "                        efaz_company.efaz_school_request_category AS cat" +
//                "                         ON" +
//                "                         tender.request_category_id = cat.request_category_id"+
//                " WHERE  tender.request_category_id = ?;";

        return jdbcTemplate.query(sql,
                new Object[]{category}, (resultSet, i) -> new SchoolRequestNewDto(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime()
                        , resultSet.getInt(6), resultSet.getString(7), resultSet.getInt(8)));
    }


    public int updateRequest(int request_id, String request_title, String request_explaination,
                             long request_display_date, long request_expired_date, int school_id,
                             String request_category_id) {

        int category = jdbcTemplate.queryForObject("SELECT request_category_id  FROM efaz_company.efaz_school_request_category WHERE  request_category_name LIKE ?;",
                Integer.class, "%" + request_category_id + "%");

        return jdbcTemplate.update("update efaz_school_tender set request_details_file=?," + " images_id=?, request_title=?," +
                        "request_explaination=?," + " request_display_date=?, request_expired_date=?, request_deliver_date=?," +
                        "request_payment_date=?, request_is_available=?, request_is_conformied=?, school_id=?, requests_category_id=?," +
                        " receive_palce_id=?, extended_payment=?, request_count=? " +
                        " where request_id=?;", null, null, request_title, request_explaination, new Timestamp(request_display_date)
                , new Timestamp(request_expired_date), null, null, null, null, school_id, category,
                null, null, null, request_id);

    }

    public int deleteSchoolRequest(int id) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        int res = jdbcTemplate.update("DELETE FROM efaz_school_tender WHERE request_id=?", id);
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
        return res;
    }


    public boolean isExist(int id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_school_tender WHERE request_id=?;",
                Integer.class, id);
        return cnt != null && cnt > 0;
    }


    public List<SchoolRequestNewDtoWitCompany> getSingleTenderDetails(int request_id) {


//
        String sql = "SELECT " +
                "request_id, request_title, request_explaination, request_display_date, " +
                "    request_expired_date, " +
                "    count( req.responsed_request_id) AS response_count, res.response_date, res.responsed_cost, company_name, company_logo_image, category_name " +
                " FROM (efaz_school_tender AS tender " +
                "                         LEFT JOIN efaz_company.efaz_company_response_school_request AS req " +
                "                         ON tender.request_id = req.responsed_request_id)" +
                "                         LEFT JOIN efaz_company.efaz_company_response_school_request AS res " +
                "                         ON tender.request_id = res.responsed_request_id " +
                "                         LEFT JOIN efaz_company.efaz_company_profile AS com " +
                "                         ON res.responsed_company_id = com.company_id" +
                "                         INNER JOIN efaz_company.efaz_company_category AS cat " +
                "                         ON com.company_category_id = cat.category_id " +
                "where request_id =?" +
                "                         GROUP BY company_name;";

        String sql1 = "SELECT " +
                "                request_id, request_title, request_explaination, request_display_date, " +
                "                    request_expired_date, res.response_date, res.responsed_cost, company_name, company_logo_image, category_name" +
                "                 FROM efaz_school_tender AS tender " +
                "  LEFT JOIN efaz_company.efaz_company_response_school_request AS res " +
                "     ON tender.request_id = res.responsed_request_id" +
                "                  LEFT JOIN efaz_company.efaz_company_profile AS com " +
                "                                        ON res.responsed_company_id = com.company_id" +
                " INNER JOIN efaz_company.efaz_company_category AS cat " +
                " ON com.company_category_id = cat.category_id " +
                "  where request_id =?;";


        return jdbcTemplate.query(sql1,
                new Object[]{request_id}, (resultSet, i) -> new SchoolRequestNewDtoWitCompany(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(),
                        resultSet.getTimestamp(6).getTime(), resultSet.getDouble(7),
                        resultSet.getString(8), resultSet.getBytes(9), resultSet.getString(10)));


    }

    public int acceptOffer(int request_id) {
        return jdbcTemplate.update("UPDATE efaz_company.efaz_school_tender SET request_is_conformied=1 WHERE request_id=?;", request_id);
    }

    public int cancelOffer(int request_id) {
        return jdbcTemplate.update("UPDATE efaz_company.efaz_school_tender SET request_is_conformied=0 WHERE request_id=?;", request_id);
    }


}