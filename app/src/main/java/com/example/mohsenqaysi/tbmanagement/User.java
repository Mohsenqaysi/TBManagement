package com.example.mohsenqaysi.tbmanagement;

/**
 * Created by mohsenqaysi on 2/2/17.
 */

public class User {

        public String username_ID;
//        public String email;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String userID) {
            this.username_ID = userID;
//            this.email = email;
        }
}
