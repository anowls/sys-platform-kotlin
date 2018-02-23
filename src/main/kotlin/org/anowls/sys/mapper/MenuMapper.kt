package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.Menu
import org.anowls.sys.domain.view.MenuVO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface MenuMapper : BaseMapper<Menu> {

    /**
     * 根据角色ID集合、是否超级管理员、应用类型查询有权限的菜单列表
     *
     * @param roleIds    角色ID集合
     * @param isAdmin    是否超级管理员
     * @param appLevel   平台类型（1：运维平台，2：业务系统）
     * @return 菜单列表
     */
    fun getByRoleIdIn(@Param("isAdmin") isAdmin: Boolean,
                      @Param("roleIds") roleIds: List<String>,
                      @Param("appLevel") appLevel: Int?): List<MenuVO>

    /**
     * 根据应用ID删除菜单
     *
     * @param appId 应用ID
     */
    fun deleteByAppId(@Param("appId") appId: String)

    /**
     * 根据应用ID查询菜单列表
     *
     * @param appId 应用ID
     * @return 菜单列表
     */
    fun getByAppId(@Param("appId") appId: String): List<MenuVO>

    /**
     * 批量新增菜单
     *
     * @param menus 菜单列表
     */
    fun insertMenus(menus: List<Menu>)

    /**
     * 查询父级的最大编码
     *
     * @param parentId 父级ID
     * @return 菜单编码
     */
    @Select("select max(t1.menu_code) from system_menu t1 where t1.sm_parent_id = #{parentId}")
    fun getLastCode(parentId: String): String
}
