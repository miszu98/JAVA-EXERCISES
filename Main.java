

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;



public class Main  {

    public static void main(String[] args)  {

    }

    public static void FizzBuzz() {
        for (int i = 1; i < 100; i++) {
            System.out.println(i % 3 == 0 && i % 5 == 0 ? "FizzBuzz" : i % 3 == 0 ? "Fizz" : i % 5 == 0 ? "Buzz": String.valueOf(i));
        }
    }
    public static void DiffInTwoArrays() {
        var a = new int[] { 1, 3, 15, 11, 2 };
        var b = new int[] { 23, 127, 235, 19, 8 };
        int numOne = 0, numTwo = 0;
        int output = Math.max(Arrays.stream(b).max().getAsInt(), Arrays.stream(a).max().getAsInt());
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                int value = a[i] - b[j];
                if (value > 0 && value < output) {
                    numOne = a[i];
                    numTwo = b[j];
                    output = value;
                }
            }
        }
        System.out.println(String.format("Pair is: (%d, %d)\nOutput: %d", numOne, numTwo, output));
    }

    public static int[] solve(int[] arr) {
        List<Integer> lista = new ArrayList<>();
        for (int i=0; i<arr.length; i++) {
            if (!lista.contains(arr[i])) {
                lista.add(arr[i]);
            }
        }
        return lista.stream().mapToInt(Integer::intValue).toArray();
    }

    public static double evaluate(String expr) {
        if (expr.equals(""))
            return 0;
        String[] text = expr.split(" ");
        List<Double> list = new ArrayList<>();
        for (String x : text) {
            if (!x.contains(".") && x.chars().allMatch(z -> Character.isDigit(z)))
                list.add(Double.parseDouble(x));
            else if (x.contains(".")) {
                String[] doubleNumber = x.split(".");
                boolean check = true;
                for (String d : doubleNumber) {
                    if (!d.chars().allMatch(Character::isDigit))
                        check = false; break;
                }
                if (check)
                    list.add(Double.parseDouble(x));
            } else {
                double n1 = list.remove(list.size()-1);
                double n2 = list.remove(list.size()-1);

                switch (x) {
                    case "+":
                        list.add(n1 + n2); break;
                    case "-":
                        list.add(n2 - n1); break;
                    case "*":
                        list.add(n1 * n2); break;
                    case "/":
                        list.add(n2 / n1); break;
                }
            }
        }
        return list.remove(list.size()-1);
    }

    public static String reverseWords(String str) {
        String[] wordsArr = str.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, wordsArr.length).forEach(x -> stringBuilder.append(wordsArr[(wordsArr.length-1)-x]).append(" "));
        return stringBuilder.substring(0, stringBuilder.length()-1);
    }

    public static int[][] twosDifference(int[] array) {
        List<List<Integer>> result = new ArrayList<>();
         for (int i=0; i<array.length; i++) {
            for (int j=0; j<array.length; j++) {
                int a = Math.max(array[i], array[j]);
                int b = Math.min(array[i], array[j]);
                if (a - b == 2 && !result.contains(Arrays.asList(b, a))) {
                    result.add(Arrays.asList(b, a));
                }
            }
        }
         Collections.sort(result, new Comparator<List<Integer>>() {
             @Override
             public int compare(List<Integer> o1, List<Integer> o2) {
                 return o1.get(1).compareTo(o2.get(1));
             }
         });
         int[][] r = new int[result.size()][];
         IntStream.range(0, result.size()).forEach(x -> r[x] = result.get(x).stream().mapToInt(i -> i).toArray());
         return r;
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } return n * factorial(n - 1);
    }

    public String decode(String bits) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0; i<bits.length(); i+=3) {
            String triplet = bits.substring(i, i + 3);
            long one = triplet.chars().filter(x -> (char) x == '1').count();
            long zero = triplet.chars().filter(x -> (char) x == '0').count();

            if (one > zero) {
                stringBuilder.append("1");
            } else {
                stringBuilder.append("0");
            }
        }
        List<Integer> dec = new ArrayList<>();
        for (int i=0; i<stringBuilder.length(); i+=8) {
            dec.add(Integer.parseInt(stringBuilder.substring(i, i+8), 2));
        }
        StringBuilder result = new StringBuilder();
        dec.forEach(x -> result.append((char) (long) x));
        return result.toString();
    }

    public String encode(String text) {
        int[] ascii = text.chars().toArray();
        String[] bin = new String[ascii.length];

        for (int i=0; i<ascii.length; i++) {
            String b = Integer.toBinaryString(ascii[i]);
            bin[i] = "0".repeat(8 - b.length()) + b;
        }
        System.out.println(Arrays.toString(bin));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<ascii.length; i++) {
            bin[i].chars().mapToObj(x -> String.valueOf((char) x).repeat(3)).forEach(el -> stringBuilder.append(el));
        }
        return stringBuilder.toString();
    }

    public static List<String> top3(String s) {
        StringBuilder r = new StringBuilder();
        List<Character> except = Arrays.asList('/', ',', '.');
        for (Character x : s.toCharArray()) {
            if (!except.contains(x)) {
                r.append(x);
            }
        }

        Map<String, Integer> mapa = new HashMap<>();
        for (String x : r.toString().split(" ")) {
            int count = 0;
            for (String z : r.toString().split(" ")) {
                if (x.equals(z)) {
                    count++;
                }
            }
            if (!x.equals(""))
                mapa.put(x, count);
        }
        LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>();
        mapa.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
        System.out.println(sorted);
        return new ArrayList<>(sorted.keySet());
    }

    public static List<Integer> oddCount(int n) {
        int number = 0;
        List<Integer> result = new ArrayList<>();
        while (result.size() != n) {
            if (number % 2 == 1) {
                result.add(number);
            }
            number++;
        }
        return result;
    }

    public static String capitalize(String s, int[] ind) {
        char[] c = s.toCharArray();
        for (int i=0; i<ind.length; i++) {
            if (ind[i] < s.length()) {
                c[ind[i]] = Character.toUpperCase(c[ind[i]]);
            }
        }
        return new String(c);
    }

    public static int[] parse(String data) {
        int i = 0;
        List<Integer> lista = new ArrayList<>();
        for (char x : data.toCharArray()) {
            switch (x) {
                case 'i':
                    i++;
                    break;
                case 'd':
                    i--;
                    break;
                case 's':
                    i = (int) Math.pow(i, 2);
                    break;
                case 'o':
                    lista.add(i);
            }
        }
        return lista.stream().mapToInt(z -> z).toArray();
    }

    public static BigInteger Get(long power) {
        if (power == 0 || power == 1 || power == 2) {
            return new BigInteger("1");
        } else {
            return new BigInteger(String.valueOf(Get(power - 2))).add(new BigInteger(String.valueOf(Get(power - 3))));
        }
    }

    public static int solve1(final String s) {
        List<String> letters = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (Arrays.asList('a', 'e', 'i', 'o', 'u').contains(s.charAt(i))) {
                letters.add(String.valueOf(temp));
                temp = new StringBuilder();

            } else {
                temp.append(s.charAt(i));
            }
            if (i == s.length()-1) {
                letters.add(String.valueOf(temp));
            }
            i++;
        }
        Map<Character, Integer> alphabet = new HashMap<>();
        int c = 1;
        for (int x=97; x<=122; x++) {
            alphabet.put((char) x, c);
            c++;
        }
        int result = 0;
        for (int z=0; z<letters.size(); z++) {
            int tempRes = 0;
            for (char v : letters.get(z).toCharArray()) {
                tempRes += alphabet.get(v);
            }
            if (tempRes > result) {
                result = tempRes;
            }
        }

        return result;
    }

    public static long powerSumDigTerm(int n) {
        int[] nums = {81, 512, 2401, 4913};
        if (n > 0 && n <= 4) {
            return nums[n - 1];
        } else {
            int number = 4;
            for (int x = 4914; x < 999999999; x++) {
                int w = 0;
                for (char z : String.valueOf(x).toCharArray()) {
                    w += Integer.parseInt(String.valueOf(z));
                }
                if ((int) Math.pow(w, 3) == x) {
                    number++;

                    if (number == n) {
                        return x;
                    }
                }
            }
            return 0;
        }
    }

    public static int[] sort(final int[] array) {
        Map<Integer, String> values = Map.ofEntries(Map.entry(1, "one"), Map.entry(2, "two"),
                Map.entry(3, "three"), Map.entry(4, "four"), Map.entry(5, "five"),
                Map.entry(6, "six"), Map.entry(7, "seven"), Map.entry(8, "eight"),
                Map.entry(9, "nine"), Map.entry(10, "ten"), Map.entry(11, "eleven"),
                Map.entry(12, "twelve"), Map.entry(13, "thirteen"), Map.entry(14, "fourteen"),
                Map.entry(15, "fiveteen"), Map.entry(16, "sixteen"), Map.entry(17, "seventeen"),
                Map.entry(18, "eighteen"), Map.entry(19, "nineteen"), Map.entry(20, "twenty"),
                Map.entry(30, "thirty"), Map.entry(40, "fourty"), Map.entry(50, "fivety"),
                Map.entry(60, "sixty"), Map.entry(70, "seventy"), Map.entry(80, "eighty"),
                Map.entry(90, "ninety"), Map.entry(0, "zero"));

        List<String> stringsValues = new ArrayList<>();
        String numString;
        for (int x : array) {
            numString = String.valueOf(x);
            switch (String.valueOf(x).length()) {
                case 1:
                    stringsValues.add(values.get(x));
                    break;
                case 2:
                    if (numString.charAt(0) == '1') {
                        stringsValues.add(values.get(x));
                    } else if (numString.charAt(1) != '0') {
                        stringsValues.add(values.get(Integer.parseInt(numString.charAt(0) + "0")) + " " + values.get(Integer.parseInt(String.valueOf(numString.charAt(1)))));
                    } else {
                        stringsValues.add(values.get(Integer.parseInt(numString.charAt(0) + "0")));
                    }
                    break;
                case 3:
                    int first = Integer.parseInt(String.valueOf(numString.charAt(0)));
                    int second = Integer.parseInt(numString.charAt(1) + "0");
                    int third = Integer.parseInt(String.valueOf(numString.charAt(2)));
                    if (numString.charAt(1) == '0' && numString.charAt(2) == '0') {
                        stringsValues.add(values.get(first) + " hundred");
                    } else if (numString.charAt(1) != '0' && numString.charAt(2) == '0') {
                        stringsValues.add(values.get(first) + " hundred " + values.get(second));
                    } else if (numString.charAt(1) == '1' && numString.charAt(2) != '0') {
                        stringsValues.add(values.get(first) + " hundred " + values.get(Integer.parseInt(numString.substring(1))));
                    } else if (numString.charAt(2) != '0') {
                        stringsValues.add(values.get(first) + " hundred " + values.get(Integer.parseInt(numString.charAt(1) + "0")) + " " + values.get(third));
                    }
                    break;
            }
        }
        Map<String, Integer> resultMap = new HashMap<>();
        for (int i=0; i<array.length; i++) {
            resultMap.put(stringsValues.get(i), array[i]);
        }
        int[] result = new int[array.length];
        Collections.sort(stringsValues);
        for (int i=0; i<array.length; i++) {
            result[i] = resultMap.get(stringsValues.get(i));
        }

        return result;
    }

    public static Integer find(final int[] array) {
        for (int x=0; x<array.length-1; x++) {
            if (array[x+1] - array[x] != 1) {
                return array[x+1];
            }
        }
        return null;
    }

    public static int mxdiflg(String[] a1, String[] a2) {
        int maxLength = 0;
        for (int i=0; i < a1.length; i++) {
            for (int j = 0; j < a2.length; j++) {
                if (a1[i].length() - a2[j].length() > maxLength) {
                    maxLength = a1[i].length() - a2[j].length();
                } else if (a2[j].length() - a1[i].length() > maxLength) {
                    maxLength = a2[j].length() - a1[i].length();
                }
            }
        }
        return maxLength != 0 ? maxLength : -1;
    }

    public static String alternateCase(final String string) {
        var wynik = new StringBuilder();
        for (int i=0; i < string.length(); i++) {
            if (Character.isLowerCase(string.charAt(i))) {
                wynik.append(Character.toUpperCase(string.charAt(i)));
            } else {
                wynik.append(Character.toLowerCase(string.charAt(i)));
            }
        }
        return String.valueOf(wynik);
    }

    public static int solve(final String digits) {
        int number = 0;
        for (int i=0; i<digits.length()-4; i++) {
            if (Integer.parseInt(digits.substring(i, i+5)) > number) {
                number = Integer.parseInt(digits.substring(i, i+5));
            }
        }
        return number;
    }

}
