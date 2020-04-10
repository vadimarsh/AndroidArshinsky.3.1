package com.example.androidarshinsky31;

import androidx.annotation.NonNull;

import java.util.Objects;

public class PressMeasure {
    private String dateTime;
    private boolean tahicard;
    private int presUp;
    private int presDown;
    private int pulse;

    public PressMeasure(String date, String time, boolean tahicard, int presUp, int presDown, int pulse) {
        if (date.isEmpty() || time.isEmpty()) {
            throw new RuntimeException();
        }
        this.dateTime = date + "t" + time;
        this.tahicard = tahicard;
        this.presUp = presUp;
        this.presDown = presDown;
        this.pulse = pulse;
    }

    public String getDateTime() {
        return dateTime;
    }

    public boolean isTahicard() {
        return tahicard;
    }

    public int getPresUp() {
        return presUp;
    }

    public int getPresDown() {
        return presDown;
    }

    public int getPulse() {
        return pulse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PressMeasure)) return false;
        PressMeasure other = (PressMeasure) o;
        return this.dateTime.equals(other.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.dateTime);
    }

    @NonNull
    @Override
    public String toString() {
        return this.getDateTime() + " -> " + this.presUp + " на " + this.presDown;
    }
}
