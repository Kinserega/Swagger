create table person(
                       id bigint,
                       name character(100) primary key ,
                       age INTEGER,
                       can_drivers boolean,
                       car_id bigint references car (id)
);
create table car(
                    id bigint primary key ,
                    marka_car varchar,
                    model_car varchar,
                    price numeric
);

select student.name, student.age, faculty.name
from student inner join faculty on student.faculty_id = faculty.id;

SELECT student.name, student.age
FROM student
         RIGHT JOIN avatar on student.id = avatar.student_id;
