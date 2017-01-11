package org.jc.util;

public abstract interface Temporizador {

    public abstract void timerArrancado(Timer paramTimer);

    public abstract void timerParado(Timer paramTimer);

    public abstract void timerMuerto(Timer paramTimer);

    public abstract void timerIntervalo(Timer paramTimer);
}