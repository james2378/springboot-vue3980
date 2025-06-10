package com.project.demo.controller;

import com.project.demo.entity.ReputationValue;
import com.project.demo.service.ReputationValueService;
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
 *信誉值：(ReputationValue)表控制层
 *
 */
@RestController
@RequestMapping("/reputation_value")
public class ReputationValueController extends BaseController<ReputationValue,ReputationValueService> {

    /**
     *信誉值对象
     */
    @Autowired
    public ReputationValueController(ReputationValueService service) {
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
