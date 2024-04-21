package com.example.demo.util;

public class AESUtilTest {
    public static void main(String[] args) {
        try {
            String originalData = "Hello, World!";

            // Encrypt the data
            String encryptedData = AESUtil.encrypt(originalData);
            System.out.println("Encrypted Data: " + encryptedData);

            // Decrypt the data
            String decryptedData = AESUtil.decrypt(encryptedData);
            System.out.println("Decrypted Data: " + decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
