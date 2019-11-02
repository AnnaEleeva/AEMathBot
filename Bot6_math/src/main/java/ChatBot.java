import java.util.List;

class ChatBot {
    private IGame gameInstance;
    private IGameFactory gameFactory;
    private List<Pair<String, Class<? extends IGame>>> tests;
    private Integer curTest;

    ChatBot(IGameFactory gameFactory, List<Pair<String, Class<? extends IGame>>> tests) {
        this.gameFactory = gameFactory;
        this.tests = tests;
        curTest = -1;
    }

    ChatBotReply answer(String message, int userId) {

        if ("GuessTheNumber".equals(message)) {
            curTest = 0;
            gameInstance = gameFactory.create(tests.get(curTest).getSecond(), tests.get(curTest).getFirst());
        }
        if ("TwoThirds".equals(message)) {
            curTest = 1;
            gameInstance = gameFactory.create(tests.get(curTest).getSecond(), tests.get(curTest).getFirst());
        }
        if ("BullsAndCows".equals(message)) {
            curTest = 2;
            gameInstance = gameFactory.create(tests.get(curTest).getSecond(), tests.get(curTest).getFirst());
        }
        if (curTest != -1) {
            ChatBotReply firstQuestion = gameInstance.proceedRequest(message, userId);
            return new ChatBotReply(firstQuestion.message);
        }
        return new ChatBotReply("Выбери игру!");
    }
}
