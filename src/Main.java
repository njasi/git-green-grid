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

    private static void pushToGit(int changeNum) throws IOException, InterruptedException { //
        String[] commands = new String[]{"git add -A", String.format("git commit -m \"Daily commit #%d\"", changeNum), "git push"};
        for (String command : commands) {

            int out = Runtime.getRuntime().exec(command).waitFor();
            if (out == 0) {
                System.out.println("SUCCESS: " + command);
            } else {
                System.out.println("FAILURE: " + command);
            }
        }
//
    }
}
