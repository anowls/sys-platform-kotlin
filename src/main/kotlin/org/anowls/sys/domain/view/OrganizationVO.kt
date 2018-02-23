package org.anowls.sys.domain.view

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.anowls.sys.domain.Organization

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 组织机构-视图对象</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/2/21 0026
 */
@ApiModel(value = "组织机构-视图对象")
data class OrganizationVO(

        @ApiModelProperty(value = "父机构名称")
        var orgParentName: String? = null,

        @ApiModelProperty(value = "子组织结构", required = true)
        var children: List<OrganizationVO>? = null,

        @ApiModelProperty(value = "是否默认机构", required = true)
        var orgDefault: Boolean? = false,

        @ApiModelProperty(value = "是否对该节点有管理权限", required = true)
        var orgAuthority: Boolean? = null

) : Organization()
