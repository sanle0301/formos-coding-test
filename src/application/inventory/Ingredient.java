package application.inventory;

import java.util.Objects;

import application.constant.Unit;

public class Ingredient {
    private String name;
    private double remainingMass;
    private double requireMass; // Require mass for 100ml smoothie. ex: 100g fruit * (50ml blended fruit / 100ml smoothie)
    private Unit unit;

    public Ingredient() {
    }

    public Ingredient(String name, double remainingMass, double requireMass, Unit unit) {
        this.name = name;
        this.remainingMass = remainingMass;
        this.requireMass = requireMass;
        this.unit = unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ingredient other = (Ingredient) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return String.format("%s, Remaining: %.1f %s", this.name, this.remainingMass, this.unit.getSymbol());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRemainingMass() {
        return remainingMass;
    }

    public void setRemainingMass(double remainingMass) {
        this.remainingMass = remainingMass;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public double getRequireMass() {
        return requireMass;
    }

    public void setRequireMass(double requireMass) {
        this.requireMass = requireMass;
    }

}
