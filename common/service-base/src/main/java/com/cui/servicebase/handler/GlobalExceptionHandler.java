package com.cui.servicebase.handler;


import com.cui.commonutils.ExceptionUtil;
import com.cui.commonutils.R;
import com.cui.servicebase.exception.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 *
 * @author water
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * @param e
     * @return 指定出现什么异常执行这个方法
     * 不在RestController中，需要@ResponseBody返回json数据。
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("GlobalException");
    }

    /**
     * 数字逻辑异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("数字逻辑异常");
    }


    /**
     * 自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e) {
        // 日志工具类详细信息
        log.error(ExceptionUtil.getMessage(e));
        //打印错误消息到控制台
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}