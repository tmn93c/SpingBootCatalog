package com.example.demo.util;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Random;
import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RsaKey {
    private BigInteger n, e, d;

    // Reading in RSA public key and private key
    RsaKey(String input) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(input));
            String line = in.readLine();
            while (line!=null) {
                if (line.contains("Modulus: ")) {
                    n = new BigInteger(line.substring(9));
                }
                if (line.contains("Public key: ")) {
                    e = new BigInteger(line.substring(12));
                }
                if (line.contains("Private key: ")) {
                    d = new BigInteger(line.substring(13));
                }
                line = in.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("--- Reading public key and private key ---");
        System.out.println("Modulus: "+n);
        System.out.println("Key size: "+n.bitLength());
        System.out.println("Public key: "+e);
        System.out.println("Private key: "+d);
    }

    // Testing encryption and description
    public void test() {
        Random rnd = new Random();
        int size = rnd.nextInt(n.bitLength()-1);
        BigInteger text = new BigInteger(size,rnd);
        BigInteger cipher = text.modPow(e,n);
        BigInteger decrypted = cipher.modPow(d,n);
        boolean isPassed = text.equals(decrypted);
        System.out.println("--- RSA encryption test ---");
        System.out.println("Is test passed: "+isPassed);
        System.out.println("Original text: "+text);
        System.out.println("Cipher text: "+cipher);
        System.out.println("Decrypted text: "+decrypted);
    }

    public static KeyPair generateKey() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        return kpg.generateKeyPair();
    }

    public static void generatePublicKeyAndPrivateKey(KeyPair keypair, String outputFileWithoutExtension)
            throws IOException {
        OutputStream out = new FileOutputStream(outputFileWithoutExtension + ".key");
        out.write(keypair.getPrivate().getEncoded());
        out.close();

        out = new FileOutputStream(outputFileWithoutExtension + ".pub");
        out.write(keypair.getPublic().getEncoded());
        out.close();
    }


    public static PublicKey getPubKey(Path filename)
            throws Exception {

        byte[] keyBytes = Files.readAllBytes(filename);

        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decriptCipher.doFinal(bytes), UTF_8);
    }
}