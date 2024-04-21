package com.example.demo.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSASymmetricKey {
    public static void main(String[] args) throws Exception {
        // Generate a symmetric key
        SecretKey symmetricKey = generateSymmetricKey();

        // Generate RSA key pair
        KeyPair keyPair = generateRSAKeyPair();

        // Encrypt the data with the symmetric key
        String data = "Hello, World!";
        byte[] encryptedData = encryptWithSymmetricKey(data, symmetricKey);

        // Encrypt the symmetric key with RSA
        byte[] encryptedSymmetricKey = encryptWithRSA(symmetricKey.getEncoded(), keyPair.getPublic());

        // Send the encrypted symmetric key and data

        // Decrypt the encrypted symmetric key with RSA
        byte[] decryptedSymmetricKey = decryptWithRSA(encryptedSymmetricKey, keyPair.getPrivate());
        SecretKey decryptedSymmetricKeyObj = new SecretKeySpec(decryptedSymmetricKey, "AES");

        // Decrypt the data with the symmetric key
        String decryptedData = decryptWithSymmetricKey(encryptedData, decryptedSymmetricKeyObj);

        System.out.println("Decrypted Data: " + decryptedData);
    }

    public static SecretKey generateSymmetricKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 128-bit key for AES
        return keyGen.generateKey();
    }

    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // 2048-bit key for RSA
        return keyGen.generateKeyPair();
    }

    public static byte[] encryptWithSymmetricKey(String data, SecretKey symmetricKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, symmetricKey);
        return cipher.doFinal(data.getBytes());
    }

    public static String decryptWithSymmetricKey(byte[] encryptedData, SecretKey symmetricKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, symmetricKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedData);
        return new String(decryptedBytes);
    }

    public static byte[] encryptWithRSA(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static byte[] decryptWithRSA(byte[] encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encryptedData);
    }
}
