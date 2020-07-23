package com.jd.study.user.interf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jd.study.common.component.Pager;
import com.jd.study.common.component.param.BaseParam;
import com.jd.study.repo.model.SUser;

/**
 * 用户表 服务类
 *
 * @author lsy
 * @since 2020-07-20
 */
public interface ISUserService extends IService<SUser> {

    /**
     * 根据条件分页查询数据
     *
     * @param pager
     * @return IPage
     */
    IPage<SUser> pageSUser(Pager<BaseParam<SUser>> pager);
}
