package Adapters.HotActivities;

/**
 * Created by zhangfengwei on 2017/12/2.
 */

public class Activities {
    private String name2;
    private int imageId2;
    private String content2;
    public Activities(String name2,String content2,int imageId2){
        this.name2 = name2;
        this.content2 = content2;
        this.imageId2 = imageId2;
    }
    public String getName(){
        return name2;
    }
    public String getContent(){
        return content2;
    }
    public int getImageId(){
        return imageId2;
    }
}
