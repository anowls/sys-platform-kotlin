package org.anowls.sys.service.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.anowls.sys.common.CommonUtils
import org.anowls.sys.common.PageQuery
import org.anowls.sys.common.SimplePage
import org.anowls.sys.common.message.SimpleMessage
import org.anowls.sys.domain.Organization
import org.anowls.sys.domain.view.OrganizationVO
import org.anowls.sys.mapper.AccountMapper
import org.anowls.sys.mapper.OrganizationMapper
import org.anowls.sys.service.OrganizationService
import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.validation.Valid

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 组织结构-业务逻辑实现</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@Service
open class OrganizationServiceImpl(@Autowired private val organizationMapper: OrganizationMapper,
                                   @Autowired private val accountMapper: AccountMapper) : OrganizationService {

    /**
     * 查询当前登录用户组织机构树
     *
     * @return 组织机构树
     */
    override val treeByCurrentUser: List<OrganizationVO>
        get() {
            val filter = mapOf(Pair("userId", ""), Pair("isAdmin", ""))
            val orgTree = organizationMapper.getOrgs(filter)
            return orgTree.filter { it.orgParentId == null }.onEach { CommonUtils.convertOrg(orgTree, it) }
        }

    override fun query(pageQuery: PageQuery): SimplePage<OrganizationVO> {
        val filter = pageQuery.convertFilterToMap()
        filter + Pair("userId", "") // todo  UserUtil.getUser().getId()
        filter + Pair("isAdmin", "") // todo  UserUtil.getUser().getUserType()
        if (Objects.nonNull(filter["orgId"])) {
            filter + Pair("orgCodePath", organizationMapper.getById(filter["orgId"].toString())?.orgCodePath)
        }
        PageHelper.startPage<OrganizationVO>(pageQuery.page, pageQuery.size, pageQuery.convertSort())
        val list = organizationMapper.query(filter)
        val pageInfo = PageInfo(list)

        return SimplePage.convert(pageInfo)
    }

    /**
     * 根据主键获取组织机构信息
     *
     * @param orgId 组织机构id
     * @return 组织机构对象
     */
    override fun getById(orgId: String): OrganizationVO? {
        val organization = organizationMapper.getById(orgId)
        val parentOrg = organizationMapper.getById(organization?.orgParentId!!)
        organization.orgParentName = parentOrg?.orgName
        return organization
    }

    /**
     * 根据用户id获取默认组织结构信息
     *
     * @param userId 用户id
     * @return 默认组织机构信息
     */
    override fun getDefaultByUserId(userId: String): OrganizationVO? {
        return organizationMapper.getById(getDefaultOrgId(userId)!!)
    }


    /**
     * 查询用户默认组织机构ID
     *
     * @param userId 用户ID
     * @return 组织机构ID
     */
    private fun getDefaultOrgId(userId: String): String? {
        val user = accountMapper.selectByPrimaryKey(userId)
        return user?.orgId
    }

//    /**
//     * 查询当前登录用户组织机构树
//     *
//     * @return 组织机构树
//     */
//    override fun getChildOrgTrees(filter: MutableMap<String, String>): List<OrganizationExt> {
//        filter["userId"] = UserUtil.getUser().getId()
//        filter["isAdmin"] = UserUtil.getUser().getUserType()
//        val orgTree = organizationMapper!!.getChildOrgTrees(filter)
//        if (CollectionUtils.isEmpty(orgTree)) {
//            return ArrayList<E>()
//        }
//        val returnOrgTree: List<OrganizationExt>
//        if (filter.containsKey("orgId")) {
//            returnOrgTree = orgTree.stream().filter({ o -> StringUtils.equals(o.getOrgParentId(), filter["orgId"]) }).collect(Collectors.toList<Any>())
//            return setOrgTree(returnOrgTree, orgTree)
//        }
//        if (UserUtil.getUser().getUserType().equals("1")) {
//            returnOrgTree = orgTree.stream().filter({ o -> StringUtils.isEmpty(o.getOrgParentId()) }).collect(Collectors.toList<Any>())
//            return setOrgTree(returnOrgTree, orgTree)
//        }
//        //根据用户id查询出 用户与最大权限机构的数组
//        val userOrgs = userOrgMapper!!.getByUserIds(Lists.newArrayList(UserUtil.getUser().getId()))
//        if (CollectionUtils.isEmpty(userOrgs)) {
//            return ArrayList<E>()
//        }
//        val parentOrgIds = userOrgs.stream().map(Function<UserOrg, R> { UserOrg.getOrgId() }).collect(Collectors.toList<Any>())
//        returnOrgTree = orgTree.stream().filter({ o -> parentOrgIds.contains(o.getId()) }).collect(Collectors.toList<Any>())
//        return setOrgTree(returnOrgTree, orgTree)
//    }

    /**
     * 封装树形结构-组织机构
     *
     * @param returnOrgTree 需要返回的组织机构
     * @param orgTree       查询出来的组织机构数据源
     * @return 封装好的数据
     */
    private fun setOrgTree(returnOrgTree: List<OrganizationVO>, orgTree: List<OrganizationVO>): List<OrganizationVO> {
        return returnOrgTree.onEach { CommonUtils.convertOrg(orgTree, it) }
        return returnOrgTree
    }

    /**
     * 新增和修改时判断机构的代码和名称是否重复
     *
     * @param organization 机构ixnxi
     * @param isAdd        是否新增模式 true 是 false 不是
     * @return 提示消息
     */
    override fun checkOrgInfo(organization: Organization, isAdd: Boolean): String {
        val map = HashMap<String, String?>(3)
        map["parentId"] = organization.orgParentId
        if (!isAdd) {
            map["id"] = organization.id
        }
        var number: Long
        if (StringUtils.isNotEmpty(organization.orgShortCode)) {
            map["orgShortCode"] = organization.orgShortCode
            number = organizationMapper.countByCondition(map)
            if (number > 0) {
                return "保存失败，机构代码【" + organization.orgShortCode + "】已存在，不允许重复添加！"
            }
            map.remove("orgShortCode")
        }
        map["orgName"] = organization.orgName
        number = organizationMapper.countByCondition(map)
        return if (number > 0) {
            "保存失败，机构名称【" + organization.orgName + "】已存在，不允许重复添加！"
        } else ""
    }

    /**
     * 根据机构id 重刷机构树
     *
     * @param orgId 机构id
     */
    private fun refreshOrgTree(orgId: String) {
//        val parentUserOrgs = userOrgMapper!!.getParentUserOrgs(orgId)
//        if (CollectionUtils.isNotEmpty(parentUserOrgs)) {
//            val userIds = parentUserOrgs.stream().map(Function<UserOrg, R> { UserOrg.getUserId() }).collect(Collectors.toList<Any>())
//            userOrgService!!.refreshTree(parentUserOrgs, userIds)
//        }
    }

    /**
     * 保存机构信息（如果id存在则更新，反之插入）
     *
     * @param orgInfo 机构信息
     * @return 操作结果
     */
    @Transactional(rollbackFor = arrayOf(Exception::class))
    override fun saveOrgInfo(@Valid orgInfo: Organization): SimpleMessage {
        if (organizationMapper.getById(orgInfo.id) == null) {
            val parentId = orgInfo.orgParentId!!
            val orgCode = CommonUtils.numberFormat(getNextCode(parentId), "000")
            var orgCodePath = orgCode
            var orgNamePath = orgInfo.orgName

            var parentOrg: Organization? = null
            if (StringUtils.isNotEmpty(parentId)) {
                parentOrg = getById(parentId)
            }
            if (parentOrg != null) {
                orgCodePath = parentOrg.orgCodePath + "-" + orgCode
                orgNamePath = parentOrg.orgNamePath + "-" + orgInfo.orgName
            }
            orgInfo.orgParentId = if (StringUtils.isNotEmpty(parentId)) parentId else null
            orgInfo.id = CommonUtils.id
            orgInfo.orgCode = orgCode
            orgInfo.orgCodePath = orgCodePath
            orgInfo.orgLevel = orgCodePath.split("-".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray().size
            orgInfo.orgNamePath = orgNamePath
            orgInfo.status = 1
            orgInfo.creatorId = ""// todo UserUtil.getUser().getId()
            orgInfo.createTime = CommonUtils.datetime
            organizationMapper.insertSelective(orgInfo)
        } else {
            orgInfo.modifierId = "" // todo UserUtil.getUser().getId()
            orgInfo.modifyTime = CommonUtils.datetime
            organizationMapper.updateByPrimaryKeySelective(orgInfo)
        }
        refreshOrgTree(orgInfo.id)
        return SimpleMessage.info(orgInfo)
    }

    /**
     * 修改机构信息状态（子机构中如有启动状态，则不能停用）
     *
     * @param id     主键id
     * @param status 启用状态
     * @return 操作结果
     */
    @Transactional(rollbackFor = arrayOf(Exception::class))
    override fun updateOrgStatus(id: String, status: Int?): SimpleMessage {
        val organization = getById(id)
        if (organization != null) {
            var childHasEnabled = false
            if (status == 0) {
                val childOrgs = organizationMapper.getChildOrgs(organization.orgCodePath!!)
                for (org in childOrgs) {
                    if (org.status == 1) {
                        childHasEnabled = true
                        break
                    }
                }
            }
            if (childHasEnabled && status == 0) {
                return SimpleMessage.warning("有启用状态的子机构，无法停用！")
            } else {
                organization.status = status
                saveOrgInfo(organization)

                return SimpleMessage.info(if (status == 1) "启用成功！" else "停用成功！")
            }
        }
        return SimpleMessage.warning("此机构不存在！")
    }

    /**
     * 删除机构信息
     *
     * @param id 机构id
     * @return 操作结果
     */
    @Transactional(rollbackFor = [Exception::class])
    override fun deleteOrgInfo(id: String): SimpleMessage {
        val organization = getById(id)
        if (organization != null) {
            val childOrgs = organizationMapper.getChildOrgs(organization.orgCodePath!!)
            if (CollectionUtils.isNotEmpty(childOrgs)) {
                return SimpleMessage.warning("当前记录存在子数据，不允许删除！")
            }
            organization.modifierId = "" //todo UserUtil.getUser().getId()
            organization.modifyTime = CommonUtils.datetime
            organization.deleted = true
            organizationMapper.updateByPrimaryKeySelective(organization)
            return SimpleMessage.info("删除成功！")

        }
        return SimpleMessage.warning("操作失败，组织机构不存在！")
    }

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
    override fun getOrgTree(filter: MutableMap<String, String>): List<OrganizationVO> {
        filter["userId"] = "" // todo UserUtil.getUser().getId()
        filter["isAdmin"] = "" // todo UserUtil.getUser().getUserType()
        val organizationVos = organizationMapper.getOrgs(filter)
        val returnOrgs: List<OrganizationVO>
        if (filter.size > 1) {
            returnOrgs = organizationVos.filter { StringUtils.equals(it.orgParentId, filter["orgId"]) }
        } else {
            returnOrgs = organizationVos.filter { StringUtils.equals(it.orgParentId, null) }
        }
        for (o in returnOrgs) {
            CommonUtils.convertOrg(organizationVos, o)
        }
        return returnOrgs
    }

    /**
     * 根据某组织机构节点的id 向上找父级节点的集合
     *
     * @param id 当前机构id
     * @return 父级节点集合
     */

    override fun getParentOrgsById(id: String): List<Organization> {
        return if (StringUtils.isEmpty(id)) emptyList() else organizationMapper.getParentOrgsById(id)
    }

    /**
     * 根据用户id和对应的机构级别获取对应的组织机构信息
     *
     * @param userId    当前机构id
     * @param orgGrades 机构级别数组
     * @return 组织机构列表
     */
    override fun getOrgList(userId: String, orgGrades: String): List<Organization> {
        val toTypedArray = orgGrades.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val grades = if (toTypedArray.isNotEmpty()) Arrays.asList(*toTypedArray) else Arrays.asList(orgGrades)
        return organizationMapper.getOrgList(userId, grades)
    }

    /**
     * 新增机构时获取最大编码值
     *
     * @param orgParentId 机构父级id
     * @return 最大编码值
     */
    private fun getNextCode(orgParentId: String): Int {
        var maxCode = organizationMapper.getLastCode(orgParentId)
        if (StringUtils.isEmpty(maxCode)) {
            maxCode = "0"
        }
        return Integer.parseInt(maxCode) + 1
    }

    override fun getChildOrgTrees(filter: Map<String, String>): List<OrganizationVO> {
        return organizationMapper.getChildOrgTrees(filter)
    }
}
