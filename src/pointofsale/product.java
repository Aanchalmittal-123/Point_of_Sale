package pointofsale;
public class product {
    int id;
    String name;
    String description;
    int price;
    String photo;
    product(String s,String s1,String s2,String s3,String s4){
        id=Integer.parseInt(s);
        name=s1;
        description=s2;
        price=Integer.parseInt(s3);
        photo=s4;
    }
    public int getid() {
        return id;
    }

    public void setid(String s) {
       id=Integer.parseInt(s);
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
    public int getprice() {
        return price;
    }

    public void setprice(String s3) {
        price = Integer.parseInt(s3);
    }
    public String getphoto() {
        return photo;
    }

    public void setphoto(String s4) {
        photo = s4;
    }   
}
