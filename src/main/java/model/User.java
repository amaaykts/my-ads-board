package model;


import exceptions.IllegalSymbolException;
import exceptions.LengthFieldsException;
import exceptions.SpaceFieldsException;
import exceptions.ValidateEmailFailException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`user`")
@NamedQueries(
        {
                @NamedQuery(name = User.GET_USER, query = "select u from User u where u.id=:id"),
                @NamedQuery(name = User.GET_USER_LIST, query = "select u from User u"),
                @NamedQuery(name = User.GET_USER_NAME, query = "select u from User u where u.name = :name")
        })
public class User {
    public static final String GET_USER = "User.GET_USER";
    public static final String GET_USER_NAME = "User.GET_USER_LIST";
    public static final String GET_USER_LIST = "User.GET_USER_NAME";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(targetEntity = Advert.class, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_adverts",
//            joinColumns = @JoinColumn(name = "123", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "321", referencedColumnName = "id"))
    private List<Advert> adverts = new ArrayList<Advert>();

    @Column(name = "photo_name")
    private String photoName; //название файла с фото

    @Column(name = "date_of_registation")
    private Date dateOfRegistation;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "password")
    private String passwordHashCode;

    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    private Role role;


    public User() {
//        this.passwordHashCode = DigestUtils.md5Hex("a1maaykts");
    }

    public User(String name, String surname, String email, Date dateOfBirth, Role role, String password) throws ValidateEmailFailException, IllegalSymbolException, LengthFieldsException, SpaceFieldsException {
        validateFieldEmail(email);
        validateFieldIllegalSymbol(password);
        validateFieldIllegalSymbol(email);
        validateFieldLength(password, 5);
        validateFieldLength(email, 7);
        validateFieldSpace(email);
        validateFieldSpace(password);
        this.name = name;   //устанавливаем имя
        this.surname = surname; //устанавливаем фамилию
        this.email = email; //это и логин и почта, должно быть уникально
        this.dateOfRegistation = new Date();    //Текущая дата
        this.dateOfBirth = dateOfBirth; //дата рождения
        this.passwordHashCode = DigestUtils.md5Hex(password);   //Хешируем пароля
        this.role = role;   //Роль нужно будет получать из базы
    }

    public User(int id, String name, String surname, String email, Date dateOfBirth, Role role, String password) throws ValidateEmailFailException, IllegalSymbolException, LengthFieldsException, SpaceFieldsException {
        validateFieldEmail(email);
        validateFieldIllegalSymbol(password);
        validateFieldIllegalSymbol(email);
        validateFieldLength(password, 5);
        validateFieldLength(email, 7);
        validateFieldSpace(email);
        validateFieldSpace(password);
        this.id = id;   //Номер
        this.name = name;   //устанавливаем имя
        this.surname = surname; //устанавливаем фамилию
        this.email = email; //это и логин и почта, должно быть уникально
        this.dateOfRegistation = new Date();    //Текущая дата
        this.dateOfBirth = dateOfBirth; //дата рождения
        this.passwordHashCode = DigestUtils.md5Hex(password);   //Хешируем пароля
        this.role = role;   //Роль нужно будет получать из базы
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

    public List<Advert> getAdverts() {
        return adverts;
    }

//    public void setAdverts(List<Advert> adverts) {
//        this.adverts = adverts;
//    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public Date getDateOfRegistation() {
        return dateOfRegistation;
    }

//    public void setDateOfRegistation(Date dateOfRegistation) {
//        this.dateOfRegistation = dateOfRegistation;
//    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPasswordHashCode() {
        return passwordHashCode;
    }

    public void setPasswordHashCode(String password) {
        this.passwordHashCode = DigestUtils.md5Hex(password);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Проверка длины поля
     */
    private void validateFieldLength(String field, int length) throws LengthFieldsException {
        if (field.length() <= length) {
            throw new LengthFieldsException("Введенная длина слишком маленькая");
        }
    }

    /**
     * Проверка наличия пробела в поле
     */
    private void validateFieldSpace(String field) throws SpaceFieldsException {
        if (field.contains(" ")) {
            throw new SpaceFieldsException("В поле был использован пробел");
        }
    }

    /**
     * Проверка на символ кавычки или слеша
     */
    private void validateFieldIllegalSymbol(String field) throws IllegalSymbolException {
        if (field.contains("'") || field.contains("\"") || field.contains("\\")) {
            throw new IllegalSymbolException("Не разрешенный символ");
        }
    }

    /**
     * Простая проверка email
     */
    private void validateFieldEmail(String email) throws ValidateEmailFailException {
        if (!email.contains("@")) {
            throw new ValidateEmailFailException("Это не email");
        }
        if (email.split("@")[1].split("\\.")[0].length() < 1) {
            throw new ValidateEmailFailException("Это не email");
        }
        if (email.split("@")[0].length() < 4) {
            throw new ValidateEmailFailException("Это не email");

        }
        if (!email.split(("@"))[1].contains(".")) {
            throw new ValidateEmailFailException("Это не email");

        }
        if (email.split(("@"))[1].split("\\.")[1].length() < 2) {
            throw new ValidateEmailFailException("Это не email");

        }
    }

    /**
     * Сгенериреум хэшкод через Apache Common Codec и его потом сравним с нашим полем
     */
    public String generateHashForPassword(String password) {
        return DigestUtils.md5Hex(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", adverts=" + adverts +
                ", photoName='" + photoName + '\'' +
                ", dateOfRegistation=" + dateOfRegistation +
                ", dateOfBirth=" + dateOfBirth +
                ", passwordHashCode='" + passwordHashCode + '\'' +
                ", role=" + role +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }
}