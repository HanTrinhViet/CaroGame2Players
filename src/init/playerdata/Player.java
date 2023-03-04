package init.playerdata;

//import exception.InvalidAgeException;
//import exception.InvalidEmailException;

import exception.InvalidAgeException;
import exception.InvalidEmailException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author HanAnh
 */
public class Player {

    private static int nextId = 1;
    private String playerId;
    private String userName;
    private String password;
    private String fullName;
    private String gender;
    private String email;
    private String phoneNumber;
    private int age;

    public Player() {

    }

    public Player(String playerId, String userName, String password, String fullName,
            String gender, String email, String phoneNumber, int age) 
            throws InvalidEmailException, InvalidAgeException {
        this.setPlayerId(playerId);
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.setEmail(email);
        this.phoneNumber = phoneNumber;
        this.setAge(age);
    }

    public static void setNextId(int presentId) {
        nextId = presentId;
    }

    public static int getNextId() {
        return nextId;
    }

    public void setPlayerId(String playerId) {
        if (playerId == null) {
            this.playerId = "PLY" + nextId;
            nextId++;
        } else {
            this.playerId = playerId;
        }
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException {
        var regex = "^[a-z]+[\\w._]*@gmail.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            this.email = email;
        } else {
            this.email = null;
            var msg = "Email sai định dạng đã xảy ra ngoại lệ";
            throw new InvalidEmailException(msg, this.email);
        }
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws InvalidAgeException {
        if (age >= 1 && age <= 120) {
            this.age = age;
        } else {
            this.age = 0;
            var msg = "Tuổi không hợp lệ xảy ra ngoại lệ";
            throw new InvalidAgeException(msg, this.age);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.userName);
        hash = 73 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "[id=" + playerId
                + ", userName='" + userName + '\''
                + ", password='" + password + '\''
                + ", fullName='" + fullName + '\''
                + ", gender='" + gender + '\''
                + ", email='" + email + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", age=" + age
                + ']';
    }
}
