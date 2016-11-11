package Pokemon;

public class FlyingPokemon extends Pokemon implements Pokemonable {

    private static final float FlyCoeff = 12;

    public FlyingPokemon(String name, float power, float speed, float endurance, float tactics, float accuracy, float aggression){
        super(name, power, speed, endurance, tactics, accuracy, aggression);
    }

    @Override
    public void sleep() {
        System.out.println(getName() + " sleep now.");
    }

    @Override
    public void eat() {
        setPower(getPower() + 0.3f);
        setSpeed(getSpeed() + 0.2f);
        System.out.println(getName() + " eat now.");
    }

    @Override
    public void play() {
        setAggression(getAggression() + 0.4f);
        System.out.println(getName() + " play now.");
    }

    @Override
    public void rest() {
        setPower(getPower() + 0.3f);
        setSpeed(getSpeed() + 0.4f);
        System.out.println(getName() + " rest now.");
    }

    @Override
    public float special(){
        System.out.println(getName() + " done special hit to enemy\n");
        return  10 * FlyCoeff * getAggression() * getPower();
    }

    @Override
    public float fatality(int step) {
        if (step > 3) {
            System.out.println(getName() + " done fatality!\n");
            return getPower() * getSpeed() * getAggression() * getEndurance() * 5 * FlyCoeff;
        }
        return 0f;
    }

}
