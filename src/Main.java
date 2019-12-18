import java.io.*;

public class Main {

    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        try {
            updateNum();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateNum() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("change.txt"));
        String firstLine = br.readLine(); // eat the first line
        int num = Integer.parseInt(br.readLine());
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter("change.txt"));
        bw.write(String.format("%s\n%d", firstLine, num + 1));
        bw.close();
    }

    private static void pushToGit(){

    }
}
