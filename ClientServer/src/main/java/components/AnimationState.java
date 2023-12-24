package components;

import java.util.ArrayList;
import java.util.List;

public class AnimationState {

    public String title;
    public List<Frame> animationFrames = new ArrayList<>();

    private static Sprite defaultSprite = new Sprite(null);
    private transient float timeTracker = 0.0f;
    private transient int currentSpriteIndex = 0;
    private boolean doesLoop = false;

    public void addFrame(Sprite sprite, float frameTime) {
        animationFrames.add(new Frame(sprite, frameTime));
    }

    public void setLoop(boolean doesLoop) {
        this.doesLoop = doesLoop;
    }

    public void update(float dt) {
        if (currentSpriteIndex < animationFrames.size()) {
            timeTracker -= dt;
            if (timeTracker <=0) {
                if (!(currentSpriteIndex == animationFrames.size() -1) || doesLoop) {
                    currentSpriteIndex = (currentSpriteIndex +1) % animationFrames.size();
                }
                timeTracker = animationFrames.get(currentSpriteIndex).frameTime;
            }
        }
    }

    public Sprite getCurrentSprite() {
        if (currentSpriteIndex < animationFrames.size()) {
            return animationFrames.get(currentSpriteIndex).sprite;
        }

        return defaultSprite;
    }
}
