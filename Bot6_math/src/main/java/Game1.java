public class Game1 implements IGame {
    private int secretNumber;

    Game1(String fileName) {
        System.out.println("я родился!");
    }

    public ChatBotReply proceedRequest(String request, int userId) {
        if (request.equals("GuessTheNumber")) {
            generate();
            return new ChatBotReply("Я загадал число от 0 до 100. Отгадай какое!");
        }
        try {
            Integer.parseInt(request);
        } catch (NumberFormatException e) {
            return new ChatBotReply("Введи число");
        }
        if (Integer.parseInt(request) == secretNumber) {
            generate();
            return new ChatBotReply("Ты отгадал! \nЯ загадал число от 0 до 100. Отгадай какое! ");
        } else if (Integer.parseInt(request) < secretNumber) return new ChatBotReply("Мое число больше");
        else return new ChatBotReply("Мое число меньше");
    }

    public void generate() {
        secretNumber = (int) (Math.random() * 100);
    }
}
