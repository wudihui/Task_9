package com.fanchen.rmi;

import com.how2java.pojo.Register;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Administrator on 2017/8/10.
 */
public interface GetServer extends Remote {
    Register get()throws RemoteException;
}
