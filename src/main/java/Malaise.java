import parameters.Parameters;
import processing.core.PApplet;
import processing.core.PVector;

import static parameters.Parameters.*;
import static save.SaveUtil.saveSketch;

public class Malaise extends PApplet {
    public static void main(String[] args) {
        PApplet.main(Malaise.class);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
        noiseSeed(floor(random(MAX_INT)));
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.blue());
        blendMode(BLEND_MODE);
        noFill();
        noLoop();
    }

    @Override
    public void draw() {
        for (float x = 0; x <= width; x += STEP) {
            stroke(
                    map(x, 0, width, RED_LOW, RED_HIGH),
                    map(x, 0, width, GREEN_LOW, GREEN_HIGH),
                    map(x, 0, width, BLUE_LOW, BLUE_HIGH),
                    Parameters.ALPHA);
            for (float y = 0; y <= height; y += STEP) {
                PVector position = new PVector(x, y);
                PVector force = PVector.sub(position, ORIGIN).mult(FORCE_AMOUNT);
                for (int l = 0; l < MAX_LENGTH; l++) {
                    if (position.x >= MARGIN
                            && position.x <= width - MARGIN
                            && position.y >= MARGIN
                            && position.y <= height - MARGIN)
                        point(position.x, position.y);
                    position.add(force.rotate(4 * PI * noise(position.x * NOISE_SCALE, position.y * NOISE_SCALE)));
                }
            }
        }
        saveSketch(this);
    }
}
