package com.straw.maker.utils;

import freemarker.template.TemplateMethodModelEx;

import java.util.List;

/**
 * @author straw(fengzy)
 * @description
 * @date 2018/10/11
 */
public class ClassNameTemplateModel implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) {
        if (arguments != null && arguments.size() > 0) {
            String argument = arguments.get(0).toString();
            return argument.toLowerCase().substring(0, 1) + argument.substring(1, argument.length());
        }
        return null;
    }
}
