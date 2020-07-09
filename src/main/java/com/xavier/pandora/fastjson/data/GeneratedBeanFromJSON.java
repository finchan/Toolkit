package com.xavier.pandora.fastjson.data;

import java.util.List;

public class GeneratedBeanFromJSON {

    //这样的映射并不成功，插件GSON这个插件在处理Map时时多么的无力！
    /**
     * mapUsers : {"userList1":[{"password":"Password3","username":"Username3"},{"password":"Password4","username":"Username4"},{"password":"Password5","username":"Username5"}],"userList2":[{"password":"Password6","username":"Username6"},{"password":"Password7","username":"Username7"},{"password":"Password8","username":"Username8"}]}
     * users : [{"password":"Password2","username":"Username2"},{"password":"Password1","username":"Username1"}]
     */

    private MapUsersBean mapUsers;
    private List<UsersBean> users;

    public MapUsersBean getMapUsers() {
        return mapUsers;
    }

    public void setMapUsers(MapUsersBean mapUsers) {
        this.mapUsers = mapUsers;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class MapUsersBean {
        private List<UserList1Bean> userList1;
        private List<UserList2Bean> userList2;

        public List<UserList1Bean> getUserList1() {
            return userList1;
        }

        public void setUserList1(List<UserList1Bean> userList1) {
            this.userList1 = userList1;
        }

        public List<UserList2Bean> getUserList2() {
            return userList2;
        }

        public void setUserList2(List<UserList2Bean> userList2) {
            this.userList2 = userList2;
        }

        public static class UserList1Bean {
            /**
             * password : Password3
             * username : Username3
             */

            private String password;
            private String username;

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public static class UserList2Bean {
            /**
             * password : Password6
             * username : Username6
             */

            private String password;
            private String username;

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }

    public static class UsersBean {
        /**
         * password : Password2
         * username : Username2
         */

        private String password;
        private String username;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
