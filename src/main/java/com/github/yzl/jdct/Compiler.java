package com.github.yzl.jdct;

public interface Compiler {

    /**
     * 编译
     */
    void compile(String javaFilePath);

    /**
     * 加载
     */
    Class<?> load(String javaFilePath);
}
