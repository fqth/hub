package com.lgvalle.material_animations;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apple on 2017/5/25.
 */

@Aspect
public class AspectJ {
    static  int flags=0;
    long start;
    long end;
    long start1;
    HashMap<String, Long> counts= new HashMap<String , Long>();
    ArrayList<String> log=new ArrayList<String>();
    long end1;
    @Before("within(*.*.material_animations.MainActivity||*.*.material_animations.RevealActivity||*.*.material_animations.SamplesRecyclerAdapter)")

   //@Before("within(*.*.material_animations.*)")

    public void onggMethodBefore(JoinPoint joinPoint) throws Throwable{
        String name =  joinPoint.getSignature().getName();

        start = System.currentTimeMillis();

        start1=System.nanoTime();

        log.add(name+":start:"+start1);
        boolean flag=counts.containsKey(name);  //判断函数是否调用过
        if(flag==false)//不存在，置为1
        {
            counts.put(name,(long)1);
        }
        else//存在，加一
        {
            counts.put(name,counts.get(name)+1);
        }
    }
   // @After("within(*.*.material_animations.*)")
   @After("within(*.*.material_animations.MainActivity||*.*.material_animations.RevealActivity||*.*.material_animations.SamplesRecyclerAdapter)")
    public void onggMethodAfter(JoinPoint joinPoint) throws Throwable{

        String name =  joinPoint.getSignature().getName();

        end = System.currentTimeMillis();

        end1=System.nanoTime();

        log.add(name+":end:"+end1);
        if(flags!=300)
        {
            flags++;
            return ;
        }
        flags=0;
        clatime ct = new clatime();
        ct.getTime(log);
        clacounts cc= new clacounts();
        cc.getCounts(counts);

        log.clear();
        counts.clear();
        // Log.d(TAG,"sb");
//        for(String str:log)
//        {
//            System.out.println(str);
//        }
        log = new ArrayList<String>();
        counts=new HashMap<String , Long>();//记录调用次数
    }
}