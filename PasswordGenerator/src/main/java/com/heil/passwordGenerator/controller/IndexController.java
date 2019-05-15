package com.heil.passwordGenerator.controller;

import com.heil.passwordGenerator.pojo.PasswordParam;

import java.util.*;

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
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(ModelMap map) {
        map.addAttribute("title", "密码生成器~");
        return "index";
    }

    @RequestMapping(value="/",method=RequestMethod.POST)
    public String bulidPassword(@ModelAttribute PasswordParam passwordParam,ModelMap map) {
		Map<String,String> charType = passwordParam.getCharType();
		Map<String, String> charMap = new HashMap<String, String>();
		charMap.put("0", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		charMap.put("1", "abcdefghijklmnopqrstuvwxyz");
		charMap.put("2", "0123456789");
		charMap.put("3", "!@#$%^&*");

		StringBuffer str = new StringBuffer();
		Iterator<Map.Entry<String, String>> iterator = charType.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, String> entry = iterator.next();
			if(Integer.parseInt(entry.getValue()) == 1)
				str.append(charMap.get(entry.getKey()));
		}

		int strLength = str.length();
		Random random = new Random();
		StringBuffer tmp =new StringBuffer();

		Integer length = passwordParam.getLength();
		System.out.println(length);
		length = length == null ? 6 : length;
		for(int i=0;i < length;i++){
			int number=random.nextInt(strLength);
			tmp.append(str.charAt(number));
		}
        map.addAttribute("password", tmp.toString());
        map.addAttribute("title", "密码生成器~");
        map.addAttribute("length", length);
        return "index";
    }
}
