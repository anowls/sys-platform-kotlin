package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.Module
import org.anowls.sys.domain.view.ModuleVO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface ModuleMapper : BaseMapper<Module> {

    /**
     * 根据角色ID集合、是否超级管理员查询有权限的模块列表
     *
     * @param roleIds    角色ID集合
     * @param isAdmin    是否超级管理员
     * @param appLevel   平台类型（1：运维平台，2：业务系统）
     * @return 模块列表
     */
    fun getByRoleIdIn(@Param("isAdmin") isAdmin: Boolean,
                      @Param("roleIds") roleIds: List<String>,
                      @Param("appLevel") appLevel: Int?): List<ModuleVO>

    /**
     * 根据应用ID删除功能模块
     *
     * @param appId 应用ID
     */
    fun deleteByAppId(@Param("appId") appId: String)

    /**
     * 根据应用ID获取功能项
     *
     * @param appId 应用ID
     * @return 功能列表
     */
    fun getByAppId(@Param("appId") appId: String): List<ModuleVO>

    /**
     * 批量写入功能模块
     *
     * @param modules 模块列表
     */
    fun insertModules(modules: List<Module>)
}
