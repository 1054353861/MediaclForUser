package com.android.mediaclforuser.model;

/**
 * Created by Administrator on 2015/12/11.
 */
public class Free {

    private String fname;//义诊活动名称
    private String fimage;//图片
    private String abstract_info;//简介
    private String info;//详细介绍
    private String hold_time;//时间
    private String location;//地点

    private Integer door_ticket;//门票数

    private Integer remain_ticket;//剩余门票数

    private String id;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFimage() {
        return fimage;
    }

    public void setFimage(String fimage) {
        this.fimage = fimage;
    }

    public String getAbstract_info() {
        return abstract_info;
    }

    public void setAbstract_info(String abstract_info) {
        this.abstract_info = abstract_info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHold_time() {
        return hold_time;
    }

    public void setHold_time(String hold_time) {
        this.hold_time = hold_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDoor_ticket() {
        return door_ticket;
    }

    public void setDoor_ticket(Integer door_ticket) {
        this.door_ticket = door_ticket;
    }

    public Integer getRemain_ticket() {
        return remain_ticket;
    }

    public void setRemain_ticket(Integer remain_ticket) {
        this.remain_ticket = remain_ticket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
