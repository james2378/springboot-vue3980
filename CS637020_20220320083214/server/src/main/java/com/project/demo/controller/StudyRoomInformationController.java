package com.project.demo.controller;

import com.project.demo.entity.StudyRoomInformation;
import com.project.demo.service.StudyRoomInformationService;
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
 *自习室信息：(StudyRoomInformation)表控制层
 *
 */
@RestController
@RequestMapping("/study_room_information")
public class StudyRoomInformationController extends BaseController<StudyRoomInformation,StudyRoomInformationService> {

    /**
     *自习室信息对象
     */
    @Autowired
    public StudyRoomInformationController(StudyRoomInformationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapname_of_study_room = new HashMap<>();
        mapname_of_study_room.put("name_of_study_room",String.valueOf(paramMap.get("name_of_study_room")));
        List listname_of_study_room = service.select(mapname_of_study_room, new HashMap<>()).getResultList();
        if (listname_of_study_room.size()>0){
            return error(30000, "字段自习室名称内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
