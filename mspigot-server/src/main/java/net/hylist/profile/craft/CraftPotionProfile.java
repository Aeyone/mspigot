package net.hylist.profile.craft;

import net.hylist.profile.PotionProfile;

public class CraftPotionProfile implements PotionProfile {

    private String name;
    private float potionFall = 0.05F;
    private float potionMultiplier = 0.5F;
    private float potionOffset = -20.0F;
    private boolean followSpeedLevel = true;
    private double potionExtraHorizontal = 1.0D;
    private double potionExtraVertical = 1.0D;
    private float potionSize = 0.3F;
    private int splashTick = 5;
    private float splashDistance = 0.16F;



    public CraftPotionProfile(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public float getPotionFall() {
        return potionFall;
    }

    @Override
    public void setPotionFall(float potionFall) {
        this.potionFall = potionFall;
    }

    @Override
    public float getPotionMultiplier() {
        return potionMultiplier;
    }

    @Override
    public void setPotionMultiplier(float potionMultiplier) {
        this.potionMultiplier = potionMultiplier;
    }

    @Override
    public float getPotionOffset() {
        return potionOffset;
    }

    @Override
    public void setPotionOffset(float potionOffset) {
        this.potionOffset = potionOffset;
    }

    @Override
    public double getPotionExtraHorizontal() { return potionExtraHorizontal; }

    @Override
    public void setPotionExtraHorizontal(double potionExtraHorizontal) { this.potionExtraHorizontal = potionExtraHorizontal; }

    @Override
    public double getPotionExtraVertical() { return potionExtraVertical; }

    @Override
    public void setPotionExtraVertical(double potionExtraVertical) { this.potionExtraVertical = potionExtraVertical; }

    @Override
    public boolean isFollowSpeedLevel() { return followSpeedLevel; }

    @Override
    public void setFollowSpeedLevel(boolean followSpeedLevel) { this.followSpeedLevel = followSpeedLevel; }

    @Override
    public float getPotionSize() { return potionSize; }

    @Override
    public void setPotionSize(float potionSize) { this.potionSize = potionSize; }

    @Override
    public int getSplashTick() { return splashTick; }

    @Override
    public void setSplashTick(int splashTick) { this.splashTick = splashTick; }

    @Override
    public float getSplashDistance() {
        return splashDistance;
    }

    @Override
    public void setSplashDistance(float splashDistance) {
        this.splashDistance = splashDistance;
    }

}
