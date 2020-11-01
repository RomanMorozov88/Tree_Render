package morozov.ru;

import java.util.ArrayList;
import java.util.List;

public class TextParser {

    private static final char OPEN_CHAR = '(';
    private static final char CLOSE_CHAR = ')';

    /**
     * Главный метод.
     *
     * @param text
     * @return - Если входящая строка некорректна- вернёт null
     */
    public static String getReadyResult(String text) {
        String result = null;
        List<Integer> opens = new ArrayList<>();
        List<Integer> close = new ArrayList<>();
        char[] textParts = text.toCharArray();
        for (int i = 0; i < textParts.length; i++) {
            if (textParts[i] == OPEN_CHAR) {
                opens.add(i);
            } else if (textParts[i] == CLOSE_CHAR) {
                close.add(i);
            }
        }
        if (opens.size() > 0 && (opens.size() == close.size())) {
            if (checkBraceCombo(opens, close)) {
                result = lookAtText(text, opens, close);
            }
        }
        return result;
    }

    /**
     * Метод проверку двумерного массива, который здесь и является таблицей Юнга.
     * Так, int[0][x] должен быть меньше int[1][x]
     *
     * @return
     */
    private static boolean checkBraceCombo(List<Integer> opens, List<Integer> close) {
        boolean result = true;
        for (int i = 0; i < opens.size(); i++) {
            if (opens.get(i) > close.get(i)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Если входящая строка прошла провыерки- происходит её парсинг.
     *
     * @param text
     * @param opens
     * @param close
     * @return
     */
    private static String lookAtText(String text, List<Integer> opens, List<Integer> close) {
        StringBuilder result = new StringBuilder();
        int level = 0;
        int closeIndex = 0;
        List<String> cutted = null;
        String buffer = null;
        for (int i = 0; i < opens.size(); i++) {

            //Что бы не выскочить за пределы листа.
            if (i + 1 < opens.size()) {
                buffer = text.substring(opens.get(i) + 1, opens.get(i + 1));
            } else {
                buffer = text.substring(opens.get(i) + 1, close.get(i));
            }

            //шагает назад для корректного подсчёта отступов.
            while (opens.get(i) > close.get(closeIndex)) {
                closeIndex++;
                level--;
            }

            //Получает строку и записывает её врезультат с нужным количеством отступов.
            //Получает null если внутри содержится некорректная нода.
            cutted = cutInfoAr(buffer);

            if (cutted == null) {
                return null;
            }

            for (String string : cutted) {
                for (int s = 0; s < level; s++) {
                    result.append("   ");
                }
                result
                        .append(string)
                        .append("\n");
            }

            level++;
        }
        return result.toString();
    }

    /**
     * Вырезает из входящей подстроки нужную информацию.
     *
     * @param buffer
     * @return
     */
    private static List<String> cutInfoAr(String buffer) {
        buffer = buffer.replaceAll("\\)", "");
        String[] bufferResult = buffer.split("\\s");
        List<String> result = new ArrayList<>();

        //Проверка корректности. Каждая нода должна начинаться с цифры не быть пустой (т.е. () )
        if (buffer.length() == 0 || !Character.isDigit(buffer.toCharArray()[0])) {
            return null;
        }

        StringBuilder bufferContent = new StringBuilder();
        for (String s : bufferResult) {
            if (Character.isDigit(s.toCharArray()[0])) {
                if (!bufferContent.toString().equals("")) {
                    result.add(bufferContent.toString());
                }
                bufferContent = new StringBuilder();
                bufferContent.append(s);
            } else {
                bufferContent
                        .append(" ")
                        .append(s);
            }
        }
        result.add(bufferContent.toString());

        return result;
    }
}