package day19homework;

public class heroes {
    private int num;
    private String name;
    private  String city;
    private String sex;
    private int brith;
    private int  deadline;
    private int nengli;

    public heroes(int num, String name, String city, String sex, int brith, int deadline, int nengli) {
        this.num = num;
        this.name = name;
        this.city = city;
        this.sex = sex;
        this.brith = brith;
        this.deadline = deadline;
        this.nengli = nengli;
    }

    @Override
    public String toString() {
        return "heroes{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", sex='" + sex + '\'' +
                ", brith=" + brith +
                ", deadline=" + deadline +
                ", nengli=" + nengli +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getBrith() {
        return brith;
    }

    public void setBrith(int brith) {
        this.brith = brith;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getNengli() {
        return nengli;
    }

    public void setNengli(int nengli) {
        this.nengli = nengli;
    }
}
