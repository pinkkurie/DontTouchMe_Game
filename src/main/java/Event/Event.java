package Event;

import Character.*;

public class Event {
    public static boolean checkHit(Cat cat, Wave wave, int catSize, int waveHeight){
        if(cat.x + catSize > wave.x && cat.x < wave.x){
            if(cat.y + catSize >= wave.y - waveHeight){
                return true;
            }
        }
        return false;
    }
}
