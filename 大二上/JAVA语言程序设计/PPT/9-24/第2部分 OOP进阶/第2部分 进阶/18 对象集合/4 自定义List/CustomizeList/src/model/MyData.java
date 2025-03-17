package model;

public class MyData {
    private int id;
    private String info;

    public MyData(int id, String info) {
        this.id = id;
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyData myData = (MyData) o;

        if (id != myData.id) return false;
        return info != null ? info.equals(myData.info) : myData.info == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
