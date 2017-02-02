package model;

import exceptions.IllegalSymbolException;
import exceptions.LengthFieldsException;
import exceptions.SpaceFieldsException;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "`advert`")
@NamedQueries(
        {
                @NamedQuery(name = Advert.GET_ADVERT, query = "select a from Advert a where a.id=:id"),
                @NamedQuery(name = Advert.GET_ADVERT_LIST, query = "select a from Advert a")
        })
public class Advert {
    public static final String GET_ADVERT = "GET_ADVERT";
    public static final String GET_ADVERT_LIST = "GET_ADVERT_LIST";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_advert")
    private String photoAdvert;

    @ManyToOne(targetEntity = Advert.class)
    private Category category;

    @Column(name = "number")
    private long number;

    @Column(name = "price")
    private double price;

    public Advert() {
    }

    public Advert(String name, String description, Category category, double price) throws LengthFieldsException {
        validateFieldLength(name, 6);
        this.name = name;
        this.description = description;
        this.category = category;
        this.number = new Random().nextInt(10000);   //Пусть пока номер объявления будет случайное число
        this.price = price;
    }

    public Advert(int id, String name, String description, Category category, double price) throws LengthFieldsException {
        validateFieldLength(name, 6);
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.number = new Random().nextInt(10000);   //Пусть пока номер объявления будет случайное число
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoAdvert() {
        return photoAdvert;
    }

    public void setPhotoAdvert(String photoAdvert) {
        this.photoAdvert = photoAdvert;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photoAdvert='" + photoAdvert + '\'' +
                ", category=" + category +
                ", number=" + number +
                ", price=" + price +
                '}';
    }
}
