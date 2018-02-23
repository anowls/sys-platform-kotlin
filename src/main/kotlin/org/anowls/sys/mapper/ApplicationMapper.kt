package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.Application
import org.anowls.sys.domain.view.ApplicationVO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface ApplicationMapper : BaseMapper<Application> {

    /**
     * 根据应用组id 统计应用个数
     *
     * @param groupId 应用组id
     * @return 统计应用个数
     */
    @Select("SELECT count(0) from system_application t where t.app_group_id = #{groupId}")
    fun countByAppGroupId(groupId: String): Int

    /**
     * 根据条件获取应用信息列表
     *
     * @param filter 筛选条件
     * @return 结果集
     */
    fun findAll(filter: Map<String, Any>): List<ApplicationVO>

    /**
     * 根据用户ID、角色ID集合、是否超级管理员查询有权限的应用列表
     *
     * @param isAdmin    是否超级管理员
     * @param roleIds    角色ID集合
     * @param userId     用户ID
     * @param appLevel   应用类型（1：运维平台，2：业务系统）
     * @return 应用列表
     */
    fun getByRoleIdIn(@Param("isAdmin") isAdmin: Boolean,
                      @Param("userId") userId: String,
                      @Param("roleIds") roleIds: List<String>,
                      @Param("appLevel") appLevel: Int): List<ApplicationVO>
}
