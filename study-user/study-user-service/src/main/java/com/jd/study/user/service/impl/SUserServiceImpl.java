package com.jd.study.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jd.study.common.component.Pager;
import com.jd.study.common.component.Translation;
import com.jd.study.common.component.param.BaseParam;
import com.jd.study.repo.mapper.SUserMapper;
import com.jd.study.repo.model.SUser;
import com.jd.study.user.interf.ISUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 用户表 服务实现类
 * @author lsy
 * @since 2020-07-20
 */
@Slf4j
@Service
public class SUserServiceImpl extends ServiceImpl<SUserMapper, SUser> implements ISUserService {

    /**
     * 根据条件查询数据列表
     *
     * @param pager
     * @return
     */
    @Override
    public IPage<SUser> pageSUser(Pager<BaseParam<SUser>> pager) {
        return baseMapper.selectPage(Pager.decorateParam(pager),
                                     Translation.translationParam(pager.getCondition()));
    }

}
