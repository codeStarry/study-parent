package com.jd.study.user.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jd.study.common.component.Pager;
import com.jd.study.common.component.param.BaseParam;
import com.jd.study.repo.config.R;
import com.jd.study.repo.model.SUser;
import com.jd.study.user.interf.ISUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 用户表 前端控制器
 *
 * @author lsy
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SUserApi {

    private final ISUserService targetService;

    /**
     * 分页查询数据
     */
    @PostMapping(value = "/page")
    public R<IPage<SUser>> pageSUser(@RequestBody Pager<BaseParam<SUser>> pager) {
        return R.ok(targetService.pageSUser(pager));
    }

    /**
     * 查询全部数据
     */
    @PostMapping(value = "/list")
    public R<List<SUser>> listSUser() {
        return R.ok(targetService.list());
    }


    /**
     * 新增数据
     */
    @PostMapping(value = "/save")
    public R<Boolean> save(@RequestBody SUser entity) {
        return R.ok(targetService.save(entity.defv()));
    }

    /**
     * 更新数据
     */
    @PostMapping(value = "/update")
    public R<Boolean> update(@RequestBody SUser entity) {
        return R.ok(targetService.updateById(entity));
    }

    /**
     * 删除
     */
    @PostMapping(value = "/del")
    public R<Boolean> delete(@RequestBody List<Long> ids) {
        return R.ok(targetService.removeByIds(ids));
    }


}

