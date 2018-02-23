package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.AppGroup
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface AppGroupMapper : BaseMapper<AppGroup> {

    /**
     * 根据Id获取应用组
     * @param id 应用组Id
     * @return 应用组
     */
    fun findOne(id: String): AppGroup?

    /**
     * 根据Code获取应用组
     * @param code 应用组Code
     * @return 应用组
     */
    fun findOneByCode(code: String): AppGroup?

    /**
     * 分页查询应用组
     * @param map title包含编码、名称、描述
     * @return 分页应用组
     */
    fun findAll(map: Map<String, Any>): List<AppGroup>

}
