package com.jd.study.system.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jd.study.common.component.Pager;
import com.jd.study.common.component.param.BaseParam;
import com.jd.study.repo.config.R;
import com.jd.study.repo.model.SysDictionary;
import com.jd.study.system.interf.ISysDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 系统字典表 前端控制器
 *
 * @author lsy
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Api(tags = "系统字典管理")
public class SysDictionaryApi {

    private final ISysDictionaryService targetService;


    /**
     * 分页查询数据
     */
    @PostMapping(value = "/page")
    @ApiOperation(value = "分页查询数据", response = SysDictionary.class)
    public R<IPage<SysDictionary>> pageSysDictionary(@RequestBody Pager<BaseParam<SysDictionary>> pager) {
        return R.ok(targetService.pageSysDictionary(pager));
    }

    /**
     * 查询全部数据
     */
    @PostMapping(value = "/list")
    @ApiOperation(value = "查询全部数据", response = SysDictionary.class)
    public R<List<SysDictionary>> listSysDictionary() {
        return R.ok(targetService.list());
    }


    /**
     * 新增数据
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增数据", response = Boolean.class)
    public R<Boolean> save(@RequestBody SysDictionary entity) {
        return R.ok(targetService.save(entity.defv()));
    }

    /**
     * 更新数据
     */
    @PostMapping(value = "/update")
    @ApiOperation(value = "更新数据", response = Boolean.class)
    public R<Boolean> update(@RequestBody SysDictionary entity) {
        return R.ok(targetService.updateById(entity));
    }

    /**
     * 删除
     */
    @PostMapping(value = "/del")
    @ApiOperation(value = "删除", response = Boolean.class)
    public R<Boolean> delete(@RequestBody List<Long> ids) {
        return R.ok(targetService.removeByIds(ids));
    }


}

