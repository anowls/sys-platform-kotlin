package org.anowls.sys.domain.view

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.anowls.sys.domain.Application

@ApiModel(description = "应用-视图对象")
data class ApplicationVO(

        @ApiModelProperty(value = "是否同步数据 true 同步 false不同步")
        var appGroupCode: String = "",

        @ApiModelProperty(value = "应用组名称")
        var appGroupName: String = "",

        @ApiModelProperty(value = "应用组排序号")
        var appGroupSort: Int = 1,

        @ApiModelProperty(value = "应用菜单")
        var menus: List<MenuVO> = emptyList(),

        @ApiModelProperty(value = "应用模块")
        var modules: List<ModuleVO> = emptyList()
) : Application()