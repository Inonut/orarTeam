package team.orar.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {
        "team.orar.**.service",
        "team.orar.**.dao"
})
public class MainContextConfig {
}
