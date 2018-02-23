package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.Feature
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface FeatureMapper : BaseMapper<Feature> {

    /**
     * 根据角色ID集合、是否超级管理员查询有权限的功能项列表
     *
     * @param roleIds    角色ID集合
     * @param stationIds 岗位ID集合
     * @param isAdmin    是否超级管理员
     * @param appLevel   平台类型（1：运维平台，2：业务系统）
     * @return 功能项列表
     */
    fun getByRoleIdIn(@Param("isAdmin") isAdmin: Boolean, @Param("roleIds") roleIds: List<String>, @Param("stationIds") stationIds: List<String>, @Param("appLevel") appLevel: Int?): List<Feature>

    /**
     * 根据应用ID删除功能模块
     *
     * @param appId 应用ID
     */
    fun deleteByAppId(@Param("appId") appId: String)

    /**
     * 根据应用ID查询功能项
     *
     * @param appId 应用ID
     * @return 功能列表
     */
    fun getByAppId(@Param("appId") appId: String): List<Feature>

    /**
     * 批量写入功能项
     *
     * @param features 功能项列表
     */
    fun insertFeatures(features: List<Feature>)

}
