package model;

public class Student {
    protected int id;
    protected String code;
    protected String name;
    protected String address;

    public Student() {}
    public Student(String code, String name, String address) {
        super();
        this.code = code;
        this.name = name;
        this.address = address;
    }
    public Student(int id, String code, String name, String address) {
        super();
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}