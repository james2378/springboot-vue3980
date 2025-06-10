package com.project.demo.controller;

import com.project.demo.entity.UseSeatInformation;
import com.project.demo.service.UseSeatInformationService;
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
 *使用座位信息：(UseSeatInformation)表控制层
 *
 */
@RestController
@RequestMapping("/use_seat_information")
public class UseSeatInformationController extends BaseController<UseSeatInformation,UseSeatInformationService> {

    /**
     *使用座位信息对象
     */
    @Autowired
    public UseSeatInformationController(UseSeatInformationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
