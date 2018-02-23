package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.AccountRole
import org.anowls.sys.domain.view.AccountRoleVO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface AccountRoleMapper : BaseMapper<AccountRole> {

    /**
     * 根据角色id集合查询用户角色列表
     *
     * @param roleIds 角色id集合
     * @return 用户角色列表
     */
    fun findByRoleId(roleIds: List<String>): List<AccountRoleVO>

    /**
     *  根据账户id查询角色
     *
     * @param accountId 账户id
     * @return 角色列表
     */
    fun findByAccountId(accountId: String): List<AccountRoleVO>

    /**
     * 新增用户角色关系
     *
     * @param AccountRoles 账户角色集合
     */
    fun insertAccountRoles(AccountRoles: List<AccountRole>)

    /**
     * 根据账户id和角色id查询用户角色关系-数据重复验证
     *
     * @param AccountRoles 账户角色集合
     * @return 用户角色信息列表
     */
    fun validAccountRole(AccountRoles: List<AccountRole>): List<AccountRole>

    /**
     * 根据用户ID集合用户角色列表
     *
     * @param userIds 用户ID集合
     * @return 用户角色列表
     */
    fun getAccountRoleMap(userIds: List<String>): List<AccountRole>

    /**
     * 查询角色人员
     *
     * @param filter: 筛选条件：{
     * keywords：用户名称关键字
     * status：用户状态
     *
     * }
     * @return 用户与角色列表
     */
    fun findAll(filter: Map<String, Any>): List<AccountRoleVO>

    /**
     * 根据id删除角色人员
     *
     * @param id 主键id
     */
    fun deleteAccountRole(id: String)

    /**
     * 根据角色id删除角色人员
     *
     * @param roleId 角色id
     */
    fun deleteByRoleIdIn(roleId: String)

    /**
     * 根据用户删除角色关系
     *
     * @param accountId      用户ID
     */
    fun deleteByAccountId(accountId: String)
}
