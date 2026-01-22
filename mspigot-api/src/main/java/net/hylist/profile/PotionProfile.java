package net.hylist.profile;

public interface PotionProfile extends Profile {
    float getPotionFall();

    void setPotionFall(float potionFall);

    float getPotionMultiplier();

    void setPotionMultiplier(float potionMultiplier);

    float getPotionOffset();

    void setPotionOffset(float potionOffset);

    double getPotionExtraHorizontal();

    void setPotionExtraHorizontal(double potionExtraHorizontal);

    double getPotionExtraVertical();

    void setPotionExtraVertical(double potionExtraVertical);

    boolean isFollowSpeedLevel();

    void setFollowSpeedLevel(boolean followSpeedLevel);
}
