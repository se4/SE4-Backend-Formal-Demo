package nju.se4.demo.util;

/**
 * Description:
 * 返回给前端的权限描述数据
 *
 * @author panyu
 * Created on 10/26/2018
 */
public class Abilities {
    private boolean update;

    private boolean destory;

    public Abilities() {
    }

    public Abilities(boolean update, boolean destory) {
        this.update = update;
        this.destory = destory;
    }

    public Abilities(boolean update) {
        this.update = update;
    }


    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDestory() {
        return destory;
    }

    public void setDestory(boolean destory) {
        this.destory = destory;
    }
}
