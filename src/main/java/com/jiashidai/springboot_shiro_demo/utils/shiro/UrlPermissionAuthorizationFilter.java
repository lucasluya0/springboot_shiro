package com.jiashidai.springboot_shiro_demo.utils.shiro;

import com.jiashidai.springboot_shiro_demo.entity.Authority;
import com.jiashidai.springboot_shiro_demo.services.AuthorityService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * author:liuxu
 * 此类在此项目没使用
 */
public class UrlPermissionAuthorizationFilter {

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
    @Autowired
    private AuthorityService authorityService;
    /**
     * 加载URL和权限的对应关系
     */
    public Map<String, String> loadFilterChainDefinitions(){
        Map<String,String> urlMap = new LinkedHashMap<>();
        //从数据库中查找所有的权限对象
        List<Authority> authorityList = authorityService.findAllPermission();

        for(Authority authority : authorityList) {
            urlMap.put(authority.getDataUrl(),"perms["+authority.getMenuCode()+"]");
        }
        return urlMap;
    }


    /**
     * 在对角色进行增删改操作时，需要调用此方法进行动态刷新
     *
     */
    public void updatePermission() {
        synchronized (this) {
            AbstractShiroFilter shiroFilter;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim()
                        .replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
        }
    }
}
