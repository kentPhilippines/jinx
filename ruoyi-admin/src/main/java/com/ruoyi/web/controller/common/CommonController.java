package com.ruoyi.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.IAlipayUserInfoService;
import com.ruoyi.common.utils.MapDataUtil;
import com.ruoyi.common.utils.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;

import java.util.Map;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Controller
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IAlipayUserInfoService alipayUserInfoService;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtils.isValidFilename(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = Global.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }

    /**
     * 商户参数公钥加密请求
     * @param request
     * @return
     */
    @PostMapping("/common/public/encrypt")
    @ResponseBody
    public AjaxResult getEncryptPublicKey(HttpServletRequest request){
        String publicKey = request.getParameter("publicKey");
        String userId = request.getParameter("userId");
        AlipayUserInfo alipayUserInfo = alipayUserInfoService.findMerchantInfoByUserId(userId);
        if(alipayUserInfo == null){
            return AjaxResult.warn("商户不存在");
        }else if(!publicKey.equals(alipayUserInfo.getPublicKey())){
            return AjaxResult.warn("与商户公钥不符");
        }
        Map<String, Object> map = MapDataUtil.convertDataMap(request);
        String param = MapDataUtil.createParam(map);
        return AjaxResult.success(RSAUtils.publicEncrypt(param,publicKey));
    }

    /**
     * 商户参数公钥加密请求
     * @param request
     * @return
     */
    @PostMapping("/common/private/decrypt")
    public AjaxResult getDecryptPrivateKey(HttpServletRequest request){
        String privateKey = request.getParameter("privateKey");
        String userId = request.getParameter("userId");
        AlipayUserInfo alipayUserInfo = alipayUserInfoService.findMerchantInfoByUserId(userId);
        if(alipayUserInfo == null){
            return AjaxResult.warn("商户不存在");
        }else if(!privateKey.equals(alipayUserInfo.getPublicKey())){
            return AjaxResult.warn("与商户公钥不符");
        }
        Map<String, Object> map = MapDataUtil.convertDataMap(request);
        String param = MapDataUtil.createParam(map);
        return AjaxResult.success(RSAUtils.privateDecrypt(param,privateKey));
    }
}
