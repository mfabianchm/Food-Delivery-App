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

    @Column(name = "first_name", nullable = false)
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String username;

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

    @Pattern(regexp = "^\\+?[0-9]{10}$", message = "Invalid phone number format")
    private String phoneNumber;

    @Min(1)
    @Max(5)
    private Double rating;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private DeliveryDriver deliveryDriver;

    @Valid
    @OneToMany(mappedBy = "user")
    private List<FoodOrder> foodOrders;

    @Valid
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserAddress> userAddresses = new ArrayList<>();

    public User() {}

    public User(String firstName, String lastName, String email, String username, String password, UserRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = true;
    }

//GETTERS

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
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

    public DeliveryDriver getDeliveryDriver() {
        return deliveryDriver;
    }

    //SETTERS


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setDeliveryDriver(DeliveryDriver deliveryDriver) {
        this.deliveryDriver = deliveryDriver;
    }

    /*It returns a readable String that shows the values of the User object’s fields.
            This is super helpful for logging, debugging, or printing the object.
            It makes your object easier to understand when printed or logged, instead of just showing
            a memory reference like User@1a2b3c4d.*/

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", active=" + active +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", rating=" + rating +
                ", deliveryDriver=" + deliveryDriver +
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
