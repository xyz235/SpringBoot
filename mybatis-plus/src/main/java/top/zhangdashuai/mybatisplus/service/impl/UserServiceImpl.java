package top.zhangdashuai.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhangdashuai.mybatisplus.domain.UserEntity;
import top.zhangdashuai.mybatisplus.mapper.UserMapper;
import top.zhangdashuai.mybatisplus.service.UserService;

/**
 * @author zhangdashuai
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
