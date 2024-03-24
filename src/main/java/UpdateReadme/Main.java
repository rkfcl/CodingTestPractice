package UpdateReadme;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.net.URLEncoder;

public class Main {
    private static final String HEADER = "# \n"
            + "# 백준 & 프로그래머스 문제 풀이 목록\n";

    public static void main(String[] args) {
        StringBuilder content = new StringBuilder();
        List<String> directories = new ArrayList<>();
        List<String> solveds = new ArrayList<>();
        content.append(HEADER);
        processDirectory("백준",content,directories,solveds);
        processDirectory("프로그래머스",content,directories,solveds);

    }
    private static void processDirectory(String targetDirectory, StringBuilder content, List<String> directories, List<String> solveds) {
        content.append("## 📚 ").append(targetDirectory).append("\n");
        try {
            Files.walk(Paths.get(targetDirectory), FileVisitOption.FOLLOW_LINKS)
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            String category = filePath.getParent().getFileName().toString();

                            if ("images".equals(category)) {
                                return;
                            }

                            String directory = filePath.getParent().getParent().getFileName().toString();
                            if (".".equals(directory)) {
                                return;
                            }

                            if (!directories.contains(directory)) {

                                content.append("### 🚀 ").append(directory).append("\n");
                                content.append("| 문제번호 | 링크 |\n");
                                content.append("| ----- | ----- |\n");

                                directories.add(directory);
                            }



                            if (!solveds.contains(category)) {
                                String link = URLEncoder.encode(filePath.toString(), "UTF-8");
                                content.append("|").append(category).append("|[링크](").append(link).append(")|\n");
                                solveds.add(category);
                                System.out.println("category : " + category);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            writeToFile("README.md", content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
