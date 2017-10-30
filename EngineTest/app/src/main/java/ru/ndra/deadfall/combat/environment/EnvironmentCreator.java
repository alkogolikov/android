package ru.ndra.deadfall.combat.environment;

public class EnvironmentCreator {

    public EnvironmentCreator(ParallaxScene background, ParallaxScene foreground) {

        background.addParallax("map/foothills/plane4.png", 200 * 3, 500, .009f / .08f, false);
        background.addParallax("map/foothills/plane3.png", 108 * 3, 400, .015f / .08f, false);
        background.addParallax("map/foothills/plane2.png", 69 * 3, 370, .03f / .08f, false);
        background.addParallax("map/foothills/plane1.png", 145 * 3, 0, .05f / .08f, false);
        background.addParallax("map/foothills/plane5.png", 74 * 3, 0, 1, false);

        foreground.addParallax("map/foothills/plane6.png", 56 * 3, -50, .15f / .08f, true);
    }

}
