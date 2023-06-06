package com.wangxt.practise.jvm;

class Parent {
    static {
        System.out.println("Parent init!");
    }
    public static int value = 123;
    public static final int value2 = 456;
}
class Child extends Parent {
    static {
        System.out.println("Child init!");
    }
}
public class SuperClass {
    public static void main(String[] args) {
//        System.out.println(Child.value2);
        //知打印了 456. 还是没有触发 Parent的初始化。
        //static final int 在 编译器 提前存储到 字段表的 属性表里的 constantvalue属性里。使用的时候
        //直接使用，不会触发类的初始化。

//        Parent[] pars = new Parent[2];
        // 没有打印结果。说static代码块没有执行，说明Parent类没有初始化。但是我们看到了new关键字啊。
        //new Parent[2]; JVM对应的指令是 ： newarray。 而newarray不会触发类的初始化。

//        System.out.println(Child.value);
        //打印结果： Parent init! // 123
        // 因为我们value 这个static属性属于我们的parent类，如果想要调用 value，就需要初始化 Parent类 -- clinit 方法。
        // 而不需要初始化 Child类。
    }
}
