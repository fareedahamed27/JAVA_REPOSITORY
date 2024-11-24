import java.sql.Date;

public class UserProfile {
    private int userId;
    private String email;
    private String phone;
    private String city;
    private String country;
    private Date dob;
    private String gender;
    private float height;
    private float weight;
    private String activityLevel;
    private String goalType;
    private float targetWeight;

    // Constructor
    public UserProfile(int userId, String email, String phone, String city, String country,
                       Date dob, String gender, float height, float weight,
                       String activityLevel, String goalType, float targetWeight) {
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.dob = dob;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.goalType = goalType;
        this.targetWeight = targetWeight;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Date getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public String getGoalType() {
        return goalType;
    }

    public float getTargetWeight() {
        return targetWeight;
    }
}
