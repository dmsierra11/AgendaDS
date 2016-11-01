package example.danielsierraf.agendads.data;

import example.danielsierraf.agendads.Constant;

/**
 * Created by danielsierraf on 10/30/16.
 */

public class Contact {

    private int id = 0;
    private String name = "";
    private String last_name = "";
    private String phone_number = "";
    private String email = "";
    private String address = "";
    private String tone = "";

    public Contact() {
    }

    public Contact(int id) {
        this.id = id;
    }

    public Contact(int id, String name, String phone_number) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    public String parseContactToString(int id){
        setId(id);
        return Constant.CONTACT_ID + ":" + this.id + ";" +
                Constant.CONTACT_NAME_KEY + ":" +this.name + ";" +
                Constant.CONTACT_PHONE_NUMBER_KEY + ":" + this.phone_number + ";" +
                Constant.CONTACT_LAST_NAME_KEY + ":" + this.last_name + ";" +
                Constant.CONTACT_ADDRESS_KEY + ":" + this.address + ";" +
                Constant.CONTACT_EMAIL_KEY + ":" + this.email + ";" +
                Constant.CONTACT_TONE_KEY + ":" + this.tone + ";";
    }
}
