package org.sang.config;

import org.sang.bean.RespBean;
import org.sang.common.HrUtils;
import org.sang.handler.AuthenticationAccessDeniedHandler;
import org.sang.handler.FailureHandler;
import org.sang.handler.LogoutHandlerImpl;
import org.sang.service.HrService;
import org.sang.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 *
 * @author sang
 * @date 2017/12/28
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final HrService hrService;
    private final CustomMetadataSource metadataSource;
    private final UrlAccessDecisionManager urlAccessDecisionManager;
    private final AuthenticationAccessDeniedHandler deniedHandler;
    private final FailureHandler failureHandler;
    private final LogoutHandlerImpl logoutHandler;

    @Autowired
    public WebSecurityConfig(HrService hrService, CustomMetadataSource metadataSource,
                             UrlAccessDecisionManager urlAccessDecisionManager,
                             AuthenticationAccessDeniedHandler deniedHandler,
                             FailureHandler failureHandler, LogoutHandlerImpl logoutHandler) {
        this.hrService = hrService;
        this.metadataSource = metadataSource;
        this.urlAccessDecisionManager = urlAccessDecisionManager;
        this.deniedHandler = deniedHandler;
        this.failureHandler = failureHandler;
        this.logoutHandler = logoutHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/index.html", "/static/**", "/login_p", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(metadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(failureHandler)
                .successHandler((req, resp, auth) -> {
                    resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    RespBean respBean = RespBean.ok("登录成功!", HrUtils.getCurrentHr());
                    ResponseUtil.writeResponse(resp, respBean);
                })
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(logoutHandler)
                .permitAll()
                .and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(deniedHandler);
    }
}