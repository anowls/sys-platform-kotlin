package org.anowls.sys.service

import org.anowls.sys.common.message.SimpleMessage
import org.anowls.sys.domain.AccountOrg

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 用户机构关系-业务逻辑</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
interface AccountOrgService {

    /**
     * 删除组织机构人员
     *
     * @param userOrgs 用户与机构关系集合
     * @return 操作结果
     */
    fun updateOrgUsers(userOrgs: List<AccountOrg>): SimpleMessage

    /**
     * 插入用户机构关系,除了插入与当前机构的对应关系 ，还要递归把上级单位的所有机构抓出来，写进一张上级单位与用户对应关系表
     *
     * @param userOrgs 用户与权限对应关系表
     * @return 操作结果
     */
    fun insertUserOrg(userOrgs: List<AccountOrg>): SimpleMessage

    /**
     * 解除非默认机构与用户的对应关系
     *
     * @param userId 用户id
     * @param orgId  机构id
     * @return 操作结果
     */
    fun deleteUserOrg(userId: String, orgId: String): SimpleMessage

    /**
     * 根据用户id获取所有组织机构名称的map 格式为： <userId></userId>,orgName...orgName>
     *
     * @param userIds 用户id
     * @return 用户与机构字典
     */
    fun getAccountOrgMap(userIds: List<String>): Map<String, String>

    /**
     * 重新刷新机构上下级全集表
     *
     * @param userOrgs 用户与机构列表
     * @param userIds  用户id集合
     */
    fun refreshTree(userOrgs: List<AccountOrg>, userIds: List<String>)
}