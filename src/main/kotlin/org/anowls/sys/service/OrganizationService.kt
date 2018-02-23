package org.anowls.sys.service

import org.anowls.sys.common.PageQuery
import org.anowls.sys.common.SimplePage
import org.anowls.sys.common.message.SimpleMessage
import org.anowls.sys.domain.Organization
import org.anowls.sys.domain.view.OrganizationVO

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 组织结构-业务逻辑</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
interface OrganizationService {

    /**
     * 根据用户id获取默认机构下范围内的树状结构
     *
     * @return 默认机构下范围内的树状结构
     */
    val treeByCurrentUser: List<OrganizationVO>

    /**
     * 获取组织机构信息
     *
     * @param orgId 组织机构id
     * @return 组织机构信息
     */
    fun getById(orgId: String): OrganizationVO?

    /**
     * 获取机构列表
     *
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    fun query(pageQuery: PageQuery): SimplePage<OrganizationVO>

    /**
     * 根据用户id获取默认组织机构
     *
     * @param userId 用户id
     * @return 默认组织机构
     */
    fun getDefaultByUserId(userId: String): OrganizationVO?

    /**
     * 查询当前登录用户组织机构树
     * @param filter 查询条件
     * @return 组织机构树
     */
    fun getChildOrgTrees(filter: Map<String, String>): List<OrganizationVO>

    /**
     * 保存机构信息（如果id存在则更新，反之插入）
     *
     * @param orgInfo 机构信息
     * @return 操作结果
     */
    fun saveOrgInfo(orgInfo: Organization): SimpleMessage

    /**
     * 新增和修改时判断机构的代码和名称是否重复
     *
     * @param organization 机构ixnxi
     * @param isAdd        是否新增模式 true 是 false 不是
     * @return 提示消息
     */
    fun checkOrgInfo(organization: Organization, isAdd: Boolean): String

    /**
     * 修改机构信息状态（子机构中如有启动状态，则不能停用）
     *
     * @param id     主键id
     * @param status 停用、启用状态
     * @return 操作结果
     */
    fun updateOrgStatus(id: String, status: Int?): SimpleMessage

    /**
     * 删除机构信息
     *
     * @param id 主键id
     * @return 操作结果
     */
    fun deleteOrgInfo(id: String): SimpleMessage

    /**
     * 获取组织机构树
     *
     * @param filter 过滤条件：{
     * orgId: 组织机构ID
     *
     *
     * }
     * @return 组织机构树
     */
    fun getOrgTree(filter: MutableMap<String, String>): List<OrganizationVO>

    /**
     * 根据某组织机构节点的id 向上找父级节点的集合
     *
     * @param id 当前机构id
     * @return 父级节点集合
     */
    fun getParentOrgsById(id: String): List<Organization>

    /**
     * 根据用户id和对应的机构级别获取对应的组织机构信息
     *
     * @param userId    当前机构id
     * @param orgGrades 机构级别，逗号隔开
     * @return 组织机构列表
     */
    fun getOrgList(userId: String, orgGrades: String): List<Organization>
}
