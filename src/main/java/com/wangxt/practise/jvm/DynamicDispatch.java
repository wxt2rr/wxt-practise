package com.wangxt.practise.jvm;

public class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }
    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }
    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman(); // 与我们之前做静态分派的时候很相似，他的静态类型（外观类型） 都是选择的Human
        man.sayHello();  // "man say hello"  new Man()
        woman.sayHello(); // woman say hello   new Woman();
        man = new Woman();
        man.sayHello();// woman say hello   new Woman();
    }
}


//         0: new           #2                  // class com/boot/jvm/DynamicDispatch$Man  创建Man
//         3: dup    复制我们的变量 man --》 操作数栈的栈顶
//         4: invokespecial #3                  // Method com/boot/jvm/DynamicDispatch$Man."<init>":()V  初始化man对象
//         7: astore_1  // 将我们的man变量存入我们的局部变量表（栈帧里边呢）
//         8: new           #4                  // class com/boot/jvm/DynamicDispatch$Woman  创建Woman
//         11: dup
//         12: invokespecial #5                  // Method com/boot/jvm/DynamicDispatch$Woman."<init>":()V
//         15: astore_2
//         //以上步骤，进行了man对象的创建和woman对象的创建，并且将其存入线程的栈帧的里边的局部变量表
//
//
//         16: aload_1  //将我们的 man 这个变量压入 操作数栈的栈顶
//         17: invokevirtual #6                  // Method com/boot/jvm/DynamicDispatch$Human.sayHello:()V
//         //invokevirtual 后边跟的是 Human.sayHello， 不是Man.sayHello
//
//         20: aload_2  //将我们的 woman 这个变量压入 操作数栈的栈顶
//         21: invokevirtual #6                  // Method com/boot/jvm/DynamicDispatch$Human.sayHello:()V
//         //invokevirtual 后边跟的是 Human.sayHello， 不是Woman.sayHello
//                 //invokevirtual 调用的是 ： 虚方法 。调用细节你知道吗？
//                 //我不知道。
//                 //invokevirtual细节步骤里边找到我们想要的答案呢？
//
//         24: new           #4                  // class com/boot/jvm/DynamicDispatch$Woman
//         27: dup
//         28: invokespecial #5                  // Method com/boot/jvm/DynamicDispatch$Woman."<init>":()V
//         31: astore_1
//         32: aload_1
//         33: invokevirtual #6                  // Method com/boot/jvm/DynamicDispatch$Human.sayHello:()V
//
//         36: return

