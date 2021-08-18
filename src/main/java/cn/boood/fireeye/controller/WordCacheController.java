package cn.boood.fireeye.controller;

import cn.boood.fireeye.cache.WordsCache;
import cn.boood.fireeye.utils.PublicUtil;
import cn.boood.fireeye.vo.SystemMsg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/16 9:54
 */
@Controller
@RequestMapping("/system")
public class WordCacheController {
    @Value("${words.path}")
    private String wordPath;
    /**
     * 敏感词显示页
     * @param model
     * @return
     */
    @GetMapping("/wordshow")
    public String wordShow(Model model){
        model.addAttribute("word",WordsCache.getWords());
        return "html/wordshow";
    }

    /**
     * 上传敏感词
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadword")
    @ResponseBody
    public SystemMsg uploadWord(@RequestParam("file") MultipartFile file) throws IOException {
        SystemMsg msg=new SystemMsg();
        if (file.isEmpty()) {
            msg.setCode("400");
            msg.setMsg("上传失败，请选择文件");
            return msg;
        }
        String fileName = file.getOriginalFilename();

//        File dest = new File( "d:\\upload\\"+ PublicUtil.getUUID()+".txt");
        File dest = new File( wordPath+ PublicUtil.getUUID()+".txt");
        BufferedReader bufferedReader=null;
        try {
            file.transferTo(dest);
            bufferedReader=new BufferedReader(new FileReader(dest));
            List<String> list=new ArrayList<>();
            bufferedReader.lines().forEach(a->list.add(a));
            WordsCache.addWords(list);
//            LOGGER.info("上传成功");

            msg.setCode("200");
            msg.setMsg("文件上传成功");
            return msg;
        } catch (IOException e) {
//            LOGGER.error(e.toString(), e);
        }finally {
            bufferedReader.close();
        }
        msg.setCode("400");
        msg.setMsg("上传失败，请选择文件");
        return msg;
    }
    @ResponseBody
    @GetMapping("/cleanwords")
    public SystemMsg cleanWords(){
        SystemMsg msg=new SystemMsg();
        WordsCache.clearWords();
        msg.setCode("201");
        msg.setMsg("敏感词缓存清空");
        return msg;
    }
}
