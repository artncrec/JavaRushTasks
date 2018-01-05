package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by admin on 05.07.2017.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            String name;
            Message requestName = new Message(MessageType.NAME_REQUEST);
            Message getName;
            while (true)
            {
                connection.send(requestName);
                getName = connection.receive();
                name = getName.getData();
                if (getName.getType() == MessageType.USER_NAME && !name.isEmpty() && !(connectionMap.containsKey(name)))
                {
                    connectionMap.put(getName.getData(), connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    return getName.getData();
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            Message message;
            for (String name : connectionMap.keySet())
                if (!name.equals(userName))
                {
                    message = new Message(MessageType.USER_ADDED, name);
                    connection.send(message);
                }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            Message message;
            while (true)
            {
                message = connection.receive();
                if (message.getType() == MessageType.TEXT)
                {
                    String s = userName + ": " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, s));
                }
                else
                    ConsoleHelper.writeMessage("В сообщении нет текста");
            }
        }

        @Override
        public void run() {
            String userName = null;
            ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом " + this.socket.getRemoteSocketAddress() + ".");
            try (Connection connection = new Connection(this.socket))
            {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            }
            catch (Exception e)
            {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");
            }
            if (userName != null)
            {
                for (String n : connectionMap.keySet())
                    if (n.equals(userName))
                    {
                        connectionMap.remove(userName);
                        sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                    }
            }
            ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто.");
        }
    }

    public static void sendBroadcastMessage(Message message){
        try {
            for (Connection c : connectionMap.values())
                c.send(message);
        } catch (IOException e){
            ConsoleHelper.writeMessage("Сообщение не отправлено");
        }
    }

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new java.net.ServerSocket(port))
        {
            System.out.println("Сервер запущен");
            while (true)
            {
                new Handler(serverSocket.accept()).start();
            }
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage(e.getMessage());
        }
    }
}