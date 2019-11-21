package saushkin.javamock;


import org.json.JSONArray;
import org.json.JSONObject;
import org.postgresql.Driver;

import java.sql.*;

public class DB {
    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:postgresql://localhost/javamockdb";

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
        DriverManager.registerDriver(new Driver());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR, "javamock", "javamock");
    }

    public JSONArray getUsers() throws SQLException {
        // Statement используется для того, чтобы выполнить sql-запрос
        Statement statement = this.connection.createStatement();
        // В данный список будем загружать наши продукты, полученные из БД
        JSONArray users = new JSONArray();
        // В resultSet будет храниться результат нашего запроса,
        // который выполняется командой statement.executeQuery()
        ResultSet resultSet = statement.executeQuery("SELECT id, first_name, last_name, middle_name, role_id FROM users");
        // Проходимся по нашему resultSet и заносим данные в products
        while (resultSet.next()) {
            users.put(new User(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("middle_name"),
                    resultSet.getString("role_id")));
        }
        // Возвращаем наш список
        return users;
    }

    public int addUser(String firstName, String lastName, String middleName, int role_id) throws SQLException {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO users(first_name, last_name, middle_name, role_id) " +
                        "VALUES(?, ?, ?, ?) RETURNING id");
        statement.setObject(1, firstName);
        statement.setObject(2, lastName);
        statement.setObject(3, middleName);
        statement.setObject(4, role_id);
        // Выполняем запрос
        ResultSet rs = statement.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }

        //System.out.println("User "+firstName+" "+middleName+" "+lastName+" has been added successfully");
        return id;
    }

    public JSONObject getUser(String id) throws SQLException {
        Statement statement = this.connection.createStatement();
        JSONObject user = new JSONObject();
        ResultSet resultSet = statement.executeQuery("SELECT id, first_name, last_name, middle_name, role_id FROM users WHERE id=" + id);
        if (resultSet.next()) {
            user.put("id", resultSet.getInt("id"));
            user.put("first_name", resultSet.getString("first_name"));
            user.put("last_name", resultSet.getString("first_name"));
            user.put("middle_name", resultSet.getString("middle_name"));
            user.put("role_id", resultSet.getInt("role_id"));
        }
        return user;
    }
}
