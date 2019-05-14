package com.heil.passwordGenerator.controller;

import com.heil.passwordGenerator.pojo.PasswordParam;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
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

		Map<String,String> charType = passwordParam.getCharType();

		Map<String, String> charMap = new HashMap<String, String>();
		charMap.put("0", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		charMap.put("1", "abcdefghijklmnopqrstuvwxyz");
		charMap.put("2", "0123456789");
		charMap.put("3", "!@#$%^&*");

		StringBuffer str = new StringBuffer();
		for(String key : charType.keySet()){
			if(Integer.parseInt(charType.get(key)) == 1)
				str.append(charMap.get(key));
		}

		int strLength = str.length();
		Random random = new Random();
		StringBuffer tmp =new StringBuffer();

		for(int i=0;i<length;i++){
			int number=random.nextInt(strLength);
			tmp.append(str.charAt(number));
		}
		return tmp.toString();
    }
}
