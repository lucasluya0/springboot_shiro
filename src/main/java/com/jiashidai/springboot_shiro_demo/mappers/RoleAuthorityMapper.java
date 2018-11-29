package com.jiashidai.springboot_shiro_demo.mappers;

import com.jiashidai.springboot_shiro_demo.entity.Authority;
import com.jiashidai.springboot_shiro_demo.entity.RoleAuthority;
import com.jiashidai.springboot_shiro_demo.entity.RoleAuthorityExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleAuthorityMapper {
    int countByExample(RoleAuthorityExample example);

    int deleteByExample(RoleAuthorityExample example);

    int insert(RoleAuthority record);

    int insertSelective(RoleAuthority record);

    List<RoleAuthority> selectByExample(RoleAuthorityExample example);

    int updateByExampleSelective(@Param("record") RoleAuthority record, @Param("example") RoleAuthorityExample example);

    int updateByExample(@Param("record") RoleAuthority record, @Param("example") RoleAuthorityExample example);

    List<Authority> findAllPermission();
}