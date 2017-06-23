package com.dxhy.dispatch.constants;

/**
 * 正则配置
 * Created by 赵睿 on 2016/11/17.
 */
public enum RegexEnum {
    codeRegex15_20("^[0-9a-zA-Z]{15,20}$","15-20位,可以是数字,字母"),
    codeRegex13Or15("^([a-zA-Z0-9]{13}|[a-zA-Z0-9]{15})$","13或者15,可以是数字,字母"),
    emailRegex("/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/","邮件格式"),
    telephoneRegex("^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$","手机")
    ;

    String regex;
    String regexDes;

    RegexEnum(String regex, String regexDes) {
        this.regex = regex;
        this.regexDes = regexDes;
    }


    public String getRegex() {
        return regex;
    }

    public String getRegexDes() {
        return regexDes;
    }
}
