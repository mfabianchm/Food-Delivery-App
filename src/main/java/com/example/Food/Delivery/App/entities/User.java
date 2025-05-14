package com.example.Food.Delivery.App.entities;

import com.example.Food.Delivery.App.enums.UserRole;
import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //NotNull is for input validation (when we put info in a body in Postman)
    //Column is specific to database
    @NotNull
    @Size(min = 3, max = 100)
    @Column(nullable = false)
    private String fullName;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @AssertTrue
    @Column(nullable = false)
    private Boolean active = true;

    @Pattern(regexp = "^\\+?[0-9]{10}$")
    private String phoneNumber;

    @Min(1)
    @Max(5)
    private Double rating;

    @Valid
    @OneToMany(mappedBy = "user")
    private List<FoodOrder> foodOrders;

    @Valid
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserAddress> userAddresses = new ArrayList<>();

    public User() {}

    public User(String fullName, String email, String password, UserRole role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = true;
    }

//GETTERS

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public Boolean getActive() {
        return active;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Double getRating() {
        return rating;
    }

    public List<FoodOrder> getFoodOrders() {
        return foodOrders;
    }

    public List<UserAddress> getUserAddresses() {
        return userAddresses;
    }


    //SETTERS

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setFoodOrders(List<FoodOrder> foodOrders) {
        this.foodOrders = foodOrders;
    }

    public void setUserAddresses(List<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }


    /*It returns a readable String that shows the values of the User object’s fields.
        This is super helpful for logging, debugging, or printing the object.
        It makes your object easier to understand when printed or logged, instead of just showing
        a memory reference like User@1a2b3c4d.*/
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", active=" + active +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", rating=" + rating +
                ", foodOrders=" + foodOrders +
                ", userAddresses=" + userAddresses +
                '}';
    }

    /*This method overrides the equals() method to define equality for User
    objects. Two User objects are considered equal if their id fields
    are equal.*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

   /* This method overrides the hashCode() function from the Object class. It generates
    a hash code based on the id field of the object. The Objects.hash(id)
    call creates a consistent and well-distributed hash code using the id,
    which is important when storing objects in hash-based collections like
    HashSet or HashMap.*/
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
