package init.playerdata;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import exception.InvalidAgeException;
import exception.InvalidEmailException;
//import exception.InvalidAgeException;
//import exception.InvalidEmailException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerQuery {

    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123";
    private static final String SERVER_NAME = "DESKTOP-HANANH\\SQLEXPRESS";
    private static final String DATABASE_NAME = "PlayerCaroGame";
    private static final int PORT = 1433;
    private SQLServerDataSource dataSource;
    private List<Player> players;

    public PlayerQuery() {
        dataSource = configDataSource();
        this.players = new ArrayList<>();
    }

    public PlayerQuery(List<Player> players) {
        dataSource = configDataSource();
        this.players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public SQLServerDataSource configDataSource() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(USERNAME);
        ds.setPassword(PASSWORD);
        ds.setServerName(SERVER_NAME);
        ds.setDatabaseName(DATABASE_NAME);
        ds.setPortNumber(PORT);
        ds.setEncrypt(false);
        ds.setTrustServerCertificate(false);
        return ds;
    }

    public void getPlayerInfomationFromDB() {
        try (Connection conn = dataSource.getConnection()) {
            var sql = "SELECT * FROM dbo.player";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                var playerId = resultSet.getString(1);
                var userName = resultSet.getString(2);
                var password = resultSet.getString(3);
                var fullName = resultSet.getString(4);
                var gender = resultSet.getString(5);
                var email = resultSet.getString(6);
                var phoneNumber = resultSet.getString(7);
                var age = resultSet.getInt(8);                
                players.add(new Player(playerId, userName, password, fullName, gender,
                        email, phoneNumber, age));
            }
        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidEmailException | InvalidAgeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int setPlayerInformationToDB(Player player) {
        players.add(player);
        this.players.forEach(e-> {
            System.out.println(e);
        });
        try ( Connection conn = dataSource.getConnection()) {
            var sql = "INSERT INTO dbo.player(player_id, user_name, password, full_name,"
                    + " gender, email, phone_number, age) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player.getPlayerId());
            ps.setString(2, player.getUserName());
            ps.setString(3, player.getPassword());
            ps.setString(4, player.getFullName());
            ps.setString(5, player.getGender());
            ps.setString(6, player.getEmail());
            ps.setString(7, player.getPhoneNumber());
            ps.setInt(8, player.getAge());
            return ps.executeUpdate();
        } catch (SQLServerException ex) {
            ex.printStackTrace();
            return -1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;

        }
    }

    public void showPlayerInDB() {
        players.forEach(stu -> {
            System.out.println(stu);
        });
    }

    public static void main(String[] args) {
        var playerQuery = new PlayerQuery();
        var dataSource = playerQuery.configDataSource();
        try ( Connection conn = dataSource.getConnection()) {
            System.out.println(conn.getCatalog());
        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        playerQuery.showPlayerInDB();

    }
}
