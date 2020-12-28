package com.ruoyi.web.controller.api;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.domain.AlipayRunOrderEntity;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import com.ruoyi.alipay.service.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.dealpay.domain.DealpayRunOrderEntity;
import com.ruoyi.dealpay.service.IDealpayRunOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 自动对账
 */
@Controller
@RequestMapping("/accountAppFundApi")
public class AccountAppFundApi extends BaseController {
    private String prefix = "alipay/accountAppFund";
    @Autowired
    IDealpayRunOrderService dealpayRunOrderServiceImpl;
    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;
    @Autowired
    private Reconciliation reconciliationImpl;
    @Autowired
    private IAlipayDealOrderEntityService dealOrderAppService;
    @Autowired
    private IAlipayWithdrawEntityService alipayWithdrawEntityService;
    @Autowired
    IAlipayRunOrderEntityService alipayRunOrderEntityServiceImpl;

    @GetMapping()
    public String fund() {
        return prefix + "list";
    }


    /**
     * 获取存疑订单
     *
     * @param alipayUserFundEntity
     * @return
     */
    @PostMapping("/accountList")
    @ResponseBody
    public TableDataInfo accountList(AlipayUserFundEntity alipayUserFundEntity) {
        startPage();
        List<AlipayUserFundEntity> list = alipayUserFundEntityService
                .findaAcountList(alipayUserFundEntity);
        return getDataTable(list);
    }

    @GetMapping("/orderAccount")
    @ResponseBody
    public void accountList(String starTime, String endTime, HttpServletRequest req, HttpServletResponse res) {
        logger.info("【当前开始对账，对账开始时间：" + starTime + "，对账结束结束时间：" + endTime + "】");
        /**
         * 1，获取所有交易订单  【主交易订单为准】
         * 2，获取所有代付订单   【代付订单为准】
         * 3，匹配交易流水      【交易订单流水表】
         * 4，获取当日交易账户详情 【fund_bak】
         * 5，核对账户金额       【fund】
         */
        /**
         * 交易订单核对
         */

        ThreadUtil.execute(() -> {
            List<AlipayDealOrderEntity> orderList = new ArrayList<>();
            Integer page = 1;
            Integer size = 500;
            boolean flag = true;
            do {
                orderList = dealOrderAppService.findOrderLimit(starTime, endTime, page, size);
                page += 500;
                if (orderList.size() < 500)
                    flag = false;
                for (AlipayDealOrderEntity orderEntity : orderList) {
                    /**
                     * 交易订单核对
                     * 交易订单成功   1，商户交易加款 20  2，商户交易手续费扣款 21 3，商户交易代理商分润 13
                     */
                    if (orderEntity.getOrderStatus().equals("2")) {//成功
                        Map<String, String> stringStringMap = reconciliationImpl.orderReconciliation(orderEntity.getAssociatedId(), orderEntity);
                        String err = stringStringMap.get(Reconciliation.ERROR_RECONCILIATION);
                        String succ = stringStringMap.get(Reconciliation.SUCCESS_RECONCILIATION);
                        if (StrUtil.isNotEmpty(succ)) {
                            logger.info("订单核对成功" + succ + "-------------------------------> 正常");
                        } else if (StrUtil.isNotEmpty(err)) {
                            logger.info("【订单核对失败" + err + "】");
                            insert(orderEntity.getDealAmount(), orderEntity.getAssociatedId(), err, orderEntity.getCreateTime(), 1, orderEntity.getOrderAccount());
                        }
                    }
                }
                orderList = null;
            } while (flag);
        });

        /**
         * 代付订单核对
         */
        ThreadUtil.execute(() -> {
            List<AlipayWithdrawEntity> witList = new ArrayList<>();
            Integer page = 1;
            Integer size = 500;
            boolean flag = true;
            do {
                witList = alipayWithdrawEntityService.findWitLimit(starTime, endTime, page, size);
                page += 500;
                if (witList.size() < 500)
                    flag = false;
                for (AlipayWithdrawEntity wit : witList) {
                    /**
                     *  代付订单核对
                     *  1，当前订单为成功 ------>  1，代付扣款 10     2，代付手续费 9   3，代付代理分润 26
                     *  2，当前订单为失败 ------>  1，代付扣款 10     2，代付手续费 9   3，代付代理分润 26 4，商户代付失败解冻 8 5，丧户代付失败手续费解冻 22
                     */
                    if (wit.getOrderStatus().toString().equals("2")) {//成功
                        Map<String, String> stringStringMap = reconciliationImpl.witReconciliation(wit.getOrderId(), wit);
                        String err = stringStringMap.get(Reconciliation.ERROR_RECONCILIATION);
                        String succ = stringStringMap.get(Reconciliation.SUCCESS_RECONCILIATION);
                        if (StrUtil.isNotEmpty(succ)) {
                            logger.info("订单核对成功" + succ + "==========>成功");
                        } else if (StrUtil.isNotEmpty(err)) {
                            logger.info("【订单核对失败" + err + "】");
                            insert(wit.getAmount(), wit.getOrderId(), err, wit.getCreateTime(), 2, wit.getUserId());

                        }
                    } else if (wit.getOrderStatus().toString().equals("3")) {//失败
                        Map<String, String> stringStringMap = reconciliationImpl.witReconciliation(wit.getOrderId(), wit);
                        String err = stringStringMap.get(Reconciliation.ERROR_RECONCILIATION);
                        String succ = stringStringMap.get(Reconciliation.SUCCESS_RECONCILIATION);
                        if (StrUtil.isNotEmpty(succ)) {
                            logger.info("订单核对成功" + succ + "==========>成功");
                        } else if (StrUtil.isNotEmpty(err)) {
                            logger.info("【订单核对失败" + err + "】");
                            insert(wit.getAmount(), wit.getOrderId(), err, wit.getCreateTime(), 2, wit.getUserId());

                        }
                    }
                }
                witList = null;
            } while (flag);
        });


    }

    void insert(Double amount, String orderId, String deal, Date submitTime, Integer type, String account) {
        DealpayRunOrderEntity run = new DealpayRunOrderEntity();
        run.setOrderId(IdUtil.objectId());
        run.setAccountW("对账");
        run.setAcountR("对账");
        run.setAmount(amount);
        run.setAssociatedId(orderId);
        run.setAmountType("1");
        run.setGenerationIp("127.0.0.1");
        run.setDealDescribe(deal);
        run.setRunOrderType(type);
        run.setOrderAccount(account);
        run.setRunType("1");
        run.setSubmitTime(submitTime);
        dealpayRunOrderServiceImpl.insertDealpayRunOrder(run);
    }


    @GetMapping("/fundAccount")
    @ResponseBody
    public void fundAccount(String starTime) {
        /**
         * 核对账户金额 方法
         * 1，查询自起始日期起账户金额
         * 2，查询该账户下所有的流水金额
         * 3，流水金额核对
         * 4，核对账户总交易情况
         * 5，通过订单金额汇总核对总交易数据
         */


        List<AlipayUserFundEntity> fundList = alipayUserFundEntityService.findUserFundAll();
        for (AlipayUserFundEntity fund : fundList) {
            boolean flag = true;
            Double accountBalance = fund.getAccountBalance();
            List<Map<String, String>> accMap = new ArrayList<>();

            Integer page = 0;
            Integer size = 1;
            String str = DatePattern.NORM_DATETIME_FORMAT.format(DateUtil.parse(starTime));//2020-12-06 01:00:00
            String end = DatePattern.NORM_DATETIME_FORMAT.format(DateUtil.offsetDay(DateUtil.parseDateTime(starTime).toJdkDate(), 1).toJdkDate());//2020-12-07 01:00:00
            do {
                String today = DateUtil.today();
                AlipayUserFundEntity fundBak = alipayUserFundEntityService.findFundBak(str, fund.getUserId(), end);//如果这里的时间是 6号则查询的账号为6-7日凌晨1点之间的账户数据 时间偏移量为  < 当前查询时间
                AlipayUserFundEntity fundBak1 = alipayUserFundEntityService.findFundBak(end, fund.getUserId(), DatePattern.NORM_DATETIME_FORMAT.format(DateUtil.offsetDay(DateUtil.parseDateTime(end).toJdkDate(), 1).toJdkDate()));//如果这里的时间是 6号则查询的账号为6-7日凌晨1点之间的账户数据 时间偏移量为  < 当前查询时间
                List<AlipayRunOrderEntity> runList = alipayRunOrderEntityServiceImpl.findUserOrderLimit(fund.getUserId(), str, end);//这里的查询流水则为  6-7日凌晨1点账户数据流水
                if (runList.size() == 0) {
                    str = DatePattern.NORM_DATETIME_FORMAT.format(DateUtil.offsetDay(DateUtil.parseDateTime(str).toJdkDate(), 1));//2020-12-07 01:00:00
                    end = DatePattern.NORM_DATETIME_FORMAT.format(DateUtil.offsetDay(DateUtil.parseDateTime(str).toJdkDate(), 1).toJdkDate());//2020-12-08 01:00:00
                    DateTime dateTime = DateUtil.parseDateTime(str);
                    String s = dateTime.toString(DatePattern.NORM_DATE_PATTERN);
                    if (today.equals(s)) {
                        flag = false;
                    }
                    continue;
                }
                if (null == fundBak1) {
                    str = DatePattern.NORM_DATETIME_FORMAT.format(DateUtil.offsetDay(DateUtil.parseDateTime(str).toJdkDate(), 1));//2020-12-07 01:00:00
                    end = DatePattern.NORM_DATETIME_FORMAT.format(DateUtil.offsetDay(DateUtil.parseDateTime(str).toJdkDate(), 1).toJdkDate());//2020-12-08 01:00:00
                    DateTime dateTime = DateUtil.parseDateTime(str);
                    String s = dateTime.toString(DatePattern.NORM_DATE_PATTERN);
                    if (today.equals(s)) {
                        flag = false;
                    }
                    continue;
                }
                if (null == fundBak) {
                    str = DatePattern.NORM_DATETIME_FORMAT.format(DateUtil.offsetDay(DateUtil.parseDateTime(str).toJdkDate(), 1));//2020-12-07 01:00:00
                    end = DatePattern.NORM_DATETIME_FORMAT.format(DateUtil.offsetDay(DateUtil.parseDateTime(str).toJdkDate(), 1).toJdkDate());//2020-12-08 01:00:00
                    DateTime dateTime = DateUtil.parseDateTime(str);
                    String s = dateTime.toString(DatePattern.NORM_DATE_PATTERN);
                    if (today.equals(s)) {
                        flag = false;
                    }
                    continue;
                }
                Map<String, String> stringStringMap = reconciliationImpl.fund(fundBak, runList, fundBak1);//对账
                str = DatePattern.NORM_DATETIME_FORMAT.format(DateUtil.offsetDay(DateUtil.parseDateTime(str).toJdkDate(), 1));//2020-12-07 01:00:00
                end = DatePattern.NORM_DATETIME_FORMAT.format(DateUtil.offsetDay(DateUtil.parseDateTime(str).toJdkDate(), 1).toJdkDate());//2020-12-08 01:00:00
                String err = stringStringMap.get(Reconciliation.ERROR_RECONCILIATION);
                String succ = stringStringMap.get(Reconciliation.SUCCESS_RECONCILIATION);
                if (StrUtil.isNotEmpty(succ)) {
                    logger.info("账户对账成功" + succ + "==========>成功");
                } else if (StrUtil.isNotEmpty(err)) {
                    insertFund(err, DateUtil.parse(str).toJdkDate(), fundBak.getUserId());
                    logger.info("【订单账户失败" + err + "】");
                }

                DateTime dateTime = DateUtil.parseDateTime(str);
                String s = dateTime.toString(DatePattern.NORM_DATE_PATTERN);
                if (today.equals(s)) {
                    flag = false;
                }
                page += 1;
            } while (flag);
        }

    }


    void insertFund(String deal, Date submitTime, String account) {
        DealpayRunOrderEntity run = new DealpayRunOrderEntity();
        run.setOrderId(IdUtil.objectId());
        run.setAccountW("对账");
        run.setAcountR("对账");
        run.setAmount(0.0);
        run.setAssociatedId(IdUtil.objectId());
        run.setAmountType("1");
        run.setGenerationIp("127.0.0.1");
        run.setDealDescribe(deal);
        run.setRunOrderType(3);
        run.setOrderAccount(account);
        run.setRunType("1");
        run.setSubmitTime(submitTime);
        dealpayRunOrderServiceImpl.insertDealpayRunOrder(run);
    }


    @GetMapping("/statistice")
    @ResponseBody
    public void statistice(String starTime) {
        reconciliationImpl.fundTask(starTime);
    }


}
