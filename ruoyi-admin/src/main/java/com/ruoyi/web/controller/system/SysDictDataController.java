package com.ruoyi.web.controller.system;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.HUOBI;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据字典信息
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
    private String prefix = "system/dict/data";

    @Autowired
    private ISysDictDataService dictDataService;

    @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dictData() {
        return prefix + "/data";
    }

    private Map cache = new ConcurrentHashMap();

    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysDictData dictData) {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        return util.exportExcel(list, "字典数据");
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add/{dictType}")
    public String add(@PathVariable("dictType") String dictType, ModelMap mmap) {
        mmap.put("dictType", dictType);
        return prefix + "/add";
    }

    @GetMapping("/addManage/{dictType}")
    public String addManage(@PathVariable("dictType") String dictType, ModelMap mmap) {
        mmap.put("dictType", dictType);
        return prefix + "/addManage";
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDictData dict) {
        dict.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改字典类型
     */
    @GetMapping("/edit/{dictCode}")
    public String edit(@PathVariable("dictCode") Long dictCode, ModelMap mmap) {
        mmap.put("dict", dictDataService.selectDictDataById(dictCode));
        return prefix + "/edit";
    }

    @GetMapping("/editManage/{dictCode}")
    public String editManage(@PathVariable("dictCode") Long dictCode, ModelMap mmap) {
        mmap.put("dict", dictDataService.selectDictDataById(dictCode));
        return prefix + "/editManage";
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDictData dict) {
        dict.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(dictDataService.updateDictData(dict));
    }

    private String MARK = "_";
    private String RATE_KEY = "usdtrate" + MARK;

    @PostMapping("/list")
    @RequiresPermissions("system:dict:list")
    @ResponseBody
    public TableDataInfo list(SysDictData dictData) {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        if (dictData.getDictType().equals("CNY_USDT")) {
            for (SysDictData data : list) {
                data.setDictValueTime(getHUOBIRateFee());
            }
        }
        return getDataTable(list);
    }

    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dict:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dictDataService.deleteDictDataByIds(ids));
    }

    public String getHUOBIRateFee() {
        Set<String> set = cache.keySet();
        for (String key : set) {
            String[] split = key.split(MARK);
            String s = split[1];
            if (DateUtil.isExpired(DateUtil.parse(s).toJdkDate(), DateField.SECOND, 5, new Date())) {
                cache.remove(key);
            }
            ;
        }
        Object o = this.cache.get(RATE_KEY + DateUtils.getTime());
        if (null == o) {
            String smallbull = "https://www.pexpay.com/bapi/c2c/v1/friendly/c2c/ad/search";
            String rateBull = getRate(smallbull,"buy");
            String smallSell = "https://www.pexpay.com/bapi/c2c/v1/friendly/c2c/ad/search";
            String rateSell = getRate(smallSell,"sell");
            BigDecimal bigDecimal = new BigDecimal(rateBull);
            BigDecimal bigDecimal1 = new BigDecimal(rateSell);
            if (bigDecimal.compareTo(bigDecimal1) == -1) {
                this.cache.put(RATE_KEY + DateUtils.getTime(), rateBull);
                return rateBull;
            } else {
                this.cache.put(RATE_KEY + DateUtils.getTime(), rateSell);
                return rateSell;
            }
        } else {
            return "" + o;
        }
    }

    @PostMapping("/listRateInHUOBI")
    @ResponseBody
    public TableDataInfo listRateInHUOBI() {
        /**
         * 获取火币网费率作为最低和最高的 费率交易标准
         */
        List<HUOBI> list = new ArrayList<HUOBI>();
        HUOBI smalls = new HUOBI();
        String smallSell = "https://www.pexpay.com/bapi/c2c/v1/friendly/c2c/ad/search";
        smalls.setId("3");
        smalls.setRateType("自选交易购买价格");
        smalls.setPrice(getRate(smallSell,"sell"));
        smalls.setCaeateTime(DateUtils.getTime());
        list.add(smalls);
        HUOBI smallb = new HUOBI();
        String smallbull = "https://www.pexpay.com/bapi/c2c/v1/friendly/c2c/ad/search";
        smallb.setId("4");
        smallb.setRateType("自选交易出售价格");
        smallb.setPrice(getRate(smallbull,"buy"));
        smallb.setCaeateTime(DateUtils.getTime());
        list.add(smallb);
        return getDataTable(list);
    }


    String getRate(String url, String type) {
        try {
            String params = null;
            if (type.equals("sell")) {
                params = "{\"page\":1,\"rows\":10,\"payTypes\":[],\"classifies\":[],\"asset\":\"USDT\",\"tradeType\":\"SELL\",\"fiat\":\"CNY\",\"publisherType\":null,\"filter\":{\"payTypes\":[]}}";
            } else if (type.equals("buy")) {
                params = "{\"page\":1,\"rows\":10,\"payTypes\":[],\"classifies\":[],\"asset\":\"USDT\",\"tradeType\":\"BUY\",\"fiat\":\"CNY\",\"publisherType\":null,\"filter\":{\"payTypes\":[]}}";
            }
            String sell = HttpUtil.post(url, params);
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(sell);
            String code = jsonObject.getString("code");
            if ("000000".equals(code) && type.contains("sell")) {
                String data = jsonObject.getString("data");
                JSONArray objects = JSONUtil.parseArray(data);
                String price = null;
                if (objects.size() >= 5) {
                    String o = objects.getStr(4);
                    com.alibaba.fastjson.JSONObject jsonObject1 = JSON.parseObject(o);
                    com.alibaba.fastjson.JSONObject adDetailResp = jsonObject1.getJSONObject("adDetailResp");
                    price = adDetailResp.getString("price");
                }
                return price;
            }

            Set<Object> setList = new HashSet<>();
            if ("000000".equals(code) && type.contains("buy")) {
                String data = jsonObject.getString("data");
                JSONArray objects = JSONUtil.parseArray(data);
                String price = null;
                if (objects.size() >= 1) {
                    String o = objects.getStr(0);
                    com.alibaba.fastjson.JSONObject jsonObject1 = JSON.parseObject(o);
                    JSONObject adDetailResp = jsonObject1.getJSONObject("adDetailResp");
                    price = adDetailResp.getString("price");
                }
                return price;
            }

        } catch (Exception e) {
            return "获取错误";
        }
        return "获取失败";
    }

}
