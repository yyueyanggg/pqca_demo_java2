package com.cbomkit.demo; 

 

import javax.crypto.Cipher; 

import javax.crypto.KeyGenerator; 

import javax.crypto.SecretKey; 

import java.security.*; 

import java.util.Base64; 

 

public class App { 

    public static void main(String[] args) throws Exception { 

        // AES key generation (symmetric) 

        KeyGenerator keyGen = KeyGenerator.getInstance("AES"); 

        keyGen.init(128); 

        SecretKey aesKey = keyGen.generateKey(); 

 

        // SHA-256 hash 

        MessageDigest sha256 = MessageDigest.getInstance("SHA-256"); 

        byte[] hash = sha256.digest("Hello CBOM".getBytes()); 

        System.out.println("SHA-256 hash: " + Base64.getEncoder().encodeToString(hash)); 

 

        // RSA key pair (asymmetric) 

        KeyPairGenerator rsaGen = KeyPairGenerator.getInstance("RSA"); 

        rsaGen.initialize(2048); 

        KeyPair keyPair = rsaGen.generateKeyPair(); 

 

        // Digital signature 

        Signature signature = Signature.getInstance("SHA256withRSA"); 

        signature.initSign(keyPair.getPrivate()); 

        signature.update("CBOM Signature".getBytes()); 

        byte[] signed = signature.sign(); 

        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signed)); 

 

        // SecureRandom 

        SecureRandom random = new SecureRandom(); 

        byte[] randomBytes = new byte[16]; 

        random.nextBytes(randomBytes); 

        System.out.println("Secure random bytes: " + Base64.getEncoder().encodeToString(randomBytes)); 

    } 

} 