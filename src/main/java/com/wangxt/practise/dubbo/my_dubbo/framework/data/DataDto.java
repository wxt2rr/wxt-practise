package com.wangxt.practise.dubbo.my_dubbo.framework.data;

/**
 * 服务之间数据传输实体对象
 */
public class DataDto {
    // 接口名称
    private String interfaceName;
    // 方法名称
    private String methodName;
    // 参数类型
    private Class[] paramsType;
    // 参数值
    private Object[] paramValue;
    // 版本
    private String version;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamsType() {
        return paramsType;
    }

    public void setParamsType(Class[] paramsType) {
        this.paramsType = paramsType;
    }

    public Object[] getParamValue() {
        return paramValue;
    }

    public void setParamValue(Object[] paramValue) {
        this.paramValue = paramValue;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
