package org.anowls.sys.web

import io.swagger.annotations.*
import org.anowls.sys.common.PageQuery
import org.anowls.sys.common.SimplePage
import org.anowls.sys.common.controller.BaseController
import org.anowls.sys.common.message.SimpleMessage
import org.anowls.sys.domain.Organization
import org.anowls.sys.service.OrganizationService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 组织机构-访问</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@RestController
@RequestMapping("/orgs")
@Api(tags = ["8、组织机构管理-内部接口"])
class OrganizationController(@Autowired private val organizationService: OrganizationService) : BaseController() {

    /**
     * 查询当前登录用户权限组织机构树
     * @param filter 查询参数
     * @return 当前登录用户权限组织机构树
     */
    @ApiOperation(value = "查询权限组织机构树", notes = "查询当前登录用户权限组织机构树")
    @GetMapping("/listChildTree")
    fun getChildOrgTrees(@ApiParam(hidden = true) @RequestParam filter: Map<String, String>): SimpleMessage {
        return SimpleMessage.info(organizationService.getChildOrgTrees(filter))
    }

    /**
     * 查询组织机构
     *
     * @param pagerQuery 分页对象
     * @return 分页结果
     */
    @ApiOperation(value = "查询组织机构", notes = "查询组织机构", response = Organization::class)
    @GetMapping("/list")
    @ApiImplicitParams(ApiImplicitParam(name = "page", value = "第几页", dataType = "int", paramType = "query"), ApiImplicitParam(name = "size", value = "每页显示的数据条数", dataType = "int", paramType = "query"), ApiImplicitParam(name = "filter", value = "查询条件（isIncludeChild=;keywords=;status=;orgId=）", paramType = "query"), ApiImplicitParam(name = "order", value = "排序规则（orgCode=asc）", paramType = "query"))
    fun query(pagerQuery: PageQuery): SimplePage<Organization> {
        return organizationService.query(pagerQuery)
    }

    /**
     * 查询组织机构信息
     *
     * @param id 组织机构ID
     * @return 机构信息
     */
    @ApiOperation(value = "查询组织机构信息", notes = "查询组织机构信息")
    @GetMapping("{id}")
    operator fun get(@ApiParam(value = "组织机构ID", required = true) @PathVariable id: String): SimpleMessage {
        val organization = organizationService.getById(id) ?: return SimpleMessage.failure("操作失败，组织机构机构不存在！")
        return SimpleMessage.info(organization)
    }

    /**
     * 新增组织机构
     *
     * @param organization 组合子机构信息
     * @return 操作结果
     */
    @ApiOperation(value = "新增组织机构", notes = "新增组织机构")
    @PostMapping
    fun insert(@ApiParam(value = "组合子机构信息", required = true) @RequestBody organization: Organization): SimpleMessage {
        val checkResult = organizationService.checkOrgInfo(organization, true)
        if (StringUtils.isNotEmpty(checkResult)) {
            return SimpleMessage.failure(checkResult)
        }
        return SimpleMessage.info("保存成功！")
    }

    /**
     * 编辑组织机构
     *
     * @param organization 组织机构信息
     * @return 操作结果
     */
    @ApiOperation(value = "编辑组织机构", notes = "编辑组织机构")
    @PutMapping
    fun update(@ApiParam(value = "组织机构信息", required = true) @RequestBody organization: Organization): SimpleMessage {
        val checkResult = organizationService.checkOrgInfo(organization, false)
        if (StringUtils.isNotEmpty(checkResult)) {
            return SimpleMessage.failure(checkResult)
        }
        return SimpleMessage.info("保存成功！")
    }

    /**
     * 删除组织机构
     *
     * @param id 组织机构ID
     * @return 操作结果
     */
    @ApiOperation(value = "删除组织机构", notes = "删除组织机构")
    @DeleteMapping
    fun delete(@ApiParam(value = "组织机构ID", required = true) @PathVariable id: String): SimpleMessage {
        return organizationService.deleteOrgInfo(id)
    }

    /**
     * 启用组织机构
     *
     * @param id 组织机构ID
     * @return 操作结果
     */
    @ApiOperation(value = "启用组织机构", notes = "启用组织机构")
    @PostMapping("/enable/{id}")
    fun enable(@ApiParam(value = "组织机构ID", required = true) @PathVariable id: String): SimpleMessage {
        return organizationService.updateOrgStatus(id, 1)
    }

    /**
     * 停用组织机构
     *
     * @param id 组织机构ID
     * @return 操作结果
     */
    @ApiOperation(value = "停用组织机构", notes = "停用组织机构")
    @PostMapping("/disable/{id}")
    fun disable(@ApiParam(value = "组织机构ID", required = true) @PathVariable id: String): SimpleMessage {
        return organizationService.updateOrgStatus(id, 0)
    }
//
//    /**
//     * 增加组织机构人员
//     *
//     * @param userOrgs 组织机构人员信息
//     * @return 操作结果
//     */
//    @ApiOperation(value = "增加组织机构人员", notes = "增加组织机构人员")
//    @RequestMapping(value = "/addOrgUser", method = arrayOf(RequestMethod.POST))
//    fun addOrgUser(
//            @ApiParam(value = "机构人员关系对象列表", required = true)
//            @RequestBody
//            userOrgs: List<UserOrg>): SimpleMessage {
//
//        userOrgService.insertUserOrg(userOrgs)
//        return SimpleMessage.info("保存成功!")
//    }

//    /**
//     * 删除组织机构人员
//     *
//     * @param userId 用户id
//     * @param orgId 组织机构id
//     * @return 操作结果
//     */
//    @ApiOperation(value = "删除组织机构人员", notes = "删除组织机构人员")
//    @RequestMapping(value = "/deleteOrgUser/{userId}/{orgId}", method = arrayOf(RequestMethod.POST))
//    fun deleteOrgUser(@ApiParam(value = "机构人员id", required = true) @PathVariable userId: String, @PathVariable orgId: String): SimpleMessage {
//        return userOrgService.deleteUserOrg(userId, orgId)
//    }
//
//    /**
//     * 查询组织机构人员
//     *
//     * @param pagerQuery 分页对象
//     * @return 组织机构人员列表
//     */
//    @ApiOperation(value = "查询组织机构人员", notes = "查询组织机构人员")
//    @RequestMapping(value = "/listOrgUser", method = arrayOf(RequestMethod.GET))
//    @ApiImplicitParams(ApiImplicitParam(name = "page", value = "第几页", dataType = "int", paramType = "query"), ApiImplicitParam(name = "size", value = "每页显示的数据条数", dataType = "int", paramType = "query"), ApiImplicitParam(name = "filter", value = "查询条件（isIncludeChild=;keywords=;status=;orgId=）", paramType = "query"), ApiImplicitParam(name = "order", value = "排序规则（userName=asc）", paramType = "query"))
//    fun listOrgUser(pagerQuery: PageQuery): SimplePage<UserOrgView> {
//        return userOrgService.listOrgUser(pagerQuery)
//    }

}
