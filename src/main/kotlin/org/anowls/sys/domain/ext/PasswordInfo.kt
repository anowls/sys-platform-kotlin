package org.anowls.sys.domain.ext

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 密码信息-扩展</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@ApiModel(description = "密码信息-扩展")
data class PasswordInfo(

        @ApiModelProperty(name = "登录名", required = true)
        var loginName: String? = "",

        @ApiModelProperty(name = "用户ID", required = true)
        var accountId: String? = "",

        @ApiModelProperty(name = "原密码", required = true)
        var oldPassword: String? = null,

        @ApiModelProperty(name = "新密码", required = true)
        var newPassword: String? = null,

        @ApiModelProperty(name = "确认新密码", required = true)
        var newCheckPass: String? = null
)