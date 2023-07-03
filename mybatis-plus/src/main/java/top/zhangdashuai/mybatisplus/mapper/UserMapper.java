package top.zhangdashuai.mybatisplus.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.zhangdashuai.mybatisplus.domain.UserEntity;

/**
 * @author zhangdashuai
 */
@Mapper
public interface UserMapper extends RootMapper<UserEntity> {

    UserEntity selectByName(String name);
}