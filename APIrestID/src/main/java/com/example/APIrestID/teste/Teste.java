package com.example.APIrestID.teste;

public class Teste {
    public static void main(String[] args) {
        for (int i = 1; i < 1000; i++) {

            if(i % 3 ==0) {
                System.out.println("365ti");
            } else if(i % 5 == 0){
                System.out.println("Não lembro o texto =/");
            } else {
                System.out.println(i);
            }
        }
    }
}
