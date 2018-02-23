package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.AccountOrg
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.util.*

@Mapper
@Repository
interface AccountOrgMapper : BaseMapper<AccountOrg> {

    /**
     * 根据主键id获取用户与机构对应关系
     *
     * @param list 主键id集合
     * @return 用户与机构列表
     */
    fun getUserOrgList(list: List<String>): List<AccountOrg>

    /**
     * 根据主键id获取用户与机构对应关系
     *
     * @param userId 用户id
     * @param orgId 组织机构id
     * @return 用户与机构列表
     */
    fun getByUserOrgId(@Param("userId") userId: String, @Param("orgId") orgId: String): AccountOrg

    /**
     * 根据用户id和是否默认机构标志获取组织机构范围对象
     *
     * @param userId
     * @return 用户与机构对应关系
     */
    fun getDefaultOrgByUserId(@Param("userId") userId: String): AccountOrg

    /**
     * 根据用户删除机构关系
     *
     * @param userId      用户id
     * @param modifierId  操作人
     * @param currentDate 操作时间
     */
    fun deleteByUserId(@Param("userId") userId: String, @Param("modifierId") modifierId: String, @Param("currentDate") currentDate: Timestamp)

    /**
     * 批量写入用户组织机构关系
     *
     * @param userOrgs 用户与机构对应关系
     */
    fun insertAccountOrg(userOrgs: List<AccountOrg>)

    /**
     * 获取用户的最小编码 也就是权限最搞的那一层
     *
     * @param userIds 用户id集合
     * @return 用户与机构列表
     */
    fun getByUserIds(userIds: List<String>): List<AccountOrg>

    /**
     * 根据机构id获取上级单位的所有用户id集合，去重
     *
     * @param orgId 新的机构节点id
     * @return 上级单位的所有用户id集合
     */
    fun getParentOrgUsers(@Param("orgId") orgId: String): List<String>

    /**
     * 根据机构id获取上级单位的所有用户id集合，去重
     *
     * @param orgId 新的机构节点id
     * @return 上级单位的所有用户id集合
     */
    fun getParentUserOrgs(@Param("orgId") orgId: String): List<AccountOrg>

    /**
     * 根据用户id获取所有组织机构名称的map 格式为： <userId></userId>,orgName...orgName>
     *
     * @param userIds 用户id
     * @return 用户与机构字典
     */
    fun getUserOrgMap(userIds: List<String>): List<AccountOrg>

    /**
     * 批量取消用户的默认机构
     *
     * @param orgId   机构id
     * @param userIds 用户id集合
     * @return 操作记录数
     */
    fun updateDefaultBatch(@Param("orgId") orgId: String, @Param("userIds") userIds: List<String>): Int?

    /**
     * 获取用户与机构信息的列表
     *
     * @param mao 筛选条件
     * @return 用户与机构信息的列表
     */
    fun getOrgUsers(mao: Map<*, *>): List<AccountOrg>

    /**
     * 批量删除机构与用户关系，通过用户id 集合+机构id
     *
     * @param userIds 通过用户id
     * @param orgId   机构id
     */
    fun deleteOrgUser(@Param("list") userIds: List<String>, @Param("orgId") orgId: String, @Param("modifierId") modifierId: String, @Param("currentDate") currentDate: Date)

    /**
     * 根据用户id获取用户与机构的map信息
     * @param userIds 用户id集合
     * @return 用户与机构的map信息
     */
    fun geOrgMapByUserIds(userIds: List<String>): Map<*, *>

}