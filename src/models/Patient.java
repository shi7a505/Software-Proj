package models;

/**
 * Patient Model Class
 * يمثل بيانات المريض في النظام
 */
public class Patient {
    private String id;
    private String name;
    private int age;
    private String phone;
    
    public Patient(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return "Patient{ID='" + id + "', Name='" + name + "', Age=" + age + "}";
    }
}
