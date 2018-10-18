package com.xcm.Leo.util;

import java.util.Iterator;
import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	 private  Jedis jedis; 
	      

	     public RedisUtil(){
	         //连接redis服务器，192.168.10.47:6379
	         jedis = new Jedis("192.168.10.47", 6379);
	         //权限认证
	          jedis.auth("redis-ibank");
	          System.out.println("连接成功");
	          System.out.println("服务正在运行: "+jedis.ping()); 
	      }
	     
	     public void quitConnection(Jedis jedis) {
	 		if (jedis != null) {
	 			jedis.quit();
	 			//pool.returnResource(jedis);
	 		}
	 	}
	     
	     public String getValue(String key){
	 		String value = null;
	 		try {
	 			if(jedis == null || !jedis.exists(key)){
	 				quitConnection(jedis);
	 				return value;
	 			}
	 			//获取key对应的数据类型
	 			String type = jedis.type(key);
//	 			log.info("key:" + key + " 的类型为：" + type);
	 			if(type.equals("string")){
	 				//get(key)方法返回key所关联的字符串值
	 				value = jedis.get(key);
	 			}
	 			if(type.equals("hash")){
	 				//一下方法仅适用于list.size=1时，否者value将取集合中最后一个元素的值
	 				List<String> list = jedis.hvals(key);//hvals(key)返回哈希表 key 中所有域的值
	 				//Set<String> set = redis.hkeys(key);
	 				Iterator<String> it=list.iterator();
	 				while(it.hasNext()){   
	 			        value = it.next();
//	 			        log.info("value:"+value);
	 				}
	 			}
	 			if(type.equals("list")){
//	 				log.info(key+"类型为list暂未处理...");
	 			}
	 			if(type.equals("set")){
//	 				log.info(key+"类型为list暂未处理...");
	 			}
	 		} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}finally{
	 			//关闭连接
	 			quitConnection(jedis);
	 		}
	 		return value;
	 	}
	     
	     public static void main(String[] args){
	    	 RedisUtil myredis = new RedisUtil();
	    	 String aa = myredis.getValue("112853115322");
	    	 System.out.println(aa);
	    	 
	     }
}
