package org.anowls.sys.service

import org.anowls.sys.common.PageQuery
import org.anowls.sys.common.SimplePage
import org.anowls.sys.common.message.SimpleMessage
import org.anowls.sys.domain.Role
import org.anowls.sys.domain.RoleOrg

interface RoleService {

    /**
     * 获取系统角色
     *
     * @return 操作结果
     */
    val systemRoles: SimpleMessage

    /**
     * 获取角色分类列表
     *
     * @return 角色分类列表
     */
    val allCategories: SimpleMessage

    /**
     * 获取角色分类列表
     *
     * @return 角色分类列表
     */
    val allRoleType: SimpleMessage

    /**
     * 保存角色信息
     *
     * @param roleInfo 角色信息
     * @return 操作结果
     */
    fun update(roleInfo: Role): SimpleMessage

    /**
     * 保存角色信息
     *
     * @param roleInfo 角色信息
     * @return 操作结果
     */
    fun insert(roleInfo: Role): SimpleMessage

    /**
     * 获取角色信息
     *
     * @param id 角色id
     * @return 角色信息
     */
    fun getRoleInfo(id: String): Role

    /**
     * 修改角色状态
     *
     * @param id     角色ID
     * @param status 状态（1:启用，0:停用）
     * @return 操作结果
     */
    fun updateRoleStatus(id: String, status: Int?): SimpleMessage

    /**
     * 删除角色信息
     *
     * @param id 根据id删除角色
     * @return 操作结果
     */
    fun deleteRoleInfo(id: String): SimpleMessage

    /**
     * 根据查询条件、分页条件查询角色列表
     *
     * @param pageQuery 查询条件、分页条件：{
     * filter： 查询条件 {
     * keywords：角色名称
     * status：角色状态
     *
     *
     * }
     * order: 排序规则 {
     * roleCode: 默认为‘asc’
     *
     *
     * }
     *
     *
     * }
     * @return 角色列表
     */
    fun query(pageQuery: PageQuery): SimplePage<Role>

    /**
     * 根据角色ID查询机构列表
     *
     * @param roleId 角色ID
     * @return 机构列表
     */
    fun queryOrgByRoleId(roleId: String): SimpleMessage

    /**
     * 保存角色与机构对应关系
     *
     * @param orgList 角色与机构列表
     * @param roleId  角色id
     * @return 操作结果
     */
    fun saveRoleOrgs(orgList: List<RoleOrg>, roleId: String): SimpleMessage

    /**
     * 检查角色
     *
     * @param roleId     角色id
     * @param baseRoleId 基础角色id
     * @return 操作结果
     */
    fun checkDefaultRole(roleId: String, baseRoleId: String): SimpleMessage

}
