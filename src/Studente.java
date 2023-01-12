import java.sql.*;
import java.util.ArrayList;

public class Studente {
    private String name;
    private String surname;

    final String DB_URL = "jdbc:mysql://localhost:3306/newdb";
    final String USER = "root";
    final String PASS = "admin";
    Connection connection = null;

    ArrayList<Studente> ItaliansStudents = new ArrayList<>();
    ArrayList<Studente> GermansStudents = new ArrayList<>();
    public Studente (){};

    public Studente (String surname, String name){
        this.name = name;
        this.surname = surname;
    }
    public void doViewforItalians() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            String queryTakeItaliansNameAndSurname = ""
                    + "CREATE view ItalianStudents as SELECT last_name, first_name FROM newdb.students WHERE country = 'Italy';";
            statement.executeUpdate(queryTakeItaliansNameAndSurname);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void doViewforGermans() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            String queryTakeGermansNameAndSurname = ""
                    + "CREATE view GermansStudents AS SELECT last_name, first_name FROM newdb.students WHERE country = 'German';";
            statement.executeUpdate(queryTakeGermansNameAndSurname);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void makeItaliansArray() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            String queryItaliansArray = ""
                    + "SELECT * FROM ItaliansStudents";
            ResultSet resultSet = statement.executeQuery(queryItaliansArray);
            while (resultSet.next()){

                System.out.println(resultSet);

                ItaliansStudents.add(new Studente(resultSet.getString("first_name"),resultSet.getString("last_name")));
            }
            GermansStudents.forEach(System.out::println);

            statement.close();
            resultSet.close();
            connection.close();
            System.out.println("eseguito con successo");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void makeGermansArray() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            String queryGermansArray = ""
                    + "SELECT * FROM GermansStudents";
            ResultSet resultSet = statement.executeQuery(queryGermansArray);
            while (resultSet.next()){

                System.out.println(resultSet);

                GermansStudents.add(new Studente(resultSet.getString("first_name"),resultSet.getString("last_name")));
            }
            GermansStudents.forEach(System.out::println);

            statement.close();
            resultSet.close();
            connection.close();
            System.out.println("eseguito con successo");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
