package Model;

public class Data {

    private int id;
    private String firstname;
    private String lastname;
    private String timeStamp;
    private String notes;
    private String company;
    private String phone;
    private String email;



    public Data() {
    }

    public Data(int id, String firstname, String lastname,String company ,String phone,String email, String notes,String timeStamp) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
        this.timeStamp = timeStamp;


    }

    public Data(String firstname, String phone, String date) {
        this.firstname = firstname;
        this.phone = phone;
        this.timeStamp = date;
    }
    public Data(String phone) {
        this.phone = phone;

    }




    public Data(int id,String firstname, String lastname, String company ,String phone,String email , String notes ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.notes = notes;

    }

    public Data( String firstname, String lastname, String company ,String phone,String email, String notes) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.notes = notes;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
