package me.duncanruns.swimgod;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SwimGod implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static boolean isRenderingBubbles = false;
    public static boolean isGettingBreakSpeed = false;
    public static boolean isPlayingTickable = false;
    public static boolean forceSwimUp = false;

    public static final String MOD_ID = "swimgod";
    public static final String MOD_NAME = "Swim God Mod";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}