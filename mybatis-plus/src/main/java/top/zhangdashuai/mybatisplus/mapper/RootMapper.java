package top.zhangdashuai.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangdashuai
 */
public interface RootMapper<T> extends BaseMapper<T> {

    /**
     * 自定义批量插入
     *
     * @param list 参数
     * @return 成功条数
     */
    int insertBatch(@Param("list") List<T> list);

    /**
     * 自定义批量更新，条件为主键
     *
     * @param list 参数
     * @return 成功条数
     */
    int updateBatch(@Param("list") List<T> list);
}

