package com.pandaria.web.end;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "学生", description = "学生相关控制器")
@RequestMapping(value = "/student")
public class StudentController {
    Logger logger = LogManager.getLogger();

    @ApiOperation(value = "获取学生信息")
    @GetMapping(value = "/getStuInfo")
    public Object getStudentInfo(ModelMap modelMap) {

        logger.info("获取用户信息...");

        return ResponseEntity.ok(modelMap);
    }

}
