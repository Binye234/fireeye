package cn.boood.fireeye.cache;

import cn.boood.fireeye.utils.DFAUtil;
import cn.hutool.dfa.WordTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:系统缓存敏感词
 * @author: boood
 * @time: 2021/8/1 13:51
 */
public class WordsCache {

    private static WordTree wordTree;

    private static List<String> words=new ArrayList<>();

    /**
     * 测试用数据
     */
    static {
        addWords(words);
    }

    public static void addWords(List<String> list){
        words.addAll(list);
        wordTree=DFAUtil.createtWordTree(words);
    }

    public static void clearWords(){
        words.clear();
        wordTree=null;
    }

    public static List<String> getWords(){
        return words;
    }

    public static WordTree getWordTree(){
        return wordTree;
    }
}
