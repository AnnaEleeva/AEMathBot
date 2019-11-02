public interface IGame {
    ChatBotReply proceedRequest(String request, int userId);
    void generate();
}