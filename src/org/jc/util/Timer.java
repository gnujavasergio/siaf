package org.jc.util;

import java.util.Date;

public class Timer implements Runnable {

    private Date inicio, parada;
    private Thread thread = null;
    private int duracion = 0;
    private int duracionAnt = 0;
    private int intervalo = 1000;
    private boolean repeticion = false;
    private boolean enEjecucion = false;
    private Temporizador handler = null;

    Timer() {
    }

    Timer(int tiempo) {
        setDuracion(tiempo);
    }

    Timer(Temporizador Handler) {
        setHandler(Handler);
    }

    Timer(int tiempo, Temporizador Handler) {
        setDuracion(tiempo);
        setHandler(Handler);
    }

    public void setDuracion(int tiempo) {
        duracion = tiempo;
    }

    public void setHandler(Temporizador Handler) {
        handler = Handler;
    }

    public void setIntervalo(int Intervalo) {
        intervalo = Intervalo;
    }

    public int getDuration() {
        return (duracion);
    }

    public Temporizador getHandler() {
        return (handler);
    }

    public int getIntervalo() {
        return (intervalo);
    }

    public int getElapsed() {
        return (calculaLapso(new Date()));
    }

    public void resetDuracion() {
        duracion = duracionAnt;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        enEjecucion = false;
        parada = new Date();
        if (handler != null) {
            handler.timerParado(this);
        }
    }

    public void run() {
        enEjecucion = true;
        duracionAnt = duracion;

        inicio = new Date();
        if (handler != null) {
            handler.timerArrancado(this);
        }

        while (enEjecucion) {
            try {
                esperar(intervalo);
            } catch (InterruptedException e) {
                return;
            }

            if (handler != null) {
                handler.timerIntervalo(this);
            }

            if (duracion > 0) {
                if (estaMuerto()) {
                    if (handler != null) {
                        handler.timerMuerto(this);
                    }

                    if (repeticion) {
                        enEjecucion = true;
                        inicio = new Date();
                        if (handler != null) {
                            handler.timerArrancado(this);
                        }
                    } else {
                        enEjecucion = false;
                    }
                }
            }
        }
    }

    // Metodos que nos informan del estado del Timer
    public boolean estaCorriendo() {
        return (enEjecucion);
    }

    public boolean estaParado() {
        return (!enEjecucion);
    }

    public boolean estaMuerto() {
        int segundos = 0;

        segundos = calculaLapso(new Date());

        if (segundos >= duracion) {
            return (true);
        } else {
            return (false);
        }
    }

    private int calculaLapso(Date actual) {
        Date dfinal;
        int segundos = 0;

        if (enEjecucion) {
            dfinal = actual;
        } else {
            dfinal = parada;
        }

        segundos += (dfinal.getHours() - inicio.getHours()) * 3600;
        segundos += (dfinal.getMinutes() - inicio.getMinutes()) * 60;
        segundos += (dfinal.getSeconds() - inicio.getSeconds());
        return (segundos);
    }

    private synchronized void esperar(int lapso)
            throws InterruptedException {
        this.wait(lapso);
    }
}
