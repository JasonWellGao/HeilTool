package com.heil.timestamp.controller;

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
        long nowTimestamp = System.currentTimeMillis()/1000;
        long timestamp = nowTimestamp;
        map.addAttribute("nowTimestamp", nowTimestamp);
        map.addAttribute("timestamp", timestamp);
        this.getCalendar(map);
        return "index";
    }
	@ApiOperation(value="时间戳转时间", notes="时间戳转时间并输出当前时间对应的时间戳")
		@ApiImplicitParams({
		@ApiImplicitParam(name = "timestamp", value = "时间戳", required = true, dataType = "long")
		})
    @RequestMapping(value="/", method=RequestMethod.POST,params = {"timestamp"})
    public String index(@RequestParam(value="timestamp",defaultValue="0") long timestamp,@ApiIgnore ModelMap map) {
        map.addAttribute("title", "时间戳转换工具~");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timestamp * 1000);
        String timeResult = simpleDateFormat.format(date);

        long nowTimestamp = System.currentTimeMillis()/1000;
        map.addAttribute("nowTimestamp", nowTimestamp);
        map.addAttribute("timestamp", timestamp);
        map.addAttribute("timeResult", timeResult);
        this.getCalendar(map);
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
            long timestampResult = time.getTime();
        System.out.println(timestampResult);
            map.addAttribute("timestampResult", timestampResult/1000);
        }
        catch (ParseException e) {
            long timestampResult = 0;
            map.addAttribute("timestampResult", timestampResult);
        }
        long nowTimestamp = System.currentTimeMillis()/1000;
        map.addAttribute("nowTimestamp", nowTimestamp);
        map.addAttribute("year", year);
        map.addAttribute("month", month);
        map.addAttribute("day", day);
        map.addAttribute("hour", hour);
        map.addAttribute("minute", minute);
        map.addAttribute("second", second);
        return "index";
    }
    protected ModelMap getCalendar(ModelMap map){
        Calendar now = Calendar.getInstance();  
        map.addAttribute("year",now.get(Calendar.YEAR));
        map.addAttribute("month",(now.get(Calendar.MONTH) + 1));
        map.addAttribute("day",now.get(Calendar.DAY_OF_MONTH));
        map.addAttribute("hour",now.get(Calendar.HOUR_OF_DAY));
        map.addAttribute("minute",now.get(Calendar.MINUTE));
        map.addAttribute("second",now.get(Calendar.SECOND));
        return map;
    }
}
