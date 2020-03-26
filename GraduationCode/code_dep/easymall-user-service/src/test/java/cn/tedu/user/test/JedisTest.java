/*package cn.tedu.user.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisTest {
	
	 * 构建一个jedis的java对象,连接底层客户端
	 * 实现api方法调用客户端命令
	 * set("name","wanglaoshi"),"set name wanglaoshi"
	 
	@Test
	public void test01(){
		Jedis jedis=new Jedis("10.9.39.13",6379);
		//jedis.set("name", "王老师");//set name 王老师
		System.out.println(jedis.get("sdfasd"));
		//jedis.exists()
		jedis.close();
		//jedis.hmset(ticket, map);
		//map={userName=value,userPassword=value}
		jedis.hset("user_id", "userName", value);
		jedis.hset("user_id", "userEmail", value);
		jedis.hset("user_id", "userName", value);
		jedis.hset("user_id", "userName", value);
		jedis.hset("user_id", "userName", value);
	}
	
	@Test
	public void test02(){
		String key="name12";
		String keyName=new String("name12");
		//System.out.println(key.equals(keyName));
		System.out.println(key.hashCode());
		System.out.println(keyName.hashCode());
		System.out.println(key.hashCode()==keyName.hashCode());
	}
	@Test
	public void test03(){
		String key1="name";
		String key2="name12";
		//System.out.println(key.equals(keyName));
		System.out.println(key1.hashCode());
		
		System.out.println(key1.hashCode()&Integer.MAX_VALUE);
		System.out.println(key2.hashCode()&Integer.MAX_VALUE);
		System.out.println(key2.hashCode());
		System.out.println(key2.hashCode()+Integer.MAX_VALUE);
		
	}
	@Test
	public void test04(){
		int a=1000;
		System.out.println("-199的二进制");
		System.out.println(Integer.toBinaryString(a));
		int result=a&Integer.MAX_VALUE;
		System.out.println("-199的31位保真运算二进制");
		System.out.println(Integer.toBinaryString(result));
		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
	}
	
	@Test
	public void test05(){
		//准备一下连接的所有节点jedis对象
		Jedis jedis1=new Jedis("10.9.39.13",6379);
		Jedis jedis2=new Jedis("10.9.39.13",6380);
		Jedis jedis3=new Jedis("10.9.39.13",6381);
		//模拟生成海量数据
		int count=0;
		for(int i=0;i<1000;i++){
			String key=UUID.randomUUID().toString();
			//利用hash取余算法,处理存储的分布式计算
			//key是0/1/2的概率是多少
			//获取算法的结果 0 1 2 
			int n=(key.hashCode()&Integer.MAX_VALUE)%3;
			if(n==0){
				count++;
				jedis1.set(key, "");
			}else if(n==1){
				jedis2.set(key, "");
			}else{
				jedis3.set(key, "");
			}
		}
		System.out.println("是的概率:"+count/1000.0);
	}
	@Test
	public void test06(){
		//使用jedis提供的分片计算对象实现分布式处理数据
		//收集节点信息,告诉jedis到底有多少个分片信息
		List<JedisShardInfo> info=new ArrayList<JedisShardInfo>();
		//将节点信息封装添加到info
		info.add(new JedisShardInfo("10.9.39.13", 6379, 1000, 1000, 5));
		info.add(new JedisShardInfo("10.9.39.13", 6380));
		info.add(new JedisShardInfo("10.9.39.13", 6381));
		//利用节点信息对象info构造一个封装了分片算法的分片对象
		ShardedJedis sJedis=new ShardedJedis(info);
		//通过测试生成的任何key值都会进行分片计算
		for(int i=0;i<1000;i++){
			String key=UUID.randomUUID().toString();
			sJedis.set(key, "");
		}
		System.out.println(sJedis.get("30dd7183-35f4-42ba-b2d1-ca137225e1a9"));
		sJedis.close();
	}
	//连接池
	@Test
	public void test07(){
		//收集节点信息,告诉jedis到底有多少个分片信息
		List<JedisShardInfo> info=new ArrayList<JedisShardInfo>();
		//将节点信息封装添加到info
		info.add(new JedisShardInfo("10.9.39.13", 6379, 1000, 1000, 5));
		info.add(new JedisShardInfo("10.9.39.13", 6380));
		info.add(new JedisShardInfo("10.9.39.13", 6381));
		//构造一个分片连接池需要一些配置信息,连接池的初始化连接数
		//最大空闲数,最小空闲数,连接池的连接上限
		GenericObjectPoolConfig config=
				new GenericObjectPoolConfig();
		config.setMaxTotal(200);//最大连接数
		config.setMaxIdle(8);//最大空闲数
		config.setMinIdle(3);//最小空闲数
		//构造连接池
		ShardedJedisPool sPool=new 
				ShardedJedisPool(config,info);
		ShardedJedis sJedis = sPool.getResource();
		sJedis.set("location", "北京");
		System.out.println(sJedis.get("location"));
		sPool.returnResource(sJedis);
	}
	@Test
	public void sentinel(){
		//多个哨兵之间没有监控关系，顺序遍历，哪个能通就走哪个，不通，再检查下一个哨兵
    	Set<String> sentinels = new HashSet<String>();
    	sentinels.add(new HostAndPort("10.9.104.184",26379).toString());
    	sentinels.add(new HostAndPort("10.9.104.184",26380).toString());
    	
    	//mymaster是在sentinel.conf中配置的名称
    	//sentinel monitor mymaster 192.168.163.200 6380 1
    	JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
    	System.out.println("现役master：" + pool.getCurrentHostMaster());
    	
    	Jedis jedis = pool.getResource();
		//jedis.auth("123456");
    	jedis.set("num", "123123");
    	System.out.println(jedis.get("num"));
    	pool.returnResource(jedis);   
    	
	}
	
	
	
	
	
	@Test
	public void hashSlot(){
		String key="name";
		int slot=(key.hashCode()&Integer.MAX_VALUE)%16384;
		System.out.println(slot);
	}
	//以 8位二进制为例 从左到右,定义0-7槽道号
	//模拟一个数字为内存的二进制,计算答应
	//0号槽道的二进制值:1
	//1号槽道的二进制值:1
	@Test
	public void binMove(){
		byte bin=106;
		
		System.out.println("节点内存的位序列:"+Integer.toBinaryString(bin));
		for(int i=0;i<8;i++){
			//获取下标 0 1 2 3 4 5 6 7 i
			//对应获取二进制值 1/0
			//0号槽道 01101010 第一个0 将整数二进制向右移动七位
			int bit=(bin>>(7-i))&1;
			boolean guanli=bit==1?true:false;
			System.out.println("槽道号为"+i+"的管理权是"+guanli);
		}
			
	}
	
	
	
	
}







*/