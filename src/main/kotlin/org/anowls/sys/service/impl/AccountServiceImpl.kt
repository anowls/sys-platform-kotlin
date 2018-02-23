package org.anowls.sys.service.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.anowls.sys.common.CommonUtils
import org.anowls.sys.common.PageQuery
import org.anowls.sys.common.SimplePage
import org.anowls.sys.common.message.SimpleMessage
import org.anowls.sys.domain.*
import org.anowls.sys.domain.ext.AccountInfo
import org.anowls.sys.domain.ext.PasswordInfo
import org.anowls.sys.domain.view.AccountVO
import org.anowls.sys.mapper.*
import org.anowls.sys.service.AccountOrgService
import org.anowls.sys.service.AccountRoleService
import org.anowls.sys.service.AccountService
import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import tk.mybatis.mapper.entity.Example
import java.util.*

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 用户信息-业务逻辑实现</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@Service
@Validated
class AccountServiceImpl(
        @Autowired private val accountMapper: AccountMapper,
        @Autowired private val accountOrgMapper: AccountOrgMapper,
        @Autowired private val accountRoleMapper: AccountRoleMapper,
        @Autowired private val organizationMapper: OrganizationMapper,
        @Autowired private val roleMapper: RoleMapper,
        @Autowired private val applicationMapper: ApplicationMapper,
        @Autowired private val featureMapper: FeatureMapper,
        @Autowired private val menuMapper: MenuMapper,
        @Autowired private val sysEnumItemMapper: SysEnumItemMapper,
        @Autowired private val configurationMapper: ConfigurationMapper,
        @Autowired private val accountOrgService: AccountOrgService,
        @Autowired private val accountRoleService: AccountRoleService
) : AccountService {

    private var config: MutableMap<*, *>? = null

    companion object {

        private val logger = LoggerFactory.getLogger(AccountServiceImpl::class.java)

        private val ENCRYPTION_NUMBER = "ENCRYPTION_NUMBER"
        private val INITIAL_PASSWORD = "INITIAL_PASSWORD"


        /**
         * 密云云盘appid
         */
        private val APPID = "gohigh"

        /**
         * 密云云盘appkey
         */
        private val APPKEY = "B9V2aC59"

        /**
         * 密云云盘api 方法
         */
        private val METHOD = "createuser"
    }

    override fun modifyPassword(passwordInfo: PasswordInfo): SimpleMessage {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateStatus(id: String, status: Boolean): SimpleMessage {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: String): SimpleMessage {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkAccountInfo(accountInfo: AccountInfo): SimpleMessage {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryAccountInfo(roleTypeId: String, accountId: String, appLevel: Int): AccountVO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryRoleType(accountId: String, scope: Int?): List<SysEnumItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resetAccount(accountId: String): SimpleMessage {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun findByIdIn(ids: List<String>): List<Account> {
        if (CollectionUtils.isEmpty(ids)) {
            logger.error("传入的用户ids集合为空")
            return emptyList()
        }
        return accountMapper.findByIdIn(ids)
    }


    /**
     * 分页查询账户信息
     *
     * @param pageQuery 查询条件
     * @return 分页结果
     */
    override fun query(pageQuery: PageQuery): SimplePage<AccountVO> {
        val filter = pageQuery.convertFilterToMap()
        if (Objects.nonNull(filter["orgId"])) {
            val org = organizationMapper.getById(filter["orgId"].toString())
            filter.plus(Pair("orgPathCode", org?.orgCodePath))
        }
        filter.plus(Pair("userId", "")) // todo UserUtil.getUser().id)
        filter.plus(Pair("isAdmin", "")) // todo UserUtil.getUser().getUserType())
        PageHelper.startPage<AccountVO>(pageQuery.page, pageQuery.size, pageQuery.convertSort())
        val list = accountMapper.findAll(filter)
        setAccountInfo(list)
        return SimplePage.convert<AccountVO>(PageInfo(list))
    }

    /**
     * 分页结果进行机构名称和角色名称封装
     *
     * @param list 用户列表
     */
    private fun setAccountInfo(accounts: List<AccountVO>) {
        val accountIds = accounts.map { it.id }
        if (CollectionUtils.isNotEmpty(accountIds)) {
            val orgMap = accountOrgService.getAccountOrgMap(accountIds)
            val roleMap = accountRoleService.getAccountRoleMap(accountIds)
//            val userRoles = accountRoleMapper.getAccountRoleMap(accountIds)
//            for (account in accounts) {
//                val stringBuilder = StringBuilder()
//                userRoles.forEach {
//                    if (StringUtils.equals(account.id, it.accountId) && it.defaultRole) {
//                        stringBuilder.append("、").append(it.roleName)
//                    }
//                }
//            }
            accounts.forEach { t ->
                if (orgMap.containsKey(t.id)) {
                    t.orgConcatName = orgMap[t.id].toString()
                }
                if (roleMap.containsKey(t.id)) {
                    t.roleConcatName = roleMap[t.id].toString()
                }
            }
        }
    }

    /**
     * 获取用户业务信息
     *
     * @param map 用户id、用户名、登录名、唯一标识
     * @return 用户扩展信息
     */
    fun getUserInfoById(filter: Map<*, *>): AccountVO? {
        val accountVO = accountMapper.findByCondition(filter)
        getUserInfoDetail(accountVO!!)
        return accountVO
    }

    /**
     * 获取用户详细信息，用户对应学校、校区、默认组织机构、角色分类列表、角色列表
     *
     * @param user User对象
     */
    private fun getUserInfoDetail(accountVO: AccountVO) {
        val org = organizationMapper.getDefaultListByUserId(accountVO.id)
        if (CollectionUtils.isNotEmpty(org)) {
            accountVO.organization = org[0]
        }
        val roles = roleMapper.getByUserId(accountVO.id)
        if (CollectionUtils.isNotEmpty(roles)) {
            val role = roles.mapNotNull {
                if (it.roleDefault) it else null
            }.first()
            accountVO.defaultRole = role
        }
        val orgMap = accountOrgService.getAccountOrgMap(listOf(accountVO.id))
        val roleMap = accountRoleService.getAccountRoleMap(listOf(accountVO.id))
        if (roleMap.containsKey(accountVO.id)) {
            accountVO.roleConcatName = roleMap[accountVO.id].toString()
        }
        if (orgMap.containsKey(accountVO.id)) {
            accountVO.orgConcatName = orgMap[accountVO.id].toString()
        }
    }

    /**
     * 通过用id 获取默认的组织机构信息
     *
     * @param userId 用户id
     * @return 默认的组织机构信息
     */
    private fun getOrganizationById(userId: String): Organization? {
        return organizationMapper.getDefaultByUserId(userId)
    }

    /**
     * 获取用户业务信息
     *
     * @param map 用户id、用户名、登录名、唯一标识
     * @return 用户扩展信息
     */
    fun getUserViewById(map: Map<*, *>): AccountVO? {
        val accountView = accountMapper.findByCondition(map)
        if (null != accountView) {
            getUserDetail(accountView)
        }
        return accountView
    }

    /**
     * 获取用户详细信息，用户对应学校、校区、默认组织机构、角色分类列表、角色列表
     *
     * @param user User对象
     */
    private fun getUserDetail(user: AccountVO) {
        val userId = user.id
//        if (StringUtils.isNotEmpty(user.getSchoolId())) {
//            user.setSchool(this.getSchoolById(user.getSchoolId()))
//        }
//        if (StringUtils.isNotEmpty(user.getCampusId())) {
//            user.setCampus(this.getCampusById(user.getCampusId()))
//        }
//        val org = this.getOrganizationById(userId)
//        if (null != org) {
//            user.setOrganization(org)
//        }
//        val types = this.getRoleCategoryByUserId(userId)
//        user.setCategories(if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(types)) types else ArrayList<E>())
//        val roles = this.getRoleByUserId(userId)
//        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(roles)) {
//            user.setRoles(roles)
//            val defaultRoles = roles.stream().filter(Predicate<Role> { Role.getRoleDefault() }).collect(Collectors.toList<Any>())
//            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(defaultRoles)) {
//                val role = defaultRoles[0]
//                user.setDefaultRole(role)
//                user.setRoleCode(role.getRoleCode())
//                user.setShortCode(role.shortCode)
//                user.setRoleTypeName(role.getRoleTypeName())
//            }
//        }
    }

    /**
     * 初始化用户密码配置
     */
    private fun initConfig() {
//        config = HashedMap()
//        config[ENCRYPTION_NUMBER] = "1"
//        config[INITIAL_PASSWORD] = "Abcd1234"
//        val c1 = configurationMapper.getByScKey(ENCRYPTION_NUMBER)
//        val c2 = configurationMapper.getByScKey(INITIAL_PASSWORD)
//
//        if (c1 != null) {
//            config[ENCRYPTION_NUMBER] = c1.getScValue()
//        }
//        if (c2 != null) {
//            config[INITIAL_PASSWORD] = c2.getScValue()
//        }
    }

    /**
     * 检查密码信息
     *
     * @param info        密码对象
     * @param accountInfo 用户对象
     * @return 检查结果
     */
    private fun checkPassWord(info: PasswordInfo, accountInfo: Account): String {
        if (StringUtils.isEmpty(info.loginName)) {
            return "操作失败，请输入账号！"
        }
        val logoName = CommonUtils.getMap("logonName", info.loginName!!)
        if (accountInfo.initType != null && accountInfo.initType == 1 && !info.loginName.equals(accountInfo.loginName)) {
            return "操作失败，账号已初始化！"
        }
        val logonNameAccount = accountMapper.findByCondition(logoName)
        if (logonNameAccount != null && logonNameAccount.id != accountInfo.id) {
            return "操作失败，账号已存在！"
        }
        if (config == null) {
            initConfig()
        }
        return ""
    }

    /**
     * 设置密码信息
     *
     * @param info        密码信息
     * @param accountInfo 用户信息
     * @return 操作结果
     */
    private fun setPasswordInfo(info: PasswordInfo, accountInfo: Account): String {
//        val encoder = Md5PasswordEncoder()
//        encoder.setIterations(Integer.parseInt(config[ENCRYPTION_NUMBER].toString()))
//        val password = encoder.encodePassword(info.getOriginalPass(), accountInfo.loginName)
//        if (!org.apache.commons.lang.StringUtils.equals(accountInfo.getPasswordVerify(), password)) {
//            return "操作失败，原密码输入有误！"
//        }
//        accountInfo.setLogonName(info.getLoginName())
//        val newPassword = encoder.encodePassword(info.getNewCheckPass(), accountInfo.loginName)
//        accountInfo.setPasswordVerify(newPassword)
//        if (accountInfo.getInitType() != null && accountInfo.getInitType() === 0) {
//            accountInfo.setInitType(1)
//            sendToYunPan(password, accountInfo.loginName)
//        }
        return ""
    }

    /**
     * 修改用户信息
     *
     * @param info 用户信息
     * @return 操作结果
     */
    @Transactional(rollbackFor = arrayOf(Exception::class))
    fun resetPassWord(info: PasswordInfo): SimpleMessage {
        val userId = if (StringUtils.isNotEmpty(info.accountId)) info.accountId else "" // todo UserUtil.getUser().id
        val accountInfo = accountMapper.selectByPrimaryKey(userId)
        var errorMessage = checkPassWord(info, accountInfo)
        if (StringUtils.isNotEmpty(errorMessage)) {
            return SimpleMessage.failure(errorMessage)
        }
        errorMessage = setPasswordInfo(info, accountInfo)
        if (StringUtils.isNotEmpty(errorMessage)) {
            return SimpleMessage.failure(errorMessage)
        }
        accountMapper.updateByPrimaryKeySelective(accountInfo)

        //todo 这里要调用档案的重设用户信息的接口 CommonUtils.getRpcBasicInfoService().resetBasicInfo(busExt);
        return SimpleMessage.info("保存成功！")
    }

    /**
     * 发送到云盘
     *
     * @param password  密码
     * @param logonName 登录名
     */
    private fun sendToYunPan(password: String, logonName: String) {
//        try {
//            //调用云盘api方法创建用户
//            val jsonObject = JSONObject()
//            jsonObject.put("password", password)
//            jsonObject.put("loginName", logonName)
//            jsonObject.put("type", 3)
//            val data = StringBuffer()
//            data.append(APPID).append(METHOD).append(jsonObject.toString())
//            val sign = calcShaHash(data.toString())
//            val signDe = URLEncoder.encode(sign, "utf-8")
//            //http 请求
//            val url = "http://pan.myedu.gov.cn:9998/v1/third?method=$METHOD&appid=$APPID&sign=$signDe"
//            ConnectUtil.postJson(url, jsonObject.toString())
//        } catch (e: IOException) {
//            logger.error("发送失败", e)
//        }

    }

    /**
     * 用户加密
     *
     * @param data 加密数据
     * @return 加密结果
     */
    private fun calcShaHash(data: String): String? {
        val hmacSha1Algorithm = "HmacSHA1"
        var result: String? = null
//        try {
//            val signingKey = SecretKeySpec(APPKEY.toByteArray(charset("UTF-8")), hmacSha1Algorithm)
//            val mac = Mac.getInstance(hmacSha1Algorithm)
//            mac.init(signingKey)
//            val rawHmac = mac.doFinal(data.toByteArray(charset("UTF-8")))
////    todo        result = org.apache.commons.codec.binary.Base64.encodeBase64String(rawHmac)
//        } catch (e: NoSuchAlgorithmException) {
//            logger.error("加密算法异常", e)
//        } catch (e: InvalidKeyException) {
//            logger.error("加密算法异常", e)
//        } catch (e: UnsupportedEncodingException) {
//            logger.error("加密算法异常", e)
//        }

        return result
    }

    /**
     * 获取账号信息，根据登录名、身份证、系统编码分别进行获取，只要查询有结果就返回
     *
     * @param logonName  登录名
     * @param idCard     身份证
     * @param systemCode 系统编码
     * @return 用户信息
     */
    private fun getExistAccount(loginName: String, idCard: String, systemCode: String): AccountVO? {
        var accountVO: AccountVO? = null
        val map = CommonUtils.getMap("systemCode", systemCode)
        //检查通过登录名是否能够在系统中查询到用户信息
        accountVO = getUserInfoById(map)
        if (accountVO != null) {
            //存在的话 就加入到用户数组中，主要是为了编辑用
            return accountVO
        }
        map - "systemCode"
        map + Pair("loginName", loginName)
        //检查通过登录名是否能够在系统中查询到用户信息
        accountVO = getUserInfoById(map)
        if (accountVO != null) {
            //存在的话 就加入到用户数组中，主要是为了编辑用
            return accountVO
        }
        map.minus("loginName")
        map.plus(Pair("idCard", idCard))
        //通过登录名没能够在系统中查询到账号信息，那么就根据身份证去查询一下
        accountVO = getUserInfoById(map)
        return accountVO
    }

    /**
     * 修改机构信息状态（子机构中如有启动状态，则不能停用）
     *
     * @param accounts     用户列表
     * @param isDefaultOrg 是否批量设置当前用户的机构为默认
     * @return 操作结果
     */
    @Transactional(rollbackFor = [Exception::class])
    @Throws(Exception::class)
    fun insertAccount(accounts: List<AccountInfo>, isDefaultOrg: Boolean): SimpleMessage {
        if (CollectionUtils.isEmpty(accounts)) {
            SimpleMessage.failure("传入的用户列表为空，操作失败！")
        }
        val returnList = emptyList<AccountVO>()

        //遍历传入进来的用户对象，检查新增和修改部分
        for (account in accounts) {
            //用户进来，有限判断是否有身份证，没有则返回
            if (StringUtils.isEmpty(account.idCard)) {
                return SimpleMessage.failure("传入的用户证件号码不能为空，操作失败！")
            }
            val existAccount = getExistAccount(account.loginName!!, account.idCard!!, account.systemCode!!)
            //通过身份证查询出有信息，则加入到用户数组中，编辑用
            if (existAccount != null) {
                returnList.plus(existAccount)
            } else {
                addAccount(account, isDefaultOrg, returnList)
            }
        }
        return SimpleMessage.info(returnList)
    }

    /**
     * 插入新的账号，在身份证和登录名都没有查询到账户时
     *
     * @param account      传入的账号信息
     * @param isDefaultOrg 是否为默认组织机构
     * @param returnList   响应返回用户列表
     */
    @Throws(Exception::class)
    private fun addAccount(account: AccountInfo, isDefaultOrg: Boolean, returnList: List<AccountVO>) {
        var accountInfo: AccountInfo? = null
        //如果没有主键 生成一个
        if (StringUtils.isEmpty(account.id)) {
            account.id = CommonUtils.id
        }
        //执行保存账号的通用逻辑
        accountInfo = saveAccountInfo(account, true, isDefaultOrg)
        returnList + accountInfo
    }

    /**
     * 更新用户装填
     *
     * @param id     用户id
     * @param status 状态值
     * @return 操作结果
     */
    @Transactional(rollbackFor = [Exception::class])
    fun updateAccountStatus(id: String, status: Boolean): SimpleMessage {
        val user = accountMapper.selectByPrimaryKey(id)
        user.modifyTime = CommonUtils.datetime
        user.modifierId = "" // todo UserUtil.getUser().id
        user.status = if (status) 1 else 0
        accountMapper.updateByPrimaryKeySelective(user)
        return SimpleMessage.info("更新成功！")
    }

    /**
     * 删除账号信息
     *
     * @param id 主键id
     * @return 操作结果
     */
    @Transactional(rollbackFor = arrayOf(Exception::class))
    fun deleteAccountInfo(id: String): SimpleMessage {
        val account = accountMapper.selectByPrimaryKey(id)
        if (account != null) {
            account.modifierId = "" // todo UserUtil.getUser().id
            account.deleted = true
            account.modifyTime = CommonUtils.datetime
            if (accountMapper.updateByPrimaryKeySelective(account) > 0) {
//                userOrgService.deleteByUserIds(Arrays.asList<T>(id))
//                userRoleService.deleteRoleUser(Arrays.asList<T>(id))
                return SimpleMessage.info("删除成功！")
            } else {
                return SimpleMessage.warning("删除失败！")
            }
        }
        return SimpleMessage.warning("操作失败，账号不存在！")
    }

    /**
     * 更新用户基本信息
     *
     * @param account 用户基本信息
     * @return 更新消息
     */
    @Transactional(rollbackFor = arrayOf(Exception::class))
    override fun updateAccount(account: Account): SimpleMessage {
        accountMapper.updateByPrimaryKeySelective(account)
        return SimpleMessage.info("修改成功！")
    }

    /**
     * 新增时获取初始密码
     *
     * @param logonName 登录名
     * @return 加密后的密码
     */
    private fun getInitPassword(logonName: String): String {
//        val encoder = Md5PasswordEncoder()
//        encoder.setIterations(Integer.parseInt(config[ENCRYPTION_NUMBER].toString()))
//        return encoder.encodePassword(config[INITIAL_PASSWORD].toString(), logonName)
        return ""
    }

    /**
     * 预判用户角色代码
     *
     * @param accountInfo 用户信息
     */
    private fun predictRoleCode(accountInfo: AccountInfo) {
        var shortCodes = accountInfo.shortCodes
        if (CollectionUtils.isNotEmpty(shortCodes) || StringUtils.isNotEmpty(accountInfo.shortCode)) {
            if (CollectionUtils.isEmpty(shortCodes)) {
                shortCodes = listOf(accountInfo.shortCode!!)
                accountInfo.shortCodes = shortCodes
            }
//            val roleParam = RoleParam()
//            roleParam.setDeleted(false)
//            roleParam.setShortCodes(shortCodes)
//            roleParam.setRoleDefault(true)
//            val defaultRoles = roleMapper.getRolesByParam(roleParam)
//            accountInfo.setDefaultRoles(defaultRoles)
        }
    }

    /**
     * 用户新增模式
     *
     * @param accountInfo 用户信息
     * @param orgId       机构id
     * @throws Exception 异常信息
     */
    @Throws(Exception::class)
    fun added(accountInfo: AccountInfo, orgId: String) {
        accountInfo.orgId = accountInfo.orgId
        val byLoginNames = accountMapper.getByLoginNames(listOf(accountInfo.loginName!!))
        if (CollectionUtils.isNotEmpty(byLoginNames)) {
            throw Exception("登录账号已存在！")
        }
        val byIdCards = accountMapper.getByIdCards(listOf(accountInfo.idCard!!))
        if (CollectionUtils.isNotEmpty(byIdCards)) {
            throw Exception("唯一身份编号已存在！")
        }
        if (StringUtils.isEmpty(accountInfo.systemCode)) {
            val systemCode = getSystemCode(accountInfo)
            if (StringUtils.isEmpty(systemCode)) {
                throw Exception("默认角色不存在！")
            }
            accountInfo.systemCode = systemCode
        }
        if (accountInfo.userType == null) {
            accountInfo.userType = false
        }
        accountInfo.theme = "default"
        accountInfo.passwordVerify = getInitPassword(accountInfo.loginName!!)
        accountInfo.createTime = CommonUtils.datetime
        accountInfo.creatorId = ""// todo UserUtil.getUser().id
        accountInfo.status = 1
        if (StringUtils.isEmpty(accountInfo.id)) {
            accountInfo.id = CommonUtils.id
        }
        accountMapper.insertSelective(accountInfo)
    }

    /**
     * 修改模式
     *
     * @param isDefaultOrg 是否默认机构
     * @param accountInfo  用户对象
     * @param orgId        机构id
     */
    fun modify(isDefaultOrg: Boolean, accountInfo: AccountInfo, orgId: String) {
        //需要覆盖当前机构
//        if (isDefaultOrg) {
//            val userOrg = userOrgMapper.getDefaultOrgByUserId(accountInfo.id)
//            if (userOrg != null && !StringUtils.equals(userOrg.orgId, orgId)) {
//                //覆盖机构和学校、校区信息
//                setBasicOrgInfo(accountInfo, orgId)
//                val resetUser = accountMapper.selectByPrimaryKey(accountInfo.id)
//                //取消当前用户的机构id
//                userOrgMapper.updateDefaultBatch(resetUser.orgId, Arrays.asList(resetUser.id))
//            }
//        }
        accountInfo.modifyTime = CommonUtils.datetime
        accountInfo.modifierId = "" // todo serUtil.getUser().id
        accountMapper.updateByPrimaryKeySelective(accountInfo)
        //todo 这里要调用档案模块 重置档案信息 CommonUtils.getRpcBasicInfoService().resetBasicInfo(accountInfo);
    }

    /**
     * 检查用户信息是否唯一
     *
     * @param accountInfo 用户信息
     * @return 检查结果
     */
    fun checkUserInfo(accountInfo: AccountInfo): String {
        val messageList = accountMapper.checkNameAndIdCard(accountInfo.loginName!!, accountInfo.idCard!!, accountInfo.id)
        return if (CollectionUtils.isNotEmpty(messageList)) {
            StringUtils.join(messageList, ";")
        } else ""
    }

    /**
     * 保存账号信息
     *
     * @param accountInfo  账号信息
     * @param isForeign    是否来自于外部 例如档案模块
     * @param isDefaultOrg 是否设置当前用户的机构为默认
     * @return 账号对象
     * @throws Exception 异常抛出
     */
    @Transactional(rollbackFor = [Exception::class])
    @Throws(Exception::class)
    override fun saveAccountInfo(accountInfo: AccountInfo, isForeign: Boolean, isDefaultOrg: Boolean): AccountInfo {
        if (config == null) {
            initConfig()
        }
        if (StringUtils.isEmpty(accountInfo.orgId)) {
            throw Exception("保存失败，组织机构id为空")
        }
        val orgId = if (StringUtils.isNotEmpty(accountInfo.orgId)) accountInfo.orgId else accountInfo.organization!!.id
        //预判用户角色代码
        predictRoleCode(accountInfo)
        if (accountMapper.selectByPrimaryKey(accountInfo.id) == null) {
            //执行新增
            added(accountInfo, orgId!!)
        } else {
            //执行修改
            modify(isDefaultOrg, accountInfo, orgId!!)
        }
        if (isForeign!!) {
            //外部传入用户角色处理
            handleIsForeign(accountInfo)
        }
        //处理组织机构的关系
        val message = saveAccountOrg(accountInfo, isDefaultOrg, orgId)
        if (message.code != 0) {
            throw Exception(message.message)
        }
        val userIds = Arrays.asList(accountInfo.id)
        return getAccountBusExt(CommonUtils.getMap("logonName", accountInfo.loginName!!))
    }

    /**
     * 根据角色简码获取用角色类型
     *
     * @param shortCodes 角色类型简码数组
     * @return 角色类型数组
     */
    private fun getCategoryList(shortCodes: List<String>): List<SysEnumItem> {
        val categoryExample = Example(SysEnumItem::class.java)
        categoryExample.createCriteria()
                .andIn("shortCode", shortCodes)
                .andEqualTo("deleted", 0)
        return sysEnumItemMapper.selectByExample(categoryExample)
    }

    /**
     * 处理外部传入用户角色处理部分
     *
     * @param accountInfo 用户信息
     * @throws Exception 异常信息
     */
    @Throws(Exception::class)
    private fun handleIsForeign(accountInfo: AccountInfo) {
        //处理用户角色分类简码 根据简码插入教师、学生、家长
        val categoryList = getCategoryList(accountInfo.shortCodes!!)
        if (CollectionUtils.isEmpty(categoryList)) {
            throw Exception(String.format("传入的分类代码【%s】错误，在系统中未找到！", accountInfo.shortCode))
        }
        var defaultRoles = accountInfo.defaultRoles
        if (CollectionUtils.isEmpty(defaultRoles)) {
            if (Objects.isNull(accountInfo.defaultRole)) {
                throw Exception("传入的角色代码，在系统中未找到！")
            }
            defaultRoles = listOf(accountInfo.defaultRole!!)
        }

        handleAccountRole(defaultRoles!!.distinct(), accountInfo.id)
    }

    /**
     * 处理用户与角色对应关系
     *
     * @param defaultRoles 迷人角色集合
     * @param userId       用户id
     */
    private fun handleAccountRole(defaultRoles: List<Role>, accountId: String) {
        val roleExample = Example(AccountRole::class.java)
        roleExample.createCriteria().andEqualTo("userId", accountId)
        accountRoleMapper.deleteByExample(roleExample)

        val userRoles = defaultRoles.map { s ->
            val userRole = AccountRole()
            userRole.id = CommonUtils.id
            userRole.accountId = accountId
            userRole.roleId = s.id
            userRole.createTime = CommonUtils.datetime
            userRole.creatorId = "" // todo UserUtil.getUser().id
            userRole
        }
        accountRoleMapper.insertAccountRoles(userRoles)
    }

    /**
     * 根据传入用户信息，生成用户系统编码
     *
     * @param accountInfo 用户信息
     * @return 系统编码值
     */
    fun getSystemCode(accountInfo: AccountInfo): String {
        if (StringUtils.isEmpty(accountInfo.shortCode)) {
            return ""
        }
        var maxNumber = accountMapper.maxWorkId()
        if (maxNumber == null) {
            maxNumber = 0L
        }
        maxNumber++
        return accountInfo.shortCode + maxNumber
    }

    /**
     * 重设用户机构信息
     *
     * @param accountInfo 用户信息
     * @param orgId       机构信息
     * @return 操作结果
     */
    private fun saveAccountOrg(accountInfo: AccountInfo, isDefaultOrg: Boolean, orgId: String): SimpleMessage {
        accountOrgMapper.deleteOrgUser(Arrays.asList(accountInfo.id), orgId, "", CommonUtils.datetime)
        val accountOrg = AccountOrg()
        accountOrg.orgId = orgId
        accountOrg.accountId = accountInfo.id
        accountOrg.orgDefault = isDefaultOrg
        accountOrgMapper.insertAccountOrg(listOf(accountOrg))
        return SimpleMessage.info("")
    }

    /**
     * 重置用户与机构的关系
     *
     * @param userOrg 用户与机构对应关系
     * @return 操作结果
     */
    @Transactional(rollbackFor = arrayOf(Exception::class))
    fun resetUserOrg(accountOrg: AccountOrg): SimpleMessage {
        val accountId = accountOrg.accountId
        val orgId = accountOrg.orgId
        val account = accountMapper.selectByPrimaryKey(accountId)
        account.orgId = orgId
        accountMapper.updateByPrimaryKeySelective(account)
        val info = AccountInfo()
        BeanUtils.copyProperties(account, info)
        saveAccountOrg(info, true, orgId!!)
        return SimpleMessage.info("保存成功！")
    }

    /**
     * 获取用户扩展信息-维护类
     *
     * @param map 查询参数
     * @return 用户扩展信息
     */
    private fun getAccountBusExt(map: Map<*, *>): AccountInfo {
        val nameAccountBus = AccountInfo()
        BeanUtils.copyProperties(nameAccountBus, getUserInfoById(map))
        return nameAccountBus
    }

    /**
     * 根据登录名获取帐号相关信息
     *
     * @param roleTypeId 角色分类ID
     * @param userId     用户ID
     * @param appLevel   平台类型（1：运维平台，2：业务系统）
     * @return 账号相关信息
     */
    fun getAccountExt(roleTypeId: String, userId: String, appLevel: Int?): AccountVO {
        //region 应用 模块 功能 菜单
        val account = getUserViewById(CommonUtils.getMap("id", userId))
//        val collect = account?.categories.filter { it.cate().equals(appLevel) }
//        account.categories = collect
        //region 应用 模块 功能 菜单
//            applicationService.getApplicationDetail(user, roleTypeId, userId, appLevel)
        return account!!
    }

    /**
     * 获取用户所在默认机构的等级和角色
     *
     * @param userId 用户id
     * @return 机构等级和角色
     */
    fun getUserOrgRole(accountId: String): Map<*, *> {
        val accountVO = AccountVO()
        accountVO.id = accountId
        getUserDetail(accountVO)
        val map = emptyMap<String, Any>()
//            map.plus(Pair("roleTypes", accountVO.getCategories()))
//            map.plus(Pair("orgGrade", accountVO.organization.getOrgGrade()))
        map.plus(Pair("orgId", accountVO.organization!!.id))
//            map.plus(Pair("orgName", accountVO.organization.getOrgName()))
        return map
    }

    /**
     * 根据用户名和密码校验是否匹配，如果匹配则返回该用户的信息
     *
     * @param loginName 用户名
     * @param password  用户密码
     * @return 校验结果
     */
    override fun valid(loginName: String, password: String): SimpleMessage {
//            val passwordVerify = encodePassword(logonName, password)
        val example = Example(Account::class.java)
        example.createCriteria().andEqualTo("loginName", loginName)
//                    .andEqualTo("passwordVerify", passwordVerify)
                .andEqualTo("deleted", 0)
        val accounts = accountMapper.selectByExample(example)
        return if (CollectionUtils.isNotEmpty(accounts)) {
            SimpleMessage.info(accounts[0])
        } else SimpleMessage.warning("用户不存在或密码错误！")
    }

    /**
     * 获取加密后的密文
     *
     * @param logonName 登录名
     * @param password  密码
     * @return 获取加密后的密文
     */
    fun encodePassword(logonName: String, password: String): String {
//        val encoder = Md5PasswordEncoder()
//        encoder.setIterations(Integer.parseInt("1"))
//        return encoder.encodePassword(password, logonName)
        return ""
    }

}
