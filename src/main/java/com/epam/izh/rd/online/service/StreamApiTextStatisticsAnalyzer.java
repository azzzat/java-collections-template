package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        int num = (int) text.chars().
                filter(x -> (x >= 60 && x <= 90 || x >= 97 && x <= 122)).
                count();
        return num;
    }

    @Override
    public int countNumberOfWords(String text) {
        return text.split("\\W+").length;
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return new HashSet<String>(Arrays.asList(text.split("\\W+"))).size();
    }

    @Override
    public List<String> getWords(String text) {
        return new ArrayList<String>(Arrays.asList(text.split("\\W+")));
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return new HashSet<String>(Arrays.asList(text.split("\\W+")));
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        List<String> wordList = new ArrayList<String>(Arrays.asList(text.split("\\W+")));
        Map<String, Integer> countedWords = new HashMap<>();
        wordList.stream()
                .forEach(s -> countedWords.put(s, countedWords.getOrDefault(s, 0) + 1));

        return countedWords;
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        Comparator<String> textComparator = new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                if (s.length() - t1.length() > 0) {
                    if (direction.toString().equals("DESC")) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else if (s.length() - t1.length() < 0) {
                    if (direction.toString().equals("DESC")) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    return 0;
                }
            }
        };


        List<String> wordList = new ArrayList<String>(Arrays.asList(text.split("\\W+")))
             .stream()
             .sorted()
             .sorted(textComparator)
             .collect(Collectors.toList());

        return wordList;
        }
    }

