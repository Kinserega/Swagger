select * from student;

select s.* from student as s where s.age between 18 and 19;

select s.name from student as s;

select s.* from student as s where s.name like ('%С%');

select s.* from student as s where s.age<s.id;

select s.* from student as s ORDER BY s.age;