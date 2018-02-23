package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.Organization
import org.anowls.sys.domain.view.OrganizationVO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 组织机构-数据访问</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@Mapper
@Repository
interface OrganizationMapper : BaseMapper<Organization> {
    /**
     * 机构列表
     *
     * @param map 查询参数（isIncludeChild是否有值，orgId机构id，keywords查询关键词，status状态是否有值）
     * @return
     */
    fun query(map: Map<*, *>): List<OrganizationVO>

    /**
     * 查询父级的最大编码
     *
     * @param parentId 父级id
     * @return 组织机构信息
     */
    //    @Select({"select max(org.org_code) from system_organization org where org.org_parent_id=#{parentId}"})
    fun getLastCode(@Param("parentId") parentId: String): String

    /**
     * 根据级联编码查询组织机构
     *
     * @param pathCode 级联编码
     * @return 组织机构列表
     */
    fun getChildOrgs(@Param("pathCode") pathCode: String): List<Organization>

    /**
     * 根据用户、机构、学校查询组织机构
     *
     * @param filter 查询条件： {
     * userId: 用户ID
     * orgId: 机构ID（可选）
     *
     *
     * }
     * @return 组织机构列表
     */
    fun getOrgs(filter: Map<String, String>): List<OrganizationVO>

    /**
     * 根据用户、机构、学校查询组织机构
     *
     * @param filter 查询条件： {
     * userId: 用户ID
     * orgId: 机构ID（可选）
     *
     *
     * }
     * @return 组织机构列表
     */
    fun getChildOrgTrees(filter: Map<String, String>): List<OrganizationVO>

    /**
     * 获取用户的默认组织机构
     *
     * @param userId 用户id
     * @return 组织机构信息
     */
    fun getDefaultByUserId(@Param("userId") userId: String): Organization

    /**
     * 获取用户的默认组织机构
     *
     * @param userId 用户id
     * @return 组织机构信息
     */
    fun getDefaultListByUserId(@Param("userId") userId: String): List<Organization>

    /**
     * 根据某组织机构节点的id 向上找父级节点的集合
     *
     * @param id 机构id
     * @return 父级节点集合
     */
    fun getParentTreeById(id: String): List<String>

    /**
     * 根据某组织机构节点的id 子级节点的集合
     *
     * @param id 机构id
     * @return 父级节点集合
     */
    fun getChildrenIds(id: String): List<String>

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
     * @param orgGrades 机构级别数组
     * @return 组织机构列表
     */
    fun getOrgList(@Param("userId") userId: String, @Param("orgGrades") orgGrades: List<String>): List<Organization>

    /**
     * 根据级联编码获取机构下级节点
     *
     * @param pathCode 机构级联编码
     * @return 子级节点集合
     */
    fun getChildIdsByCodePath(@Param("pathCode") pathCode: String): List<Map<*, *>>

    /**
     * 根据主键id获取机构信息
     *
     * @param id 主键id
     * @return 机构信息
     */
    fun getById(id: String): OrganizationVO?

    /**
     * 根据主键id获取机构信息
     *
     * @param filter 筛选条件
     * @return 机构信息
     */
    fun countByCondition(filter: Map<*, *>): Long

//    /**
//     * 根据id获取对外同步的机构数据
//     * @param orgId 机构id，可以空 查询所有
//     * @return 机构同步数据
//     */
//    fun getSyncOrgs(@Param("orgId") orgId: String): List<SyncOrg>
}
