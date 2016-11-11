package Pokemon;

public class Hit {

    private Health damage;
    private boolean block;

    public Health getDamage() {
        return damage;
    }

    public Hit(Health damage, boolean block) {
        this.damage = damage;
        this.block = block;
    }
}