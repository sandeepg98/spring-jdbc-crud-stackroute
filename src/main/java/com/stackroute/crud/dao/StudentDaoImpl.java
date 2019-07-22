package com.stackroute.crud.dao;

import com.stackroute.crud.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int create(Student student) {

        String sql = "INSERT INTO student(name, email, course) VALUES(?,?,?)";

        try {
            int counter = jdbcTemplate.update(sql, new Object[] { student.getName(), student.getEmail(), student.getCourse() });

            return counter;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Student> read() {

        List<Student> studentList = jdbcTemplate.query("SELECT * FROM student", new RowMapper<Student>() {

            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student student = new Student();

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setCourse(rs.getString("course"));

                return student;
            }
        });

        return studentList;
    }

    @Override
    public List<Student> findStudent(int id) {

        List<Student> studentList = jdbcTemplate.query("SELECT * FROM student where id=?", new Object[] { id }, new RowMapper<Student>() {

                    @Override
                    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Student student = new Student();

                        student.setId(rs.getInt("id"));
                        student.setName(rs.getString("name"));
                        student.setEmail(rs.getString("email"));
                        student.setCourse(rs.getString("course"));

                        return student;
                    }

                });

        return studentList;
    }

    @Override
    public int update(Student student) {

        String sql = "UPDATE student SET name=?, email=?, course=? WHERE id=?";

        try {

            int counter = jdbcTemplate.update(sql, new Object[] { student.getName(), student.getEmail(), student.getCourse(), student.getId() });

            return counter;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int id) {

        String sql = "DELETE FROM student WHERE id=?";

        try {

            int counter = jdbcTemplate.update(sql, new Object[] { id });

            return counter;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}