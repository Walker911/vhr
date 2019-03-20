package org.sang.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.sang.common.DateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.*;

/**
 * @author sang
 * @date 2018/1/26
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }

    @Bean
    public ExecutorService executorService() {
        // guava
        ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("email-pool-%d").build();
        // 自定义线程池
        return new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(1024), factory, new ThreadPoolExecutor.AbortPolicy());
    }
}
