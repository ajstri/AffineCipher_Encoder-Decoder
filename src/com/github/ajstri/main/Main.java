package com.github.ajstri.main;

import java.util.Scanner;

public class Main {

    private static final int[] aPossible = new int[]{3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};

    private static final char[] alphabet = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't' ,'u',
            'v', 'w', 'x', 'y', 'z'
    };

    public static void main (String[] args) {
        /*
        int a = 5;
        int b = 11;

        String toEncode = "MATH";

        char[] encodeArray = toEncode.toLowerCase().toCharArray();

        for (char character : encodeArray) {
            System.out.println(character + " encodes to: " + encode(a, b, character));
        }

        String toDecode = "TLCU";

        char[] decodeArray = toDecode.toLowerCase().toCharArray();

        for (char character : decodeArray) {
            System.out.println(character + " decodes to: " + decode(a, b, character));
        }
        */
        boolean cont = true;
        Scanner scan = new Scanner(System.in);

        int a;
        int b;
        String input;

        while (cont) {
            System.out.print("Please input a: ");
            a = scan.nextInt();
            System.out.print("Please input b: ");
            b = scan.nextInt();

            System.out.println("\n\n");
            System.out.println("Please input string to encode or decode: ");
            input = scan.next().toLowerCase();

            System.out.print("Are you encoding or decoding? (1 for encode, 2 for decode): ");
            int enOrDecode = scan.nextInt();

            System.out.println("Processing...");

            StringBuilder result = new StringBuilder();

            if (enOrDecode == 1) {
                char[] encodeArray = input.toLowerCase().toCharArray();
                for (char encode : encodeArray) {
                    result.append(encode(a, b, encode));
                }
                System.out.println("Encoded, the result is: " + result.toString().toUpperCase());
            }
            else if (enOrDecode == 2) {
                char[] decodeArray = input.toLowerCase().toCharArray();
                for (char decode : decodeArray) {
                    result.append(decode(a, b, decode));
                }
                System.out.println("Decoded, the result is: " + result.toString().toUpperCase());
            }
            else {
                System.out.println("Please input 1 or 2.");
            }

            System.out.print("Would you like to try again? (y/n): ");

            String yesOrNo = scan.next();
            if (yesOrNo.contains("n")) {
                cont = false;
            }
        }

    }

    private static char encode (int a, int b, char input) {
        // output = (a(input) + b) % 26

        int intOutput = ((a * discoverIntFromChar(input)) + b) % 26;
        return discoverCharFromInt(intOutput);
    }

    private static char decode (int a, int b, char input) {
        // b + b' = 0
        // a' * a = 1

        // (input + b') * (a') % 26 = output

        int bPrime = 26 - b;
        int aPrime = 0;

        // (a' * a) % 26 = 1
        for (int i = 1; i < 26; i++) {
            // check in array
            for (int check : aPossible) {
                if (i == check && ((a * check) % 26) == 1) {
                    // i is in array and equals 1 in mod26
                    aPrime = i;
                    break;
                }
            }
        }
        // if 0, failed
        if (aPrime == 0) {
            System.out.println("Could not find a suitable value for a'.");
            System.exit(0);
        }
        int intOutput = ((discoverIntFromChar(input) + bPrime) * aPrime) % 26;

        return discoverCharFromInt(intOutput);
    }

    private static char discoverCharFromInt(int input) {
        return alphabet[input];
    }

    private static int discoverIntFromChar(char input) {
        return new String(alphabet).indexOf(input);
    }

}
