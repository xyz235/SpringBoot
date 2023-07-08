package top.zhangdashuai.mybatisplus.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.zhangdashuai.mybatisplus.domain.UserEntity;

/**
 * @author zhangdashuai
 */
@Mapper
public interface UserMapper extends RootMapper<UserEntity> {

    /**
     * 根据名称查询
     *
     * @param name 名称
     * @return 结果
     */
    UserEntity selectByName(String name);
}