package com.how2java.test;

import com.fanchen.util.MemcachedUtil;
import com.fanchen.verification.NetEaseYunxin;
import com.how2java.mapper.CategoryMapper;
import com.how2java.pojo.Register;
import com.how2java.service.TencentYunService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    TencentYunService tencentYun;

    @Autowired
    NetEaseYunxin netEaseYunxin;

    //测试获取人数
    @Test
    public void getPP() {
        Long pp=categoryMapper.getPpNumber();
        System.out.println(pp);
    }

    @Test
    public void upProterict(){
        System.out.println(tencentYun.upDate("E:/1.jpg"));
    }

    @Test
    public void setHead(){
        Register register=new Register();
        register.setEmail("897254488@qq.com");
        register.setPortraitpath("aaaaaa");
        categoryMapper.setPortrait(register);
    }

    @Test
    public void netEaseTest(){
        String phone="15378776160";
        /**发送验证码并存放到缓存,5分钟后过期*/
        MemcachedUtil.add(phone,netEaseYunxin.send(phone),new Date(1000*60*5));
    }

}

