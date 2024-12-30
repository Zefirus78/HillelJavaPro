package hw_29;

import java.sql.*;

public class EmployeeDAO {
    public void add(Employee emp) {
        String insertSQL = "INSERT INTO employees(name, age, position, salary) VALUES(?, ?, ?, ?);";
        try (Connection connection = DBConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)){

        preparedStatement.setString(1, emp.getName());
        preparedStatement.setInt(2, emp.getAge());
        preparedStatement.setString(3, emp.getPosition());
        preparedStatement.setDouble(4, emp.getSalary());

        preparedStatement.executeUpdate();

            System.out.println("Added successfully!\n");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Employee emp) {
        String updateSQL = "UPDATE employees SET name = ?, age = ?, position = ?, salary = ? WHERE id = ?;";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)){

            preparedStatement.setString(1, emp.getName());
            preparedStatement.setInt(2, emp.getAge());
            preparedStatement.setString(3, emp.getPosition());
            preparedStatement.setDouble(4, emp.getSalary());
            preparedStatement.setInt(5, emp.getId());

            preparedStatement.executeUpdate();

            System.out.println("Updated successfully!\n");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String deleteSQL = "DELETE FROM employees WHERE id = ?;";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)){

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            System.out.println("Deleted successfully!\n");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void view() {
        String viewSQL = "SELECT * FROM employees;";
        try (Connection connection = DBConnector.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(viewSQL);

            while(resultSet.next()) {
                Employee employee = new Employee(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("position"),
                    resultSet.getDouble("salary")
                );

                System.out.println(employee);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
