package com.yx.hello.spring.cloud.zuul.fallback;

import java.util.ArrayList;
import java.util.List;

public class Times {

    private static float [] f = new float[2];
    public static void main(String[] args) {
        Enum e;
        System.out.println("f[0]"+f[0]);
    }

    public static  List<?>   revers(String[] arr){
        List<String> list = new ArrayList<>();
        for (String s : arr) {
            if( ! list.contains(s)){
                list.add(s);
            }
        }
        return list;
    }
}

/**
 * 枚举类
 */
enum HttpStatus{
//        Enum e;
    OK(1001,"OK"),
    BAD(202,"请求参数错误"),
    NOT_FOUND(501,"参数未找到");
    Integer state;
    String msg;

    HttpStatus(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }
}
