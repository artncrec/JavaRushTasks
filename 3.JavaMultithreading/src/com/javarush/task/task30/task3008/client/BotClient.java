package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by admin on 07.07.2017.
 */
public class BotClient extends Client {
    public class BotSocketThread extends SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(": ")) {
                String name = message.split(": ")[0];
                String data = message.split(": ")[1];
                String date;
                switch (data) {
                    case "дата":
                        date = "d.MM.YYYY";
                        break;
                    case "день":
                        date = "d";
                        break;
                    case "месяц":
                        date = "MMMM";
                        break;
                    case "год":
                        date = "YYYY";
                        break;
                    case "время":
                        date = "H:mm:ss";
                        break;
                    case "час":
                        date = "H";
                        break;
                    case "минуты":
                        date = "m";
                        break;
                    case "секунды":
                        date = "s";
                        break;
                    default:
                        date = null;
                }
                if (date != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(date);
                    Date time = new GregorianCalendar().getTime();
                    sendTextMessage("Информация для " + name + ": " + dateFormat.format(time));
                }
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
