package models;

public class Users {

    private int id;
    private String username1,fullname,email,address, password1;

    public Users(int id, String username1, String fullname, String email, String address, String password1) {
        this.id = id;
        this.username1 = username1;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.password1 = password1;
    }

    public int getId() {
        return id;
    }

    public String getUsername1() {
        return username1;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword1() {
        return password1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }
}
