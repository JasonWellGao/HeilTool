package com.heil.passwordGenerator.controller;

import com.heil.passwordGenerator.pojo.PasswordParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Jason <1878566968@qq.com>
 * @version 0.0.1 
 * @github https://github.com/orgs/heil-coder
 *
 */
@Controller
public class IndexController {

    private PasswordParam passwordParam ;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(ModelMap map) {
        map.addAttribute("title", "密码生成器~");
        return "index";
    }

    @ResponseBody
    @RequestMapping(value="/",method=RequestMethod.POST)
    public String bulidPassword(@RequestParam(value="length") long length , @ModelAttribute PasswordParam passwordParam) {
		System.out.println("length:" + length);
		System.out.println("passwordParam.length:" + passwordParam.getLength());
		System.out.println("passwordParam.charType:" + passwordParam.getCharType());
        return "It's building!";
    }
}
