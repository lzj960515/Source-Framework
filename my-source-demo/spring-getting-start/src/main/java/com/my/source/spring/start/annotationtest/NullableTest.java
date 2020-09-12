package com.my.source.spring.start.annotationtest;

import com.sun.istack.internal.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 测试 @Nullable注解
 *
 * @author Zijian Liao
 * @since 1.0.0
 */
public class NullableTest {

    public static void main(String[] args) {
        test(null);
        test2(null);
    }

    public static void test(@Nullable String string) {
        System.out.println(string);
    }

    public static void test2(@NonNull String string) {
        System.out.println(string);
    }
}
