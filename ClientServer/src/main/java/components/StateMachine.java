package components;

import jade.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class StateMachine extends Component {

    private class StateTrigger {
        public String state;
        public String trigger;

        public StateTrigger() {}
        public StateTrigger(String state, String trigger) {
            this.state = state;
            this.trigger = trigger;
        }

        @Override
        public boolean equals(Object o) {
            if (o.getClass() != StateTrigger.class) return false;
            StateTrigger t2 = (StateTrigger) o;
            return t2.trigger.equals(this.trigger) && t2.state.equals(this.state);
        }

        public int hashCode() {
            return Objects.hash(trigger, state);
        }
    }


    public HashMap<StateTrigger, String> stateTransfers = new HashMap<>();
    private List<AnimationState> states = new ArrayList<>();
    private transient AnimationState currentState = null;
    private  String defaultStateTitle = "";

    public void addStateTrigger(String from, String to, String onTrigger) {
        this.stateTransfers.put(new StateTrigger(from, onTrigger), to);
    }

    public void addState(AnimationState state) {
        this.states.add(state);
    }

//    Transfer to next state depending on trigger
//    Finite state machine
    public void trigger(String trigger) {
        int newStateIndex = -1;
        int index = 0;
        for (AnimationState s : states) {
            if (s.title.equals(trigger)) {
                newStateIndex = index;
            }
            index++;
        }
        if (newStateIndex > -1){
            currentState = states.get(newStateIndex);
        } else {
            System.out.println("Unable to find trigger '" + trigger + "'");
        }
    }



    @Override
    public void start() {
        for (AnimationState state : states) {
            if (state.title.equals(defaultStateTitle)) {
                currentState = state;
            }
        }

    }

    @Override
    public void update(float dt) {
        if (currentState != null) {
            currentState.update(dt);
            SpriteRenderer sprite = parentObject.getComponent(SpriteRenderer.class);
            if (sprite != null) {
                sprite.setSprite(currentState.getCurrentSprite());
            }
        }
    }

    public void setDefaultState(String animationTitle) {
        for (AnimationState state : states) {
            if (state.title.equals(animationTitle)) {
                defaultStateTitle = animationTitle;
                if (currentState == null) {
                    currentState = state;
                    return;
                }
            }
        }
    }


}
