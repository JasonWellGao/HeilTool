package com.heil.timeStamp.controller;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
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
	@ApiOperation(value="访问时间戳转换工具", notes="输出当前时间对应时间戳")
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(@ApiIgnore ModelMap map ) {
        map.addAttribute("title", "时间戳转换工具~");
        long nowTimeStamp = System.currentTimeMillis()/1000;
        long timeStamp = nowTimeStamp;
        map.addAttribute("nowTimeStamp", nowTimeStamp);
        map.addAttribute("timeStamp", timeStamp);
        return "index";
    }
	@ApiOperation(value="时间戳转时间", notes="时间戳转时间并输出当前时间对应的时间戳")
		@ApiImplicitParams({
		@ApiImplicitParam(name = "timeStamp", value = "时间戳", required = true, dataType = "long")
		})
    @RequestMapping(value="/", method=RequestMethod.POST,params = {"timeStamp"})
    public String index(@RequestParam(value="timeStamp",defaultValue="0") long timeStamp,@ApiIgnore ModelMap map) {
        map.addAttribute("title", "时间戳转换工具~");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeStamp * 1000);
        String timeResult = simpleDateFormat.format(date);

        long nowTimeStamp = System.currentTimeMillis()/1000;
        map.addAttribute("nowTimeStamp", nowTimeStamp);
        map.addAttribute("timeStamp", timeStamp);
        map.addAttribute("timeResult", timeResult);
        return "index";
    }
	@ApiOperation(value="时间转时间戳", notes="时间转时间戳并输出当前时间对应的时间戳")
		@ApiImplicitParams({
		@ApiImplicitParam(name = "year", value = "年", required = true, dataType = "long")
        ,@ApiImplicitParam(name = "month", value = "月", required = true, dataType = "int")
        ,@ApiImplicitParam(name = "day", value = "日", required = true, dataType = "int")
        ,@ApiImplicitParam(name = "hour", value = "时", required = true, dataType = "int")
        ,@ApiImplicitParam(name = "minute", value = "分", required = true, dataType = "int")
        ,@ApiImplicitParam(name = "second", value = "秒", required = true, dataType = "int")
        })
    @RequestMapping(value="/", method=RequestMethod.POST,params = {"year","month","day","hour","minute","second"})
    public String index(@RequestParam(value = "year",defaultValue = "1970",required = true) long year
            ,@RequestParam(value="month",defaultValue="1",required = true) int month
            ,@RequestParam(value="day",defaultValue="1",required = true) int day
            ,@RequestParam(value="hour",defaultValue="8",required = true) int hour
            ,@RequestParam(value="minute",defaultValue="0",required = true) int minute
            ,@RequestParam(value="second",defaultValue="0",required = true) int second
            ,@ApiIgnore ModelMap map) {
        map.addAttribute("title", "时间戳转换工具~");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer timeBuffer = new StringBuffer(Long.toString(year));
        timeBuffer.append("-").append(month).append('-').append(day)
            .append(" ")
            .append(hour).append(":").append(minute).append(":").append(second);
        try {
            Date time = simpleDateFormat.parse(timeBuffer.toString());
            long timeStampResult = time.getTime();
        System.out.println(timeStampResult);
            map.addAttribute("timeStampResult", timeStampResult/1000);
        }
        catch (ParseException e) {
            long timeStampResult = 0;
            map.addAttribute("timeStampResult", timeStampResult);
        }
        long nowTimeStamp = System.currentTimeMillis()/1000;
        map.addAttribute("nowTimeStamp", nowTimeStamp);
        map.addAttribute("year", year);
        map.addAttribute("month", month);
        map.addAttribute("day", day);
        map.addAttribute("hour", hour);
        map.addAttribute("minute", minute);
        map.addAttribute("second", second);
        return "index";
    }
}
