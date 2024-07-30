package server;

import client.ClientGUI;
import server.interfaces.IServer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ServerGUI extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    public IServer server;

    List<ClientGUI> clientGUIList;

    JButton btnStart, btnStop;

    JTextArea textArea;

    public ServerGUI(IServer server){
        this.server = server;

        clientGUIList = new ArrayList<>();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }

    private void createPanel() {
        textArea = new JTextArea();
        add(textArea);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(_ -> {
            if (server.getWorkingStatus()){
                server.message("Сервер уже был запущен");
                textArea.append("Сервер уже был запущен");
            } else {
                server.setWorkingStatus(true);
                server.message("Сервер запущен!");
                textArea.append("Сервер запущен!");
            }
        });

        btnStop.addActionListener(_ -> {
            if (!server.getWorkingStatus()) {
                server.message("Сервер уже был остановлен");
                textArea.append("Сервер уже был остановлен");
            } else {
                server.setWorkingStatus(false);
                while (!clientGUIList.isEmpty()){
                    server.disconnectUser(clientGUIList.getLast());
                }
                server.message("Сервер остановлен!");
                textArea.append("Сервер остановлен!");
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
}
