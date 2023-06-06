package com.wangxt.practise.jvm;

//相信很多小伙在进行代码开发的过程中，会使用到类似的这种方式，存储我们的一些常量值，
//使用的时候只需要进行 类.变量名就行了。CommonConstant
public class ConstantValueTest {
    public final static String NAME = "name";
    public final static int CODE = 200; // 我现在正处于编译期，代码并没有运行啊，code的200，和 NAME的name 都已经被存储了
    // 说明已然初始化完成，太棒了，不需要我们运行时再次初始化，提高我们的执行效率（微乎其微）。
    public static String INFO = "info";
    // 这个INFO没有constantvalue，他就需要在其他的时间点进行初始化了<clinit>()方法中进行初始化。。

    public final static Integer INTEGER = 110120;
}
