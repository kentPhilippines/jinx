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

    public int verifyGoogleCode(String username, String validateCode) {
        SysUserGoogle sysUserGoogle = sysUserGoogleService.selectSysUserGoogleByUsername(username);
        if (sysUserGoogle == null) {
            return 0;
        }
       String key = sysUserGoogle.getSecretKey();
       Long now = System.currentTimeMillis();
       boolean check_code = GoogleAuthenticator.check_code(key, validateCode, now);
       return check_code ? 1 : 2 ;
    }

    public String getSecretKey(){
       return GoogleAuthenticator.getRandomSecretKey();
    }

    public String getGoogleQRCodeURL(String userId,String host, String secret){
     return GoogleAuthenticator.getQRBarcodeURL(userId,host,secret);
    }

}
