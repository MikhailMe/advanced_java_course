package Pokemon;

import java.util.Random;
import java.util.Scanner;

public abstract class Pokemon implements Pokemonable {

    private String name;
    private Health health;
    private float power;
    private float speed;
    private float endurance;
    private float tactics;
    private float accuracy;
    private float aggression;
    private boolean block;

    Pokemon(String name, float power, float speed, float endurance, float tactics, float accuracy, float aggression) {
        this.name = name;
        this.health = new Health(100f, 100f);
        this.power = power / 100;
        this.speed = speed / 100;
        this.endurance = endurance / 100;
        this.tactics = tactics / 100;
        this.accuracy = accuracy / 100;
        this.aggression = aggression / 100;
        this.block = false;
    }

    public abstract void sleep();

    public abstract void eat();

    public abstract void play();

    public abstract float special();

    public abstract float fatality(int step);

    public String getName() {
        return name;
    }

    public Health getHealth() {
        return health;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {

        this.speed = speed;
    }

    public float getEndurance() {

        return endurance;
    }

    public float getAggression() {
        return aggression;
    }

    public void setAggression(float aggression) {

        this.aggression = aggression;
    }

    private void haveDamage(Health h) {
        health.setHeadHP(health.getHeadHP() - h.getHeadHP());
        health.setBodyHP(health.getBodyHP() - h.getBodyHP());
    }

    public float getTactics() {
        return tactics;
    }

    protected boolean keepHitting(Hit hit) {
        if (hit == null)
            return false;
        if (getHealth().getBodyHP() < 100 || getHealth().getHeadHP() < 100)
            health.recovery(endurance);
        haveDamage(hit.getDamage());
        if (!getHealth().isAlive())
            return false;
        return true;
    }

    protected Hit choice(Hit hit, int step, Scanner in) {
        keepHitting(hit);
        if (!health.isAlive())
            return null;
        float sentDamage = this.fight(rndInt(), step);
        if (rndFloat() > 0.5f)
            return new Hit(new Health(sentDamage, 0f), block);
        else return new Hit(new Health(0f, sentDamage), block);
    }

    protected int chooseVariant() {
        float temp = rndFloat();
        if (temp < 0.33f)
            return 0;
        else if (temp > 0.33f && temp < 0.67f)
            return 1;
        else return 2;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;

    }

    private int rndInt(){
        return new Random().nextInt(5) + 1;
    }

    protected float rndFloat() {
        return new Random().nextFloat();
    }

    protected float hitUp() {
        System.out.println(getName() + " hit up enemy");
        return (10 * power * speed) + (aggression * tactics * aggression);
    }

    protected float hitDown() {
        System.out.println(getName() + " hit down enemy");
        return (10 * power * speed) + (aggression * accuracy) - tactics;
    }

    protected float hitForward() {
        System.out.println(getName() + " hit forward enemy");
        return (10 * power * speed) + (accuracy * tactics);
    }

    public void train() {
        switch (chooseVariant()) {
            case 0: {
                power += 0.3f;
                aggression += 0.3f;
                haveDamage(new Health(0.1f, 0.1f));
                break;
            }
            case 1: {
                tactics += 0.2f;
                accuracy += 0.2f;
                speed += 0.2f;
                break;
            }
            default: {
                power += 0.3f;
                aggression += 0.3f;
                tactics += 0.2f;
                accuracy += 0.2f;
                speed += 0.2f;
                haveDamage(new Health(0.2f, 0.2f));
                break;
            }
        }
        System.out.println(name + " trains now.");
    }

    @Override
    public float fight(int index, int step) {
        switch (index) {
            case 1:
                return hitUp();
            case 2:
                return hitDown();
            case 3:
                return hitForward();
            case 4:
                return special();
            case 5:
                return fatality(step);
            default: {
                System.out.println("you can't do other hit");
                return 0f;
            }
        }
    }
}