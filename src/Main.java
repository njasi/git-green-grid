import java.io.*;

public class Main {

    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        try {
            pushToGit(updateNum());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int updateNum() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("change.txt"));
        String firstLine = br.readLine(); // eat the first line
        int num = Integer.parseInt(br.readLine());
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter("change.txt"));
        bw.write(String.format("%s\n%d", firstLine, num + 1));
        bw.close();
        return num + 1;
    }

    private static void pushToGit(int changeNum) throws IOException, InterruptedException {
        Runtime.getRuntime().exec("git add change.txt").waitFor();
        Runtime.getRuntime().exec(String.format("git commit -m\"Daily commit #%d\"", changeNum)).waitFor();
        Runtime.getRuntime().exec("git push").waitFor();
    }
}
