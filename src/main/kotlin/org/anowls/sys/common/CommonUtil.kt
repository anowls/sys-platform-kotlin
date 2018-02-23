package org.anowls.sys.common

import org.anowls.sys.domain.view.*
import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import java.beans.IntrospectionException
import java.beans.Introspector
import java.beans.PropertyDescriptor
import java.lang.reflect.InvocationTargetException
import java.sql.Timestamp
import java.text.DecimalFormat
import java.util.*
import java.util.UUID
import java.util.regex.Pattern

object CommonUtils {

    private val logger = LoggerFactory.getLogger(CommonUtils::class.java)

    /**
     * 正则表达式：验证身份证
     */
    private const val REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)"

    val datetime: Timestamp
        get() = Timestamp(Date().time)

    val id: String
        get() = UUID.randomUUID().toString().replace("-".toRegex(), "")

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    fun isIDCard(idCard: String): Boolean {
        return Pattern.matches(REGEX_ID_CARD, idCard)
    }

    /**
     * 格式化数字，使用零填补占位
     *
     * @param num     数字
     * @param pattern 格式
     * @return
     */
    fun numberFormat(num: Int, pattern: String): String {
        val df = DecimalFormat(pattern)
        return df.format(num.toLong())
    }

    /**
     * 构建菜单树
     *
     * @param menus       菜单集合
     * @param returnMenus 当前菜单
     */
    fun convertMenu(menus: List<MenuVO>, returnMenus: MenuVO) {
        val tmps = menus.filter { StringUtils.equals(it.smParentId, returnMenus.id) }
        if (CollectionUtils.isNotEmpty(tmps)) {
            returnMenus.children = tmps
        }
        for (m in tmps) {
            convertMenu(menus, m)
        }
    }

    /**
     * 简易map封装
     *
     * @param key   键
     * @param value 值
     * @return map对象
     */
    fun getMap(key: String, value: String): Map<String, String> {
        return mapOf(Pair(key, value))
    }

    /**
     * 构建组织机构树
     *
     * @param orgs       组织机构集合
     * @param returnOrgs 当前组织机构对象
     */
    fun convertOrg(orgs: List<OrganizationVO>, returnOrgs: OrganizationVO) {
        val tmps = orgs.filter { StringUtils.equals(it.orgParentId, returnOrgs.id) }
        if (CollectionUtils.isNotEmpty(tmps)) {
            returnOrgs.children = tmps
        }
        for (o in tmps) {
            convertOrg(orgs, o)
        }
    }

    /**
     * 构造引用模块对象
     *
     * @param accountVO    用户对象
     * @param returnMenus 菜单
     * @param tmpModules 模块
     * @param tmpFeatures 功能项
     */
    fun setApps(accountVO: AccountVO, returnMenus: List<MenuVO>, tmpModules: List<ModuleVO>, tmpFeatures: List<FeatureVO>) {
        for (app in accountVO.applications) {
            app.menus = returnMenus.filter { StringUtils.equals(it.appId, app.id) }
            val returnModules = tmpModules.filter { o -> StringUtils.equals(o.appId, app.id) }
            for (m in returnModules) {
                val features = tmpFeatures.filter { StringUtils.equals(it.smId, m.id) }
                m.features = features
            }
            app.modules = returnModules
        }
    }

    /**
     * 通用设置对象属性
     *
     * @param objKey   比较目标key
     * @param value    要设置目标的value
     * @param key      被遍历的对象的key
     * @param property 被遍历的对象属性
     * @param object   被遍历的对象
     * @throws InvocationTargetException 调用目标异常
     * @throws IllegalAccessException    非法访问
     */
    @Throws(InvocationTargetException::class, IllegalAccessException::class)
    private fun invokeSetter(objKey: String, value: Any, key: String, property: PropertyDescriptor, `object`: Any) {

        if (StringUtils.equals(objKey, key)) {
            val getter = property.readMethod
            if (getter.invoke(`object`) == null) {
                val setter = property.writeMethod
                setter.invoke(`object`, value)
            }
        }
    }

    /**
     * 设置基本信息
     * 创建者，创建时间，修改者，修改时间，UUID
     *
     * @param object 用户对象
     */
    fun baseInfo(`object`: Any) {
        try {
            val userId = "" // UserUtil.getUser().getId()
            val deleted = false
            val beanInfo = Introspector.getBeanInfo(`object`.javaClass)
            val propertyDescriptors = beanInfo.propertyDescriptors
            for (property in propertyDescriptors) {
                val key = property.name
                if ("class" == key) {
                    continue
                }
                invokeSetter("id", id, key, property, `object`)
                invokeSetter("creatorId", userId, key, property, `object`)
                invokeSetter("createTime", datetime, key, property, `object`)
                invokeSetter("modifierId", userId, key, property, `object`)
                invokeSetter("modifyTime", datetime, key, property, `object`)
                invokeSetter("deleted", deleted, key, property, `object`)
            }
        } catch (e: IllegalAccessException) {
            logger.error("设置基本信息baseInfo异常", e)
        } catch (e: IntrospectionException) {
            logger.error("设置基本信息baseInfo异常", e)
        } catch (e: InvocationTargetException) {
            logger.error("设置基本信息baseInfo异常", e)
        }

    }
}
