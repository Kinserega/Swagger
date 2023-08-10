-- liquibase formatted sql

-- changeset skindrat:1

CREATE index student_name_index on student (name);
create index faculty_nc_inx on faculty (name, color);