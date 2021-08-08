package cn.boood.fireeye.spider;

import cn.boood.fireeye.dao.SensitiveWordsMapper;
import cn.boood.fireeye.mybatis.entity.SensitiveWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/7 22:47
 */
@Component
public class MysqlPipeline implements Pipeline {
    @Autowired
    private SensitiveWordsMapper sensitiveWordsMapper;
    @Override
    public void process(ResultItems resultItems, Task task) {

        List<SensitiveWords> list=resultItems.get("SensitiveWords");

        for (SensitiveWords words : list){
            sensitiveWordsMapper.insertSensitiveWords(words);
        }
    }
}
