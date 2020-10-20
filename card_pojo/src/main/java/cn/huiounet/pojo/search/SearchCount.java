package cn.huiounet.pojo.search;


import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "card_search_count")
public class SearchCount implements Serializable {
    private int id;
    private String search_mess;
    private String count;

    public SearchCount() {
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
