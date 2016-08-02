package pal.database.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dhruvr on 1/8/16.
 */

@Entity
@Table(name  ="test")
public class Test implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "userNme")
    private String userName;

    @Basic
    @Column(name = "password")
    private String passWord;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (!id.equals(test.id)) return false;
        if (!userName.equals(test.userName)) return false;
        return passWord.equals(test.passWord);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + passWord.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
