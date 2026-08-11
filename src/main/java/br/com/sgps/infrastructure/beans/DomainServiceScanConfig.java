package br.com.sgps.infrastructure.beans;

import br.com.sgps.shared.domain.annotation.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Config para que o spring reconheca as classes anotas com o @DomainService
 * possam ser injetadas pelo spring na camada de application
 */
@Configuration
@ComponentScan(
        basePackages = "br.com.sgps",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = DomainService.class
        )
)
public class DomainServiceScanConfig {
}
