1.select count(*) from teacher where tname like '李%';
2.select count(*),sex from student group by sex;
3.select count(*),sname from student group by sname;
4.select * from student where year(birthday)=1981;
5.select se.sid,se.score,student.sname from se,student where se.sid=student.sid && se.score>=60;
6.select count(distinct sid) from se where cid>0;
7.select count(*),sid from se group by sid having count(*)>=2;
8.select cid '课程ID',max(score) 最高分,min(score) 最低分 from se group by cid;
9.select cid,count(*) from se group by cid order by count(*) desc,cid;


以下练习针对部门员工表;
1.select * from emp where 2018-year(hiredate) >= 38;
2.select * from emp where month(hiredate)=12;
3.select * from emp where month(hiredate)>=6;
4.select * from emp where month(hiredate)>=6 && year(hiredate)=1980;
5.select ename,empno from emp where length(ename)=4;
6.select min(sal),job from emp group by job;
7.select count(*) 人数,month(hiredate) 入职月份 from emp where year(hiredate)=1980 group by month(hiredate);
8.select deptno 部门编号,max(sal) 最高工资 from emp group by deptno;
9.select deptno 部门编号,job 职位,max(sal) 最高工资 from emp group by deptno,job;
10.select deptno,sum(sal),avg(sal) from emp group by deptno;
11.select deptno,avg(sal) from emp where deptno<=20 group by deptno;
12.select deptno,avg(sal) from emp group by deptno having avg(sal)>2000;
13.select count(*),job from emp where job = 'MANAGER';
14.select * from emp order by sal desc limit 3;
15.select * from emp order by sal desc limit 5,5;
表连接查询;
1.select sname,course.cname,score,teacher.tname from student,course,se,teacher 
	where student.sid=se.sid && 
		  se.cid = course.cid && 
		  course.tid = teacher.tid && 
		  student.sname='李四';