package Adapters.PreviousActivity;

/**
 * Created by zhangfengwei on 2017/12/24.
 */

public class PreviousActivity {
    private String name;
    private String id;
    public PreviousActivity(String name,String id){
        this.name = name;
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
}
