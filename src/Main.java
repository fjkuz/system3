//В файле 17-342.txt содержится последовательность целых чисел.
//Элементы последовательности – натуральные числа, не превосходящие 10000.
//Найдите такие пары элементов, в которых только одно число
//находится между значениями минимального кратного 37 и максимального кратного 73.
//Гарантируется, что такая пара в последовательности есть.
//В ответе запишите количество найденных пар и минимальную сумму элементов среди таких пар.
//В данной задаче под парой подразумевается два идущих подряд элемента последовательности.
import java.io.*;
import java.util.*;
void main() {
    Scanner scanner=null;
    ArrayList<Integer> numbers = new ArrayList<>();//список, в который будут записываться все числа из файла
    try { //поиск файла
        scanner = new Scanner(new File("1111.txt"));
        System.out.println("Файл найден");
    }
    catch (FileNotFoundException e) { //если такой файл не найден
        System.out.println("Файл не найден — " + e.getMessage());
    }
    while (scanner.hasNextInt()) {//пока в файле есть числа, считываем их и добавляем в список
        numbers.add(scanner.nextInt());
    }
    int min37 = Integer.MAX_VALUE;//для хранения текущего мин числа, кратного 37
    int max73 = Integer.MIN_VALUE;//для хранения текущего макс числа, кратного 73

    for (int number : numbers) { //перебираем числа в списке
        if (number % 37 == 0 && number < min37) {//если текущ число делится на 37 и меньше текущего мин37 заносим его как новое мин37
            min37 = number;
        }
        if (number % 73 == 0 && number > max73) {//если текущ число делится на 73 и больше текущего макс73 заносим его как новое макс73
            max73 = number;
        }
    }
    int left = Math.min(min37, max73); //определяем левую границу диапазона
    int right = Math.max(min37, max73); //определяем правую границу диапазона
    int count = 0; //счетчик найденных пар
    int minSum = Integer.MAX_VALUE; //хранение текущей мин суммы пары

    for (int i = 0; i < numbers.size() - 1; i++) { //перебор пар соседних элементов
        int a = numbers.get(i);//первый элемент пары
        int b = numbers.get(i + 1);//второй элемент пары
        boolean first = a > left && a < right; //находится ли первый элемент между left и right
        boolean second = b > left && b < right; //находится ли второй элемент между left и right
        if (first != second) {
            count++;
            int sum = a + b;
            if (sum < minSum) { //если сумма меньше предыдущ минСуммы, заносим ее как новую минСумму
                minSum = sum;
            }
        }
    }
    System.out.println("мин. число, кратное 37: " + min37);
    System.out.println("макс. число,  кратное 73: " + max73);
    System.out.println("кол-во пар: " + count);
    System.out.println("мин. сумма: " + minSum);
}
