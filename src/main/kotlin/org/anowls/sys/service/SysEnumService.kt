package org.anowls.sys.service

import org.anowls.sys.common.PageQuery
import org.anowls.sys.common.SimplePage
import org.anowls.sys.common.message.SimpleMessage
import org.anowls.sys.domain.SysEnum
import org.anowls.sys.domain.SysEnumItem
import org.anowls.sys.domain.view.SysEnumVO

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 这里填写描述信息</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
interface SysEnumService {

    /**
     * 根据数据字典分类编码查询对应数据项
     *
     * @param codes 数据字典分类编码，多个以“,”分隔
     * @return 数据字典分类数据项列表
     */
    fun queryByCodes(codes : String) : SimpleMessage

    /**
     * 分页查询数据字典分类
     *
     * @param pagerQuery 分页参数
     * @return 数据字典分类列表
     */
    fun query(pagerQuery : PageQuery) : SimplePage<SysEnum>

    /**
     * 根据数据字典分类ID查询数据字典分类数据项
     *
     * @param id 数据字典分类ID
     * @return 数据字典分类数据项列表
     */
    fun getById(id : String) : SimpleMessage

    /**
     * 新增数据字典分类
     *
     * @param sysEnum 数据字典分类信息
     * @return 操作结果
     */
    fun insert(sysEnum : SysEnum) : SimpleMessage

    /**
     * 修改数据字典分类信息
     *
     * @param sysEnum 数据字典分类信息
     * @return 操作结果
     */
    fun update(sysEnum : SysEnum) : SimpleMessage

    /**
     * 删除数据字典分类数据
     *
     * @param id 数据字典分类ID
     * @return 操作结果
     */
    fun delete(id : String) : SimpleMessage

    /**
     * 启用数据字典分类
     *
     * @param id 数据字典分类ID
     * @return 操作结果
     */
    fun enable(id : String) : SimpleMessage

    /**
     * 停用数据字典分类
     *
     * @param id 数据字典分类ID
     * @return 操作结果
     */
    fun disable(id : String) : SimpleMessage

    /**
     * 根据数据字典分类数据项ID查询数据字典分类数据项信息
     *
     * @param id 数据字典分类数据项ID
     * @return 数据字典分类数据项信息
     */
    fun getItemById(id : String) : SimpleMessage

    /**
     * 分页查询数据字典分类数据项
     *
     * @param pageQuery 分页参数
     * @return 数据字典分类数据项列表
     */
    fun queryItem(pageQuery : PageQuery) : SimplePage<SysEnumItem>

    /**
     * 新增数据字典分类数据项
     *
     * @param enumItem 数据字典分类数据项
     * @return 操作结果
     */
    fun insertItem(enumItem : SysEnumItem) : SimpleMessage

    /**
     * 修改数据字典分类数据项
     *
     * @param enumItem 数据字典分类数据项
     * @return 操作结果
     */
    fun updateItem(enumItem : SysEnumItem) : SimpleMessage

    /**
     * 删除数据字典分类数据项
     *
     * @param 数据字典数据项ID
     * @return 操作结果
     */
    fun deleteItem(ids : List<String>) : SimpleMessage

    /**
     * 启用数据字典分类数据项
     *
     * @param id 数据字典分类数据项ID
     * @return 操作结果
     */
    fun enableItem(id : String) : SimpleMessage

    /**
     * 停用数据字典分类数据项
     *
     * @param id 数据字典分类数据项ID
     * @return 操作结果
     */
    fun disableItem(id : String) : SimpleMessage

}