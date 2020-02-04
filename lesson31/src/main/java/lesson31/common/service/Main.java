package lesson31.common.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("server_content.xml");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

    }
}
