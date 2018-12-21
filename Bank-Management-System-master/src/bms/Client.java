package bms;

public class Client {

    private String name;
    private int age;
    private int nationalNumber;
    private String address;
    private String email;
    private String job;

    public Client() {
    }

    public Client(String name, int age, int nationalNumber, String address, String email,
            String job) {
        this.name = name;
        this.age = age;
        this.nationalNumber = nationalNumber;
        this.address = address;
        this.email = email;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getNationalNumber() {
        return nationalNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNationalNumber(int nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" + "Age: " + age + "\n"
                + "National Number: " + nationalNumber + "\n" + "Address: " + address + "\n"
                + "Email: " + email + "\n" + "Job: " + job + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Client)) {
            return false;
        }
        Client c = (Client) obj;
        return c.nationalNumber == nationalNumber;
    }
}
