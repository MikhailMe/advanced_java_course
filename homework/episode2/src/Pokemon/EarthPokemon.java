package Pokemon;

public class EarthPokemon extends Pokemon implements Pokemonable {

    private static float EarthCoeff = 10f;

    public EarthPokemon(String name, float power, float speed, float endurance, float tactics, float accuracy, float aggression) {
        super(name, power, speed, endurance, tactics, accuracy, aggression);
    }

    @Override
    public void sleep() {
        setPower(getPower() + 0.3f);
        getHealth().recovery(getEndurance());
        System.out.println(getName() + " sleep now.");
    }

    @Override
    public void eat() {
        setPower(getPower() + 0.1f);
        System.out.println(getName() + " eat now.");
    }

    @Override
    public void play() {
        System.out.println(getName() + " play now.");
    }

    @Override
    public void rest() {
        switch (chooseVariant()) {
            case 0:
                sleep();
                break;
            case 1:
                eat();
                break;
            default:
                play();
                break;
        }
    }

    @Override
    public float special() {
        System.out.println(getName() + " done special hit to enemy");
        return EarthCoeff * getAggression() * getPower();
    }

    @Override
    public float fatality(int amount) {
        if (amount > 3) {
            System.out.println(getName() + " done fatality!!!");
            return getPower() * getSpeed() * getAggression() * getEndurance() * 5 * EarthCoeff;
        }
        return 0f;
    }
}