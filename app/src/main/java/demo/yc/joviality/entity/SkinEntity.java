package demo.yc.joviality.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author: YC
 * @date: 2017/9/4 0004
 * @time: 17:37
 * @detail:
 */

@Entity(nameInDb = "skin_plugin")
public class SkinEntity
{
    @Id
    private Long id;
    private String apkPath;
    private String pckName;
    private String color;
    private String name;
    @Unique
    private int type;
    @Generated(hash = 1876489310)
    public SkinEntity(Long id, String apkPath, String pckName, String color,
            String name, int type) {
        this.id = id;
        this.apkPath = apkPath;
        this.pckName = pckName;
        this.color = color;
        this.name = name;
        this.type = type;

    }
    @Generated(hash = 237489517)
    public SkinEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getApkPath() {
        return this.apkPath;
    }
    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }
    public String getPckName() {
        return this.pckName;
    }
    public void setPckName(String pckName) {
        this.pckName = pckName;
    }
    public String getColor() {
        return this.color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
