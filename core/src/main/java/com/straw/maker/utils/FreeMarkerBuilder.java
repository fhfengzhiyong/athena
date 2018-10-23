package com.straw.maker.utils;

import com.straw.maker.run.BaseConfig;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import org.apache.commons.lang3.ObjectUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author straw(fengzy)
 * @description
 * @date 2018/10/9
 */


public class FreeMarkerBuilder {
    static Configuration config;

    static {
        config = new Configuration(freemarker.template.Configuration.VERSION_2_3_0);
        config.setSharedVariable("classNameLower", new ClassNameTemplateModel());
    }

    public static void createJavaFile(String ftl, EntityBean entityBean, BaseConfig baseConfig) {
        try {
            config.setDirectoryForTemplateLoading(new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "ftl/"));
            Template template = config.getTemplate(ftl);
            String tmpDir = baseConfig.getEntityPath() + entityBean.getPackagePath().replace(".", "/");
            File htmlFile = new File(tmpDir);
            if (!htmlFile.exists()) {
                htmlFile.mkdirs();
            }
            htmlFile = new File(tmpDir + entityBean.getFileName() + baseConfig.getSuffix());
            FileWriter writer = new FileWriter(htmlFile);
            System.out.println(ftl + "---" + htmlFile.getName());
            Map<String, Object> data = entityBean.getData();
            data.putAll(objectToMap(entityBean));
            template.process(data, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }
}