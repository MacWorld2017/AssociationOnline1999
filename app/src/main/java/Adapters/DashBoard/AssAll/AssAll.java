package Adapters.DashBoard.AssAll;

/**
 * Created by zhangfengwei on 2017/12/15.
 */

public class AssAll {
    private String name;
    private String text;
    private String imageUrl;
    private String id;

    public AssAll(String name,String text,String imageUrl,String id){
        this.name = name;
        this.imageUrl = imageUrl;
        this.text=text;
        this.id=id;
    }

    public String getName2(){
        return name;
    }

    public String getIntro2(){
        return text;
    }

    public String getPic3(){
        return imageUrl;
    }
    public String getId(){
        return id;
    }
}
