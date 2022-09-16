package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Drive Test", group = "default")
public class DriveTest extends OpMode {
    DcMotor front, right, left, back;

    @Override
    public void init() {
        front = hardwareMap.get(DcMotor.class, "front");
        right = hardwareMap.get(DcMotor.class, "right");
        left = hardwareMap.get(DcMotor.class, "left");
        back = hardwareMap.get(DcMotor.class, "back");
    }

    @Override
    public void loop() {
        double x = gamepad1.right_stick_x;
        double y = gamepad1.right_stick_y;
        double r = gamepad1.left_stick_x;

        if (gamepad1.right_bumper) {
            x *= .25;
            y *= .25;
            r *= .25;
        }

        front.setPower(x + r);
        right.setPower(-y + r);
        left.setPower(y + r);
        back.setPower(-x + r);

        telemetry.addData("x", x);
        telemetry.addData("y", y);
        telemetry.addData("r", r);
    }
}
