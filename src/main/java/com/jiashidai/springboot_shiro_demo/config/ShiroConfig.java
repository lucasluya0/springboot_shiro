package com.jiashidai.springboot_shiro_demo.config;
import com.jiashidai.springboot_shiro_demo.utils.shiro.AjaxPermissionsAuthorizationFilter;
import com.jiashidai.springboot_shiro_demo.utils.shiro.CustomerFilterChainDefinition;
import com.jiashidai.springboot_shiro_demo.utils.shiro.ShiroRealm;
import com.jiashidai.springboot_shiro_demo.utils.shiro.UrlPermissionAuthorizationFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * HashedCredentialsMatcher，这个类是为了对密码进行编码的，
     * 防止密码在数据库里明码保存，当然在登陆认证的时候，
     * 这个类也负责对form里输入的密码进行编码。
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }
    /**
     * ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，
     * 负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
     */
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        //配置密码加密方式
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }
    /**
     * EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，
     * 然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
     */
//    @Bean(name = "ehCacheManager")
////    @DependsOn("lifecycleBeanPostProcessor")
////    public EhCacheManager ehCacheManager() {
////        return new EhCacheManager();
////    }
    /**
     * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
     * //
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }


//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean() throws Exception {
//        ShiroFilterFactoryBean shiroFilterFactoryBean   = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager());
//        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        Map<String, Filter> filterMap = new LinkedHashMap<>();
//        filterMap.put("authc", ajaxPermissionsAuthorizationFilter());
//        shiroFilterFactoryBean.setFilters(filterMap);
//        //自定义加载权限资源关系
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(urlPermissionAuthorizationFilter().loadFilterChainDefinitions());
//        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/css/**", "anon");
//        filterChainDefinitionMap.put("/acount/login", "anon");
//        filterChainDefinitionMap.put("/js/**", "anon");
//        filterChainDefinitionMap.put("/fonts/**", "anon");
//        filterChainDefinitionMap.put("/img/**", "anon");
//        filterChainDefinitionMap.put("/druid/**", "anon");
//        filterChainDefinitionMap.put("/acount/loginout", "logout");
//        filterChainDefinitionMap.put("/", "anon");
//        filterChainDefinitionMap.put("/**", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//        @Bean
//        public UrlPermissionAuthorizationFilter urlPermissionAuthorizationFilter() {
//            return new UrlPermissionAuthorizationFilter();
//        }
    @Bean
    public CustomerFilterChainDefinition customerFilterChainDefinition(ShiroFilterFactoryBean shiroFilterFactoryBean) throws Exception {
        CustomerFilterChainDefinition customerFilterChainDefinition = new CustomerFilterChainDefinition();
        customerFilterChainDefinition.setShiroFilter((AbstractShiroFilter) shiroFilterFactoryBean.getObject());
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", ajaxPermissionsAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/acount/login", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/acount/loginout", "logout");
        filterChainDefinitionMap.put("/favicon.ico","anon");
        filterChainDefinitionMap.put("/", "anon");
        customerFilterChainDefinition.setFilterChainDefinitions(filterChainDefinitionMap);
        return customerFilterChainDefinition;
    }



    @Bean
    public Filter ajaxPermissionsAuthorizationFilter() {
        AjaxPermissionsAuthorizationFilter ajaxPermissionsAuthorizationFilter = new AjaxPermissionsAuthorizationFilter();
        return ajaxPermissionsAuthorizationFilter;
    }


    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setMaxAge(86400);
        return cookie;
    }

    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
        aASA.setSecurityManager(securityManager());
        return aASA;
    }
}