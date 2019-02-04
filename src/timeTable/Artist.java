package timeTable;

public class Artist {

    private double popularity;
    private String name;

    public Artist(String name, double popularity) {
        this.name = name;
        this.popularity = popularity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public double getPopularity() {
        return popularity;
    }
}
