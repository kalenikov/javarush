package com.javarush.task.task30.task3008.client;

public class ClientGuiController extends Client {
    private ClientGuiModel model = new ClientGuiModel();
    private ClientGuiView view = new ClientGuiView(this);

    @Override
    protected SocketThread getSocketThread() {
        return new GuiSocketThread();
    }



    @Override
    public void run() {
        getSocketThread().run();
    }
    // Объяви метод ClientGuiModel getModel(), который должен возвращать модель.
    public ClientGuiModel getModel() {
        return model;
    }
    //getters
    @Override
    protected String getServerAddress() {
        return view.getServerAddress();
    }
    @Override
    protected int getServerPort() {
        return view.getServerPort();
    }
    @Override
    protected String getUserName() {
        return view.getUserName();
    }

    public class GuiSocketThread extends SocketThread {
        // должен устанавливать новое сообщение у модели и вызывать обновление вывода сообщений у представления.
        @Override
        protected void processIncomingMessage(String message) {
            model.setNewMessage(message);
            view.refreshMessages();
        }
        // должен добавлять нового пользователя в модель и вызывать обновление вывода пользователей у отображения.
        @Override
        protected void informAboutAddingNewUser(String userName) {
            model.addUser(userName);
            view.refreshUsers();
        }
        // должен удалять пользователя из модели и вызывать обновление вывода пользователей у отображения.
        @Override
        protected void informAboutDeletingNewUser(String userName) {
            model.deleteUser(userName);
            view.refreshUsers();
        }
        // должен вызывать аналогичный метод у представления.
        @Override
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            view.notifyConnectionStatusChanged(clientConnected);
        }
    }

    public static void main(String[] args) {
        ClientGuiController controller = new ClientGuiController();
        controller.run();
    }
}