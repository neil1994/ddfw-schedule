package com.dxhy.dispatch.manage.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验企业信息工具类
 * Created by 赵睿 on 2016/11/17.
 */
@SuppressWarnings({"SameReturnValue", "UnusedAssignment", "JavaDoc", "unused"})
public class CheckEnterpriseUtils {
    private final static Logger logger = LoggerFactory.getLogger(CheckEnterpriseUtils.class);

    /***
     *
     * <p>校验纳税人识别号15-20位,数字,字母</p>
     *
     * @param NSRSBH
     * @return boolean
     * @author: 腾金玉
     * @throws Exception
     * @date: Created on 2016年11月15日 下午5:18:08
     */
    public static boolean checkNSRSBH(String NSRSBH) throws Exception{

        //只能为数字、字母
        String regex = "^[0-9a-zA-Z]{15,20}$";

        if(Objects.equals(NSRSBH, "") || NSRSBH == null){
            throw new Exception("税务登记证号不能为空 ");
        }

        if(NSRSBH.matches(regex)){
            return true;
        }else{
            if (logger.isDebugEnabled()) {
                logger.info("纳税人识别号格式错误  :{}",NSRSBH);
            }
            throw new Exception("纳税人识别号格式错误  : "+NSRSBH);
        }
    }

    /***
     *
     * <p>校验社会信用代码15-20位,数字,字母</p>
     *
     * @param SHXYDM
     * @return boolean
     * @author: 腾金玉
     * @throws Exception
     * @date: Created on 2016年11月15日 下午5:18:08
     */
    public static boolean checkSHXYDM(String SHXYDM) throws Exception{

        //只能为数字、字母
        String regex = "^[0-9a-zA-Z]{15,20}$";

        if(Objects.equals(SHXYDM, "") || SHXYDM == null){
            throw new Exception("社会信用代码不能为空 ");
        }

        if(SHXYDM.matches(regex)){
            return true;
        }else{
            if (logger.isDebugEnabled()) {
                logger.info("社会信用代码格式错误  :{}",SHXYDM);
            }
            throw new Exception("社会信用代码格式错误  : "+SHXYDM);
        }
    }

    /***
     *
     * <p>校验税务机关登记证号15-20位,数字,字母</p>
     *
     * @param SWDJZH
     * @return
     * @throws Exception boolean
     * @author: 腾金玉
     * @date: Created on 2016年11月15日 下午6:18:04
     */
    public static boolean checkSWDJZH(String SWDJZH) throws Exception{
        //只能为数字、字母
        String regex = "^[0-9a-zA-Z]{15,20}$";

        if(Objects.equals(SWDJZH, "") || SWDJZH == null){
            throw new Exception("税务登记证号不能为空 ");
        }

        if(SWDJZH.matches(regex)){
            return true;
        }else{
            if (logger.isDebugEnabled()) {
                logger.info("税务登记证号格式错误  :{}",SWDJZH);
            }
            throw new Exception("税务登记证号格式错误  : "+SWDJZH);
        }
    }

    /***
     *
     * <p>组织机构代码9-10位,数字,字母</p>
     *
     * @param ZZJGDM
     * @return boolean
     * @author: 腾金玉
     * @date: Created on 2016年11月15日 下午6:20:32
     */
    public static boolean checkZZJGDM(String ZZJGDM) throws Exception{
        //组织机构代码两种正则校验,有"-" ,和 无"-"
        String regexOne = "[a-zA-Z0-9]{8}-[a-zA-Z0-9]";
        String regexTwo = "[a-zA-Z0-9]{9}";
        if(Objects.equals(ZZJGDM, "") || ZZJGDM == null){
            throw new Exception("组织机构代码不能为空 ");
        }

        if(ZZJGDM.matches(regexOne) || ZZJGDM.matches(regexTwo)){
            return true;
        }else{
            if (logger.isDebugEnabled()) {
                logger.info("组织机构代码格式错误  :{}",ZZJGDM);
            }
            throw new Exception("组织机构代码格式错误  : "+ZZJGDM);
        }
    }

    /***
     *
     * <p>检验营业执照号码13或者15位数字,字母</p>
     *
     * @param YYZZH
     * @return
     * @throws Exception boolean
     * @author: 腾金玉
     * @date: Created on 2016年11月15日 下午6:45:53
     */
    public static boolean checkYYZZH(String YYZZH) throws Exception{
        String regex = "^([a-zA-Z0-9]{13}|[a-zA-Z0-9]{15})$";
        if(Objects.equals(YYZZH, "") || YYZZH == null){
            throw new Exception("营业执照号码不能为空 ");
        }

        if(YYZZH.matches(regex) || YYZZH.matches(regex)){
            return true;
        }else{
            if (logger.isDebugEnabled()) {
                logger.info("营业执照号码格式错误  :{}",YYZZH);
            }
            throw new Exception("营业执照号码格式错误  : "+YYZZH);
        }
    }

    /***
     *
     * <p>校验邮箱格式,限制长度50位以内</p>
     *
     * @param email
     * @return
     * @throws Exception boolean
     * @author: 腾金玉
     * @date: Created on 2016年11月15日 下午7:13:43
     */
    public static boolean checkEmail(String email) throws Exception{
        String regex = "^(?=\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$).{0,50}$";
        if(Objects.equals(email, "") || email == null){
            throw new Exception("邮箱不能为空 ");
        }

        if(email.matches(regex)){
            return true;
        }else{
            if (logger.isDebugEnabled()) {
                logger.info("邮箱格式错误  :{}",email);
            }
            throw new Exception("邮箱格式错误  : "+email);
        }
    }

    /***
     *
     * <p>校验身份证号是否合法15或者18位</p>
     *
     * @param IDCard
     * @return
     * @throws Exception boolean
     * @author: 腾金玉
     * @date: Created on 2016年11月15日 下午8:02:11
     */
    @SuppressWarnings("rawtypes")
    public static boolean IDCardValidate(String IDCard) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.info("身份证格式校验  :{}",IDCard);
        }
        boolean tipInfo = true;
        String Ai = "";
        // 判断号码的长度 15位或18位
        if (IDCard.length() != 15 && IDCard.length() != 18) {
            throw new Exception("身份证格式错误不合法: "+IDCard);
        }
        // 18位身份证前17位位数字，如果是15位的身份证则所有号码都为数字
        if (IDCard.length() == 18) {
            Ai = IDCard.substring(0, 17);
        } else if (IDCard.length() == 15) {
            Ai = IDCard.substring(0, 6) + "19" + IDCard.substring(6, 15);
        }
        if (!isNumeric(Ai)) {
            throw new Exception("身份证格式错误不合法: "+IDCard);
        }
        // 判断出生年月是否有效
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 日期
        if (!isDate(strYear + "-" + strMonth + "-" + strDay)) {
            throw new Exception("身份证格式错误不合法: "+IDCard);
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                || (gc.getTime().getTime() - s.parse(
                strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
            throw new Exception("身份证格式错误不合法: "+IDCard);
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            throw new Exception("身份证格式错误不合法: "+IDCard);
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            throw new Exception("身份证格式错误不合法: "+IDCard);
        }

        // 判断地区码是否有效
        Hashtable areacode = GetAreaCode();
        //如果身份证前两位的地区码不在Hashtable，则地区码有误
        if (areacode.get(Ai.substring(0, 2)) == null) {
            throw new Exception("身份证格式错误不合法: "+IDCard);
        }

        if(!isVarifyCode(Ai, IDCard)){
            throw new Exception("身份证格式错误不合法: "+IDCard);
        }


        return true;
    }
    private static boolean isVarifyCode(String Ai,String IDCard) {
        String[] VarifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4","3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7","9", "10", "5", "8", "4", "2" };
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum = sum + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = sum % 11;
        String strVerifyCode = VarifyCode[modValue];
        Ai = Ai + strVerifyCode;
        if (IDCard.length() == 18) {
            if (!Ai.equals(IDCard)) {
                return false;

            }
        }
        return true;
    }
    @SuppressWarnings(value={"unchecked", "rawtypes"})
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }
    /**
     * 判断字符串是否为数字,0-9重复0次或者多次
     * @param strnum
     * @return
     */
    private static boolean isNumeric(String strnum) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(strnum);
        return isNum.matches();
    }
    /**
     * 功能：判断字符串出生日期是否符合正则表达式：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天
     *
     * @param strDate
     * @return
     */
    private static boolean isDate(String strDate) {
        String regex = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$";
        return strDate.matches(regex);
    }

    public static void main(String[] args) {
        boolean check ;
        try {
				/*check = CheckUtils.checkNSRSBH("");
				check = CheckUtils.checkZZJGDM("12345678");
				check = CheckUtils.checkSWDJZH("123");
				check = CheckUtils.checkYYZZH("123");*/
            check =IDCardValidate("42058119851012862X");
            System.out.println(check);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
            System.out.println(e.getMessage());
        }
    }
}
