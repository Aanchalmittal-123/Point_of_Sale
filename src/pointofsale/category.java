package pointofsale;

public class category {
    String name;
    String description;
    String photo;
    category(String s1,String s2,String s3){
        name=s1;
        description=s2;
        photo=s3;
    }
    public String getname() {
        return name;
    }

    public void setname(String s1) {
        name = s1;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String s2) {
        description = s2;
    }

    public String getphoto() {
        return photo;
    }

    public void setphoto(String s3) {
        photo = s3;
    }
}
