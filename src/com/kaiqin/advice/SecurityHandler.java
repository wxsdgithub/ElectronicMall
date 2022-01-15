package com.kaiqin.advice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaiqin.domain.Order;
import com.kaiqin.service.UserService;
import com.kaiqin.service.impl.OrderServiceImpl;


@Aspect
public class SecurityHandler {
	@Autowired
	private UserService userService;
	
	@Pointcut("execution(* com.kaiqin.service.impl.OrderServiceImpl.deleteById(..))")
	public void deleteOrder(){};
	
	
	@Pointcut("execution(* com.kaiqin.dao.OrderDao.insert(..))")
	public void addOrder(){};
	
	
	@Before("deleteOrder()")
	public void securityCheck(JoinPoint joinPoint) throws IOException{
		
		Object[] args = joinPoint.getArgs();
		//获得删除订单的订单号
		String oid = (String) args[0];
		//获得订单业务对象
		OrderServiceImpl orderService = (OrderServiceImpl) joinPoint.getThis();
		//获得订单对应的用户id
		int uid = orderService.findUidByOid(oid);
		String username = userService.findUserByUid(uid).getUsername();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String filepath = "C:\\Users\\18741\\workspace\\ElectronicMall\\"+ username +".txt";
			String content = username + "在"+df.format(new Date())+"时删除了订单：" + oid;
		    FileOutputStream fileOutputStream = new FileOutputStream(filepath, true);
		    fileOutputStream.write(content.getBytes("UTF-8"));
		    fileOutputStream.write("\r".getBytes());
	}
	
	@After("addOrder()")
	public void securityCheck1(JoinPoint joinPoint) throws IOException{
		Object[] args = joinPoint.getArgs();
		//获得新增的订单
		Order order = (Order) args[0];
		//获得订单对应的用户id
		int uid = order.getUid();
		String username = userService.findUserByUid(uid).getUsername();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String filepath = "C:\\Users\\18741\\workspace\\ElectronicMall\\"+ username +".txt";
			String content = username + "在"+df.format(new Date())+"时新增了订单：" + order.getId();
		    FileOutputStream fileOutputStream = new FileOutputStream(filepath, true);
		    fileOutputStream.write(content.getBytes("UTF-8"));
		    fileOutputStream.write("\r".getBytes());
	}
	
	
}
