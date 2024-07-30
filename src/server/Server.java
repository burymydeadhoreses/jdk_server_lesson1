package server;

import client.ClientGUI;
import server.interfaces.IRepository;
import server.interfaces.IServer;

import java.util.ArrayList;
import java.util.List;

public class Server implements IServer {
    public boolean work;

    IRepository repository;
    List<ClientGUI> clientGUIList;

    public Server(IRepository repository)
    {
        this.repository = repository;
        clientGUIList = new ArrayList<>();
    }

    @Override
    public boolean getWorkingStatus() {
        return work;
    }

    @Override
    public void setWorkingStatus(boolean status) {
        work = status;
    }

    @Override
    public boolean connectUser(ClientGUI clientGUI){
        if (!work){
            return false;
        }
        clientGUIList.add(clientGUI);
        return true;
    }

    @Override
    public String getLog() {
        return repository.readLog();
    }

    @Override
    public void disconnectUser(ClientGUI clientGUI){
        clientGUIList.remove(clientGUI);
        if (clientGUI != null){
            clientGUI.disconnectFromServer();
        }
    }

    @Override
    public void message(String text){
        if (!work){
            return;
        }
        text += "";
        repository.appendLog(text);
        answerAll(text);
    }

    private void answerAll(String text){
        for (ClientGUI clientGUI: clientGUIList){
            clientGUI.answer(text);
        }
    }

}
