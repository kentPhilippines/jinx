package com.ruoyi.web.controller.back;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.service.IAlipayDealOrderAppService;
import com.ruoyi.alipay.service.IAlipayDealOrderEntityService;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.alipay.service.StatisticService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 客户数据可视化模块
 *
 * @author kent
 */
@RequestMapping("/back/merchant/statistics")
@Controller
public class StatisticsMerchantContorller extends BaseController {
    private String prefix = "merchant/statistics";
    @Autowired
    StatisticService statisticServiceImpl;
    @Autowired
    private IAlipayDealOrderAppService alipayDealOrderAppService;
    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;
    @Autowired
    private IAlipayDealOrderEntityService alipayDealOrderEntityService;

    @GetMapping("/view")
    public String detail(ModelMap mmap) {
        BaseEntity baseEntity = new BaseEntity();
        Map<String, Object> params = new HashMap<>();
        // DateUtil.format(DateUtil.offsetMonth(DateUtil.parseDate(dayStart.toString(s)).toJdkDate(),-1).toJdkDate(), DatePattern.NORM_DATETIME_PATTERN);
        params.put("dayStart", DateUtil.format(DateUtil.offsetMonth(new Date(), -1).toJdkDate(), DatePattern.NORM_DATETIME_PATTERN));
        params.put("dayEnd", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN));
        baseEntity.setParams(params);
        SysUser sysUser = ShiroUtils.getSysUser();
        String merchantId = sysUser.getMerchantId();//交易账号
        Map<String, Object> stackedAreaChartMap = statisticServiceImpl.getStackedAreaChart(merchantId, baseEntity, false);
        mmap.put("data", stackedAreaChartMap);
        return prefix + "/agentStatistics";
    }


    @GetMapping("/channelhours")
    public String channelhours(ModelMap mmap) {
        return prefix + "/channelhours";
    }

    @GetMapping("/merchanthours")
    public String merchanthours(ModelMap mmap) {
        return prefix + "/merchanthours";
    }

    /**
     * 显示具体统计内容
     */
    @PostMapping("/channelhoursList")
    @ResponseBody
    public TableDataInfo channelhoursList(StatisticsEntity statisticsEntity) {
        startPage();
        List<StatisticsEntity> list = alipayDealOrderEntityService.selectStatisticsDataByHours(statisticsEntity, DateUtils.dayStart(), DateUtils.dayEnd());
        List<AlipayUserFundEntity> listFund = alipayUserFundEntityService.findUserFundAll();
        ConcurrentHashMap<String, AlipayUserFundEntity> userCollect = listFund.stream().collect(Collectors.toConcurrentMap(AlipayUserFundEntity::getUserId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        for (StatisticsEntity sta : list)
            if (ObjectUtil.isNotNull(userCollect.get(sta.getUserId())))
                sta.setUserName(userCollect.get(sta.getUserId()).getUserName());
        return getDataTable(list);
    }


    @PostMapping("/merchanthoursList")
    @ResponseBody
    public TableDataInfo merchanthoursList(StatisticsEntity statisticsEntity) {
        startPage();
        List<StatisticsEntity> list = alipayDealOrderAppService.selectMerchantStatisticsDataByHours(statisticsEntity, DateUtils.dayStart(), DateUtils.dayEnd());
        List<AlipayUserFundEntity> listFund = alipayUserFundEntityService.findUserFundAll();
        ConcurrentHashMap<String, AlipayUserFundEntity> userCollect = listFund.stream().collect(Collectors.toConcurrentMap(AlipayUserFundEntity::getUserId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        for (StatisticsEntity sta : list) {
            if (ObjectUtil.isNotNull(userCollect.get(sta.getUserId())))
                sta.setUserName(userCollect.get(sta.getUserId()).getUserName());
        }
        return getDataTable(list);
    }


}
