package com.flexpag.paymentscheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
        		.info(new Info()
        				.title("Desafio BackEnd da FlexPag")
        				.description(" A Flexpag é uma empresa de tecnologia especializada em soluções digitais de pagamento, "
        						+ "com atuação nas principais distribuidoras de energia, gás natural, água e saneamento do país."
        						+ "O desafio backend consiste na implementação de um serviço de pagamento agendando.")
        				.termsOfService("")
        				.license(new License().name("Apache 2.0")
        						.url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
    
}
