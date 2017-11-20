package com.babayre.coderdecoder.cryptho;

import java.lang.reflect.Array;

public class Caesar {
    private int key;
    private String table = "qwertyuiopasdfghjklzxcvbnmёйцукенгшщзхъфывапролджэячсмитьбюії";
    
    public Caesar(int arg) {
        if (arg > table.length() ){
            key = 0;
        } else {
            key = arg;
        }
    }
    
    public String toCodingText(String text){
        String result = "";
        char x; //каждый символ текста
        int y; //индекс букв в таблице
        String tableUp = table.toUpperCase(); // та же таблица, но в верхнем регистре
        char z; // готовая зашифрованая буква
        int tableLength = table.length(); // длина таблицы, чтоб постоянно не вызывать метод lenght
        boolean f = false; // флаг обозначающий надо ли переносить символ в верхний регистр

        for (int i = 0; i < text.length(); i++) {
                x = text.charAt(i);
                y = table.indexOf(x);
                if (y == -1){
                    y = tableUp.indexOf(x);
                    if (y == -1){
                        result += x;
                        continue;
                    } else{
                        f = true;
                    }
                } // если этого символа нет в таблице, проверить есть ли он в таблице больших букв.
                  // и если и там нету, то оставить его как есть и перейти на следующую итерацию.
                  // в случае если есть, то поставить флаг
                y = y + key; // сдвиг
                if (y >= tableLength){
                    y = y - tableLength;
                } // перейти к началу таблицы если предпологается выход за ее границы (тупо пояснил)(короче чтоб не было выхода за пределы таблицы, вот)
                z = table.charAt(y);
                if (f) {
                    z = Character.toUpperCase(z);
                    f = false;
                } // если стоит флаг, то поднимаем символ в верхний регистр и снимаем флаг, чтоб не мешался
                result += z;
            }
        return result;
    }

    public String toEncodingText(String text){
        String result = "";
        char x;
        int y;
        char z;
        boolean f = false;
        String tableUp = table.toUpperCase();
        int tableLength = table.length();

        for (int i = 0; i < text.length(); i++) {
            x = text.charAt(i);
            y = table.indexOf(x);
            if (y == -1){
                y = tableUp.indexOf(x);
                if (y == -1){
                    result += x;
                    continue;
                } else{
                    f = true;
                }
            }
            y = y - key;
            if (y < 0){
                y = y + tableLength;
            }
            z = table.charAt(y);
            if (f) {
                z = Character.toUpperCase(z);
                f = false;
            }
            result += z;
        }

        return result;
    }
}
