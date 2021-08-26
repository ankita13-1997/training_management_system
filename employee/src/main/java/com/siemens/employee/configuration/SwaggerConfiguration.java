package com.siemens.employee.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static java.util.Collections.singletonList;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String SWAGGER_API_VERSION= "v1";

    private static final String SWAGGER_TITLE="Employee Application API's";

    private static final String SWAGGER_DESCRIPTION="Restful Api's for QManager Application";


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .version(SWAGGER_API_VERSION)
                .title(SWAGGER_TITLE)
                .description(SWAGGER_DESCRIPTION)
                .build();
    }
    @Bean
    public Docket customSwaggerApi() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.siemens.employee"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .globalOperationParameters(singletonList(
                        new ParameterBuilder()
                                .name("Authorization")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .hidden(true)
                                .defaultValue("Bearer ")
                                .build()
                        )
                );
    }


}
