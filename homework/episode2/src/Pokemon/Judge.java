package Pokemon;

import java.util.Scanner;

public class Judge {

    private static float receivedPokemon1HP;
    private static float receivedPokemon2HP;
    private static Judge instance = new Judge();
    private static Pokemon pokemon1;
    private static Pokemon pokemon2;
    private Judge() {
        pokemon1 = null;
        pokemon2 = null;
    }

    public static float getReceivedPokemon1HP() {
        return receivedPokemon1HP;
    }

    public static float getReceivedPokemon2HP() {
        return receivedPokemon2HP;
    }

    public static Judge getInstance() {
        return instance;
    }

    public static void setPokemons(Pokemon p1, Pokemon p2) {
        pokemon1 = p1;
        pokemon2 = p2;
    }

    public static void hello() {
        System.out.println("Now your pokemon in the arena and ready for battle.");
        System.out.println("Pokemon Judge ready to start.");
    }

    private static void printHP(Pokemon p) {
        System.out.println(p.getName() + " have:");
        System.out.println(p.getHealth().getHeadHP() + " head hp " + p.getHealth().getBodyHP() + " body HP");
    }

    public static String fight(Scanner in) {
        String winner;
        int step = 0;
        while (pokemon1.getHealth().isAlive() || pokemon2.getHealth().isAlive()) {
            System.out.println("You can choose some activities: ");
            System.out.println("1) Hit up\n2) Hit down\n3) Hit forward\n4) Hit special\n5) Hit fatality (only after 3 steps)");
            float damage = pokemon1.fight(in.nextInt(), step);
            pokemon2.setBlock(pokemon2.getHealth().check(pokemon2.getTactics()));
            receivedPokemon2HP += damage;
            Hit hitToFirst;
            if (pokemon2.rndFloat() > 0.5f)
                hitToFirst = pokemon2.choice(new Hit(new Health(damage, 0f), pokemon2.isBlock()), step, in);
            else hitToFirst = pokemon2.choice(new Hit(new Health(0f, damage), pokemon2.isBlock()), step, in);
            if (!pokemon1.keepHitting(hitToFirst))
                break;
            damage = hitToFirst.getDamage().getHeadHP() + hitToFirst.getDamage().getBodyHP();
            receivedPokemon1HP += damage;
            step++;
            System.out.println();
            printHP(pokemon1);
            printHP(pokemon2);
            System.out.println();
        }
        winner = pokemon1.getHealth().isAlive() ? pokemon1.getName() : pokemon2.getName();
        return winner;
    }
}