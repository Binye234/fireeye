package cn.boood.fireeye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/17 14:56
 */
@Controller
public class ErrorController {
    @GetMapping("/error404")
    public String error404(){
        return "error/404";
    }
    @GetMapping("/error500")
    public String error500(){
        return "error/500";
    }
}
