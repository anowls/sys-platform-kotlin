package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.Configuration
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface ConfigurationMapper : BaseMapper<Configuration> {

    /**
     * 分页查询系统参数
     * @param map 过滤条件
     * @param orgIds 机构ids集合关系
     * @return 分页数据
     */
    fun query(@Param("filter") map: Map<*, *>, @Param("orgIds") orgIds: List<String>): List<Configuration>

    /**
     * 分页查询系统参数
     * @param keys 过滤条件
     * @param orgIds 机构ids集合关系
     * @return 分页数据
     */
    fun queryByKeys(@Param("scKeys") keys: List<String>, @Param("orgIds") orgIds: List<String>): List<Configuration>

    /**
     * 获取所有参数，带上机构ids集合关系
     * @param orgIds 机构ids集合关系
     * @return 系统参数列表
     */
    fun getAll(@Param("orgIds") orgIds: List<String>): List<Configuration>

    /**
     * 根据key获取全局参数对象
     * @param scKey 参数键值
     * @return 全局参数对象
     */
    fun getByScKey(@Param("scKey") scKey: String): Configuration
}
