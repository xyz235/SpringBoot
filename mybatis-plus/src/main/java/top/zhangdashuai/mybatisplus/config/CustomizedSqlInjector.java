package top.zhangdashuai.mybatisplus.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

import java.util.List;

/**
 * @author zhangdashuai
 */
public class CustomizedSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass,tableInfo);
        methodList.add(new InsertBatchMethod("批量添加"));
        methodList.add(new UpdateBatchMethod("批量修改"));
        return methodList;
    }
}
