import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;

public class bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi t = new TelegramBotsApi();
        try {
            t.registerBot(new bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        String text = msg.getText();
        //Location loc = msg.getLocation();
        Weather w = new Weather();
        SendMessage sm = new SendMessage();
        sm.setChatId(msg.getChatId());
        //if (loc.getLatitude()!=0 && loc.getLongitude() !=0){
        //sm.setText("lat:"+loc.getLatitude()+"lon:"+loc.getLongitude());

        if (text.equals("/start")) {
            sm.setText("Hello!");
        } else if (text.equals("/help")) {
            sm.setText("Enter a city name to get the current temperature");
        } else if (text.equals("/about")) {
            sm.setText("You are using version 3.4! All information imported from the openweathermap.org");

        } else {
            try {
                sm.setText(w.getAddress(text));
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return "Robocode_weather_7437_bot";
    }

    @Override
    public String getBotToken() {
        return "1439984118:AAHWoBlmjknYdrkMOMoxZDZLKvUmY1-a6j8";
    }
}
    