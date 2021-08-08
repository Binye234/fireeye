package cn.boood.fireeye.utils;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/7 16:18
 */
public class HttpUtils {
    /**
     * 判断网址能否访问
     * @param url
     * @return
     */
    static public boolean isVisit(String url){
        try{
            HttpResponse response = HttpRequest.get(url).timeout(2000).execute();
            if(response.getStatus()==200){
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }

    /**
     * 取域名
     * @param url
     * @return
     */
    static public String getDomain(String url){
        int start=0,end=url.length();
        int index=0;
        char[] chars=url.toCharArray();
        while (index<url.length()){
            if(chars[index]=='/'){
                index=index+2;
                start=index;
                break;
            }
            index++;
        }
        while (index<url.length()){
            if(chars[index]=='/'||chars[index]=='?'){
                end=index;
                break;
            }
            index++;
        }
        return url.substring(start,end);
    }

    /**
     * 排除文件下载网址
     * @param url
     * @return
     */
    static public boolean excludeSuffix(String url){
        String[] Suffix=new String[]{".pdf",".zip",".xls",".xlsx",
        ".doc",".docx"};
        for (String s : Suffix){
            if(url.indexOf(s)>=0){
                return true;
            }
        }
        return false;
    }
}
