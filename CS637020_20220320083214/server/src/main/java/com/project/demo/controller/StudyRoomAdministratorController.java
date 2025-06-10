package com.project.demo.controller;

import com.project.demo.entity.StudyRoomAdministrator;
import com.project.demo.service.StudyRoomAdministratorService;
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
 *自习室管理员：(StudyRoomAdministrator)表控制层
 *
 */
@RestController
@RequestMapping("/study_room_administrator")
public class StudyRoomAdministratorController extends BaseController<StudyRoomAdministrator,StudyRoomAdministratorService> {

    /**
     *自习室管理员对象
     */
    @Autowired
    public StudyRoomAdministratorController(StudyRoomAdministratorService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapmerchant_account_number = new HashMap<>();
        mapmerchant_account_number.put("merchant_account_number",String.valueOf(paramMap.get("merchant_account_number")));
        List listmerchant_account_number = service.select(mapmerchant_account_number, new HashMap<>()).getResultList();
        if (listmerchant_account_number.size()>0){
            return error(30000, "字段商家账号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
