package com.ruoyi.framework.util;


import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.framework.google.GoogleAuthenticator;
import com.ruoyi.system.domain.SysUserGoogle;
import com.ruoyi.system.service.ISysUserGoogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoogleUtils {

    @Autowired
    private ISysUserGoogleService sysUserGoogleService;

    public boolean verifyGoogleCode(String username, String validateCode) {
        SysUserGoogle sysUserGoogle = sysUserGoogleService.selectSysUserGoogleByUsername(username);
        if (sysUserGoogle == null) {
            throw new BusinessException("用户名输入有误");
        }
        String key = sysUserGoogle.getSecretKey();
        Long now = System.currentTimeMillis();
       return GoogleAuthenticator.check_code(key, validateCode, now);
    }

    public String getSecretKey(){
       return GoogleAuthenticator.getRandomSecretKey();
    }

    public String getGoogleQRCodeURL(String userId,String host, String secret){
     return GoogleAuthenticator.getQRBarcodeURL(userId,host,secret);
    }

}
