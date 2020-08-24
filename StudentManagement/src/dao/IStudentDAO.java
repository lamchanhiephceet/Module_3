package dao;

import model.Student;
import dao.StudentDAO;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDAO {
    public void insertStudent(Student student) throws SQLException;
    public Student selectStudent(int id);
    public List<Student> selectAllStudents();
    public boolean deleteStudent(int id) throws SQLException;
    public boolean updateStudent(Student student) throws  SQLException;
}