package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.virtual.VirtualHardwareMapFactory;

public class VirtwareTest extends OpMode {
    DcMotorEx motor;
    @Override
    public void init() {
        hardwareMap = VirtualHardwareMapFactory.createVirtualHardwareMap("org/firstinspires/ftc/teamcode/virtual/SampleHardwareMapping.xml");
        motor = hardwareMap.get(DcMotorEx.class, "leftfrontdrive");
        motor.setPower(0.13);
    }

    @Override
    public void loop() {
        telemetry.addData("pow", motor.getPower());
        telemetry.update();
    }
}