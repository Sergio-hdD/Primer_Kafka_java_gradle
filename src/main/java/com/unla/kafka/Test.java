package com.unla.kafka;

import org.json.JSONObject;

import java.time.LocalDate;

public class Test {

    public static void main(String[] args) {

        long id = 1;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oldName", "OldName");
        jsonObject.put("newName", "NewName");
        jsonObject.put("oldPrice", "OldPrice");
        jsonObject.put("newPrice", "NewPrice");
        jsonObject.put("editionDate", LocalDate.now());

        Producer.createMessage(String.valueOf(id), "key", jsonObject);
    }

}