package ru.ndra.deadfall.combat.environment;

public class EnvironmentCreator {

    public EnvironmentCreator(ParallaxScene background, ParallaxScene foreground) {

       /* background.addParallax("map/foothills/plane4.png", 200 * 3, 500, .1125f, 0, false);
        background.addParallax("map/foothills/plane3.png", 108 * 3, 400, .1875f, 0, false);
        background.addParallax("map/foothills/plane2.png", 69 * 3, 370, .375f, 0, false);
        background.addParallax("map/foothills/plane1.png", 145 * 3, 0, .625f, 0, false);
        background.addParallax("map/foothills/plane5.png", 74 * 3, 0, 1, 0, false);

        foreground.addParallax("map/foothills/plane6.png", 56 * 3, -50, 1.875f, 0, true);

        // Туман
        foreground.addParallax("map/weather/myst1.png", 56 * 3, 150, 1.1f, 10, true);
        foreground.addParallax("map/weather/myst1.png", 56 * 7, 150, 1.7f, -3, true); */


        background.addParallax("map/forest/plane1.png", 1000, 0, .1125f, 0, false);
        background.addParallax("map/forest/plane3.png", 1000, 0, .2f, 0, false);
        background.addParallax("map/forest/plane2.png", 1000, 0, .4f, 0, false);
       // background.addParallax("map/foothills/plane1.png", 145 * 3, 0, .625f, 0, false);
        background.addParallax("map/foothills/plane5.png", 74 * 3, 0, 1, 0, false);
        foreground.addParallax("map/foothills/plane6.png", 56 * 3, -50, 1.875f, 0, true);


      //  foreground.addParallax("map/weather/myst1.png", 56 * 3, 150, 1.1f, 10, true);
      //  foreground.addParallax("map/weather/myst1.png", 56 * 7, 150, 1.7f, -3, true);

    }

}
