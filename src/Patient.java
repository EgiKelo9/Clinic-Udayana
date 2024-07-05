import java.time.*;
import java.time.format.*;

public class Patient {
    
    private String name;
    private int age;
    private String category;
    private String description;
    private LocalDateTime registTime;
    public boolean isServe = false;

    public Patient(String name, int age, String category, String description) {
        this.name = name;
        this.age = age;
        this.category = category;
        this.description = description;
        this.registTime = LocalDateTime.now();
    }

    public String getRegistTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return registTime.format(formatter);
    }

    public String getRegistDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return registTime.format(formatter);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

}