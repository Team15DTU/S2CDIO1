package data.dto;

import data.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserList {

    ArrayList<UserDTO> list = new ArrayList<>();

    public void add(UserDTO e) {
        list.add(e);
    }

    public List UserL() {
        return list;
    }

    public void delete(int i) {
        list.remove(i);
    }




}
