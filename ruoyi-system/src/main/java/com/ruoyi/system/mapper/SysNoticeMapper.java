package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.SysNotice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 公告 数据层
 *
 * @author ruoyi
 */
public interface SysNoticeMapper {
    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(SysNotice notice);

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(SysNotice notice);

    /**
     * 批量删除公告
     *
     * @param noticeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteNoticeByIds(String[] noticeIds);

    /**
     * 查询通知公告
     * @param roleId 角色ID
     * @return 结果
     */
    @Select("select notice_id, notice_title, notice_type, notice_content, status, create_by, create_time, update_by, update_time, remark " +
            "from sys_notice where find_in_set(#{roleId},remark)")
    List<SysNotice> selectNoticeListByRoleIdMapper(@Param("roleId") Long roleId);
}