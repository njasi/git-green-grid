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
        String[][] commands = new String[][]{
                new String[]{"git", "add", "-A"},
                new String[]{"git", "commit", "-m", "Daily Commit #" + changeNum},
                new String[]{"git", "push"}};
        for (String[] command : commands) {
            Process process = Runtime.getRuntime().exec(command);


            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int out = process.waitFor();

            if (out == 0) {
                System.out.println("SUCCESS: " + command.toString());
            } else {
                System.out.println("FAILURE: " + command.toString());
            }
        }
//
    }
}
