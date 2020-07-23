package com.jd.study.system.interf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jd.study.common.component.Pager;
import com.jd.study.common.component.param.BaseParam;
import com.jd.study.repo.model.SysDictionary;

/**
 * 系统字典表 服务类
 *
 * @author lsy
 * @since 2020-07-20
 */
public interface ISysDictionaryService extends IService<SysDictionary> {

    /**
     * 根据条件分页查询数据
     *
     * @param pager
     * @return IPage
     */
    IPage<SysDictionary> pageSysDictionary(Pager<BaseParam<SysDictionary>> pager);
}
