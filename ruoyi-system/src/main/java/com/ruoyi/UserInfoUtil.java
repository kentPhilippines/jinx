package com.ruoyi;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.ruoyi.alipay.domain.AdminApiVo;
import com.ruoyi.alipay.domain.UserInfo;
import com.ruoyi.alipay.domain.ZullResult;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class UserInfoUtil extends BaseUtil {
    public final static String ADD_USER = "/userInfo/add";
    public final static String LIST = "/userInfo/list";
    public final static String AMOUNT = "/userInfo/amount";



    public static List<UserInfo> selectUserInfoList(UserInfo userInfo) {
        AdminApiVo.UserInfoRequestVo vo = new AdminApiVo.UserInfoRequestVo();
        vo.setName(userInfo.getName());
        vo.setUserStatus(userInfo.getUserStatus());
        vo.setCurrency(userInfo.getCurrency());
        vo.setAgent(userInfo.getAgent());
        String s = HttpUtils.sendPostJson(ZUUL + LIST, JSONUtil.parse(vo).toString());
        ZullResult bean = JSONUtil.toBean(s, ZullResult.class);
        Object data = bean.getData();
        JSONArray objects = JSONUtil.parseArray(data);
        List<UserInfo> list = JSONUtil.toList(objects, UserInfo.class);
        return list;
    }




    public static int insertUserInfo(UserInfo userInfo) {
        AdminApiVo.UserInfoRequestVo vo = new AdminApiVo.UserInfoRequestVo();
        vo.setName(userInfo.getName());
        vo.setCurrency(userInfo.getCurrency());
        vo.setAgent(userInfo.getAgent());
        vo.setUserStatus(userInfo.getUserStatus());
        String s = HttpUtils.sendPostJson(ZUUL + ADD_USER, JSONUtil.parse(vo).toString());
        log.info(s);
        //{"code":0,"msg":"success","data":{"userId":"testaa","balance":"0.0000","agent":null,"currency":"CNY","userStatus":null}}
        ZullResult bean = JSONUtil.toBean(s, ZullResult.class);
        log.info(bean.toString());
        if (bean.getCode().equals(0)) {
            return 1;
        }
        return 0;
    }

    public static int add(String userId) {
        UserInfo info = new UserInfo();
        info.setName(userId);
        return insertUserInfo(info);
    }


    public static int amount(AdminApiVo.UserInfoAmount amount) {
        String s = HttpUtils.sendPostJson(ZUUL + AMOUNT, JSONUtil.parse(amount).toString());
        log.info(s);
        ZullResult bean = JSONUtil.toBean(s, ZullResult.class);
        log.info(bean.toString());
        if (bean.getCode().equals(0)) {
            return 1;
        }
        throw new BusinessException("加减款错误");
    }

    public static UserInfo selectUserInfoByName(String name) {
        UserInfo info = new UserInfo();
        info.setName(name);
        List<UserInfo> userInfos = selectUserInfoList(info);
        return CollUtil.getFirst(userInfos);
    }

    public static void main(String[] args) {
        UserInfo userInfo = selectUserInfoByName("1111");
        if(ObjectUtil.isNull(userInfo)){
            System.out.println("暂无商户");
        }
    }
}
