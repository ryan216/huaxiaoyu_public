package com.example.huaxiaoyu.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: 你与黎明
 * @Description: 上下文获取工具类
 * @create: 2022-12-12 22:41
 * @Version: 1.0
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * 获取上下文对象
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取 applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过 name 获取 bean 对象
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {

        return getApplicationContext().getBean(name);
    }

    /**
     * 通过 class 获取 bean 对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {

        return getApplicationContext().getBean(clazz);


    }

    /**
     * 通过 name，clazz  获取指定的 bean 对象
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
