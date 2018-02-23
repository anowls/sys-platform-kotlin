package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Column
import javax.persistence.Table

@ApiModel(description = "应用")
@Table(name = "sys_application")
open class Application(

        @NotBlank(message = "应用组必填！")
        @ApiModelProperty(value = "应用组Id", required = true)
        @Column(name = "app_group_id")
        var appGroupId: String = "",

        @NotBlank(message = "应用编码必填！")
        @Length(max = 10, message = "应用编码长度不能超过50位！")
        @ApiModelProperty(value = "编码", required = true)
        @Column(name = "app_code")
        var appCode: String = "",

        @NotBlank(message = "应用名称必填！")
        @Length(max = 10, message = "应用名称长度不能超过100位！")
        @ApiModelProperty(value = "名称", required = true)
        @Column(name = "app_name")
        var appName: String = "",

        @Length(max = 10, message = "应用别名长度不能超过50位！")
        @ApiModelProperty(value = "别名")
        @Column(name = "app_alias")
        var appAlias: String? = null,

        @ApiModelProperty(value = "打开方式（1 当前页面 2 新标签页 3 弹出窗口）默认2", required = true)
        @Column(name = "app_level")
        var appLevel: Int? = 2,

        @ApiModelProperty(value = "打开方式（1 当前页面 2 新标签页 3 弹出窗口）默认1", required = true)
        @Column(name = "app_target")
        var appTarget: Int? = 1,

        @ApiModelProperty(value = "地址", required = true)
        @NotBlank(message = "应用地址必填！")
        @Length(max = 10, message = "应用地址长度不能超过150位！")
        @Column(name = "app_url")
        var appUrl: String? = null,

        @Length(max = 10, message = "应用运行地址长度不能超过100位！")
        @ApiModelProperty(value = "运行地址", required = true)
        @Column(name = "app_run_url")
        var appRunUrl: String? = null,

        @ApiModelProperty(value = "图标")
        @Length(max = 10, message = "应用图标长度不能超过100位！")
        @Column(name = "app_icon")
        var appIcon: String? = null,

        @ApiModelProperty(value = "描述")
        @Length(max = 10, message = "应用描述长度不能超过150位！")
        @Column(name = "app_desc")
        var appDesc: String? = null,

        @NotBlank(message = "应用排序号必填！")
        @ApiModelProperty(value = "排序号", required = true)
        @Column(name = "sort_number")
        var appSort: Int? = null,

        @ApiModelProperty(value = "应用状态 （1 启用 0 停用） 默认1", required = true)
        @Column(name = "status")
        var status: Int? = 1,

        @Length(max = 10, message = "应用厂商长度不能超过60位！")
        @ApiModelProperty(value = "应用厂商")
        @Column(name = "app_company")
        var appCompany: String? = null,

        @Length(max = 10, message = "公司网址长度不能超过100位！")
        @ApiModelProperty(value = "公司网址")
        @Column(name = "company_url")
        var companyUrl: String? = null,

        @Length(max = 10, message = "邮件地址长度不能超过50位！")
        @ApiModelProperty(value = "公司邮箱")
        @Column(name = "company_email")
        var companyEmail: String? = null,

        @Length(max = 10, message = "服务电话长度不能超过50位！")
        @ApiModelProperty(value = "服务电话")
        @Column(name = "company_phone")
        var companyPhone: String? = null,

        @Length(max = 10, message = "联系人长度不能超过60位！")
        @ApiModelProperty(value = "联系人")
        @Column(name = "linkman")
        var linkman: String? = null,

        @Length(max = 10, message = "联系人电话长度不能超过50位！")
        @ApiModelProperty(value = "联系人电话")
        @Column(name = "contact_phone")
        var contactPhone: String? = null,

        @Length(max = 10, message = "联系人邮箱长度不能超过50位！")
        @ApiModelProperty(value = "联系人邮箱")
        @Column(name = "contact_email")
        var contactEmail: String? = null

) : BaseDomain()