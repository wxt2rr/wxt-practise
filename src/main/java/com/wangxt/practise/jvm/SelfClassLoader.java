package com.wangxt.practise.jvm;

import lombok.SneakyThrows;

import java.io.InputStream;

public class SelfClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myClassLoader = new ClassLoader() {
//            @SneakyThrows
//            @Override
            // 首先我们自定义的类加载器加载的 我们的 SelfClassLoader 这个文件，如果没有自定义的类加载器，这个文件是应该
            // 被我们的 系统类加载器进行加载的。现在我们自定义了一个加载器，jvm为了保证只有一个类能够被加载，所以，我们的
            // jvm中只有一份儿该类的信息。对于我们进行自定义类加载器的书写的时候，复写 findClass 永远不会破坏双亲委派。
            // 因为双亲委派的代码逻辑存在于 loadClass 这个方法中。
            // 对于我们这个例子来说，这个类其实就是被 系统类 加载器加载的。 哪怕我们的在代码中用自己的类加载器进行再次加载，
            // 也会在 Class<?> c = findLoadedClass(name); 从系统类加载器里获取该类。
//            protected Class<?> findClass(String name) throws ClassNotFoundException {
//                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
//                InputStream is = getClass().getResourceAsStream(fileName);
//                byte[] b = new byte[is.available()];
//                is.read(b);
//                return defineClass(name, b, 0, b.length);
//            }

            @SneakyThrows
            @Override
            // 现在我的自定义类加载器复写了 loadClass 方法，里边没有双亲委派的任何逻辑。
            // 对于我们这个自定义的类加载器加载的类，就会出现类名重复的问题。
            // 我们不小心复写了 loadclass方法，破坏了我们双亲委派模型。导致 com.boot.jvm.SelfClassLoader 被我们的
            // 系统类加载器和 自定义类加载器，进行了两次加载。而且最要命的是，我们系统类加载器标注的类名称空间是一份，
            // 我们自定义的类加载器也标注了一份儿类名称空间。
            // 由加载它的类加载器和这个类本身一起共同确立其在Java虚拟机中的唯一性.
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            }
        };

        Object obj = myClassLoader.loadClass("com.boot.jvm.SelfClassLoader").newInstance();
        System.out.println(obj.getClass()); //com.boot.jvm.SelfClassLoader

        System.out.println(obj // com.boot.jvm.SelfClassLoader
                instanceof com.wangxt.practise.jvm.SelfClassLoader);
    }
}
