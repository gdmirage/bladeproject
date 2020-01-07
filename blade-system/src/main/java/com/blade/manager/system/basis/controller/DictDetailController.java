package com.blade.manager.system.basis.controller;

import com.blade.core.controller.BaseController;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.basis.entity.DictDetail;
import com.blade.manager.system.basis.model.DictDetailPageSearchDTO;
import com.blade.manager.system.basis.service.IDictDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 15:53:36
 */
@RestController
@RequestMapping("/basis/dictDetail")
public class DictDetailController extends BaseController {
    private static final long serialVersionUID = -2686758240003605838L;
    private IDictDetailService dictDetailService;

    @Autowired
    public DictDetailController(IDictDetailService dictDetailService) {
        this.dictDetailService = dictDetailService;
    }

    @PostMapping("/page")
    public PageInfo<DictDetail> page(@RequestBody DictDetailPageSearchDTO dictDetailPageSearchDTO) {
        return this.dictDetailService.page(dictDetailPageSearchDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        this.dictDetailService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public DictDetail getById(@PathVariable Integer id) {
        return this.dictDetailService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody DictDetail dictDetail) {
        this.dictDetailService.insert(dictDetail);
    }

    @PostMapping("/edit")
    public void update(@RequestBody DictDetail dictDetail) {
        this.dictDetailService.update(dictDetail);
    }

    @GetMapping("/getByDictName")
    public List<DictDetail> getByDictName(String dictName) {
        return this.dictDetailService.getByDictName(dictName);
    }
}