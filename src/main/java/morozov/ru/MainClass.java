package morozov.ru;

public class MainClass {

    public static void main(String[] args) {
        if (args.length == 2) {
            String input = args[0];
            String output = args[1];

            String deliveredText = ReadWriteTextFile.readText(input);
            String result = TextParser.getReadyResult(deliveredText);
            if (result != null && !result.equals("")) {
                ReadWriteTextFile.writeText(output, result);
            } else {
                System.out.println("wrong format");
            }
        } else {
            System.out.println("Wrong arguments");
        }
    }

}