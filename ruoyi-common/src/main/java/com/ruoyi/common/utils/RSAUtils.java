package com.ruoyi.common.utils;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.ruoyi.common.exception.BusinessException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;


public class RSAUtils {
    public static final String CHARSET = "UTF-8";   //设置以UTF-8编码
    public static final String RSA_ALGORITHM = "RSA"; //采用RSA加解密算法
    public static final Integer KEY_SIZE = 1024;
    public static final String RSA_ALGORITHM_SIGN = "SHA256WithRSA";

    /**
     * 随机生成密钥对
     */
    public static List<String> genKeyPair() {
        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            // 初始化密钥对生成器
            keyPairGen.initialize(KEY_SIZE);
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 得到私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            String publicKeyString = java.util.Base64.getEncoder().encodeToString(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = java.util.Base64.getEncoder().encodeToString(privateKey.getEncoded());
            // 将公钥和私钥保存到List
            return ImmutableList.of(publicKeyString, privateKeyString);
        } catch (NoSuchAlgorithmException var15){
            return null;
        }
    }

    /**
     * 公钥对象
     * @param publicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            X509EncodedKeySpec x509Key = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
            RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509Key);
            return key;
        } catch (Exception e) {
            throw new RuntimeException("得到公钥时异常", e);
        }
    }

    /**
     * 私钥对象
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8Key = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8Key);
        return key;

    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, String publicKey) {
        try {
            RSAPublicKey rsaPublicKey = getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), rsaPublicKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串" + data + "时异常", e);
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */
    public static String privateDecrypt(String data, String privateKey) {
        try {
            RSAPrivateKey rsaPrivateKey = getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), rsaPrivateKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥加密
     * @param data          加密字符串
     * @param privateKey    私钥字符串
     * @return              密文
     */
    public static String privateEncrypt(String data, String privateKey) {
        try {
            RSAPrivateKey rsaPrivateKey = getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), rsaPrivateKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("解密字符串" + data + "时异常", e);
        }
    }

    /**
     * 公钥解密
     * @param data          加密字符串
     * @param publicKey     公钥字符串
     * @return              解密字符串
     */
    public static String publicDecrypt(String data, String publicKey) {
        try {
            RSAPublicKey rsaPublicKey = getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), rsaPublicKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串" + data + "时异常", e);
        }
    }

    /**
     *  RSA加密算法对于加密的长度是有要求的。一般来说，加密时，明文长度大于加密钥长度-11时，明文就要进行分段；解密时，密文大于解密钥长度时，密文就要进行分段（以字节为单位）
     * @param cipher
     * @param opmode
     * @param datas
     * @param keySize
     * @return
     */
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }


    /**
     * 签名
     *
     * @param content
     * @return
     */
    public String sign(String content, String privateKey) {
        try {
            //sign
            Signature signature = Signature.getInstance(RSA_ALGORITHM_SIGN);
            signature.initSign(getPrivateKey(privateKey));
            signature.update(content.getBytes(CHARSET));
            return org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(signature.sign());
        } catch (Exception e) {
            throw new RuntimeException("签名字符串[" + content + "]时遇到异常", e);
        }
    }

    /**
     * 验签的方法
     *
     * @param content
     * @param sign
     * @return
     */
    public boolean verify(String content, String sign, String publicKey) {
        try {
            Signature signature = Signature.getInstance(RSA_ALGORITHM_SIGN);
            signature.initVerify(getPublicKey(publicKey));
            signature.update(content.getBytes(CHARSET));
            return signature.verify(Base64.decodeBase64(sign));
        } catch (Exception e) {
            throw new RuntimeException("验签字符串[" + content + "]时遇到异常", e);
        }
    }

    /**
     * 将参数用公钥进行加密，平台内部调用
     * @param map           map参数
     * @param privateKey    私钥
     * @return              返回map
     */
    public static String getEncryptPublicKey(Map<String, Object> map, String privateKey){
        //拼接参数
        String urlParam = MapDataUtil.createParam(map);
        //私钥解密密文得到字符串参数
        String cipherText = publicEncrypt(urlParam,privateKey);
        //调用方法转成map
        if (StringUtils.isEmpty(cipherText)) {
            throw new BusinessException("加密字符串为空");
        }
        return cipherText;
    }

    /**
     * 将解密的密文转成map返回 所有的验证都在调用前完成
     * @param data          密文
     * @param privateKey    私钥
     * @return              返回map
     */
    public Map<String, Object> retMapDecode(String data, String privateKey){
        //私钥解密密文得到字符串参数
        String urlParam = privateDecrypt(data,privateKey);
        //调用方法转成map
        if (StringUtils.isEmpty(urlParam)) {
            throw new BusinessException("解密字符串为空");
        }
       return MapDataUtil.paramToMap(urlParam);
    }

}
