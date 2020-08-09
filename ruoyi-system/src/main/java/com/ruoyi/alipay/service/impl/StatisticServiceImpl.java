package com.ruoyi.alipay.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.domain.echarts.StackedAreaChart;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.alipay.service.StatisticService;
import com.ruoyi.common.core.domain.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StatisticServiceImpl implements StatisticService {
    protected final Logger log = LoggerFactory.getLogger(StatisticServiceImpl.class);
    private static final String TODAY_AGENT_PROFIT = "TODAY_AGENT_PROFIT";
    private static final String SUM_AGENT_PROFIT = "SUM_AGENT_PROFIT";
    private static final String WIT_MONEY = "WIT_MONEY";
    private static final String FORMAT = "FORMAT";
    @Autowired
    IAlipayUserFundEntityService alipayUserFundEntityServiceImpl;
    @Override
    public Map getStackedAreaChart(String merchantId, BaseEntity baseEntity) {
        log.info("【代理查询自己下线交易数据】");
        /**
         * 根据时间和账号查询备份数据
         * 对数据进行分类
         */
        List<StackedAreaChart> staList = new ArrayList<>();
        Map<String , StackedAreaChart> map = new ConcurrentHashMap<>();//各个子账户的交易情况
        Map<String , Double> sumMoneyMap = new ConcurrentHashMap<>();//总金额趋势
        Map<String , List<Double>> sumMoneyAgentProfitMap = new ConcurrentHashMap<>();//个人当日接单总利润
        Map<String , List<String>> witAgentProfitMap = new ConcurrentHashMap<>();//个人支出
        Map<String, List<String>> mapCorr = new ConcurrentHashMap<>();
        TreeSet<String> set = new TreeSet<>();
        Set userSet = new HashSet<>();
        List<AlipayUserFundEntity>  userList = alipayUserFundEntityServiceImpl.findUserBakBy( merchantId,baseEntity);
        userList.sort(( ord1, ord2) -> ord1.getCreateTime().compareTo(ord2.getCreateTime()));
        List<AlipayUserFundEntity>  myUserList = alipayUserFundEntityServiceImpl.findMyUserBak(merchantId,baseEntity);
        myUserList.sort(( ord1, ord2) -> ord1.getCreateTime().compareTo(ord2.getCreateTime()));
        for(AlipayUserFundEntity userfund :myUserList){
            String format = FORMAT;//DateUtil.format(DateUtil.offsetDay(userfund.getCreateTime(),-1), DatePattern.NORM_DATE_PATTERN);
            String todayKey = format + TODAY_AGENT_PROFIT;
            String sumKey = format + SUM_AGENT_PROFIT;
            String witKey = format + WIT_MONEY;
            if(CollUtil.isNotEmpty(sumMoneyAgentProfitMap.get(todayKey))){
              List<Double> doubles = sumMoneyAgentProfitMap.get(todayKey);
              doubles.add(userfund.getTodayAgentProfit());
              sumMoneyAgentProfitMap.put(todayKey,doubles);
                doubles = null;
          }else{
              List<Double> doubles = new ArrayList<>();
              doubles.add(userfund.getTodayAgentProfit());
              sumMoneyAgentProfitMap.put(todayKey,doubles);
                doubles = null;
          }
          if(CollUtil.isNotEmpty(sumMoneyAgentProfitMap.get(sumKey))){
              List<Double> doubles = sumMoneyAgentProfitMap.get(sumKey);
              doubles.add(userfund.getSumProfit());
              sumMoneyAgentProfitMap.put(sumKey,doubles);
              doubles = null;
          }else{
              List<Double> doubles = new ArrayList<>();
              doubles.add(userfund.getSumProfit());
              sumMoneyAgentProfitMap.put(sumKey,doubles);
              doubles = null;
          }
          if(CollUtil.isNotEmpty(witAgentProfitMap.get(witKey))){
              List<String> doubles = witAgentProfitMap.get(witKey);
              doubles.add("-");
              witAgentProfitMap.put(witKey,doubles);
              doubles = null;
          }else{
              List<String> doubles = new ArrayList<>();
              doubles.add("-");
              witAgentProfitMap.put(witKey,doubles);
              doubles = null;
          }





        }
            for(AlipayUserFundEntity userfund : userList){
                String format = DateUtil.format(userfund.getCreateTime(), DatePattern.NORM_DATE_PATTERN);
                set.add(format);
                if(ObjectUtil.isNull(sumMoneyMap.get(format))){
                    sumMoneyMap.put(format,userfund.getTodayDealAmount());
                }else{
                    Double aDouble = sumMoneyMap.get(format);
                    aDouble += userfund.getTodayDealAmount();
                    sumMoneyMap.put(format,aDouble);
                }
                if(ObjectUtil.isNull(map.get(userfund.getUserName()))){
                    StackedAreaChart chart = new StackedAreaChart();
                  //  chart.setAreaStyle(JSONUtil.createObj());
                    chart.setName(userfund.getUserName());
                    chart.setType("line");
                    List list = new ArrayList<>();
                    list.add(userfund.getTodayDealAmount());
                    chart.setData(list);
                //    chart.setStack("总量");
                    map.put(userfund.getUserName(),chart);
                    List m = new ArrayList() ;
                    m.add(userfund.getUserName()+MARK+DateUtil.format(userfund.getCreateTime(), DatePattern.NORM_DATE_PATTERN) +MARK+userfund.getTodayDealAmount());//姓名+时间+金额
                    mapCorr.put(userfund.getUserName(),m);
                    m = null;
                } else {
                    StackedAreaChart stackedAreaChart = map.get(userfund.getUserName());
                    List data = stackedAreaChart.getData();
                    data.add(userfund.getTodayDealAmount());
                    stackedAreaChart.setData(data);
                    map.put(userfund.getUserName(),stackedAreaChart);
                    List m = mapCorr.get(userfund.getUserName());
                    m.add(userfund.getUserName()+MARK+DateUtil.format(userfund.getCreateTime(), DatePattern.NORM_DATE_PATTERN) +MARK+userfund.getTodayDealAmount());//姓名+时间+金额
                    mapCorr.put(userfund.getUserName(),m);
                    m = null;
                }
            }
        Set<Map.Entry<String, StackedAreaChart>> entries = map.entrySet();
            for (Map.Entry<String, StackedAreaChart> m : entries ){
                staList.add(m.getValue());
                userSet.add(m.getKey());
            }
        return completion(map,mapCorr,set,userSet,sumMoneyAgentProfitMap,sumMoneyMap);
    }

    /**
     *
     * @param map               前端需要的map数据格式
     * @param mapCorr           账户数据不全记录
     * @param timeSet           时间排序的set
     * @param userSet           账户set
     * @param sumMoneyAgentProfitMap            个人每日代理分润map
     * @param sumMoneyMap                   会员月交易汇总
     * @return
     */
    Map   completion(Map<String, StackedAreaChart> map, Map<String, List<String>> mapCorr,
                     TreeSet<String> timeSet, Set userSet, Map<String, List<Double>> sumMoneyAgentProfitMap,
                     Map<String, Double> sumMoneyMap){
        Map result = new HashMap();
        Set<Map.Entry<String, StackedAreaChart>> entries = map.entrySet();
        List<StackedAreaChart> staList = new ArrayList<>();
        for (Map.Entry<String, StackedAreaChart> m : entries ){
            if( m.getValue().getData().size()  != timeSet.size()){//缺少数据时进行数据补全操作    //姓名+时间+金额
               // userSet.remove(m.getKey());
                /**
                 * 进行数据补全操作
                 */
                List data = m.getValue().getData();
                Object[] objects = timeSet.toArray();
                List<Double> list = new ArrayList<>();
                for(int i = 0 ; i < timeSet.size() ; i++ ){
                    String s = objects[i].toString();//拿到时间
                    String name = m.getKey();//拿到姓名
                    List<String> strings = mapCorr.get(name);
                    int a = 0;
                    Double amount = 0.0;
                    for(Object obj : data){
                        if(strings.contains(name+MARK+s+MARK+obj))
                            amount = Double.valueOf(obj.toString());
                    }
                    list.add(amount);
                }
                StackedAreaChart stackedAreaChart = new StackedAreaChart();
                stackedAreaChart.setData(list);
                stackedAreaChart.setName(m.getKey());
                stackedAreaChart.setType("line");
                staList.add(stackedAreaChart);
            }else{
                staList.add(m.getValue());
            }
        }


        TreeSet<String> set = new TreeSet();
        List<Double> sumMoneyList = new ArrayList<>();

        for(Object time :timeSet)
            set.add(DateUtil.format(DateUtil.offsetDay(DateUtil.parseDate(time.toString()).toJdkDate(),-1), DatePattern.NORM_DATE_PATTERN));
         for (String time :set){
           /* String todayKey = time + TODAY_AGENT_PROFIT;
            String sumKey = time + SUM_AGENT_PROFIT;
            List<Double> doubles = sumMoneyAgentProfitMap.get(todayKey);//当日代理金额利润
            List<Double> doubles1 = sumMoneyAgentProfitMap.get(sumKey);//累计代理金额利润*/
             DateTime dateTime = DateUtil.offsetDay(DateUtil.parseDate(time).toJdkDate(), 1);
             Double aDouble = sumMoneyMap.get(DateUtil.format(dateTime.toJdkDate(),DatePattern.NORM_DATE_PATTERN ));
             sumMoneyList.add(aDouble);
         }




        result.put(TODAY_AGENT_PROFIT,sumMoneyAgentProfitMap.get(FORMAT+TODAY_AGENT_PROFIT));
        result.put("sumMoneyList",sumMoneyList);
        result.put(WIT_MONEY,sumMoneyAgentProfitMap.get(FORMAT+WIT_MONEY));
        result.put(SUM_AGENT_PROFIT,sumMoneyAgentProfitMap.get(FORMAT+SUM_AGENT_PROFIT));
        result.put(DATE,set);
        result.put(CHART, staList);
        result.put(USERS,userSet);








        return result;
    }
}
