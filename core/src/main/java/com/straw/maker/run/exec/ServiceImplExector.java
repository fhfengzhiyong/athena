package com.straw.maker.run.exec;

import com.straw.maker.run.BaseConfig;
import com.straw.maker.run.Task;
import com.straw.maker.utils.EntityBean;
import com.straw.maker.utils.FreeMarkerBuilder;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @author straw(fengzy)
 * @description
 * @date 2018/10/9
 */
public class ServiceImplExector implements Runnable, Task {

    Logger logger = LoggerFactory.getLogger(ServiceImplExector.class);

    BaseConfig baseConfig;
    EntityBean entityBean;
    CountDownLatch latch;


    public ServiceImplExector(BaseConfig baseConfig, EntityBean entityBean, CountDownLatch latch) {
        this.baseConfig = ObjectUtils.clone(baseConfig);
        this.entityBean = ObjectUtils.clone(entityBean);
        this.latch = latch;
    }

    @Override
    public void exec() {

        baseConfig.setSuffix(".java");
        entityBean.setPackagePath("service/impl/");
        entityBean.setFileName(entityBean.getClassName() + "ServiceImpl");
        FreeMarkerBuilder.createJavaFile("service_impl.ftl", entityBean, baseConfig);
        latch.countDown();
    }

    @Override
    public void run() {
        exec();
    }
}
