package cn.boood.fireeye.controller;

        import cn.boood.fireeye.cache.TaskSpiderInfo;
        import cn.boood.fireeye.cache.TasksCache;
        import cn.boood.fireeye.mybatis.entity.AdminUser;
        import cn.boood.fireeye.service.SystemService;
        import cn.boood.fireeye.utils.PublicUtil;
        import cn.boood.fireeye.vo.TaskNum;
        import com.fasterxml.jackson.core.JsonProcessingException;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;
        import javax.servlet.http.HttpSession;
        import java.util.ArrayList;
        import java.util.List;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/3 9:09
 */
@Controller
@RequestMapping("/system")
public class SystemController {
    private Logger logger= LoggerFactory.getLogger("SystemController");
    @Autowired
    private SystemService systemService;
    /**
     * 系统管理首页
     * @return
     */
    @GetMapping("/home")
    public String home(Model model){
        List<TaskNum> taskNums=systemService.getLastTasks();
        model.addAttribute("one",taskNums.get(0));
        model.addAttribute("two",taskNums.get(1));
        model.addAttribute("three",taskNums.get(2));
        model.addAttribute("four",taskNums.get(3));
        model.addAttribute("five",taskNums.get(4));
        List<TaskSpiderInfo> list= TasksCache.getSpiderInfoList();
        List<String> names=new ArrayList<>();
        for (TaskSpiderInfo taskSpiderInfo : list){
            names.add(taskSpiderInfo.getTaskInfo().getTaskName());
        }
        model.addAttribute("names",names);
        return "index";
    }

    /**
     * 敏感词记录展示
     * @return
     */
    @GetMapping("/taskshow")
    public String newTask(){
        return "html/taskshow";
    }

    /**
     * 任务管理页
     * @return
     */
    @GetMapping("/taskmanagement")
    public String taskManagement(){
        return "html/taskmanagement";
    }

    /**
     * 退出系统
     * @param session
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @GetMapping("/signout")
    public String signOut(HttpSession session) throws JsonProcessingException {
        AdminUser user = (AdminUser) session.getAttribute("user");
        logger.info("用户："+user.getUsername()+"退出系统");
        session.invalidate();
        return "true";
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/changepassword")
    public String changePassword(String oldPassword,String newPassword,HttpSession session){
        AdminUser user = (AdminUser) session.getAttribute("user");
        if(!user.getPassword().equals(PublicUtil.getMD5Hash(oldPassword))){
            return "false";
        }
        user.setPassword(PublicUtil.getMD5Hash(newPassword));
        systemService.changePassword(user);
        logger.info("用户："+user.getUsername()+"成功修改密码");
        return "true";
    }

}
