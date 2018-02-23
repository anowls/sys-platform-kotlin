package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Column
import javax.persistence.Table

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 用户信息</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@ApiModel(description = "用户信息")
@Table(name = "sys_account")
open class Account(
        @ApiModelProperty(value = "组织机构id", required = true)
        @NotBlank(message = "组织机构必填！")
        @Column(name = "org_id")
        var orgId: String? = "",

        @ApiModelProperty(value = "登录名", required = true)
        @NotBlank(message = "登录名必填！")
        @Column(name = "login_name")
        var loginName: String? = "",

        @ApiModelProperty(value = "系统编码", required = true)
        @NotBlank(message = "系统编码必填！")
        @Column(name = "sys_code")
        var systemCode: String? = "",

        @ApiModelProperty(value = "密码", required = true)
        @NotBlank(message = "密码必填！")
        @Column(name = "password_verify")
        var passwordVerify: String? = "",

        @ApiModelProperty(value = "用户姓名", required = true)
        @NotBlank(message = "用户姓名必填！")
        @Column(name = "user_name")
        var userName: String? = "",

        @ApiModelProperty(value = "简称", required = true)
        @Column(name = "user_name")
        var shortName: String? = "",

        @ApiModelProperty(value = "主题", required = true)
        @Column(name = "theme")
        var theme: String? = "",

        @ApiModelProperty(value = "头像", required = true)
        @Column(name = "account_avatar")
        var accountAvatar: String? = "",

        @ApiModelProperty(value = "是否管理员", required = true)
        @Column(name = "user_type")
        var userType: Boolean? = false,

        @ApiModelProperty(value = "状态", required = true)
        @Column(name = "status")
        var status: Int? = 1,

        @ApiModelProperty(value = "身份证号", required = true)
        @Column(name = "id_card")
        var idCard: String? = "",

        @ApiModelProperty(value = "初始化状态", required = true)
        @Column(name = "init_type")
        var initType: Int? = 0,

        @ApiModelProperty(value = "用户pinyin", required = true)
        @Column(name = "pinyin")
        var pinyin: String? = "",

        @ApiModelProperty(value = "用户性别", required = true)
        @Column(name = "sex_code")
        var sexCode: String? = "",

        @ApiModelProperty(value = "联系电话", required = true)
        @Column(name = "contact_number")
        var contactNumber: String? = "",

        @ApiModelProperty(value = "邮箱", required = true)
        @Column(name = "mail")
        var mail: String? = ""
) : BaseDomain()