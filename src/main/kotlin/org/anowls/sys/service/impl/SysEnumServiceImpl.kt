package org.anowls.sys.service.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.google.common.collect.Lists
import org.anowls.sys.common.PageQuery
import org.anowls.sys.common.SimplePage
import org.anowls.sys.common.message.SimpleMessage
import org.anowls.sys.domain.SysEnum
import org.anowls.sys.domain.SysEnumItem
import org.anowls.sys.domain.view.SysEnumVO
import org.anowls.sys.mapper.SysEnumItemMapper
import org.anowls.sys.mapper.SysEnumMapper
import org.anowls.sys.service.SysEnumService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 数据字典-业务逻辑实现</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@Service
class SysEnumServiceImpl(@Autowired private val sysEnumMapper : SysEnumMapper,
                         @Autowired private val sysEnumItemMapper : SysEnumItemMapper) : SysEnumService {

    override fun queryByCodes(codes : String) : SimpleMessage {
        val queryByCodes = sysEnumItemMapper.queryByCodes(codes.split(","))
        val groupMap = queryByCodes.groupBy{ it.seCode }
        return SimpleMessage.info(groupMap)
    }

    override fun query(pagerQuery: PageQuery): SimplePage<SysEnumVO> {
        val filter = pagerQuery.convertFilterToMap()
        PageHelper.startPage<SysEnum>(pagerQuery.page, pagerQuery.size, pagerQuery.convertSort())
        val sysEnums: List<SysEnumVO> = sysEnumMapper.findAll(filter)
        val pageInfo: PageInfo<SysEnumVO> = PageInfo(sysEnums)
        return SimplePage.convert(pageInfo)
    }

    override fun getById(id : String) : SimpleMessage {
        return SimpleMessage.info(sysEnumMapper.selectByPrimaryKey(id))
    }


    override fun  insert(sysEnum : SysEnum) : SimpleMessage {
        return SimpleMessage.info("")
    }


    override fun update(sysEnum : SysEnum ) : SimpleMessage {
        return SimpleMessage.info("")
    }


    override fun delete(id : String) : SimpleMessage  {
        return SimpleMessage.info("")
    }


    override fun  enable(id : String) : SimpleMessage {
        return SimpleMessage.info("")
    }


    override fun  disable(id : String) : SimpleMessage {
        return SimpleMessage.info("")
    }


    override fun  getItemById(id : String) : SimpleMessage {
        return SimpleMessage.info("")
    }


    override fun queryItem(pageQuery : PageQuery ) : SimplePage<SysEnumItem> {
        PageHelper.startPage<SysEnum>(pageQuery.page, pageQuery.size, pageQuery.convertSort())
        return SimplePage.convert(PageInfo(Lists.newArrayList()))
    }


    override fun  insertItem(enumItem : SysEnumItem ) : SimpleMessage {
        return SimpleMessage.info("")
    }


    override fun  updateItem(enumItem : SysEnumItem) : SimpleMessage {
        return SimpleMessage.info("")
    }


    override fun  deleteItem(ids : List<String>) : SimpleMessage {
        return SimpleMessage.info("")
    }


    override fun  enableItem(id : String) : SimpleMessage {
        return SimpleMessage.info("")
    }


    override fun  disableItem(id : String) : SimpleMessage {
        return SimpleMessage.info("")
    }
}
