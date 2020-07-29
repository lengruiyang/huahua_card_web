package cn.huiounet.pojo.search;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_search_sys")
public class SearchSys implements Serializable{

    private int id;
    private String search_mess;
    private String search_time;
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearch_mess() {
        return search_mess;
    }

    public void setSearch_mess(String search_mess) {
        this.search_mess = search_mess;
    }

    public String getSearch_time() {
        return search_time;
    }

    public void setSearch_time(String search_time) {
        this.search_time = search_time;
    }


    public SearchSys() {
    }

    public SearchSys(int id, String search_mess, String search_time, String count) {
        this.id = id;
        this.search_mess = search_mess;
        this.search_time = search_time;
    }
}
