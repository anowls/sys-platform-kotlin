package org.anowls.sys.web

import com.google.common.collect.Lists
import io.swagger.annotations.*
import org.anowls.sys.common.PageQuery
import org.anowls.sys.common.SimplePage
import org.anowls.sys.common.message.SimpleMessage
import org.anowls.sys.domain.SysEnum
import org.anowls.sys.domain.SysEnumItem
import org.anowls.sys.domain.view.SysEnumVO
import org.anowls.sys.service.SysEnumService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 数据字典-访问</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@RestController
@RequestMapping("/enum")
@Api(tags = ["数据字典分类-控制层"])
class SysEnumController(@Autowired private val sysEnumService : SysEnumService) {

    companion object {
        private val logger = LoggerFactory.getLogger(SysEnumController::class.java)
    }

    /**
     * 根据数据字典分类编码查询对应数据项
     *
     * @param codes 数据字典分类编码，多个以“,”分隔
     * @return 数据字典分类数据项列表
     */
    @ApiOperation(value = "查询字典数据分类数据项", notes = "查询字典数据分类数据项")
    @GetMapping("/queryByCodes/{codes}")
    fun queryByCodes(@ApiParam(value = "数据字典分类编码，多个以“,”分隔", required = true) @PathVariable codes : String) : SimpleMessage {
        return sysEnumService.queryByCodes(codes)
    }

    /**
     * 分页查询数据字典分类
     *
     * @param pagerQuery 分页参数
     * @return 数据字典分类列表
     */
    @ApiOperation(value = "分页查询数据字典分类", notes = "分页查询数据字典分类")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ApiImplicitParams(value = [ApiImplicitParam(name = "page", value = "第几页", dataType = "int", paramType = "query"),
        ApiImplicitParam(name = "size", value = "每页显示的数据条数", dataType = "int", paramType = "query"),
        ApiImplicitParam(name = "filter", value = "查询条件（title=）", paramType = "query"),
        ApiImplicitParam(name = "order", value = "排序规则（id=asc）", paramType = "query")])
    fun query(pagerQuery: PageQuery): SimplePage<SysEnumVO> {
        return  sysEnumService.query(pagerQuery)
    }

    /**
     * 根据数据字典分类ID查询数据字典分类数据项
     *
     * @param id 数据字典分类ID
     * @return 数据字典分类数据项列表
     */
    @ApiOperation(value = "查询数据字典分类数据项", notes = "根据数据字典分类ID查询数据字典分类数据项")
    @RequestMapping("/{id}", method = [RequestMethod.GET])
    fun getById(@ApiParam(value = "数据字典ID", required = true) @PathVariable id : String) : SimpleMessage {
        return sysEnumService.getById(id)
    }

    /**
     * 新增数据字典分类
     *
     * @param sysEnum 数据字典分类信息
     * @return 操作结果
     */
    @PostMapping
    @ApiOperation(value = "新增数据字典分类", notes = "新增数据字典分类")
    fun insert(@ApiParam(value = "数据字典信息", required = true) @RequestBody sysEnum : SysEnum) : SimpleMessage {
        return sysEnumService.insert(sysEnum)
    }

    /**
     * 修改数据字典分类信息
     *
     * @param sysEnum 数据字典分类信息
     * @return 操作结果
     */
    @PutMapping
    @ApiOperation(value = "修改数据字典分类信息", notes = "修改数据字典分类信息")
    fun update(@ApiParam(value = "数据字典分类信息", required = true) @RequestBody sysEnum : SysEnum) : SimpleMessage {
        return sysEnumService.update(sysEnum)
    }

    /**
     * 删除数据字典分类数据
     *
     * @param id 数据字典分类ID
     * @return 操作结果
     */
    @ApiOperation(value = "删除数据字典数据", notes = "删除数据字典数据")
    @DeleteMapping("/{id}")
    fun delete(@ApiParam(value = "数据字典分类ID", required = true) @PathVariable id : String) : SimpleMessage {
        return sysEnumService.delete(id)
    }

    /**
     * 启用数据字典分类
     *
     * @param id 数据字典分类ID
     * @return 操作结果
     */
    @PostMapping("/enable/{id}")
    @ApiOperation(value = "启用数据字典分类", notes = "启用数据字典分类")
    fun enable(@ApiParam(value = "数据字典分类ID", required = true) @PathVariable id : String) : SimpleMessage {
        return sysEnumService.enable(id)
    }

    /**
     * 停用数据字典分类
     *
     * @param id 数据字典分类ID
     * @return 操作结果
     */
    @PostMapping("/disable/{id}")
    @ApiOperation(value = "停用数据字典分类", notes = "停用数据字典分类")
    fun disable(@ApiParam(value = "数据字典分类ID", required = true) @PathVariable id : String) : SimpleMessage {
        return sysEnumService.disable(id)
    }

    /**
     * 根据数据字典分类数据项ID查询数据字典分类数据项信息
     *
     * @param id 数据字典分类数据项ID
     * @return 数据字典分类数据项信息
     */
    @GetMapping("/item/{id}")
    @ApiOperation(value = "根据数据字典分类数据项ID查询数据字典分类数据项信息", notes = "根据数据字典分类数据项ID查询数据字典分类数据项信息")
    fun getItemById(@ApiParam(value = "数据字典分类数据项ID", required = true) @PathVariable id : String) : SimpleMessage {
        return sysEnumService.getItemById(id)
    }

    /**
     * 分页查询数据字典分类数据项
     *
     * @param pageQuery 分页参数
     * @return 数据字典分类数据项列表
     */
    @ApiOperation(value = "分页查询数据字典分类数据项", notes = "分页查询数据字典分类数据项")
    @GetMapping("/item")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "page", value = "第几页", dataType = "int", paramType = "query"),
        ApiImplicitParam(name = "size", value = "每页显示的数据条数", dataType = "int", paramType = "query"),
        ApiImplicitParam(name = "filter", value = "查询条件（id=code=title=)", paramType = "query"),
        ApiImplicitParam(name = "order", value = "排序规则", paramType = "query")
    ])
    fun queryItem(pageQuery : PageQuery) : SimplePage<SysEnumItem> {
        return sysEnumService.queryItem(pageQuery)
    }

    /**
     * 新增数据字典分类数据项
     *
     * @param enumItem 数据字典分类数据项
     * @return 操作结果
     */
    @PostMapping("/item")
    @ApiOperation(value = "新增数据字典分类数据项", notes = "新增数据字典分类数据项")
    fun insertItem(@ApiParam(value = "数据字典数据项", required = true) @RequestBody enumItem : SysEnumItem) : SimpleMessage {
        return sysEnumService.insertItem(enumItem)
    }

    /**
     * 修改数据字典分类数据项
     *
     * @param enumItem 数据字典分类数据项
     * @return 操作结果
     */
    @PutMapping("/item")
    @ApiOperation(value = "修改数据字典分类数据项", notes = "修改数据字典分类数据项")
    fun updateItem(@ApiParam(value = "数据字典分类数据项", required = true) @RequestBody enumItem : SysEnumItem) : SimpleMessage {
        return sysEnumService.updateItem(enumItem)
    }

    /**
     * 删除数据字典分类数据项
     *
     * @param 数据字典数据项ID
     * @return 操作结果
     */
    @DeleteMapping("/item/{id}")
    @ApiOperation(value = "删除数据字典分类数据项", notes = "删除数据字典分类数据项")
    fun deleteItem(@ApiParam(value = "数据字典分类数据项ID", required = true) @PathVariable id : String) : SimpleMessage {
        return sysEnumService.deleteItem(Lists.newArrayList(id))
    }

    /**
     * 启用数据字典分类数据项
     *
     * @param id 数据字典分类数据项ID
     * @return 操作结果
     */
    @PostMapping("/item/enable/{id}")
    @ApiOperation(value = "启用数据字典分类数据项", notes = "启用数据字典分类数据项")
    fun enableItem(@ApiParam(value = "数据字典分类数据项ID", required = true) @PathVariable id : String) : SimpleMessage {
        return sysEnumService.enableItem(id)
    }

    /**
     * 停用数据字典分类数据项
     *
     * @param id 数据字典分类数据项ID
     * @return 操作结果
     */
    @PostMapping("/item/disable/{id}")
    @ApiOperation(value = "停用数据字典分类数据项", notes = "停用数据字典分类数据项")
    fun disableItem(@ApiParam(value = "数据字典分类数据项ID", required = true) @PathVariable id : String) : SimpleMessage {
        return sysEnumService.disableItem(id)
    }
}
