package test;

public class Data {
    public Data(Integer str) {
        this.str = str;
    }

    public Integer getStr() {
        return str;
    }

    public void setStr(Integer str) {
        this.str = str;
    }

    Integer str;

    @Override
    public String toString() {
        return str.toString();
    }
}
