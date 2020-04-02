package Lesson_3;

import java.util.*;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> records;

    public PhoneBook() {
        records = new HashMap<>();
    }

    public void addRecord(String name, String newPhoneNumber) {
        records.forEach((existingName, numbers)->
                numbers.forEach((number)-> {
                    if (newPhoneNumber.equals(number)) {
                        throw new PhoneNumberExistsException(name, existingName, newPhoneNumber);
                }}));

        if (records.containsKey(name)) {
            records.get(name).add(newPhoneNumber);
        } else {
            records.put(name, new ArrayList<>(Collections.singletonList(newPhoneNumber)));
        }
    }

    public List<String> getPhoneNumbersByName(String name) {
        return records.getOrDefault(name, new ArrayList<>());
    }

    public void printPhoneNumbersByName(String name) {
        List<String> phoneNumbers = getPhoneNumbersByName(name);
        System.out.printf("По имени %s найдено номеров телефонов %d%s\n", name, phoneNumbers.size(),
                phoneNumbers.size() == 0 ? "." : ":");

        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }
    }
}
