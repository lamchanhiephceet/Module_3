package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Student;

public class StudentDAO implements IStudentDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/student?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "1205";

    private static final String INSERT_STUDENTS_SQL = "INSERT INTO students" + "(code,name,address)VALUES" + "(?,?,?);";

    private static final String SELECT_STUDENTS_BY_ID = "select id,code,name,address from students where id =?";
    private static final String SELECT_ALL_STUDENTS = "select * from students";
    private static final String DELETE_STUDENTS_SQL = "delete from students where id = ?;";
    private static final String UPDATE_STUDENTS_SQL = "update students set code = ?,name= ?, address =? where id = ?;";

    public StudentDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("connect successfully!");
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("connect failure!");

            e.printStackTrace();
        }
        return connection;
    }

    public void insertStudent(Student student) throws SQLException {
        System.out.println(INSERT_STUDENTS_SQL);

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)) {
            preparedStatement.setString(1, student.getCode());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getAddress());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Student selectStudent(int id) {
        Student student = null;

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String address = rs.getString("address");
                student = new Student(id, code, name, address);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return student;
    }


    public List<Student> selectAllStudents() {

        List<Student> students= new ArrayList<>();

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String name = rs.getString("name");
                String address = rs.getString("address");
                students.add(new Student(id, code, name, address));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }

    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENTS_SQL)) {
            statement.setString(1, student.getCode());
            statement.setString(2, student.getName());
            statement.setString(3, student.getAddress());
            statement.setInt(4, student.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}