package cn.boood.fireeye.controller;

        import cn.boood.fireeye.mybatis.entity.AdminUser;
        import cn.boood.fireeye.service.SystemService;
        import cn.boood.fireeye.utils.PublicUtil;
        import cn.boood.fireeye.vo.TaskNum;
        import com.fasterxml.jackson.core.JsonProcessingException;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;
        import javax.servlet.http.HttpSession;
        import java.util.List;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/3 9:09
 */
@Controller
@RequestMapping("/system")
public class SystemController {
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

        return "index";
    }

    /**
     * 新建任务页
     * @return
     */
    @GetMapping("/newtask")
    public String newTask(){
        return "html/newtask";
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
        return "true";
    }
}
