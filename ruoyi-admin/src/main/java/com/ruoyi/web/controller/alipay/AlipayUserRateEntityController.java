package com.ruoyi.web.controller.alipay;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.alipay.domain.*;
import com.ruoyi.alipay.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.RSAUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 用户产品费率Controller
 * 为什么不到业务层写业务代码，因为多数据源切的是业务层，数据获取灵活度不高，所以业务层写业务不好弄
 *
 * @author kent
 * @date 2020-03-18
 */
@Controller
@RequestMapping("/alipay/rate")
public class AlipayUserRateEntityController extends BaseController {
    private String prefix = "alipay/merchant/rate";
    @Autowired
    private IAlipayUserRateEntityService alipayUserRateEntityService;
    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;
    @Autowired
    IAlipayProductService iAlipayProductService;
    @Autowired
    IAlipayChanelFeeService alipayChanelFeeServiceImpl;
    @Autowired
    private IAlipayUserInfoService alipayUserInfoService;
    @Autowired
    private IMerchantInfoEntityService merchantInfoEntityService;
    /*
     * 查询商户产品费率列表
     */
    static final String PAY_TYPE = "1";

    @RequiresPermissions("merchant:rate:view")
    @GetMapping()
    public String rate(ModelMap modelMap) {
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        List<AlipayUserFundEntity> channelList = alipayUserFundEntityService.findUserFundRate();
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        modelMap.put("productList", list);
        modelMap.put("channelList", channelList);
        return prefix + "/rate";
    }

    static final String MSG = "【当交易额为1000时盈利：";
    @Autowired
    private IAlipayChanelFeeService alipayChanelFeeService;

    private static String getKeyedDigestUTF8(String strSrc) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("UTF8"));
            String result = "";
            byte[] temp;
            temp = md5.digest("".getBytes("UTF8"));
            for (int i = 0; i < temp.length; i++) {
                result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 新增用户产品费率
     */
    @Log(title = "新增商户费率", businessType = BusinessType.INSERT)
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        AlipayUserInfo alipayUserInfo = new AlipayUserInfo();
        alipayProductEntity.setStatus(1);
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        modelMap.put("productList", list);
        List<AlipayUserFundEntity> rateList = alipayUserFundEntityService.findUserFundRate();
        modelMap.put("rateList", rateList);
        //查询所有的商户
        alipayUserInfo.setSwitchs(1);
        alipayUserInfo.setUserType(1);
        List<AlipayUserInfo> userInfo = alipayUserInfoService.selectAllUserInfoList(alipayUserInfo);
        modelMap.put("merList", userInfo);
        return prefix + "/add";
    }

    private static String createParam(Map<String, Object> map) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }
            Object[] key = map.keySet().toArray();
            Arrays.sort(key);
            StringBuffer res = new StringBuffer(128);
            for (int i = 0; i < key.length; i++) {
                if (ObjectUtil.isNotNull(map.get(key[i]))) {
                    res.append(key[i] + "=" + map.get(key[i]) + "&");
                }
            }
            String rStr = res.substring(0, res.length() - 1);
            return rStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 修改用户产品费率
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserRateEntity alipayUserRateEntity = alipayUserRateEntityService.selectAlipayUserRateEntityById(id);
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        mmap.put("productList", list);
        List<AlipayUserFundEntity> rateList = alipayUserFundEntityService.findUserFundRate();
        mmap.put("rateList", rateList);
        mmap.put("alipayUserRateEntity", alipayUserRateEntity);
        return prefix + "/edit";
    }

    @GetMapping("/edits/{ids}")
    public String edit(@PathVariable("ids") String ids, ModelMap mmap) {
        //查询产品类型下拉菜单
        mmap.put("ids", ids);
        List<AlipayUserRateEntity> rateEntityList = alipayUserRateEntityService.findRates(ids);
        StrBuilder strBuilder = StrBuilder.create();
        for (AlipayUserRateEntity rateEntity : rateEntityList) {
            strBuilder.append("id:").append(rateEntity.getId()).append(" 账号：").append(rateEntity.getUserId()).append(" ").append("原渠道：").
                    append(rateEntity.getChannelId()).append(" ").append("原产品：").append(rateEntity.getPayTypr() + " ");
        }
        mmap.put("rete", strBuilder.toString());
        List<AlipayUserFundEntity> rateList = alipayUserFundEntityService.findUserFundRate();
        mmap.put("rateList", rateList);
        return prefix + "/edits";
    }

    @RequiresPermissions("merchant:rate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserRateEntity alipayUserRateEntity) {
        List<AlipayUserInfo> userList = new ArrayList<>();
        List<String> userNameList = new ArrayList<>();
        if (StrUtil.isNotEmpty(alipayUserRateEntity.getAgentUserId())) {
            userList = alipayUserFundEntityService.findUserByAgent(alipayUserRateEntity.getAgentUserId());
        }
        if (userList.size() > 0) {
            for (AlipayUserInfo user : userList) {
                userNameList.add(user.getUserId());
            }
            alipayUserRateEntity.setAgentList(userNameList);
        }
        startPage();
        List<AlipayUserRateEntity> list = alipayUserRateEntityService.selectAlipayUserRateEntityList(alipayUserRateEntity);
        List<AlipayUserFundEntity> rateList = alipayUserFundEntityService.findUserFundRate();
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        List<AlipayProductEntity> productlist = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        ConcurrentHashMap<String, AlipayUserFundEntity> qrCollect = rateList.stream().collect(Collectors.toConcurrentMap(AlipayUserFundEntity::getUserId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        ConcurrentHashMap<String, AlipayProductEntity> prCollect = productlist.stream().collect(Collectors.toConcurrentMap(AlipayProductEntity::getProductId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        BigDecimal a = new BigDecimal("0");
        for (AlipayUserRateEntity rate : list) {
            AlipayUserFundEntity channel = qrCollect.get(rate.getChannelId());
            AlipayProductEntity product = prCollect.get(rate.getPayTypr());
            AlipayChanelFee channelBy = alipayChanelFeeService.findChannelBy(rate.getChannelId(), rate.getPayTypr());
            String channelRFee = channelBy.getChannelRFee();
            a = new BigDecimal("" + rate.getFee());
            if (rate.getFeeType().toString().equals(PAY_TYPE)) {
                rate.setChannelFee(channelRFee);
                rate.setProfit(String.valueOf(a.subtract(new BigDecimal(channelRFee))));
            } else {
                rate.setChannelFee(channelBy.getChannelDFee());
                rate.setProfit(String.valueOf(a.subtract(new BigDecimal(channelBy.getChannelDFee()))));
            }
            if (ObjectUtil.isNotNull(channel)) {
                rate.setChannelId(channel.getUserName());
            }
            if (ObjectUtil.isNotNull(product)) {
                rate.setPayTypr(product.getProductName());
            }
        }
        return getDataTable(list);
    }

    /*
     * 新增保存用户产品费率
     */
    @RequiresPermissions("merchant:rate:add")
    @Log(title = "商户费率", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayUserRateEntity alipayUserRateEntity) {
        AlipayChanelFee channel = alipayChanelFeeServiceImpl.findChannelBy(alipayUserRateEntity.getChannelId(), alipayUserRateEntity.getPayTypr());
        if (ObjectUtil.isNull(channel)) {
            return error("当前渠道未接通，请联系技术人员对接");
        }
        AlipayUserRateEntity check = alipayUserRateEntityService.checkUniqueRate(alipayUserRateEntity);
        if (null != check) {
            throw new BusinessException("操作失败，商户费率重复");
        }
        return toAjax(alipayUserRateEntityService.insertAlipayUserRateEntity(alipayUserRateEntity));
    }

    /*
     * 删除用户产品费率
     */
    @RequiresPermissions("merchant:rate:remove")
    @Log(title = "商户费率", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayUserRateEntityService.deleteAlipayUserRateEntityByIds(ids));
    }

    /**
     * 商户费率状态更新
     */
    @RequiresPermissions("merchant:rate:status")
    @Log(title = "商户费率修改", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult updateStatus(String id, String userId, String feeType, String switchs) {
        logger.info("[当前处理费率状态开启或关闭的管理员账号为：" + ShiroUtils.getSysUser().getLoginName() + "]");
        logger.info("[当前处理商户状态的参数为：" + switchs + "]");
        return toAjax(alipayUserRateEntityService.changeStatus(id, userId, feeType, switchs));
    }

    @RequiresPermissions("merchant:rate:edit")
    @Log(title = "商户费率批量修改", businessType = BusinessType.UPDATE)
    @PostMapping("/edits")
    @ResponseBody
        public AjaxResult editsSave(String ids, final String channel) {
        List<AlipayUserRateEntity> rateEntityList = alipayUserRateEntityService.findRates(ids);
        Map map = new HashMap();
        for (AlipayUserRateEntity rateEntity : rateEntityList) {
            map.put(rateEntity.getChannelId(), rateEntity.getChannelId());
        }
        if (map.size() > 1) {
            throw new BusinessException("当前批量修改中存在多个渠道，请选择相同渠道完成批量修改");
        }
        for (AlipayUserRateEntity rateEntity : rateEntityList) {
            List<AlipayUserInfo> users = alipayUserInfoService.findAgenByUser(rateEntity.getUserId());
            if (CollUtil.isNotEmpty(users) || users.size() > 0) {
                continue;// 凡是存在下级代理商户的商户费率一律不允许批量切换，代理商费率批量结算容易造成业务bug
            }
            String channelId = rateEntity.getChannelId();
            rateEntity.setChannelId(channel);
            try {
                alipayUserRateEntityService.clickFee(rateEntity);
                alipayUserRateEntityService.isAgentFee(rateEntity);
                AlipayChanelFee channel1 = alipayChanelFeeServiceImpl.findChannelBy(rateEntity.getChannelId(), rateEntity.getPayTypr());
                if (ObjectUtil.isNull(channel1)) {
                    throw new BusinessException("当前渠道未接通，请联系技术人员对接");
                }
                alipayUserRateEntityService.updateAlipayUserRateEntity(rateEntity);
            } catch (Exception e) {
                ThreadUtil.execute(() -> {
                    if (e.getMessage().contains("java.sql.SQLIntegrityConstraintViolationException")) {//费率发生数据库唯一键约束
                        //停用当前费率，开启目标费率
                        int i = alipayUserRateEntityService.changeStatus(rateEntity.getId() + "", rateEntity.getUserId(), rateEntity.getFeeType() + "", "0");
                        if (i > 0) {
                            rateEntity.setStatus(null);
                            rateEntity.setSwitchs(null);
                            List<AlipayUserRateEntity> rateEntityList1 = alipayUserRateEntityService.selectAlipayUserRateEntityList(rateEntity);
                            if (rateEntityList1.size() == 1) {
                                AlipayUserRateEntity rateEntity1 = rateEntityList1.get(0);
                                alipayUserRateEntityService.changeStatus(rateEntity1.getId() + "", rateEntity1.getUserId(), rateEntity1.getFeeType() + "", "1");
                            }
                        }
                    }
                });
            }

        }

        return success("修改完毕");
    }

    /*
     * 修改保存用户产品费率
     */
    @RequiresPermissions("merchant:rate:edit")
    @Log(title = "商户费率修改", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayUserRateEntity alipayUserRateEntity) {
        //1，查看当前修改完的费率是否有配置渠道费率,并检查是否有重复配置的情况
        //2，查看当前修完费率是否有配置代理商费率
        //3，如以上不存在问题，则保存当前修改完费率，且对相同产品类型的费率进行关闭
        alipayUserRateEntityService.clickFee(alipayUserRateEntity);
        alipayUserRateEntityService.isAgentFee(alipayUserRateEntity);
        AlipayChanelFee channel = alipayChanelFeeServiceImpl.findChannelBy(alipayUserRateEntity.getChannelId(), alipayUserRateEntity.getPayTypr());
        if (ObjectUtil.isNull(channel)) {
            return error("当前渠道未接通，请联系技术人员对接");
        }
        return toAjax(alipayUserRateEntityService.updateAlipayUserRateEntity(alipayUserRateEntity));
    }

    @GetMapping("/paytest")
    @Log(title = "商户拉单测试", businessType = BusinessType.UPDATE)
    public void payTest(HttpServletResponse response,
                        HttpServletRequest request) throws IOException {
        String userId = request.getParameter("userId");
        String feeId = request.getParameter("Id");
        AlipayUserRateEntity rateEntity = alipayUserRateEntityService.selectAlipayUserRateEntityById(Long.valueOf(feeId));
        Map<String, Object> parMap = new HashMap<>();
        AlipayUserInfo userInfo = new AlipayUserInfo();
        userInfo.setUserId(userId);
        List<AlipayUserInfo> alipayUserInfos = merchantInfoEntityService.selectMerchantInfoEntityList(userInfo);
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        String userid = rateEntity.getUserId();
        String key = alipayUserInfos.get(0).getPayPasword();//交易密钥
        String publicKey = alipayUserInfos.get(0).getPublicKey();
        String dealurl = alipayUserInfos.get(0).getDealUrl();
        String amount = "50.00";
        parMap.put("amount", amount);
        parMap.put("appId", userId);
        parMap.put("applyDate", d.format(new Date()));
        parMap.put("notifyUrl", dealurl);
        parMap.put("pageUrl", dealurl);
        parMap.put("orderId", IdUtil.simpleUUID());
        parMap.put("passCode", rateEntity.getPayTypr());
        parMap.put("subject", amount);
        parMap.put("userid", "王富贵");
        String createParam = createParam(parMap);
        logger.info("签名前请求串：" + createParam);
        String md5 = getKeyedDigestUTF8(createParam + key);
        logger.info("签名：" + md5);
        parMap.put("sign", md5);
        String createParam2 = createParam(parMap);
        logger.info("加密前字符串：" + createParam2);
        String publicEncrypt = RSAUtils.publicEncrypt(createParam2, publicKey);
        logger.info("加密后字符串：" + publicEncrypt);
        Map<String, Object> postMap = new HashMap<String, Object>();
        postMap.put("cipherText", publicEncrypt);
        postMap.put("userId", userid);
        logger.info("请求参数：" + postMap.toString());
         String post = HttpUtil.post(dealurl + "/deal/pay", postMap);
        logger.info("相应结果集：" + post);
        JSONObject json = JSONObject.parseObject(post);
        String result = json.getString("success");
        if (StrUtil.isNotEmpty(result) && result.equals("true")) {
            response.setContentType("text/html; charset=UTF-8");
            response.sendRedirect(json.getJSONObject("result").getString("returnUrl"));

            //  ().write(json.getJSONObject("result").getString("returnUrl"));
        } else {
            String message = json.getString("message");
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(message);
        }
        return;
    }

}
