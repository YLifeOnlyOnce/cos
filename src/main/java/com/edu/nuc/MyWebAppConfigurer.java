package com.edu.nuc;


import com.edu.nuc.interceptor.UserLoginInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 *
 * @Description :图片路径设置
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    private static final Log log = LogFactory.getLog(MyWebAppConfigurer.class);
    public static final String win_path = "C:\\cospic\\";
    public static final String linux_path = "/Users/macbookair/cospic/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        log.info(os);
        if (os.toLowerCase().startsWith("win")) {
            log.info("当前操作系统为windows");
            createDir(win_path);
            registry.addResourceHandler("/cospic/**").addResourceLocations("file:" +win_path);
        } else {
            log.info("当前操作系统为linux");
            createDir(linux_path);
            registry.addResourceHandler("/cospic/**").addResourceLocations("file:" + linux_path);
        }
    }

    private void createDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            log.info("文件目录不存在");
            log.info("创建：" + path);
            boolean mkdir = file.mkdirs();
            if (!mkdir) {
                log.warn("创建：" + path + "失败");
            }
        } else {
            log.info("文件目录存在");
        }
    }

    /**
     * @return 存放图片的物理路径
     */
    public static String picsPath() {
        String os = System.getProperty("os.name");
        log.info(os);
        if (os.toLowerCase().startsWith("win")) {
            return win_path;
        } else {
            return linux_path;
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/user/**");
    }

}
