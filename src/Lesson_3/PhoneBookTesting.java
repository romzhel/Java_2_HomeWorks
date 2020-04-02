package Lesson_3;

public class PhoneBookTesting {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        String[][] inputData = {
                {"Пупкин", "+7(999)999-9999"}, {"Пупкин брат", "+7(999)999-9999"},
                {"Пупкин", "+7(999)888-8888"},
                {"Пупкин", "+7(999)777-7777"}
        };

        for (String[] recordData : inputData) {
            try {
                phoneBook.addRecord(recordData[0], recordData[1]);
            } catch (PhoneNumberExistsException e) {
                System.out.printf("Не удалось добавить запись с именем %s и номером телефона %s, так как данный номер" +
                        "уже числится за именем %s\n", e.getNewName(), e.getPhoneNumber(), e.getExistingName());
            }
        }

        phoneBook.printPhoneNumbersByName("Пупкин");
        phoneBook.printPhoneNumbersByName("Пупкин брат");
        phoneBook.printPhoneNumbersByName("Непупкин");
    }
}
