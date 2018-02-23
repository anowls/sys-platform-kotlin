package org.anowls.sys.service

import org.anowls.sys.common.PageQuery
import org.anowls.sys.common.SimplePage
import org.anowls.sys.common.message.SimpleMessage
import org.anowls.sys.domain.Account
import org.anowls.sys.domain.SysEnumItem
import org.anowls.sys.domain.ext.AccountInfo
import org.anowls.sys.domain.ext.PasswordInfo
import org.anowls.sys.domain.view.AccountVO
import org.springframework.web.bind.annotation.PathVariable

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 用户信息-业务逻辑</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
interface AccountService {

    /**
     * 修改密码
     *
     * @param passwordInfo 密码信息
     * @return 操作结果
     */
    fun modifyPassword(passwordInfo: PasswordInfo): SimpleMessage

    /**
     * 修改用户状态
     *
     * @param id     用户ID
     * @param status 状态值（1 启用 0 停用）
     * @return 操作结果
     */
    fun updateStatus(id: String, status: Boolean): SimpleMessage

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 操作结果
     */
    fun delete(id: String): SimpleMessage

    /**
     * 保存账号信息（新增或修改）
     *
     * @param accountInfo  账号信息
     * @param isForeign    是否外部接口调用
     * @param isDefaultOrg 是否设置当前用户的机构为默认
     * @return 账号信息
     * @throws Exception 异常信息
     */
    @Throws(Exception::class)
    fun saveAccountInfo(accountInfo: AccountInfo, isForeign: Boolean, isDefaultOrg: Boolean): AccountInfo

    /**
     * 修改用户基本信息
     *
     * @param account 用户基本信息
     * @return 操作结果
     */
    fun updateAccount(account: Account): SimpleMessage

    /**
     * 分页查询账户
     *
     * @param pageQuery 分页对象
     * @return 账户列表
     */
    fun query(pageQuery: PageQuery): SimplePage<Account>

    /**
     * 重置用户信息
     *
     * @param accountId 用户ID
     * @return 操作结果
     */
    fun resetAccount(accountId: String): SimpleMessage

    /**
     * 检查用户信息是否唯一
     *
     * @param accountInfo 用户信息
     * @return 检查结果
     */
    fun checkAccountInfo(accountInfo: AccountInfo): SimpleMessage

    /**
     * 校验用户名和密码是否匹配，如果匹配则返回对应用户信息
     *
     * @param loginName 用户名
     * @param password 密码
     * @return 校验结果
     */
    fun valid(@PathVariable loginName: String, password: String): SimpleMessage


    /**
     * 根据登录名查询帐号相关信息
     *
     * @param roleTypeId 角色分类ID
     * @param accountId  用户ID
     * @param appLevel   平台类型（1：运维平台，2：业务系统）
     * @return 账号信息（应用，菜单，权限）
     */
    fun queryAccountInfo(roleTypeId: String, accountId: String, appLevel: Int): AccountVO

    /**
     * 根据用户ID以及登录范围查询用户的角色
     *
     * @param scope  登录范围（1 运维 2业务 3 所有）
     * @param userId 用户ID
     * @return 角色列表
     */
    fun queryRoleType(accountId: String, scope: Int?): List<SysEnumItem>
}