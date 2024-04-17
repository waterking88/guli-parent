package com.cui.oss.controller;

import com.cui.commonutils.R;
import com.cui.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author water
 * @date 2024/3/27
 * @Description CrossOrigin 跨域
 */
@RestController
@RequestMapping("/eduoss/file")
//@CrossOrigin  网关已配置跨域
@Api(tags = "阿里云文件管理")
public class FileUploadController {
    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        String uploadUrl = fileService.upload(file);
        //返回r对象
        return R.ok().message("文件上传成功").data("url", uploadUrl);
    }

    /**
     * 删除文件
     *
     * @param url
     */
    @ApiOperation(value = "删除文件")
    @DeleteMapping("remove")
    public R remove(
            @ApiParam(name = "url", value = "文件路径", required = true)
            @RequestParam("url") String url) {
        fileService.remove(url);
        return R.ok().message("文件删除成功");
    }
}