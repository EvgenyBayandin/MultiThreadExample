import java.util.Arrays;
import java.util.List;

public class Hr {

    public static void main(String[] args) {

        // создаем две очереди из сотрудников
        PeopleQueue queue1 = new PeopleQueue(getEmployeeNames());
        PeopleQueue queue2 = new PeopleQueue(getEmployeeNames2());

        // выведем стартовое сообщение
        System.out.println("Начали");
        // запускаем две очереди
        queue1.start();
        queue2.start();

    }

    private static List<String> getEmployeeNames() {
        return Arrays.asList("Иванов", "Петров", "Сидоров");
    }

    private static List<String> getEmployeeNames2() {
        return Arrays.asList("Иванова", "Петрова", "Сидорова");
    }

}

class PeopleQueue extends Thread{

    private final List<String> employeeNames;

    public PeopleQueue(List<String> employeeNames) {
        this.employeeNames = employeeNames;
    }


    // переопределяем метод run()
    @Override
    public void run(){
        for (String name : employeeNames) {
            System.out.println("Обработаны документы сотрудника: " + name);
            try {
                // ждем 1 секунду
                Thread.sleep(1000);
            } catch (Exception e)  {}

        }
    }

}
