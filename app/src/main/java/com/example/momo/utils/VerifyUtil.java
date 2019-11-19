package com.example.momo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class VerifyUtil {



    /**
     *  使用md5方式进行加密
     *  @return
     */
    public static String digest(String content){
        StringBuilder builder = new StringBuilder();
        try {
            MessageDigest msgDitest = MessageDigest.getInstance("MD5");
            msgDitest.update(content.getBytes());
            byte[] digests = msgDitest.digest();
            //将每个字节转为16进制
            for (int i=0;i<digests.length;i++){
                builder.append(Integer.toHexString(digests[i] & 0xff +8));//+8为加盐操作
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return  builder.toString();
    }

    /*
   初始化KeyPairGenerator类，并获取到公钥和私钥
 */
    byte[] publicKeyByte;
    byte[] prvateKtyByte;

    public void getKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024); //设置密钥对的大小
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();//获取私钥
            PublicKey publicKey = keyPair.getPublic();//获取公钥
            prvateKtyByte = privateKey.getEncoded();//私钥对应的字节数组
            publicKeyByte = publicKey.getEncoded(); //公钥对应的字节数组

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
  /*
  公钥加密
  * */
    public byte[] encryption(String content) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyByte);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result = cipher.doFinal(content.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return result;
    }
/*
* 私钥解密
* */
    public void decryption() {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
            //私钥需要通过PKCS8EncodedKeySpec来读取
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(prvateKtyByte);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            //生成私钥
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            //String content = "123456";
            byte[] input = encryption("123456");
            byte[] result = cipher.doFinal(input);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    //加密文件
    public static String md5ForFile(File file){
        int buffersize = 1024;
        FileInputStream fis = null;
        DigestInputStream dis = null;

        try {
            //创建MD5转换器和文件流
            MessageDigest messageDigest =MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            dis = new DigestInputStream(fis,messageDigest);

            byte[] buffer = new byte[buffersize];
            //DigestInputStream实际上在流处理文件时就在内部就进行了一定的处理
            while (dis.read(buffer) > 0);

            //通过DigestInputStream对象得到一个最终的MessageDigest对象。
            messageDigest = dis.getMessageDigest();

            // 通过messageDigest拿到结果，也是字节数组，包含16个元素
            byte[] array = messageDigest.digest();
            // 同样，把字节数组转换成字符串
            StringBuilder hex = new StringBuilder(array.length * 2);
            for (byte b : array) {
                if ((b & 0xFF) < 0x10){
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }




    public static String getSHA(String info) {
        byte[] digesta = null;
        try {
// 得到一个SHA-1的消息摘要
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
// 添加要进行计算摘要的信息
            alga.update(info.getBytes());
// 得到该摘要
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
// 将摘要转为字符串
        String rs = byte2hex(digesta);
        return rs;
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (byte aB : b) {
            stmp = (Integer.toHexString(aB & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }


}


