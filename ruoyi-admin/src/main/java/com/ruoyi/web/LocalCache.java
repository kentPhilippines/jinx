//package com.ruoyi.web;
//
//
//import com.ruoyi.alipay.domain.AlipayUserInfo;
//import com.ruoyi.alipay.domain.MerchantInfoEntity;
//import com.ruoyi.alipay.service.IMerchantInfoEntityService;
//import com.ruoyi.common.utils.StringUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.crypto.hash.Hash;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Service
//public class LocalCache {
//    public static List<String> alipay = new ArrayList();
//    @Autowired
//    private IMerchantInfoEntityService merchantInfoEntityService;
//
//    /**
//     * 查询 商户 信息放进缓存
//     *
//     * @return MerchantBet
//     */
//
//    @PostConstruct
//    public void cacheInit() {
//        AlipayUserInfo merchantInfoEntity = new AlipayUserInfo();
//        List<AlipayUserInfo> alipayUserInfos = merchantInfoEntityService.selectMerchantInfoEntityList(merchantInfoEntity);
//        alipay = alipayUserInfos.stream().filter(tmp -> {
//            return StringUtils.isNotBlank(tmp.getAgent());
//        }).map(AlipayUserInfo::getAgent).collect(Collectors.toList());
//        System.out.println(alipayUserInfos);
//    }
//
//
//}
