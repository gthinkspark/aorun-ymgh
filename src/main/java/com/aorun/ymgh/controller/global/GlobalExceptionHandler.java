package com.aorun.ymgh.controller.global;

import com.aorun.ymgh.util.jsonp.Jsonp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public Jsonp runtimeExceptionHandler(RuntimeException ex) {
        //log.error(resultFormat(1, ex));
        return resultFormat(500, ex);
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public Jsonp nullPointerExceptionHandler(NullPointerException ex) {
        System.err.println("NullPointerException:");
        //log.error(resultFormat(2, ex));
        return resultFormat(500, ex);
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public Jsonp classCastExceptionHandler(ClassCastException ex) {
        //log.error(resultFormat(3, ex));
        return resultFormat(500, ex);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public Jsonp iOExceptionHandler(IOException ex) {
        //log.error(resultFormat(4, ex));
        return resultFormat(500, ex);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public Jsonp noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        //log.error(resultFormat(5, ex));
        return resultFormat(500, ex);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Jsonp indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        //log.error(resultFormat(6, ex));
        return resultFormat(500, ex);
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Jsonp requestNotReadable(HttpMessageNotReadableException ex) {
        //log.error(resultFormat(7, ex));
        System.out.println("400..requestNotReadable");
        return resultFormat(500, ex);
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public Jsonp requestTypeMismatch(TypeMismatchException ex) {
        //log.error(resultFormat(8, ex));
        //System.out.println("400..TypeMismatchException");
        return resultFormat(500, ex);
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public Jsonp requestMissingServletRequest(MissingServletRequestParameterException ex) {
        //log.error(resultFormat(9, ex));
        System.out.println("400..MissingServletRequest");
        return resultFormat(500, ex);
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Jsonp request405(HttpRequestMethodNotSupportedException ex) {
        //log.error(resultFormat(10, ex));
        return resultFormat(500, ex);
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public Jsonp request406(HttpMediaTypeNotAcceptableException ex) {
        //log.error(resultFormat(11, ex));
        System.out.println("406...");
        return resultFormat(500, ex);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public Jsonp server500(RuntimeException ex) {
        //log.error(resultFormat(12, ex));
        System.out.println("500...");
        return resultFormat(500, ex);
    }

    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    public Jsonp requestStackOverflow(StackOverflowError ex) {
        //log.error(resultFormat(13, ex));
        return resultFormat(500, ex);
    }

    //除数不能为0
    @ExceptionHandler({ArithmeticException.class})
    public Jsonp arithmeticException(ArithmeticException ex) {
        //log.error(resultFormat(13, ex));
        return resultFormat(500, ex);
    }


    //其他错误
    @ExceptionHandler({Exception.class})
    public Jsonp exception(Exception ex) {
        //log.error(resultFormat(14, ex));
        return resultFormat(500, ex);
    }

    //https://jira.spring.io/browse/SPR-14651
    //4.3.5 supports RedirectAttributes redirectAttributes
    @ExceptionHandler(MultipartException.class)
    public Object handleError1(MultipartException e) {
        return resultFormat(500, e);
    }


    private <T extends Throwable> Jsonp resultFormat(Integer code, T ex) {
        // //log.error(JsonResult.failed(code, ex.getMessage()));
        ex.printStackTrace();
        //log.error(String.format(logExceptionFormat, code, ex.getMessage()));
        //return JsonResult.failed(code, ex.getMessage());
        return Jsonp.newInstance(String.valueOf(code), ex.getMessage());
    }


}
