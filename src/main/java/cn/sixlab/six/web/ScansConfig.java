package cn.sixlab.six.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("tech.minesoft.mine.site.core.mapper")
@MapperScan("cn.sixlab.six.web.mapper")
@ComponentScan("tech.minesoft.mine.site")
@Configuration
public class ScansConfig {
}
