package net.hylist.profile;

public interface KnockbackProfile extends Profile {

    @Override
    default String getType(){
        return "Knockback";
    }

    double getFriction();

    void setFriction(double friction);

    double getHorizontal();

    void setHorizontal(double horizontal);

    double getVertical();

    void setVertical(double vertical);

    double getVerticalLimit();

    void setVerticalLimit(double verticalLimit);

    double getExtraHorizontal();

    void setExtraHorizontal(double extraHorizontal);

    double getExtraVertical();

    void setExtraVertical(double extraVertical);

    double getSlowdown();

    void setSlowdown(double slowdown);

}
