package Pokemon;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Pokemon hero;
    private static Pokemon bot;
    private static Random rand = new Random();

    private static float getRnd() {
        return 100 * rand.nextFloat();
    }

    private static Pokemon initBot() {
        bot = new FlyingPokemon("Bot9ra", getRnd(), getRnd(), getRnd(), getRnd(), getRnd(), getRnd());
        return bot;
    }

    private static void delay() throws InterruptedException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        ;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("======================== Welcome to Pokemon Universe ========================");
        delay();
        System.out.println("You can make your pokemon now!");
        System.out.println("Let's do it!");
        String[] params = {"name", "power", "speed", "endurance", "tactics", "accuracy", "aggression"};
        delay();
        System.out.println("You must write name, power, speed, endurance, tactics, accuracy, aggression of your pokemon");
        delay();
        System.out.println("Characteristics can change from 0 to 100 points");
        System.out.println("Let's go!");
        Scanner in = new Scanner(System.in);
        System.out.print(params[0] + " : ");
        String name = in.next();
        float[] temp = new float[6];
        for (int i = 1; i < 7; ++i) {
            System.out.print(params[i] + " : ");
            temp[i - 1] = in.nextFloat();
        }
        hero = new EarthPokemon(name, temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
        System.out.println("Congratulation! You made " + hero.getName() + " pokemon!");
        delay();
        System.out.println("Now you have 3 calm days!");
        delay();
        System.out.println("At this days you can: 1 - sleep, 2 - eat, 3 - play, 4 - rest, 5 - train");
        System.out.println("Please write one digit three times. Let's go!");
        delay();
        for (int i = 0; i < 3; ++i) {
            switch (in.nextInt()) {
                case 1: {
                    hero.sleep();
                    break;
                }
                case 2: {
                    hero.eat();
                    break;
                }
                case 3: {
                    hero.play();
                    break;
                }
                case 4: {
                    hero.rest();
                    break;
                }
                case 5:
                    hero.train();
                    break;
                default: {
                    System.out.println("Sorry, you lose one attempt :(");
                    break;
                }
            }
        }
        System.out.println("You're good!");
        System.out.println("Let's see how well you are in a fight");
        initBot();
        delay();
        System.out.println("Now your pokemon will fight with " + bot.getName() + " pokemon");
        Judge.setPokemons(hero, bot);
        Judge.hello();
        System.out.println("Are you ready to fight?(Y/N)fd");
        in.next();
        delay();
        System.out.println("Sorry. But any answer satisfies the judge :)");
        System.out.println("Let's fight!");
        delay();
        System.out.print("******************************");
        delay();
        System.out.print("******************************");
        delay();
        System.out.print("******************************");
        delay();
        System.out.print("******************************");
        delay();
        System.out.print("******************************");
        delay();
        System.out.println("\nFIGHT");
        System.out.println("FIGHT");
        System.out.println("FIGHT\n");
        delay();
        String winner = Judge.fight(in);
        System.out.println("WE HAVE WINNER !!!\n");
        System.out.println("WAIT 3\n");
        delay();
        System.out.println("WAIT 2\n");
        delay();
        System.out.println("WAIT 1\n");
        delay();
        if (winner.equals(hero.getName())) {
            System.out.println("CONGRATULATIONS!!!");
            System.out.println("YOU WIN THIS POKEMON BATTLE");
            delay();
            System.out.println("You received " + Judge.getReceivedPokemon1HP() + " HP");
            System.out.println(bot.getName() + " received " + Judge.getReceivedPokemon2HP() + " HP");
            delay();
            System.out.println("THANK YOU FOR THE GAME");
        } else {
            System.out.println("So, You lose :(");
            delay();
            System.out.println("You might train your pokemon and come back to the arena!");
            delay();
            System.out.println("We will wait you");
            delay();
            System.out.println("See you later...");
        }
    }
}