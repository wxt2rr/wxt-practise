package com.wangxt.practise.volatile_test;

public class Single {
    private volatile static Single single;

    public static Single getInstance(){
        if(single == null){
            synchronized (Single.class){
                if(single == null){
                    single = new Single();
                }
            }
        }

        return single;
    }
}
