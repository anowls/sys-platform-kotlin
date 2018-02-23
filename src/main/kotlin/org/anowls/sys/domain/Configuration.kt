package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Column
import javax.persistence.Table

@ApiModel(description = "系统参数")
@Table(name = "sys_configuration")
open class Configuration(

        @ApiModelProperty(value = "数据类型", required = true)
        @NotBlank(message = "数据类型必填！")
        @Length(max = 20, message = "数据类型长度不能超过20位！")
        @Column(name = "data_type")
        var dataType: String? = null,

        @ApiModelProperty(value = "键", required = true)
        @NotBlank(message = "键必填！")
        @Length(max = 50, message = "键长度不能超过50位！")
        @Column(name = "cfg_key")
        var scKey: String? = null,

        @ApiModelProperty(value = "值", required = true)
        @Column(name = "cfg_value")
        var scValue: String? = null,

        @ApiModelProperty(value = "描述")
        @Column(name = "cfg_desc")
        var scDesc: String? = null,

        @ApiModelProperty(value = "状态")
        @Column(name = "status")
        var status: Int? = 1,

        @ApiModelProperty(value = "范围机构Id")
        @Column(name = "org_id")
        var orgId: String? = null
) : BaseDomain()