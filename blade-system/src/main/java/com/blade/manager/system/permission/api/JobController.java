package com.blade.manager.system.permission.api;

import com.blade.core.controller.BaseController;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.entity.Job;
import com.blade.manager.system.permission.model.JobPageSearchDTO;
import com.blade.manager.system.permission.model.job.JobListVO;
import com.blade.manager.system.permission.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 岗位前端控制器
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@RestController("ApiJobController")
@RequestMapping("/api/permission/job")
public class JobController extends BaseController {
    private static final long serialVersionUID = 4324850316618322859L;
    private IJobService jobService;

    @Autowired
    public JobController(IJobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/page")
    public PageInfo<JobListVO> page(@RequestBody JobPageSearchDTO jobPageSearchDTO) {
        return this.jobService.page(jobPageSearchDTO);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        this.jobService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public Job getById(@PathVariable Integer id) {
        return this.jobService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody Job job) {
        this.jobService.insert(job);
    }

    @PutMapping("/edit")
    public void update(@RequestBody Job job) {
        this.jobService.update(job);
    }

    public static void main(String[] args) {
    }

    @PostMapping("/download")
    public void download(HttpServletResponse response, @RequestBody JobPageSearchDTO jobPageSearchDTO) {
        // 文件名
        String fileName = "test";
        if (fileName != null) {
            String filePath = JobController.class.getClassLoader().getResource("static/common/image/bg.jpg").getPath();
            super.logger.info("file path is : {}", filePath);
            //设置文件路径
//            File file = new File(filePath);
//            if (file.exists()) {
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
                BufferedInputStream bis = null;
                InputStream inputStream = JobController.class.getClassLoader().getResourceAsStream("static/common/image/bg.jpg");
                super.logger.info("inputStream : {}", inputStream);
                if (null == inputStream) {
                    return;
                }
                try {
//                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(inputStream);
                    ServletOutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
//            }
        }
        System.out.println("end");
    }
}