package com.edu.nuc;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
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
    public static final String linux_path = "/Users/macbookair/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
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
            log.info("创建：" + path);
            boolean mkdir = file.mkdirs();
            if (!mkdir) {
                log.warn("创建：" + path + "失败");
            }
        }
    }
}
