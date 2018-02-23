package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Column
import javax.persistence.Table

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 用户机构</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@ApiModel(description = "用户机构")
@Table(name = "sys_account_org")
open class AccountOrg(

        @ApiModelProperty(value = "用户ID", required = true)
        @Column(name = "account_id")
        var accountId: String = "",

        @ApiModelProperty(value = "组织机构ID", required = true)
        @Column(name = "org_id")
        var orgId: String = "",

        @ApiModelProperty(value = "是否默认机构:0、否；1、是", required = true)
        @Column(name = "org_default")
        var orgDefault: Boolean = false,

        @ApiModelProperty(value = "状态:1 启用 0 停用", required = true)
        @Column(name = "status")
        var status: Int = 1

) : BaseDomain()