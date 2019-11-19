package saushkin.javamock;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DB {
    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:C:/soft/SQLiteDatabaseBrowserPortable/Data/javamockdb.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DB
    private static DB instance = null;
    private String errorMessage;

    public static synchronized DB getInstance() throws SQLException {
        if (instance == null)
            instance = new DB();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private DB() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public List<User> getUsers() throws SQLException {
        // Statement используется для того, чтобы выполнить sql-запрос
 Statement statement = this.connection.createStatement();
            // В данный список будем загружать наши продукты, полученные из БД
            List<User> users = new ArrayList<User>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT id, first_name, last_name, middle_name, role FROM users");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("middle_name"),
                        resultSet.getString("role")));
            }
            // Возвращаем наш список
            return users;
    }

    public void addUser(User user) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO users(`first_name`, `last_name`, `middle_name`, `role`) " +
                        "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, user.firstName);
            statement.setObject(2, user.lastName);
            statement.setObject(3, user.middleName);
            statement.setObject(4, user.role);
            // Выполняем запрос
            statement.execute();
            System.out.println("User has "+user.firstName+" "+user.middleName+" "+user.lastName+" been added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
