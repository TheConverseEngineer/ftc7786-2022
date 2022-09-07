package org.firstinspires.ftc.teamcode.controllers;

public class PController {
    double maxSpeed, zeta, target;

    public PController(double maxSpeed, double zeta, double target) {
        this.maxSpeed = maxSpeed;
        this.zeta = zeta;
        this.target = target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public void setConstants(double maxSpeed, double zeta) {
        this.maxSpeed = maxSpeed;
        this.zeta = zeta;
    }

    public double get(double current) {
        return (maxSpeed*2)/(1 + Math.exp(-1*zeta*(target-current))) - maxSpeed;
    }
}
