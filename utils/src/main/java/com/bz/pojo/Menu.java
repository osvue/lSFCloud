package com.bz.pojo;

import java.util.List;

public class Menu {
    private Integer id;
    private String menuid;//菜单ID
    private String menuname;//菜单名称
    private String icon;//图标
    private String url;//URL
    private String pid;
    private List<Menu> menus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuid='" + menuid + '\'' +
                ", menuname='" + menuname + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", pid='" + pid + '\'' +
                ", menus=" + menus +
                '}';
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
