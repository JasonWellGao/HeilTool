package com.heil.timeStamp.controller;

import java.util.*;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;

/**
 *
 * @author Jason <1878566968@qq.com>
 * @version 0.0.1 
 * @github https://github.com/orgs/heil-coder
 *
 */
@Api(description = "时间戳转换工具~")
@Controller
public class IndexController {
	@ApiOperation(value="访问时间戳转换工具", notes="输出当前时间以及对应时间戳")
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(ModelMap map) {
        map.addAttribute("title", "时间戳转换工具~");
        long nowTimeStamp = System.currentTimeMillis()/1000;
        long timeStamp = nowTimeStamp;
        map.addAttribute("nowTimeStamp", nowTimeStamp);
        map.addAttribute("timeStamp", timeStamp);
        return "index";
    }
	@ApiOperation(value="访问时间戳转换工具", notes="输出当前时间以及对应时间戳")
		@ApiImplicitParams({
		@ApiImplicitParam(name = "timeStamp", value = "时间戳", required = true, dataType = "long")
		})
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String index(@RequestParam long timeStamp,ModelMap map) {
        map.addAttribute("title", "时间戳转换工具~");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeStamp * 1000);
        String time = simpleDateFormat.format(date);

        long nowTimeStamp = System.currentTimeMillis()/1000;
        map.addAttribute("nowTimeStamp", nowTimeStamp);
        map.addAttribute("timeStamp", timeStamp);
        map.addAttribute("time", time);
        return "index";
    }
}
