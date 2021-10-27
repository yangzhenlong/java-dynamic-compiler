package com.github.yzl.jdct;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractCompiler implements Compiler {

    protected static final JavaCompiler COMPILER = ToolProvider.getSystemJavaCompiler();

    protected static final int COMPILE_SUCCESS = 0;

    protected static Map<String, ClassLoader> classLoaderContext = new ConcurrentHashMap<>();

    private MessageDigest md5;

    private Type type;

    public AbstractCompiler(Type type) {
        this.type = type;
        try {
            this.md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("init md5 failed!");
        }
    }

    public Type getType() {
        return type;
    }

    public ClassLoader getClassLoader(String url) {
        String hash = hash(url);
        if (classLoaderContext.containsKey(hash)) {
            return classLoaderContext.get(hash);
        }
        try {
            URLClassLoader cl = URLClassLoader.newInstance(new URL[]{new File(url).toURI().toURL()});
            classLoaderContext.put(hash, cl);
            return cl;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("url [" + url + "] is not formatted!");
        }
    }

    private String hash(String message) {
        if (null == message || message.length() == 0) {
            throw new NullPointerException("message cannot null");
        }
        byte[] digest = md5.digest(message.getBytes(StandardCharsets.UTF_8));
        return new BigInteger(1, digest).toString(16);
    }
}
