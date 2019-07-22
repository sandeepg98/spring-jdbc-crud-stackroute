package com.stackroute.crud.dao;

import com.stackroute.crud.model.Student;

import java.util.List;

public interface StudentDao {

    public int create(Student student);

    public List<Student> read();

    public List<Student> findStudent(int id);

    public int update(Student student);

    public int delete(int id);
}