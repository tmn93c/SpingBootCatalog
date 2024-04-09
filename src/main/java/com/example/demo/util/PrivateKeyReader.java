package com.example.demo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class PrivateKeyReader {
    public static void main(String[] args) throws Exception {
        // Read the PEM-encoded private key file
        byte[] key = Files.readAllBytes(Paths.get("src\\main\\resources\\key2.pem"));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
        PrivateKey finalKey = keyFactory.generatePrivate(keySpec);
        System.out.println(finalKey.getAlgorithm());
    }
}