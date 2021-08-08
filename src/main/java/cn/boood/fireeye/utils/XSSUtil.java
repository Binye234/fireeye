package cn.boood.fireeye.utils;

import cn.hutool.http.HtmlUtil;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/7 17:13
 */
public class XSSUtil {
    /**
     * 过滤xss，转义字符
     * @param text
     * @return
     */
    static public String xssFilter(String text){
        return HtmlUtil.escape(text);
    }
}
