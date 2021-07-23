import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


class CollectionsHandler {

    private File fileIn;
    private File fileOut;

    private List<Integer> inputLineList;


    public CollectionsHandler() {
        inputLineList = new ArrayList<>();
    }

    public static void main(String[] args) {
        CollectionsHandler numbers = new CollectionsHandler();

        String pathIn = "src/main/resources/input.txt";
        String pathOut = "src/main/resources/output.txt";

        numbers.setFileIn(new File(pathIn).getAbsoluteFile());
        numbers.setFileOut(new File(pathOut).getAbsoluteFile());

        numbers.readFileLines();
        numbers.setInputLineList(numbers.filterEven(numbers.getInputLineList())); // Фильтр четных
        numbers.setInputLineList(numbers.umnojeniye(numbers.getInputLineList()));
        numbers.setInputLineList(numbers.sortList(numbers.getInputLineList()));

        numbers.writeResult();
    }

    public void setFileIn(File fileIn) {
        this.fileIn = fileIn;
    }
    public void setFileOut(File fileOut) {
        this.fileOut = fileOut;
    }

    public List<Integer> getInputLineList() {
        return inputLineList;
    }

    public void setInputLineList(List<Integer> list) {
        inputLineList = list;
    }

    public List<Integer> filterEven(List<Integer> list) {
        return list.stream().filter(line -> line %2 == 0).collect(Collectors.toList());
    }

    public List<Integer> sortList(List<Integer> list) {
        return list.stream().sorted().collect(Collectors.toList());
    }

    public List<Integer> umnojeniye(List<Integer> lines) {
        List<Integer> result = new ArrayList<>();
        for (Integer line : lines) {
            result.add((int) Math.pow(line, 2));
        }
        return result;
    }

    public void readFileLines() {
        try (Scanner in = new Scanner(fileIn)) {
            String line0 = in.nextLine();
            while (in.hasNextLine()) {

                String line = in.nextLine();
                int number = Integer.parseInt(line);
                inputLineList.add(number);
            }
            System.out.println(inputLineList);
        } catch (FileNotFoundException e) {
            System.out.println(e.fillInStackTrace());

        }
    }

    public void writeResult() {
        try (FileWriter writer = new FileWriter(fileOut)) {
            inputLineList.forEach(item -> {
                try {
                    writer.write(String.valueOf(item));
                    writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}