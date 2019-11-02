import java.util.*;

public class Game3 implements IGame {
    private String secretNumber;

    Game3(String fileName) {System.out.println("я родился!3");
    }

    public ChatBotReply proceedRequest(String request, int userId) {
        if (request.equals("BullsAndCows")) {
            generate();
            return new ChatBotReply("Я загадал четырехзначное число\nПовторяющихся цифр нет. Отгадай!");
        }
        try {
            Integer.parseInt(request);
            //return true;
        } catch (NumberFormatException e) {
            return new ChatBotReply("Введи число");
        }
        if (request.length() != 4) return new ChatBotReply("Число должно быть четырехзначным!");
        Set<Character> resultSet = new HashSet<Character>();

        for (int i = 0; i < request.length(); i++) {
            resultSet.add(request.charAt(i));
        }
        if (resultSet.size() != request.toCharArray().length) return new ChatBotReply("Есть повторяющиеся числа :C");
        if (request.equals(secretNumber)) {
            generate();
            return new ChatBotReply("Ты отгадал! \nЯ загадал четырехзначное число\nПовторяющихся цифр нет. Отгадай!");
        } else {
            return BullsAndCows(request);
        }
    }

    public void generate() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        String result = "";
        for (int i = 0; i < 4; i++) {
            result += numbers.get(i).toString();
        }
        System.out.print("secretNumber " + result);
        System.out.println();
        secretNumber = result;
    }

    private ChatBotReply BullsAndCows(String number) {
        String secretNumber2 = secretNumber;
        int bulls = 0;

        ArrayList<Character> listClient = new ArrayList<Character>();
        ArrayList<Character> listComputer = new ArrayList<Character>();

        System.out.println(number);

        for (int i = 0; i < number.length(); i++) {
            listClient.add(number.charAt(i));
            listComputer.add(secretNumber2.charAt(i));
        }
        ArrayList<Character> cows = new ArrayList<>(listComputer);
        cows.retainAll(listClient);

        for (Character cow : cows) {
            if (listComputer.indexOf(cow) == listClient.indexOf(cow)) bulls++;
        }

        return new ChatBotReply("коров: " + (cows.size() - bulls) + "\nбыков: " + bulls);
    }
}
