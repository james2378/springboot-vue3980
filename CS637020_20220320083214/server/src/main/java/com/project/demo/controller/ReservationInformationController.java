package com.project.demo.controller;

import com.project.demo.entity.ReservationInformation;
import com.project.demo.service.ReservationInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *预约信息：(ReservationInformation)表控制层
 *
 */
@RestController
@RequestMapping("/reservation_information")
public class ReservationInformationController extends BaseController<ReservationInformation,ReservationInformationService> {

    /**
     *预约信息对象
     */
    @Autowired
    public ReservationInformationController(ReservationInformationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        String sql = "SELECT MAX(reservation_information_id) AS max FROM "+"reservation_information";
        Query select = service.runCountSql(sql);
        Integer max = (Integer) select.getSingleResult();
        sql = ("SELECT count(*) count FROM `study_room_information` INNER JOIN `reservation_information` ON study_room_information.name_of_study_room=reservation_information.name_of_study_room WHERE study_room_information.spare_seats &#60; reservation_information.number_of_appointments AND reservation_information.reservation_information_id="+max).replaceAll("&#60;","<");
        select = service.runCountSql(sql);
        Integer count = Integer.valueOf(String.valueOf(select.getSingleResult()));
        if(count>0){
            sql = "delete from "+"reservation_information"+" WHERE "+"reservation_information_id"+" ="+max;
            select = service.runCountSql(sql);
            select.executeUpdate();
            return error(30000,"座位不足!");
        }
        sql = "UPDATE `study_room_information` INNER JOIN `reservation_information` ON study_room_information.name_of_study_room=reservation_information.name_of_study_room SET study_room_information.spare_seats = study_room_information.spare_seats - reservation_information.number_of_appointments WHERE reservation_information.reservation_information_id="+max;
        select = service.runCountSql(sql);
        select.executeUpdate();
        return success(1);
    }

}
