package Pokemon;

public class Health {

    private float headHP;
    private float bodyHP;

    public void setHeadHP(float headHP) {
        this.headHP = headHP;
    }

    public void setBodyHP(float bodyHP) {
        this.bodyHP = bodyHP;
    }

    public float getHeadHP() {

        return headHP;
    }

    public float getBodyHP() {
        return bodyHP;
    }

    Health(float headHP, float bodyHP) {
        this.headHP = headHP;
        this.bodyHP = bodyHP;
    }

    public boolean check(float tactics){
        float val = tactics * 10;
        return headHP - val < -30 && bodyHP - val < - 30;
    }

    public void recovery(float endurance){
        float delta = endurance;
        this.headHP += delta;
        this.bodyHP += delta;
        if (this.headHP > 100)
            this.headHP = 100;
        if (this.bodyHP > 100)
            this.bodyHP = 100;
    }

    public boolean isAlive(){
        return (headHP > 0 && bodyHP > 0);
    }
}
