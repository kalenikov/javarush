package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model {
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        List<User> testUsers = new ArrayList<User>();
        User user1 = new User("Amigo", 1, 24);
        User user2 = new User("Zajaz", 2, 27);
        User user3 = new User("Newbie", 3, 7);
        User user4 = new User("Jaja", 4, 14);
        testUsers.add(user1);
        testUsers.add(user2);
        testUsers.add(user3);
        testUsers.add(user4);
        modelData.setUsers(testUsers);
    }
}
