package cn.boood.fireeye.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/17 14:58
 */
@Configuration
public class ErrorpageConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error404=new ErrorPage(HttpStatus.NOT_FOUND,"/error404");
        ErrorPage error500=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error404");
        registry.addErrorPages(error404,error500);
    }
}
