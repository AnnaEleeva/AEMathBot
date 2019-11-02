import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {

    private static ChatBot chatBot;
    private String username="AEMathBot";
    private String token="***************************************";

    TelegramBot(){
        var tests = new ArrayList<Pair<String, Class<? extends IGame>>>();
        tests.add(new Pair("guessTheNumber", Game1.class));
        tests.add(new Pair("twoThirds", Game2.class));
        tests.add(new Pair("BullsAndCows", Game3.class));
        chatBot = new ChatBot(new GameFactory(), tests);

    }
    public void onUpdateReceived(Update update) {
        try {
            ChatBotReply reply = chatBot.answer(update.getMessage().getText(),
                    update.getMessage().getFrom().getId());

            var sendMessage = new SendMessage(
                    update.getMessage().getChatId(),
                    reply.message
            );
                setButton(sendMessage);
                execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void setButton(SendMessage s){
        ReplyKeyboardMarkup rkm=new ReplyKeyboardMarkup();
        s.setReplyMarkup(rkm);
        rkm.setSelective(true);
        rkm.setResizeKeyboard(true);
        rkm.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRowList=new ArrayList<>();
        KeyboardRow keyboardFirstRow=new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("GuessTheNumber"));
        keyboardFirstRow.add(new KeyboardButton("TwoThirds"));
        keyboardFirstRow.add(new KeyboardButton("BullsAndCows"));

        keyboardRowList.add(keyboardFirstRow);
        rkm.setKeyboard(keyboardRowList);
    }
    public String getBotUsername() {
        return username;
    }
    public String getBotToken() {
        return token;
    }
}
