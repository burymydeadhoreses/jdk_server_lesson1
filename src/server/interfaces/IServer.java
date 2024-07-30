package server.interfaces;

import client.ClientGUI;

public interface IServer {
    boolean getWorkingStatus();
    void setWorkingStatus(boolean status);
    boolean connectUser(ClientGUI clientGUI);
    void disconnectUser(ClientGUI clientGUI);
    String getLog();
    void message(String text);
}
