# mybatisOne

有这么一个应用场景：需要在Mybatis框架上进行多个连接池的配置，然后进行多种SQL语句的测试，因为有些SQL语句的结构比较复杂，有些会涉及到很多group by、join操作还有一些聚集函数的使用，同时数据量又较大。 
在这种情况下，常规的Mybatis面对对象进行操作的办法就不再试适用，查看Mybatis官方手册，提供了SQL构造器的办法也会有较大的工作量，所以选择直接执行SQL语句


mapper 配置文件写法


```
	<select id="superSelect" parameterType="String" resultType="java.util.LinkedHashMap"> 
		${sql} 
	</select>

```

调用时

```
		List<Agricultural> agricultural1 = sqlSessionTemplate.selectList("superSelect",new SQLAdapter(sql));
```
typeAliases配置
```
     	<typeAlias alias="sqladapter" type="com.test.dtjc.tdsc0002.action.SQLAdapter" />
```


sqlAdaper 类需要自己写
```
package com.test.dtjc.tdsc0002.action;


public class SQLAdapter {  
    String sql;  
  
    public SQLAdapter(String sql) {  
        this.sql = sql;  
    }  
  
    public String getSql() {  
        return sql;  
    }  
  
    public void setSql(String sql) {  
        this.sql = sql;  
    }  
}

```
代码参照
https://download.csdn.net/download/guoruijun_2012_4/10716454

https://github.com/knowledge0603/mybatisOne
