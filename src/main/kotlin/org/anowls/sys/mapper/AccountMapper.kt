package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.Account
import org.anowls.sys.domain.view.AccountVO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

/**
 * @author YCJ
 * @date 2017-11-03
 * Copyright Hanboard
 * Created by YCJ on 2017-11-03
 * 用户信息-数据访问层
 */
@Mapper
@Repository
interface AccountMapper : BaseMapper<Account> {

    /**
     *  查询系统中最大的 workId
     *
     * @return workId
     */
    @Select("select max(work_id) from sys_account")
    fun maxWorkId(): Long?

    /**
     * 根据查询条件查询账户
     *
     * @param filter 查询条件
     * @return 账户列表
     */
    fun findAll(filter: Map<*, *>): List<AccountVO>

    /**
     *  根据账户id集合查询账户
     *
     * @param ids 账户id集合
     * @return 账户列表
     */
    fun findByIdIn(ids: List<String>): List<Account>

    /**
     * 根据登录名获取用户id、登录名、系统编码、身份证唯一标识获取用户信息
     *
     * @param filter 用户id、登录名、系统编码、身份证唯一标识
     * @return 用户信息
     */
    fun findByCondition(filter: Map<*, *>): AccountVO?

    /**
     * 根据多个登录名获取用户列表
     *
     * @param loginNames 登录名集合
     * @return 用户列表信息
     */
    fun getByLoginNames(loginNames: List<String>): List<AccountVO>

    /**
     * 根据多个 系统编码获取用户列表
     *
     * @param sysCodes 系统编码集合
     * @return 用户列表信息
     */
    fun getBySysCodes(sysCodes: List<String>): List<AccountVO>

    /**
     * 根据多个身份证唯一标识获取用户列表
     *
     * @param idCards 身份证集合
     * @return 用户列表信息
     */
    fun getByIdCards(idCards: List<String>): List<AccountVO>

    /**
     * 根据身份证号判断是否存在交叉 即身份证号 在登录账号那一列是否存在
     *
     * @param logonName 登录账号
     * @param idCard 身份证号
     * @param id 主键id
     * @return 统计数
     */
    fun checkNameAndIdCard(@Param("logonName") logonName: String, @Param("idCard") idCard: String, @Param("id") id: String): List<String>

}
