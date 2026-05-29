package com.generation.italy.utils;

public class OutputUtils {
    public static void print(String msg) { System.out.println(msg); }
    public static void print(Object obj) { System.out.println(obj); }
    public static void print()           { System.out.println(); }

    public static void printTitle() {
        print("=================================");
        print("   DnD DUNGEON CRAWLER - v2.0   ");
        print("        by Manu/Konrad         ");
        print("=================================");
        print();
    }
}

