package com.jd.study.repo.generator;

import com.jd.study.common.generator.MyCodeGeneratorProcessor;
import com.jd.study.common.utils.SpringUtils;
import org.springframework.stereotype.Component;

@Component
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        MyCodeGeneratorProcessor processor = SpringUtils.getBean(MyCodeGeneratorProcessor.class);
        processor.codeGenerator();
    }
}
