package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ivan Korol on 8/6/2018
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен");
            while (true) {
                new Handler(serverSocket.accept()).start();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sendBroadcastMessage(Message message) {
        try {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet())
                pair.getValue().send(message);
        } catch (IOException e) {
            System.out.println("Сообщение не отправлено");
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        private Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            String name = null;
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                name = message.getData();
                if (message.getType() == MessageType.USER_NAME && !message.getData().isEmpty() && !name.equals("") && !connectionMap.containsKey(name)) {
                    connectionMap.put(name, connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    return name;
                }
            }
        }


        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                if (pair.getKey() != userName) {
                    connection.send(new Message(MessageType.USER_ADDED, pair.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                StringBuffer textMessage = new StringBuffer();
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    textMessage.append(userName + ": " + message.getData());
                    sendBroadcastMessage(new Message(MessageType.TEXT, textMessage.toString()));
                } else if (message.getType() != MessageType.TEXT) {
                    ConsoleHelper.writeMessage("Ошибка: в сообщении должен быть текст.");
                }
            }
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("Соединение установлено: " + socket.getRemoteSocketAddress());
            String usetName = null;
            try (Connection connection = new Connection(socket)) {
                usetName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, usetName));
                notifyUsers(connection, usetName);
                serverMainLoop(connection, usetName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом." + e.getMessage());
            } finally {
                if (usetName != null) {
                    connectionMap.remove(usetName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, usetName));
                }
            }
            ConsoleHelper.writeMessage("Соединение закрыто.");
        }
    }
}
