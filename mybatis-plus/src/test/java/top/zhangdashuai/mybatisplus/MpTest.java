package top.zhangdashuai.mybatisplus;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.zhangdashuai.mybatisplus.domain.UserEntity;
import top.zhangdashuai.mybatisplus.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MpTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void insertBatchTest() {
        List<UserEntity> userList = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(String.valueOf(i));
            userList.add(userEntity);
        }
        userMapper.insertBatch(userList);
    }

    @Test
    public void updateBatchTest() {
        List<UserEntity> userList = userMapper.selectList(null);
        for (UserEntity userEntity : userList) {
            userEntity.setName("999");
        }
        userMapper.updateBatch(userList);
    }
}
