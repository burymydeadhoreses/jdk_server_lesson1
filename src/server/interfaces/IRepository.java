package server.interfaces;

public interface IRepository {
    void appendLog(String text);
    String readLog();
}
