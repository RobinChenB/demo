package com.liyulin.mock.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.mock.entity.ApiLogEntity;
import com.liyulin.mock.service.AppLogService;

@RestController
@Validated
@RequestMapping("aaa")
public class AppLogController {

	@Autowired
	public AppLogService appLogService;
	
	@PostMapping("queryById")
	public ApiLogEntity queryById(@RequestBody @NotNull Long id) {
		return appLogService.queryById(id);
	}
	
}