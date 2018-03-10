package Adapters.JoinAss;

/**
 * Created by zhangfengwei on 2017/12/25.
 */

public class JoinAss {
    private String name;
    private String time;
    private String place;
    public JoinAss(String name,String time,String place) {
        this.name=name;
        this.time=time;
        this.place=place;
    }
    public String getName_join() {
        return name;
    }
    public String getTime_join() {
        return time;
    }
    public String getPlace_join() {
        return place;
    }
}
