package com.blade.manager.system.modules.permission.controller;


import com.blade.core.controller.BaseController;
import com.blade.core.model.response.ResponseResult;
import com.blade.manager.system.modules.permission.entity.DictDetail;
import com.blade.manager.system.modules.permission.service.IDictDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author blade
 * @since 2019-10-06
 */
@RestController
@RequestMapping("/dictDetail")
public class DictDetailController extends BaseController {
    private IDictDetailService dictDetailService;

    @Autowired
    public DictDetailController(IDictDetailService dictDetailService) {
        this.dictDetailService = dictDetailService;
    }

    @GetMapping("/getByName")
    public ResponseResult<List<DictDetail>> getDictDetailByDictName(String dictName) {
        return ResponseResult.ok(dictDetailService.selectDictDetailByDictName(dictName));
    }
}
