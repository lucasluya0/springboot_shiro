package com.jiashidai.springboot_shiro_demo;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class JavaTest {
    public static void main(String[] args) {
        String algorithmName = "md5";
        String username = "hhsykx";
        String password = "123";
        String salt1 = username;
        int hashIterations = 2;
        System.out.println(ByteSource.Util.bytes(username));
        SimpleHash hash = new SimpleHash(algorithmName, password, salt1, hashIterations);
        System.out.println(hash);
        String encodedPassword = hash.toHex();
        System.out.println(encodedPassword);
    }
}
