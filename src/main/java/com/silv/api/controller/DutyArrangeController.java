package com.silv.api.controller;

import com.silv.api.dto.Arrange3To2Dto;
import com.silv.api.model.Result;
import com.silv.api.service.DutyArrangeService;
import com.silv.api.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("duty")
public class DutyArrangeController {

    @Autowired
    private DutyArrangeService dutyArrangeService;

    /**
     * 三班两倒（一天3条记录，一个月30天就是90条记录）
     *
     * @return
     */
    @PostMapping(value = "/get3to2Arrange")
    public Result get3to2Arrange(@Validated Arrange3To2Dto arrange, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResultUtil.error(map);
        }
        return this.dutyArrangeService.get3to2Arrange(arrange);
    }

    /**
     * 四班三倒（一天四条纪录）
     *
     * @return
     */
    @PostMapping(value = "/get4to3Arrange")
    public Result get4to3Arrange() {
        return this.dutyArrangeService.get4to3Arrange();
    }

    /**
     * 做五休二
     *
     * @return
     */
    @GetMapping(value = "/get5to2Arrange")
    public Result get5to2Arrange() {
        return this.dutyArrangeService.get5to2Arrange();
    }
}
