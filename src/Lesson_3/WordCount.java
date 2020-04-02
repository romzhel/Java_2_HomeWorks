package Lesson_3;

import java.util.HashMap;

public class WordCount {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] words = {
                "Москва", "Москва", "Москва", "Москва", "Москва",
                "Екатеринбург",
                "Новосибирск",
                "Липецк", "Липецк", "Липецк", "Липецк",
                "Рязань",
                "Тула", "Тула", "Тула",
                "Ярославль",
                "Рыбинск", "Рыбинск",
                "Волгоград",
                "Воронеж"
        };

        for (String word : words) {
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }

        hashMap.forEach((word, count) -> System.out.printf("Слово %s встречается %d раз(а)\n", word, count));
    }
}
