//Создать программу управления банковским счетом (Account).
//Программа должна позволять пользователю вводить начальный баланс счета,
// сумму депозита и сумму снятия средств. При этом она должна обрабатывать следующие
// исключительные ситуации:
//
//Попытка создать счет с отрицательным начальным балансом должна вызывать исключение IllegalArgumentException
// с соответствующим сообщением.
//Попытка внести депозит с отрицательной суммой должна вызывать исключение
// IllegalArgumentException с соответствующим сообщением.
//Попытка снять средства, сумма которых превышает текущий баланс,
// должна вызывать исключение InsufficientFundsException с сообщением о недостаточных средствах и текущим балансом.
//
//Продемонстрируйте работу вашего приложения:
//Программа должна обрабатывать все исключения с помощью конструкции try-catch,
// выводя соответствующие сообщения об ошибках.
//
//2*.
//Создать несколько типов счетов, унаследованных от Account, например: CreditAcciunt, DebitAccount.
// Создать класс (Transaction), позволяющий проводить транзакции между счетами
// (переводить сумму с одного счета на другой)
//
//Класс Transaction должен возбуждать исключение в случае неудачной попытки перевести деньги с одного счета на другой.
//
//Продемонстрируйте работу вашего приложения:
//Программа должна обрабатывать все исключения с помощью конструкции
// try-catch, выводя соответствующие сообщения об ошибках.
//
//Достаточно выпонить только первую задачу, вторая задача является дополнительной.


import ru.gb.sem4.BankAccount;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<BankAccount> clients = new ArrayList<>();

        creatingAnAccount(clients);
        makeDeposit();
        withdrawalOfFunds(clients);
    }

    /**
     *Проверяем есть ли клиент в базе, в случае отсутствия, добавляем в нее. Зачилсяем первоначальный баланс
     * @param clients
     *
     */
    public static void creatingAnAccount(ArrayList<BankAccount> clients) {
        System.out.println("Введите Ваше имя");
        Scanner scanner2 = new Scanner(System.in);
        String name = scanner2.nextLine();

        System.out.println("Введите начальный баланс счета:");
        Scanner scanner = new Scanner(System.in);
        int initialBalance = scanner.nextInt();
        try {
            if (initialBalance < 0) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный формат ввода");
        }

        BankAccount foundClient = findClientByName(clients, name);

        if (foundClient != null) {
            foundClient.setInitialAccountBalance(initialBalance);
        } else {
            BankAccount newClient = new BankAccount(name, 0, initialBalance);
            clients.add(newClient);
            System.out.println("Клиент " + name + " успешно создан с начальным балансом " + initialBalance);
        }
    }

    public static BankAccount findClientByName(ArrayList<BankAccount> clients, String name) {
        for (BankAccount client : clients) {
            if (client.getName().equals(name)) {
                return client;
            }
        }
        return null;
    }

    /**
     *Пополняем счет
     * @return
     */
    public static int makeDeposit() {
        System.out.println("Какую сумму хотите внести");
        Scanner scanner1 = new Scanner(System.in);
        int depositAmount = scanner1.nextInt();

        try {
            if (depositAmount < 0) {
                throw new IllegalArgumentException("Попытка внести депозит с отрицательной суммой");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return depositAmount;
    }

    /**
     * Выаод средств со счета
     * @param clients
     */
    public static void withdrawalOfFunds(ArrayList<BankAccount> clients) {
        System.out.println("Введите имя клиента, у которого хотите вывести средства:");
        Scanner scanner2 = new Scanner(System.in);
        String name = scanner2.nextLine();

        BankAccount client = findClientByName(clients, name);

        if (client == null) {
            System.out.println("Клиент не найден.");
            return;
        }

        System.out.println("Какую сумму Вы хотите вывести?");
        Scanner scanner3 = new Scanner(System.in);
        int withdrawalAmount = scanner3.nextInt();
        try {
            if (withdrawalAmount < 0) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный формат ввода");
            return;
        }

        try {
            if (withdrawalAmount > client.getInitialAccountBalance()) {
                throw new InsufficientFundsException();
            }
        } catch (InsufficientFundsException e) {
            System.out.println("Недостаточно средств!");
            return;
        }
    }
}


