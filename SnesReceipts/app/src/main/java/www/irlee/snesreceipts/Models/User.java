package www.irlee.snesreceipts.Models;

public class User {

    public String UserName;
    public String UserEmail;
    public String FirstName;
    public String LastName;
    public String Password;
    public String UserPicture;
    public String CreatedTime;
    public String UpdatedTime;

    public User(String userName, String userEmail, String firstName, String lastName, String password, String userPicture, String createdTime, String updatedTime) {
        UserName = userName;
        UserEmail = userEmail;
        FirstName = firstName;
        LastName = lastName;
        Password = password;
        UserPicture = userPicture;
        CreatedTime = createdTime;
        UpdatedTime = updatedTime;
    }

    public User() {
    }

    public String getUpdatedTime() {
        return UpdatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        UpdatedTime = updatedTime;
    }

    public String getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        CreatedTime = createdTime;
    }

    public String getUserPicture() {
        return UserPicture;
    }

    public void setUserPicture(String userPicture) {
        UserPicture = userPicture;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
