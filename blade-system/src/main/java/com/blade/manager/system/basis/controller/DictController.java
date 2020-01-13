package com.blade.manager.system.basis.controller;

import com.blade.core.controller.BaseController;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.basis.entity.Dict;
import com.blade.manager.system.basis.model.DictPageSearchDTO;
import com.blade.manager.system.basis.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 14:34:01
 */
@RestController
@RequestMapping("/basis/dict")
public class DictController extends BaseController {
    private static final long serialVersionUID = 8806439238172883270L;
    private IDictService dictService;

    @Autowired
    public DictController(IDictService dictService) {
        this.dictService = dictService;
    }

    @PostMapping("/page")
    public PageInfo<Dict> page(@RequestBody DictPageSearchDTO dictPageSearchDTO) {
        return this.dictService.page(dictPageSearchDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        this.dictService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public Dict getById(@PathVariable Integer id) {
        return this.dictService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody Dict dict) {
        this.dictService.insert(dict);
    }

    @PostMapping("/edit")
    public void update(@RequestBody Dict dict) {
        this.dictService.update(dict);
    }
}