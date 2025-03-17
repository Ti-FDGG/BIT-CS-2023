package com.jinxuliang;

import java.lang.annotation.Annotation;
import java.lang.module.ModuleDescriptor;
import java.util.Arrays;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class Main {
    public static void main(String[] args) {
        showModuleInfo();
        queryLayer();
    }

    private static void queryLayer() {
        var bootLayerInfo = describe(ModuleLayer.boot());
        System.out.println(bootLayerInfo);
        //查询指定的模块是否己经加载
        System.out.println(isModulePresent("jdk.crypto.ec"));
    }

    private static void showModuleInfo() {
        Module module = Main.class.getModule();
        //获取模块的元数据
        System.out.println(describe(module));
    }

    public static String describe(Module module) {
        ModuleDescriptor md = module.getDescriptor();
        if (md == null)
            return "UNNAMED module { }";
        return " module " + md.name()
                + " {\n"
                + "\trequires " + md.requires() + "\n"
                + "\texports " + md.exports() + "\n"
                + "\topens " + md.opens() + "\n"
                + "\tcontains " + md.packages() + "\n"
                + "\tmain " + toString(md.mainClass()) + "\n"
                + "}";
    }

    private static String toString(Optional<?> optional) {
        return optional.isPresent()
                ? optional.get().toString()
                : "[]";
    }

    //获取层中所包容的所有模块
    private static String describe(ModuleLayer layer) {
        return layer
                .modules().stream()
                .map(Module::toString)
                .collect(joining("\n"));
    }

    //检查某模块是否己经装载
    public static boolean isModulePresent(String moduleName) {
        return ModuleLayer
                .boot()
                .findModule(moduleName)
                .isPresent();
    }
}
