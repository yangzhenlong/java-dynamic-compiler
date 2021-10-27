package com.github.yzl.jdct.sys;

import com.github.yzl.jdct.AbstractCompiler;
import com.github.yzl.jdct.Type;

import java.util.logging.Logger;

public class SysCompiler extends AbstractCompiler {

    private static final Logger logger = Logger.getLogger("SysCompiler");

    public SysCompiler() {
        super(Type.SYS);
    }


    @Override
    public void compile(String javaFilePath) {
        // Compiling the code
        int result = COMPILER.run(null, null, null, javaFilePath);
        logger.info("compile result: " + result);
        if (COMPILE_SUCCESS != result) {
            throw new RuntimeException("compile failed!");
        }
    }

    public Class<?> load(String javaFilePath) {
        String classFilePath = javaFilePath.substring(0, javaFilePath.lastIndexOf('/'));
        logger.info("classFilePath: " + classFilePath);
        // Load and instantiate compiled class.
        try {
            // Loading the class
            return Class.forName("build.HelloWorld", true, getClassLoader(classFilePath));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("load class failed! message: " + e.getMessage());
        }
    }
}
