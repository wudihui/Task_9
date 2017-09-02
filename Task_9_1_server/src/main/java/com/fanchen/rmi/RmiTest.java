package com.fanchen.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Administrator on 2017/8/10.
 */
public class RmiTest {
    public static void main(String []args) {
        try {
            //创建一个远程对象
            GetServer getServer=new GetServiceImpl();

            //远程主机远程对象注册表Registry的实例，并指定端口为8888，这一步必不可少（Java默认端口是1099），
            // 必不可缺的一步，缺少注册表创建，则无法绑定对象到远程注册表上
            LocateRegistry.createRegistry(8888);

            //把远程对象注册到RMI注册服务器上，并命名为RHello
            //绑定的URL标准格式为：rmi://host:port/name(其中协议名可以省略，下面两种写法都是正确的）
            Naming.bind("rmi://localhost:8888/getServer",getServer);

            //Naming.bind("//localhost:8888/RmiHello",rmiHello);

            //必须捕获这三个异常，否则需要在main方法中抛出
        }  catch (RemoteException e) {
            System.out.println("创建远程对象发生异常");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("URL畸形异常");
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            System.out.println("重复绑定对象异常");
            e.printStackTrace();
        }
    }
}
