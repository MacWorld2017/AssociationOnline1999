package Adapters.DashBoard;

/**
 * Created by zhangfengwei on 2017/12/15.
 */

public class Dash {
    private String text;
    private int imageId;
    public Dash(String text,int imageId){
        this.text = text;
        this.imageId = imageId;
    }
    public String getText(){
        return text;
    }
    public int getPic(){
        return imageId;
    }
}