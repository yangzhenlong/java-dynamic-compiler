package com.github.yzl.jdct;

import com.github.yzl.jdct.sys.SysCompiler;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestSys {

 @Test
 public void test01() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, InterruptedException {
  String javaFilePath = System.getProperty("user.dir") + "/src/test/resources/build/HelloWorld.java";
  // 编译
  Compiler compiler = new SysCompiler();
  compiler.compile(javaFilePath);

  Thread.sleep(3000);

  // 加载
  Class<?> cls = compiler.load(javaFilePath);

  // 实例化和方法调用
  Object instance = cls.newInstance();
  Method method = cls.getMethod("hello");
  Object methodResult = method.invoke(instance);
  System.out.println("methodInvokedResult: " + methodResult);
 }
}