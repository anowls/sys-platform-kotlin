package org.anowls.sys.config;

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * <p>Title: sys_platform</p>
 * <p>Description: Swagger2 配置</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@Configuration
@EnableSwagger2
open class Swagger2Configuration {

    @Bean
    open fun  createRestApi() : Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.anowls.sys"))
                .paths(PathSelectors.any())
                .build();

    }

    private fun apiInfo() : ApiInfo {
        return ApiInfoBuilder()
                .title("管理平台 服务 RESTful APIs")
                .description("使用 Swagger2 构建 RESTful APIs 文档")
                .contact(Contact("管理平台", "", "hppstv9538@gmail.com"))
                .version("1.0")
                .build()
    }

}
