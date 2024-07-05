public class Doctor {
    
    private String name;
    private String speciality;
    public boolean available;

    public Doctor(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public synchronized boolean isAvailable() {
        return available;
    }

    public synchronized void setAvailable(boolean available) {
        this.available = available;
    }

}