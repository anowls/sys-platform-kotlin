package org.anowls.sys.config;

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.text.DateFormat

/**
 * <p>Title: sys_platform</p>
 * <p>Description: Jackson 配置</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@Configuration
open class WebMvcConfiguration : WebMvcConfigurerAdapter() {

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val objectMapper = Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .dateFormat(DateFormat.getDateInstance()).build<ObjectMapper>()

        converters.add(0, MappingJackson2HttpMessageConverter(objectMapper))
        super.configureMessageConverters(converters)
    }

}
