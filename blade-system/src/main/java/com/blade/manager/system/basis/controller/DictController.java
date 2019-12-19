package com.blade.manager.system.basis.controller;

import java.time.LocalDateTime;
import com.blade.core.controller.BaseController;
import com.blade.core.model.response.ResponseResult;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.basis.service.IDictService;
import com.blade.manager.system.basis.entity.Dict;
import com.blade.manager.system.basis.model.DictPageSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
    *  前端控制器
    * </p>
 *
 * @author Blade
 * @since 2019-12-19 16:00:20
 */
@RestController
@RequestMapping("/basis/dict")
public class DictController extends BaseController {
    private IDictService dictService;

    @Autowired
    public DictController (IDictService dictService) {
        this.dictService = dictService;
    }

    @PostMapping("/page")
    public ResponseResult <PageInfo<Dict>> page(@RequestBody DictPageSearchDTO dictPageSearchDTO) {
        return ResponseResult.ok(200, "请求成功", this.dictService.page(dictPageSearchDTO));
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody Integer id) {
        this.dictService.delete(id);
        return ResponseResult.ok();
    }

    @GetMapping("/getById")
    public ResponseResult<Dict> getById(Integer id) {
        return ResponseResult.ok(200, "成功", this.dictService.selectByPk(id));
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody Dict dict) {
        dict.setCreateTime(LocalDateTime.now());
        this.dictService.insert(dict);
        return ResponseResult.ok();
    }

    @PostMapping("/edit")
    public ResponseResult update(@RequestBody Dict dict) {
        this.dictService.update(dict);
        return ResponseResult.ok();
    }
}