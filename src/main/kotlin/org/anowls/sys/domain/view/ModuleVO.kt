package org.anowls.sys.domain.view

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.anowls.sys.domain.Feature
import org.anowls.sys.domain.Module

@ApiModel(value = "功能模块")
data class ModuleVO(

        @ApiModelProperty(value = "功能项集合", required = true)
        var features: List<Feature>

) : Module()