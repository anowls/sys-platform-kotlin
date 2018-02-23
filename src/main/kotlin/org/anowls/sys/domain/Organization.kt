package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Column
import javax.persistence.Table

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 组织结构</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@Table(name = "sys_organization")
@ApiModel(value = "组织结构")
open class Organization(

        @ApiModelProperty(value = "上级id", required = true)
        @Column(name = "org_parent_id")
        var orgParentId: String? = "",

        @ApiModelProperty(value = "编码", required = true)
        @Column(name = "org_code")
        var orgCode: String? = "",

        @ApiModelProperty(value = "编码(自定义)", required = true)
        @Column(name = "org_short_code")
        var orgShortCode: String? = "",

        @ApiModelProperty(value = "级联编码", required = true)
        @Column(name = "org_code_path")
        var orgCodePath: String? = "",

        @ApiModelProperty(value = "名称", required = true)
        @NotBlank(message = "组织结构名称必填")
        @Column(name = "org_name")
        var orgName: String? = "",

        @ApiModelProperty(value = "名称路径", required = true)
        @Column(name = "org_name_path")
        var orgNamePath: String? = "",

        @ApiModelProperty(value = "简称", required = true)
        @Column(name = "org_short_name")
        var orgShortName: String? = "",

        @ApiModelProperty(value = "层级", required = true)
        @Column(name = "org_level")
        var orgLevel: Int? = 0,

        @ApiModelProperty(value = "排序号", required = true)
        @Column(name = "org_sort")
        var orgSort: Int? = 1,

        @ApiModelProperty(value = "logo", required = true)
        @Column(name = "org_logo")
        var orgLogo: String? = "",

        @ApiModelProperty(value = "组织机构级别 0 系统 1 区 2 校", required = true)
        @Column(name = "org_grade")
        var orgGrade: Int? = 0,

        @ApiModelProperty(value = "状态", required = true)
        @Column(name = "status")
        var status: Int? = 1,

        @ApiModelProperty(value = "机构类型", required = true)
        @Column(name = "org_type")
        var orgType: Int? = 0

) : BaseDomain()