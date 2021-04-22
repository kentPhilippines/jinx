package com.ruoyi.framework.shiro.web.filter.online;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.enums.OnlineStatus;
import com.ruoyi.framework.shiro.session.OnlineSession;
import com.ruoyi.framework.shiro.session.OnlineSessionDAO;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * 自定义访问控制
 *
 * @author ruoyi
 */
public class OnlineSessionFilter extends AccessControlFilter {
    /**
     * 强制退出后重定向的地址
     */
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;
    @Value("${shiro.session.filter}")
    private String filter;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;
    @Autowired
    private SysUserServiceImpl sysUserImpl;

    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null || subject.getSession() == null) {
            return true;
        }
        Session session = onlineSessionDAO.readSession(subject.getSession().getId());
        if (session != null && session instanceof OnlineSession) {
            OnlineSession onlineSession = (OnlineSession) session;
            request.setAttribute(ShiroConstants.ONLINE_SESSION, onlineSession);
            // 把user对象设置进去
            boolean isGuest = onlineSession.getUserId() == null || onlineSession.getUserId() == 0L;
            if (isGuest == true) {
                SysUser user = ShiroUtils.getSysUser();
                if (user != null) {
                    onlineSession.setUserId(user.getUserId());
                    onlineSession.setLoginName(user.getLoginName());
                    onlineSession.setAvatar(user.getAvatar());
                    onlineSession.setDeptName(user.getDept().getDeptName());
                    onlineSession.markAttributeChanged();
                    HttpServletRequest req = (HttpServletRequest) request;
                    StringBuffer url = req.getRequestURL();
                /*  String host = url.delete(url.length() - req.getRequestURI().length(), url.length()).toString();
                    SysUser sysUser = sysUserImpl.selectUserByLoginName(user.getLoginName());
                    boolean b = loginIp(sysUser, ShiroUtils.getIp());//ip不在访问进程内
                   if(!b){
                        return  b;
                    }
                    if (filter.equals("1")) {
                        boolean contains = host.contains("bestpays789.net");
                        Long userId = user.getUserId();
                        if (!contains && userId < 20) {
                            return false;
                        }
                        if (userId > 20 && contains) {//外部人员访问我们内部地址
                            return false;
                        }
                    }*/
                }
            }
            if (filter.equals("1")) {
                /*HttpServletRequest req = (HttpServletRequest) request;
                StringBuffer url = req.getRequestURL();
                String host = url.delete(url.length() - req.getRequestURI().length(), url.length()).toString();
                SysUser user = ShiroUtils.getSysUser();
                if (null == user) {
                    return true;
                }
                SysUser sysUser = sysUserImpl.selectUserByLoginName(user.getLoginName());
                boolean b = loginIp(sysUser, ShiroUtils.getIp());//ip不在访问进程内
                if(!b){
                    return  b;
                }
                boolean contains = host.contains("bestpays789.net");//内部管理访问
                Long userId = user.getUserId();
                if (!contains && userId < 20) {
                    return false;
                }
                if (userId > 20 && contains) {//外部人员访问我们内部地址
                    return false;
                }
*/
            }

            if (onlineSession.getStatus() == OnlineStatus.off_line) {
                return false;
            }
        }
        return true;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject != null) {
            subject.logout();
        }
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }

    // 跳转到登录页
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.issueRedirect(request, response, loginUrl);
    }


    boolean loginIp(SysUser user, String ip) {
        String ips = user.getRemark();
        String[] split = ips.split(",");
        boolean contains = Arrays.asList(split).contains(StrUtil.trim(ip));
        return contains;
    }
}
