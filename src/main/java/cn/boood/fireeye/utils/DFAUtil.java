package cn.boood.fireeye.utils;

import cn.boood.fireeye.cache.WordsCache;
import cn.hutool.dfa.WordTree;
import cn.hutool.http.HtmlUtil;

import java.util.*;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/1 14:03
 */
public class DFAUtil {
    /**
     * 取摘要的前后字符数
     */
    static private int chaNums=20;
    /**
     * 生成DFA对象
     * @param words
     * @return
     */
    static public WordTree createtWordTree(List<String> words) {
        WordTree wordTree = new WordTree();
        wordTree.addWords(words);
        return wordTree;
    }

    /**
     * 清除html标签
     *
     * @param text
     * @return
     */
    static public String cleanHtmlTag(String text) {
        return HtmlUtil.cleanHtmlTag(text);
    }

    /**
     * 单次查找关键词摘要
     * @param text 文本
     * @param key 关键词
     * @param index 开始位置
     * @return 摘要
     */
    static private Map<String, Object> findKeyContent(String text, String key, int index) {
        int i = text.indexOf(key, index);
        if (i == -1) {
            return null;
        }
        int start=0,end=text.length()-1;
        if(i>=chaNums){
            start=i-chaNums;
        }
        if(i+chaNums<=text.length()-1){
            end=i+chaNums;
        }
        Map<String, Object> map=new HashMap<>();
        map.put("content",text.substring(start,end));
        map.put("index",i);
        return map;
    }

    /**
     * 找到所有摘要
     * @param text
     * @param key
     * @return
     */
    static public List<String> findKeyContents(String text, String key){
        List<String> result=new ArrayList<>();
        Map<String, Object> map=null;
        int index=0;
        while (true){
            map=findKeyContent(text,key,index);
            if(map==null){
                break;
            }
            result.add(map.get("content").toString().replace('\n',' '));
            index=(int)map.get("index")+key.length();
        }
        return result;
    }

    /**
     * 关键字去重
     * @param list
     * @return
     */
    static public Set<String> wordsToSet(List<String> list){
        Set<String> set=new HashSet<>();
        set.addAll(list);
        return set;
    }

    /**
     * 匹配文本内的关键字，返回匹配出的集合
     * @param text 文本
     * @return 返回匹配的集合
     */
    static public List<String> matchKey(String text) {
        return WordsCache.getWordTree().matchAll(text, -1, true, true);
    }
}
