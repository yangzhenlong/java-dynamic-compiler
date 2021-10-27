# Java 动态编译

## 编译

```java
String javaFilePath = "/path/HelloWorld.java";
// 编译
Compiler compiler = new SysCompiler();
compiler.compile(javaFilePath);
```
> 会在 `/path` 目录下生成 `HelloWorld.class`

## 加载类和调用

```java
// 加载
Class<?> cls = compiler.load(javaFilePath);

// 实例化和方法调用
Object instance = cls.newInstance();
Method method = cls.getMethod("hello");
Object methodResult = method.invoke(instance);
System.out.println("methodInvokedResult: " + methodResult);
```