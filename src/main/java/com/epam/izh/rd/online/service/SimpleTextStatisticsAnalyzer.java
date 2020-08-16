package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;

import static java.util.Collections.*;

/**
 * Совет:
 * Начните с реализации метода {@link SimpleTextStatisticsAnalyzer#getWords(String)}.
 * Затем переиспользуйте данный метод при реализации других.
 * <p>
 * При необходимости, можно создать внутри данного класса дополнительные вспомогательные приватные методы.
 */
public class SimpleTextStatisticsAnalyzer implements TextStatisticsAnalyzer {

    /**
     * Необходимо реализовать функционал подсчета суммарной длины всех слов (пробелы, знаким препинания итд не считаются).
     * Например для текста "One, I - tWo!!" - данный метод должен вернуть 7.
     *
     * @param text текст
     */
    @Override
    public int countSumLengthOfWords(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            if (a >= 60 && a <= 90 || a >= 97 && a <= 122) {
                count++;
            }
        }
        return count;
    }

    /**
     * Необходимо реализовать функционал подсчета количества слов в тексте.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" - данный метод должен вернуть 7.
     *
     * @param text текст
     */
    @Override
    public int countNumberOfWords(String text) {
        String[] words = text.split("\\W+");
        return words.length;
    }

    /**
     * Необходимо реализовать функционал подсчета количества уникальных слов в тексте (с учетом регистра).
     * Например для текста "One, two, three, three - one, tWo, tWo!!" - данный метод должен вернуть 5.
     * param text текст
     */
    @Override
    public int countNumberOfUniqueWords(String text) {
        String[] words = text.split("\\W+");
        Set<String> unicWords = new HashSet<String>(Arrays.asList(words));
        return unicWords.size();
    }

    /**
     * Необходимо реализовать функционал получения списка слов из текста.
     * Пробелы, запятые, точки, кавычки и другие знаки препинания являются разделителями слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должен вернуться список :
     * {"One", "two", "three", "three", "one", "tWo", "tWo"}
     *
     * @param text текст
     */
    @Override
    public List<String> getWords(String text) {
        String[] words = text.split("\\W+");
        List<String> wordList = new ArrayList<String>(Arrays.asList(words));
        return wordList;
    }

    /**
     * Необходимо реализовать функционал получения списка уникальных слов из текста.
     * Пробелы, запятые, точки, кавычки и другие знаки препинания являются разделителями слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должен вернуться список :
     * {"One", "two", "three", "one", "tWo"}
     *
     * @param text текст
     */
    @Override
    public Set<String> getUniqueWords(String text) {
        String[] words = text.split("\\W+");
        Set<String> unicWords = new HashSet<String>(Arrays.asList(words));
        return unicWords;
    }

    /**
     * Необходимо реализовать функционал подсчета количества повторений слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должны вернуться результаты :
     * {"One" : 1, "two" : 1, "three" : 2, "one" : 1, "tWo" : 2}
     *
     * @param text текст
     */
    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        String[] words = text.split("\\W+");
        List<String> wordList = new ArrayList<String>(Arrays.asList(words));
        Map<String, Integer> countedWords = new HashMap<String, Integer>();

        for (String word: wordList) {
            if (countedWords.containsKey(word)) {
                countedWords.put(word, countedWords.get(word) + 1);
            } else {
                countedWords.put(word, 1);
            }
        }

        return countedWords;
    }

    /**
     * Необходимо реализовать функционал вывода слов из текста в отсортированном виде (по длине) в зависимости от параметра direction.
     * Например для текста "Hello, Hi, mother, father - good, cat, c!!" должны вернуться результаты :
     * ASC : {"mother", "father", "Hello", "good", "cat", "Hi", "c"}
     * DESC : {"c", "Hi", "cat", "good", "Hello", "father", "mother"}
     *
     * @param text текст
     */
    @Override
    public List<String> sortWordsByLength(String text, Direction direction){
        String[] words = text.split("\\W+");
        Comparator<String> compare = new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                if (direction.toString().equals("DESC")) {
                    if (s.length() - t1.length() > 0) {
                        return 1;
                    } else if (s.length() - t1.length() < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                } else {
                    if (s.length() - t1.length() > 0) {
                        return -1;
                    } else if (s.length() - t1.length() < 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        };

        List<String> wordList = new ArrayList<String>(Arrays.asList(words));

        Collections.sort(wordList);
        Collections.sort(wordList, compare);

        return wordList;
    }
}
