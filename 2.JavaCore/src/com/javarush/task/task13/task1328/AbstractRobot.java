package com.javarush.task.task13.task1328;

public abstract class AbstractRobot implements Attackable, Defensable {
    private static int hitCount;
    String name;

    abstract String getName();

    public BodyPart attack() {
        BodyPart attackedBodyPart = null;
        hitCount = hitCount + (int) (Math.random() * 3);

        if (hitCount == 0) {
            attackedBodyPart = BodyPart.ARM;
        } else if (hitCount == 1) {
            attackedBodyPart = BodyPart.HEAD;
        } else if (hitCount == 2) {
            attackedBodyPart = BodyPart.CHEST;
        } else {
            hitCount = 0;
            attackedBodyPart = BodyPart.LEG;
        }
        return attackedBodyPart;
    }

    public BodyPart defense() {
        BodyPart defencedBodyPart = null;
        hitCount = hitCount + (int) (Math.random() * 3);

        if (hitCount == 0) {
            defencedBodyPart = BodyPart.HEAD;
        } else if (hitCount == 1) {
            defencedBodyPart = BodyPart.LEG;
        } else if (hitCount == 2) {
            defencedBodyPart = BodyPart.CHEST;
        } else {
            hitCount = 0;
            defencedBodyPart = BodyPart.ARM;
        }
        return defencedBodyPart;
    }
}
