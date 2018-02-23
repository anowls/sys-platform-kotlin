package org.anowls.sys.service

import org.anowls.sys.common.PageQuery
import org.anowls.sys.common.SimplePage
import org.anowls.sys.common.message.SimpleMessage
import org.anowls.sys.domain.AccountRole

interface AccountRoleService {

    /**
     * 新增角色用户
     *
     * @param userRoles 角色用户集合
     * @return 新增结果
     */
    fun addRoleUser(accountRoles: List<AccountRole>): SimpleMessage

    /**
     * 删除角色人员
     *
     * @param userRoleIds 用户角色ID集合
     * @return 删除结果
     */
    fun deleteRoleUser(userRoleIds: List<String>): SimpleMessage

    /**
     * 查询角色人员
     *
     * @param pageQuery 过滤条件、分页条件：{
     * filter: 过滤条件{
     * keywords：人员名称（可选）
     * status：人员状态（可选）
     * roleId：角色ID
     *
     *
     * }
     * order: 排序规则{
     * userName：默认 ‘asc’
     *
     *
     * }
     *
     *
     * }
     * @return 角色人员列表
     */
    fun listRoleUsers(pageQuery: PageQuery): SimplePage<AccountRole>

    /**
     * 根据用户id获取所有角色名称的map 格式为： <userId></userId>,orgName...orgName>
     *
     * @param userIds 用户id
     * @return 用户与角色名称字典
     */
    fun getAccountRoleMap(userIds: List<String>): Map<String, String>
}
