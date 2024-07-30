package server;

import client.ClientGUI;
import server.interfaces.IRepository;
import server.interfaces.IServer;

public class Main {
    public static void main(String[] args) {
        IRepository repository = new Repository("src/server/log.txt");
        IServer server = new Server(repository);
        ServerGUI serverGUI = new ServerGUI(server);

        new ClientGUI(serverGUI);
        new ClientGUI(serverGUI);
    }
}
