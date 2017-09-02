package com.how2java.rmi;

import com.how2java.mapper.CategoryMapper;
import com.how2java.pojo.Register;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Administrator on 2017/8/10.
 */
public class GetServiceImpl extends UnicastRemoteObject implements GetServer{
    @Autowired
    CategoryMapper categoryMapper;

    protected GetServiceImpl() throws RemoteException {
    }

    @Override
    public Register get() throws RemoteException {
        return categoryMapper.getMP("897254488@qq.com");
    }
}
