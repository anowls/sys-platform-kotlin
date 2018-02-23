package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.Role
import org.anowls.sys.domain.SysEnumItem
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface RoleMapper : BaseMapper<Role> {

    /**
     * 获取默认（系统）角色列表
     *
     * @return 角色列表
     */
    val systemRoles: List<Role>

    /**
     * 查询用户的角色列表
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    fun getByUserId(userId: String): List<Role>

    /**
     * 根据用户ID查询用户的角色
     *
     * @param scope  角色登陆范围（1 运维 2业务 3 所有）
     * @param userId 用户ID
     * @return 角色列表
     */
    fun findRoleType(@Param("userId") userId: String, @Param("scope") scope: Int?): List<SysEnumItem>

    /**
     * 角色列表
     *
     * @param map 查询参数 ：{
     * isAdmin：是否管理员
     * keywords：查询关键词
     * status：状态是否有值
     *
     *
     * }
     * @return 角色列表
     */
    fun query(map: Map<*, *>): List<Role>

    /**
     * 根据CODE查询角色信息
     *
     * @param code 角色CODE
     * @return 角色信息
     */
    fun selectByCode(code: String): Role

    /**
     * 根据角色ID集合删除角色
     *
     * @param ids 角色ID集合
     * @return 成功删除条数
     */
    fun deleteByIdIn(ids: List<String>): Int

//    /**
//     * 是否存在默认角色-数据校验
//     *
//     * @param roleId     角色ID
//     * @param roleTypeId 角色分类ID
//     * @return 默认角色列表
//     */
//    fun checkOrgRole(@Param(value = "roleId") roleId: String, @Param(value = "roleTypeId") roleTypeId: String): List<RoleCheck>

    /**
     * 检查角色下是否配置了人员-数据校验
     *
     * @param roleId 角色ID
     * @return 配置的人员数
     */
    fun getRoleUserCount(roleId: String): Int?

    /**
     * 检查角色名称、代码是否重复
     *
     * @param id       角色ID
     * @param roleName 角色名称
     * @param roleCode 角色代码
     * @return 存在的角色数
     */
    fun checkNameOrCode(@Param(value = "id") id: String,
                        @Param(value = "roleName") roleName: String,
                        @Param(value = "roleCode") roleCode: String): Long?

}
