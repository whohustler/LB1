import java.util.Scanner;

class Person {
    private String name;
    private int height;

    public Person(String name, int height) {
        this.name = name;
        this.height = height;
    }

    @Override
    public String toString() {
        return name + ", рост: " + height;
    }
}

class FullName {
    private String lastName; // Фамилия
    private String firstName; // Личное имя
    private String patronymic; // Отчество

    // Конструктор
    public FullName(String lastName, String firstName, String patronymic) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    // Метод для получения строкового представления
    @Override
    public String toString() {
        StringBuilder fullName = new StringBuilder();
        if (firstName != null && !firstName.isEmpty()) {
            fullName.append(firstName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            fullName.append(" ").append(lastName);
        }
        if (patronymic != null && !patronymic.isEmpty()) {
            fullName.append(" ").append(patronymic);
        }
        return fullName.toString().trim();
    }

    // Метод для проверки, содержит ли строка цифры
    public static boolean containsDigits(String input) {
        return input.matches(".*\\d.*");
    }

}

class Name {
    private String surname;
    private String firstName;
    private String patronymic;

    // Конструктор для только личного имени
    public Name(String firstName) {
        this.firstName = firstName;
        this.surname = "";
        this.patronymic = "";
    }

    // Конструктор для личного имени и фамилии
    public Name(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = "";
    }

    // Конструктор для всех трех параметров
    public Name(String firstName, String surname, String patronymic) {
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    // Метод для получения строкового представления
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!surname.isEmpty()) sb.append(surname).append(" ");
        if (!firstName.isEmpty()) sb.append(firstName).append(" ");
        if (!patronymic.isEmpty()) sb.append(patronymic);
        return sb.toString().trim(); // Удаление лишних пробелов
    }
}

class Пистолет {
    private int maxПатронов; // Максимальное количество патронов
    int заряжено; // Количество заряженных патронов

    // Конструктор, принимающий максимальное количество патронов и количество заряженных патронов
    public Пистолет(int maxПатронов, int заряжено) {
        if (maxПатронов <= 0) {
            throw new IllegalArgumentException("Максимальное количество патронов должно быть больше 0.");
        }
        if (заряжено < 0 || заряжено > maxПатронов) {
            throw new IllegalArgumentException("Некорректное количество заряженных патронов.");
        }
        this.maxПатронов = maxПатронов;
        this.заряжено = заряжено;
    }

    // Конструктор, принимающий только максимальное количество патронов. Заряжено по умолчанию 5.
    public Пистолет(int maxПатронов) {
        this(maxПатронов, 5); // Вызов другого конструктора
    }

    // Метод для выстрела
    public void выстрел() {
        if (заряжено > 0) {
            System.out.println("Бах!");
            заряжено--;
        } else {
            System.out.println("Клац!");
        }
    }

    // Метод toString() для вывода информации о пистолете
    @Override
    public String toString() {
        return "Пистолет{" +
                "maxПатронов=" + maxПатронов +
                ", заряжено=" + заряжено +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Выберите задачу:");
            System.out.println("1. Ввод роста человека");
            System.out.println("2. Ввод полного имени (FullName)");
            System.out.println("3. Ввод полного имени (Name)");
            System.out.println("4. Работа с пистолетом");
            System.out.println("0. Выход");

            int choice = getValidInt(scanner);

            switch (choice) {
                case 1:
                    handlePerson(scanner);
                    break;
                case 2:
                    handleFullName(scanner);
                    break;
                case 3:
                    handleName(scanner);
                    break;
                case 4:
                    handleПистолет(scanner);
                    break;
                case 0:
                    running = false;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
        scanner.close(); // Закрываем сканер
    }



    /// Человек. Задание 1.2
    //Создайте сущность Человек, которая описывается:
    // Имя: строка
    // Рост: целое число
    //Может возвращать текстовое представление вида “Name, рост: height”, где Name и height это
    //переменная с именем и ростом.
    //Необходимо создать и вывести на экран следующих людей:
    // Человек с именем “Клеопатра” и ростом 152
    // Человек с именем “Пушкин ” и ростом 167
    // Человек с именем “Владимир ” и ростом 189
    private static void handlePerson(Scanner scanner) {
        System.out.print("Введите имя человека: ");
        String name = getValidString(scanner);
        System.out.print("Введите рост человека (целое число): ");
        int height = getValidInt(scanner);
        Person person = new Person(name, height);
        System.out.println(person);
    }




    /// Имена. Задание 1.3
    //Создайте сущность Имя, которая описывается тремя параметрами: Фамилия, Личное имя,
    //Отчество. Имя может быть приведено к строковому виду, включающему традиционное
    //представление всех трех параметров: Фамилия Имя Отчество (например “Иванов Иван
    //Иванович”). Необходимо предусмотреть возможность того, что какой-либо из параметров может
    //быть не задан, и в этом случае он не учитывается при приведении к текстовому виду.
    //Необходимо создать следующие имена:
    // Клеопатра
    // Пушкин Александр Сергеевич
    // Маяковский Владимир
    //Обратите внимание, что при выводе на экран, не заданные параметры никак не участвуют в
    //образовании строки.
    private static void handleFullName(Scanner scanner) {
        String lastName;
        String firstName;
        String patronymic;

        while (true) {
            System.out.print("Введите фамилию (можно оставить пустым): ");
            lastName = scanner.nextLine();
            if (!FullName.containsDigits(lastName)) {
                break;
            }
            System.out.println("Фамилия не может содержать цифры. Пожалуйста, введите фамилию заново.");
        }

        while (true) {
            System.out.print("Введите личное имя: ");
            firstName = scanner.nextLine();
            if (!FullName.containsDigits(firstName) && !firstName.isEmpty()) {
                break;
            }
            System.out.println("Имя не может содержать цифры или быть пустым. Пожалуйста, введите имя заново.");
        }

        while (true) {
            System.out.print("Введите отчество (можно оставить пустым): ");
            patronymic = scanner.nextLine();
            if (!FullName.containsDigits(patronymic)) {
                break;
            }
            System.out.println("Отчество не может содержать цифры. Пожалуйста, введите отчество заново.");
        }

        FullName fullName = new FullName(lastName, firstName, patronymic);
        System.out.println("Полное имя: " + fullName);
    }





    ///Создаем Имена.  4.5
    //Измените сущность Имя из задачи 1.3. Новые требования включают:
    // Имя можно создать указав только Личное имя
    // Имя можно создать указав Личное имя и Фамилию.
    // Имя можно создать указав все три параметра: Личное имя, Фамилию, Отчество.
    //Необходимо создать следующие имена:
    //1. Клеопатра
    //2. Александр Сергеевич Пушкин
    //3. Владимир Маяковский
    //4. Христофор Бонифатьевич (здесь Христофор это имя, а Бонифатьевич - фамилия)
    private static void handleName(Scanner scanner) {
        String firstName, surname, patronymic;

        while (true) {
            System.out.print("Введите личное имя (обязательно): ");
            firstName = scanner.nextLine().trim();
            if (!firstName.isEmpty() && firstName.matches("[a-zA-Zа-яА-ЯёЁ]+")) {
                break;
            } else {
                System.out.println("Ошибка: Личное имя должно быть указано и не должно содержать цифр. Попробуйте снова.");
            }
        }

        while (true) {
            System.out.print("Введите фамилию (или оставьте пустым): ");
            surname = scanner.nextLine().trim();
            if (surname.isEmpty() || surname.matches("[a-zA-Zа-яА-ЯёЁ]+")) {
                break;
            } else {
                System.out.println("Ошибка: Фамилия не должна содержать цифр. Попробуйте снова.");
            }
        }

        while (true) {
            System.out.print("Введите отчество (или оставьте пустым): ");
            patronymic = scanner.nextLine().trim();
            if (patronymic.isEmpty() || patronymic.matches("[a-zA-Zа-яА-ЯёЁ]+")) {
                break;
            } else {
                System.out.println("Ошибка: Отчество не должно содержать цифр. Попробуйте снова.");
            }
        }

        Name name;

        if (!surname.isEmpty() && !patronymic.isEmpty()) {
            name = new Name(firstName, surname, patronymic);
        } else if (!surname.isEmpty()) {
            name = new Name(firstName, surname);
        } else {
            name = new Name(firstName);
        }

        System.out.println("Созданное имя: " + name);
    }






    ///Пистолет стреляет. Задание 5.1
    // Создайте сущность Пистолет, которая описывается следующим образом:
    // Имеет Количество патронов (целое число)
    // Может быть создан с указанием начального количества патронов
    // Может быть создан без указания начального количества патронов, в этом случае он
    //изначально заряжен пятью патронами.
    // Может Стрелять, что приводит к выводу на экран текста “Бах!” в том случае, если
    //количество патронов больше нуля, иначе делает “Клац!”. После каждого выстрела (когда
    //вывелся “Бах!”) количество патронов уменьшается на один.
    //Создайте пистолет с тремя патронами и выстрелите из него пять раз.
    private static void handleПистолет(Scanner scanner) {
        try {
            System.out.print("Введите максимальное количество патронов: ");
            int maxПатронов = getValidInt(scanner);

            int заряжено;
            System.out.print("Введите количество заряженных патронов (оставьте пустым для 5 по умолчанию): ");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                заряжено = 5;
            } else {
                заряжено = Integer.parseInt(input);
            }

            Пистолет пистолет = new Пистолет(maxПатронов, заряжено);
            System.out.println(пистолет);

            // Выстреливаем до тех пор, пока есть патроны
            while (пистолет.заряжено > 0) {
                пистолет.выстрел();
            }

            // Выводим "Клац!" столько раз, сколько пустот
            for (int i = 0; i < maxПатронов - заряжено; i++) {
                System.out.println("Клац!");
            }

            System.out.println(пистолет);

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static String getValidString(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.matches("[a-zA-Zа-яА-ЯёЁ]+")) {
                return input;
            } else {
                System.out.print("Некорректный ввод. Попробуйте снова: ");
            }
        }
    }

    private static int getValidInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Некорректный ввод. Введите целое число: ");
            }
        }
    }
}
