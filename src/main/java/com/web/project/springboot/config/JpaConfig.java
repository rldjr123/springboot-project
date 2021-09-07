package com.web.project.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 1개 이상 Bean을 등록하고 있음을 명시하는 어노테이션
@Configuration
@EnableJpaAuditing //Jpa Auditing 활성화
public class JpaConfig {
}
