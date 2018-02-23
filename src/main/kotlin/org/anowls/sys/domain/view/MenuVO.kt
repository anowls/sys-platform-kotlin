package org.anowls.sys.domain.view

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.anowls.sys.domain.Menu

@ApiModel(value = "菜单视图对象")
data class MenuVO(

        @ApiModelProperty(value = "菜单子项")
        var sfCode: String? = null,

        @ApiModelProperty(value = "菜单子项")
        var children: List<MenuVO> = emptyList()
) : Menu()