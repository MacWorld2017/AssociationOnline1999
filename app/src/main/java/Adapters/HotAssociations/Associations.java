package Adapters.HotAssociations;

/**
 * Created by zhangfengwei on 2017/12/1.
 */

public class Associations {
    private String name;
    private int imageId;
    private String content;
    public Associations(String name,String content,int imageId){
        this.name = name;
        this.content = content;
        this.imageId = imageId;
    }
    public String getName(){
        return name;
    }
    public String getContent(){
        return content;
    }
    public int getImageId(){
        return imageId;
    }
}