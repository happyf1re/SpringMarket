package com.example.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataBaseHandler {

    public static void main(String[] args) throws SQLException {

        //создаём мапу, для хранения пар ключ=значение, где ключ - id, значение - кол-во очков
       HashMap<String, String> map = new HashMap<>();

        //читаем файл и записываем в мапу
        File file = new File("C:\\Users\\Happyfire\\Java\\spring-data\\src\\main\\java\\com\\example\\database\\score.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String key = scanner.next();
                String value = scanner.next();
                map.put(key, value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //удаляем поле с ненужным ключом и выводим мапу в консоль
        map.remove("id");
        System.out.println(map);

        Database db = new Database();

        for (Map.Entry<String, String> next : map.entrySet()) {
            db.executeUpdate("UPDATE scoreboard SET score = "+next.getValue()+" where id = "+next.getKey()+"");
        }
        db.closeConnection();
    }
}
