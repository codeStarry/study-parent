package com.jd.study.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jd.study.common.component.Pager;
import com.jd.study.common.component.Translation;
import com.jd.study.common.component.param.BaseParam;
import com.jd.study.repo.mapper.SysDictionaryMapper;
import com.jd.study.repo.model.SysDictionary;
import com.jd.study.system.interf.ISysDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 系统字典表 服务实现类
 *
 * @author lsy
 * @since 2020-07-20
 */
@Slf4j
@Service
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryMapper, SysDictionary> implements ISysDictionaryService {

    /**
     * 根据条件查询数据列表
     *
     * @param pager
     * @return
     */
    @Override
    public IPage<SysDictionary> pageSysDictionary(Pager<BaseParam<SysDictionary>> pager) {
        return baseMapper.selectPage(Pager.decorateParam(pager), Translation.translationParam(pager.getCondition()));
    }
}
