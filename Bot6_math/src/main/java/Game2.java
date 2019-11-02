public class Game2 implements IGame {
    private int compNumber;
    private double MainNumber;
    Game2(String fileName) {
        System.out.println("я родился!2");
    }

    public ChatBotReply proceedRequest(String request, int userId) {
        if (request.equals("TwoThirds")) {
            generate();
            return new ChatBotReply("Введи свое число от 0 до 100");
        }
        try {
            Integer.parseInt(request);
            //return true;
        } catch (NumberFormatException e) {
            return new ChatBotReply("Введи число");
        }
        int compNumberOld;
        if (Integer.parseInt(request) == compNumber) {
            compNumberOld = compNumber;
            generate();
            return new ChatBotReply("Ничья!\n Мое число было: " + compNumberOld + "\nВведи свое число от 0 до 100");
        }
        getNumber(request);
        if (Math.abs(Integer.parseInt(request) - MainNumber) < Math.abs(compNumber - MainNumber)) {
            compNumberOld = compNumber;
            generate();
            return new ChatBotReply("Ты выиграл!\nМое число было: " + compNumberOld + "\nГлавное число: "+MainNumber+"\nВведи свое число от 0 до 100");
        } else {
            compNumberOld = compNumber;
            generate();
            return new ChatBotReply("Ты проиграл!\nМое число было: " + compNumberOld + "\nГлавное число: "+MainNumber+"\nВведи свое число от 0 до 100");
        }
    }

    public void generate() {
        compNumber = (int) (Math.random() * 100);
    }

    private void getNumber(String x) {
        MainNumber=((Integer.parseInt(x) + compNumber) / 2.0) * (2.0/3.0);
    }
}

