import java.io.*;  

public class Server
{
    public static void main(String[] args) throws Exception {
        System.out.println("[Server] Lauched");
        final int TransposeRequestPortNumber = 3000;
        final int DeterminateRequestPortNumber = 4000;

        new TransposeListener(TransposeRequestPortNumber).start();
        new DeterminateListener(DeterminateRequestPortNumber).start();
    }
}