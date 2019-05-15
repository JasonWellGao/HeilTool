package com.heil.passwordGenerator.pojo;

import java.util.*;

/**
 *
 * @author Jason <1878566968@qq.com> 
 * @version 0.0.1 
 * @github https://github.com/orgs/heil-coder
 *
 */
public class PasswordParam {

    private Map<String,String> charType = new HashMap<String,String>();
    private Integer length;


    public Map<String,String> getCharType() {
        return charType;
    }

    public void setCharType(Map<String,String> charType) {
        this.charType = charType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
