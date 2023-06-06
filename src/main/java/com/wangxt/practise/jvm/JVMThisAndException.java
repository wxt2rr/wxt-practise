package com.wangxt.practise.jvm;

public class JVMThisAndException {
    private int m; // 实例变量，成员变量，不是局部变量
    private static int n;

    public int inc(){ // 方法。这个东西肉眼看见： 没有参数
        return this.m + 1; // 没有局部变量。
        // locals=1, args_size=1 ????
        // 参数问题，对于我们的 普通方法 都会有一个隐藏的this参数。这样我们在使用this的时候，就能够像我们的inc方法那样，
        // 进行this.m的调用了。
        // 局部变量表来说，也会为我们的this开辟一个单独的区域进行this的一个存储。这个存储的是我们this指向的这个当前对象。
    }

    public static int inc2() {
        return n; //哎呀，不能使用this，因为该方法是static的
        // locals=0, args_size=0
    }

    public int inc3 () {
        try {
            return m + 1;
        } catch (Exception e) { // e 是个局部使用的变量名称，局部变量
            e.printStackTrace();
        }
        return 0;

        // locals=2(this, e), args_size=1 (this)
        //
    }
}
