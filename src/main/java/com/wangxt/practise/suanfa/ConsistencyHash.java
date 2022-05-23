package com.wangxt.practise.suanfa;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author wangxt
 * @description 一致性hash例子
 * @date 2022/4/10 10:18
 **/
public class ConsistencyHash {

    /**
     * 当某个服务通过集群进行部署时，怎么能够合理的对服务进行平均访问以及服务的扩容缩容对访问产生的影响是我们需要面临的一个问题
     * 我们可以通过hash算法，对每次请求进行hash，使请求基本均衡的打到集群种的每台服务器上，但是假如某台服务器宕机了，获取我们需要对集群中的服务
     * 器进行扩容，那么所有的请求又要重新计算hash值，这样非常不灵活，并且每次缩容扩容的成本很大。
     *
     * 一致性hash是怎么解决上边的问题的，原理就是将每台服务器的ip进行hash放置到hash环上，然后对每次请求的ip同样进行hash，以请求的hash为基按照顺时针的方式
     * 获取最近的一个服务器hash值，那么由这个服务器来处理这次请求，假如需要扩容或者缩容，那么影响的也只是其起止服务器之前的请求，其它的请求不会影响
     *
     * hash倾斜：如果两台服务器的hash计算的相差不大，那么有可能某个服务器需要处理的请求的数量要比另一台服务器处理的多得多，为了解决这个问题，引入了虚拟机节点
     * 每个服务器分别处理n个节点的请求，由于节点数量增多了，那么请求分配相对来说变得均匀了
     *
     */

    public static void main(String[] args) {
        // 服务器ip，有3个服务器处理请求
        String[] serverIp = {"127.0.0.1", "127.0.0.2", "127.0.0.3"};

        // 客户端请求ip
        String[] clientIp = {"127.0.101.1", "127.0.140.2", "127.0.107.3", "127.0.10.1", "127.0.10.5"};

        // 普通hash算法
        for(String cIp : clientIp){
            int i = Math.abs(cIp.hashCode()) % serverIp.length;
            System.out.println("client :" + cIp + " 被 server : " + serverIp[i] + " 处理了");
        }

        System.out.println("================");

        // 一致性hash
        // 首先计算服务器ip的hash值，并存储，我们使用hashMap存储对应关系
        //Map<Integer,String> serverIpMap = new HashMap<>();

        SortedMap<Integer,String> serverIpMap = new TreeMap<>();
        for(String sIP : serverIp){
            // 这里hashcode返回的就是int类型，所以不需要在取余
            serverIpMap.put(Math.abs(sIP.hashCode()), sIP);

            // 为了防止hash倾斜的问题，我们这里进行虚拟节点的初始化操作
            for(int i=0;i<3;i++){
                serverIpMap.put((sIP + "#" + i).hashCode(), sIP);
            }
        }

        for(String cIp : clientIp){
            // 客户端ip的hash值
            int i = Math.abs(cIp.hashCode());
            // 顺时针取hash最近的服务器
            // 这里发现使用hashMap查找很不方便，所以我们改为使用TreeMap存储
            SortedMap<Integer, String> tailMap = serverIpMap.tailMap(i);
            if(tailMap.isEmpty()){
                Integer firstKey = serverIpMap.firstKey();
                System.out.println("client :" + cIp + " 被 server : " + serverIpMap.get(firstKey) + " 处理了");
            }else {
                Integer firstKey = tailMap.firstKey();
                System.out.println("client :" + cIp + " 被 server : " + serverIpMap.get(firstKey) + " 处理了");
            }
        }
    }
}
