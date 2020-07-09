package com.xavier.pandora.fastjson.data;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ComplexBean {
    private Set<User> users;
    private Map<String, List<User>> mapUsers;

    public ComplexBean(Set<User> users, Map<String, List<User>> mapUsers) {
        this.users = users;
        this.mapUsers = mapUsers;
    }

    @Override
    public String toString() {
        return "ComplexBean{" +
                "users=" + users +
                ", mapUsers=" + mapUsers +
                '}';
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Map<String, List<User>> getMapUsers() {
        return mapUsers;
    }

    public void setMapUsers(Map<String, List<User>> mapUsers) {
        this.mapUsers = mapUsers;
    }
}
