package cn.boood.fireeye.controller;

import cn.boood.fireeye.mybatis.entity.AdminUser;
import cn.boood.fireeye.service.impl.LoginServiceImpl;
import cn.boood.fireeye.utils.PublicUtil;
import cn.boood.fireeye.vo.SystemMsg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/2 10:57
 */
@Controller
public class LoginController {
    static private Logger logger=LoggerFactory.getLogger("LoginController");
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private LoginServiceImpl loginImpl;
    /**
     * 登录页
     * @return
     */
    @GetMapping({"/login","/"})
    public String login(){
        return "login";
    }

    /**
     * 登录验证
     * @param username
     * @param password
     * @param code
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @PostMapping(value = "/loginCheck", produces = {"application/json;charset=UTF-8"})
    public SystemMsg loginCheck(String username, String password, String code, HttpSession session) throws JsonProcessingException {
        SystemMsg msg=new SystemMsg();
        if(!code.equals(session.getAttribute("verificationCode"))){
            msg.setCode("302");
            msg.setMsg("验证码错误");
            session.removeAttribute("verificationCode");
            return msg;
        }

        AdminUser user=loginImpl.getAdminUser(username);

        if(user==null){
            msg.setCode("301");
            msg.setMsg("用户名或密码错误");
            session.removeAttribute("verificationCode");
            return msg;
        }

        if(!user.getPassword().equals(PublicUtil.getMD5Hash(password))){
            msg.setCode("301");
            msg.setMsg("用户名或密码错误");
            session.removeAttribute("verificationCode");
            return msg;
        }

        session.removeAttribute("verificationCode");
        session.setAttribute("user",user);

        msg.setCode("200");
        msg.setMsg("/system/home");
        logger.info("用户："+username+"登录成功");
        return msg;
    }

    /**
     * 生成验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @GetMapping("/code")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("verificationCode", createText);
            // 使用生成的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
