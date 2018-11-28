package com.jiashidai.springboot_shiro_demo.mappers;

import com.jiashidai.springboot_shiro_demo.entity.Role;
import com.jiashidai.springboot_shiro_demo.entity.UserRoleExample;
import com.jiashidai.springboot_shiro_demo.entity.UserRoleKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserRoleMapper {
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);

    List<UserRoleKey> selectByExample(UserRoleExample example);

    int updateByExampleSelective(@Param("record") UserRoleKey record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRoleKey record, @Param("example") UserRoleExample example);

    List<Role> getRoles(@Param("userId") Long userId);
}