package ru.stqa.pft.mantis.model;
import javax.persistence.*;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "mantis_user_table")
public class UserData {
  @Id
  @Column(name = "id")
  private int id;

  @Expose
  @Column(name = "email")
  private String email;
  @Expose
  @Column(name = "username")
  private String username;
  @Expose
  @Column(name = "password")
  private String password;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserData userData = (UserData) o;

    if (id != userData.id) return false;
    if (email != null ? !email.equals(userData.email) : userData.email != null) return false;
    if (username != null ? !username.equals(userData.username) : userData.username != null) return false;
    return password != null ? password.equals(userData.password) : userData.password == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }
}
