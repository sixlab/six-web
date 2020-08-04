package cn.sixlab.six.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("tech.minesoft.minesite.core.mapper")
@MapperScan("cn.sixlab.six.web.mapper")
@ComponentScan("tech.minesoft.minesite")
@Configuration
public class ScansConfig {
}
