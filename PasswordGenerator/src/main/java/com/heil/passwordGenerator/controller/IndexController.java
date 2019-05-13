package com.heil.passwordGenerator.controller;

import com.heil.passwordGenerator.pojo.PasswordParam;

import java.util.Map;
import java.util.Random;

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

		String str0 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String str1 = "abcdefghijklmnopqrstuvwxyz";
		String str2 = "0123456789";
		String str3 = "!@#$%^&*";

		Map charType = passwordParam.getCharType();

		StringBuffer str = new StringBuffer();

		int tmp0 = Integer.parseInt(charType.get("0").toString());
		if(tmp0 == 1)
			str.append(str0);
		int tmp1 = Integer.parseInt(charType.get("1").toString());
		if(tmp1 == 1)
			str.append(str1);
		int tmp2 = Integer.parseInt(charType.get("2").toString());
		if(tmp2 == 1)
			str.append(str2);
		int tmp3 = Integer.parseInt(charType.get("3").toString());
		if(tmp3 == 1)
			str.append(str3);
		System.out.println("str:" + str.toString());

		int strLength = str.length();
		Random random=new Random();
		StringBuffer tmp =new StringBuffer();

		for(int i=0;i<length;i++){
			int number=random.nextInt(strLength);
			tmp.append(str.charAt(number));
		}
		return tmp.toString();
    }
}
