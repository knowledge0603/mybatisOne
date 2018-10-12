package com.jefry;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



//  百度搜索 ： (association 为什么只查出一条数据)  https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=association%20%E4%B8%BA%E4%BB%80%E4%B9%88%E5%8F%AA%E6%9F%A5%E5%87%BA%E4%B8%80%E6%9D%A1%E6%95%B0%E6%8D%AE&oq=association&rsv_pq=8ab7dbae00059955&rsv_t=a981hKNDui%2BKd87QD11hBViybs57oTKw5iXyLcTq9Oykq9gfJXCcjYX2wVw&rqlang=cn&rsv_enter=1&rsv_sug1=8&rsv_sug7=100&rsv_sug3=60&rsv_sug2=0&inputT=33439&rsv_sug4=33439
//  mybatis association 关联查询时，只返回第一条记录    http://ask.csdn.net/questions/196306
//  http://cczakai.iteye.com/blog/1276722


public class Test {
	static String resource = "mybatis-config.xml";

	public static void main(String[] args) throws IOException {
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			//List<Mobile> mobileList = session.selectList("mobile.selectMobile",1);
			List<Agricultural> mobileList = session.selectList("mobile.selectAllocatingland",1);			
            //--------------以下例子是 输入任意sql 不需要 mybatis 配置，执行原生sql，直接执行sql不需要配置
			//适应多表多条件动态拼接sql语句情况
			String sql ="";
			sql = sql +"SELECT"
			+ " A.XZQ_DM  AS XZQ_DM,"
			+ " A.XM_MC   AS XM_MC,"
			+ " A.GD_GUID AS GD_GUID,"
			+ " A.PZ_WH   AS PZ_WH,"
			+ " A.GD_ZMJ  AS GD_ZMJ,"
			+ " A.GY_MJ   AS GY_MJ,"
			+ " A.XM_ZT   AS XM_ZT,"
			+ " A.PZ_RQ   AS PZ_RQ,"
			+ " A.ZD_GUID AS ZD_GUID,"
			+ " A.PZ_JG   AS PZ_JG,"
			+ " A.GY_FS   AS GY_FS,"
			+ " A.ZD_BH   AS ZD_BH,"
			+ " A.TD_JB   AS TD_JB,"
			+ " A.KFLY_BZ AS KFLY_BZ,"
			+ " A.DZ_BA_BH AS DZ_BA_BH,"
			+ " A.TZZT_XZ  AS TZZT_XZ,"
			+ " A.HY_FL    AS HY_FL,"
			+ " B.SYQR    AS SYQR"
		    + " FROM T_GDXM A,T_HBGY_KZ B" 
		    + " WHERE A.GD_GUID = B.GD_GUID" 
		    + " and A.GD_GUID = '002895bf-5cda-4ee8-bd92-1d8aa873b022' ";

			List<Agricultural> agricultural1 = session.selectList("superSelect",new SQLAdapter(sql));
			agricultural1.toArray();
			//--------------
			System.out.println("mobileList.size():"+mobileList.size());
			for(Agricultural i: mobileList)
			{
				System.out.println("bh:"+i.getBH());
				System.out.println("GD_GUID:"+i.getGD_GUID());
				System.out.println("GD_ZMJ:"+i.getGD_ZMJ());
				System.out.println("PZ_JG:"+i.getPZ_JG());
				System.out.println("PZ_RQ:"+i.getPZ_RQ());
				/*Set<Callrecord> callrecordList = i.getCallrecordSet();
				for(Callrecord k: callrecordList)
				{
					System.out.println(", 通话时长:"+k.getCallDuration());
					System.out.println(", call record noid:"+k.getCallNoid());
					System.out.println(", call record CallPasstive:"+k.getCallPasstive());
					System.out.println(", call record id:"+k.getId());					
				}*/
				System.out.println("-------------");
			}
		//	TyZcsLogReport re:lis
			
			
			
		} finally {
			session.close();
		}
	}

}
