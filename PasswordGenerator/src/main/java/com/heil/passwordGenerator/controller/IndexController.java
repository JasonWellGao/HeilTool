package com.heil.passwordGenerator.controller;

import com.heil.passwordGenerator.pojo.PasswordParam;

import java.util.*;

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
@Api(description = "密码生成器~")
@Controller
public class IndexController {
	@ApiOperation(value="访问密码生成器", notes="默认四种字符类型为选中状态")
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(ModelMap map) {
        map.addAttribute("title", "密码生成器~");
        Map<String,String> charType = new HashMap<String, String>();
		charType.put("0","1");
		charType.put("1","1");
		charType.put("2","1");
		charType.put("3","1");
        map.addAttribute("charType", charType);
        return "index";
    }

	@ApiOperation(value="请求生成随机密码", notes="发送预设的密码生成参数，生成随机密码")
		@ApiImplicitParams({
		@ApiImplicitParam(name = "charType", value = "字符类型", required = true, dataType = "charType")
			,@ApiImplicitParam(name = "length", value = "密码长度", required = false, dataType = "Integer")
		})
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
		Random random = new Random(System.currentTimeMillis());
		StringBuffer tmp =new StringBuffer();

		Integer length = passwordParam.getLength();
		length = length == null ? 6 : length;
		if(str.length() == 0){
			map.addAttribute("tips", "请选择字符类型");
		}
		else{
			for(int i=0;i < length;i++){
				int number=random.nextInt(strLength);
				tmp.append(str.charAt(number));
			}
		}
        map.addAttribute("password", tmp.toString());
        map.addAttribute("title", "密码生成器~");
        map.addAttribute("length", length);
        map.addAttribute("charType", charType);
        return "index";
    }
}
