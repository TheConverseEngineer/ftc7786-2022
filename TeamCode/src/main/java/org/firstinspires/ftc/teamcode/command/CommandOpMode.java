package org.firstinspires.ftc.teamcode.command;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.List;

/** OpMode template to help simplify the creation of Command-Based code
 * TODO: add virtual hardware support
 */
public abstract class CommandOpMode extends LinearOpMode {

    /** Represents the prior state of gamepad 1 (useful for debouncing) */
    protected Gamepad _gamepad1 = new Gamepad();

    /** Represents the prior state of gamepad 2 (useful for debouncing) */
    protected Gamepad _gamepad2 = new Gamepad();

    @Override
    public final void runOpMode() {
        // Setup Command stuff
        CommandScheduler master = CommandScheduler.getInstance();
        master.reset();
        init(master);

        // Setup bulk reads (manual mode -> Beware stale values!)
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        waitForStart();
        try {
            gamepad1.copy(_gamepad1);
            gamepad2.copy(_gamepad2);
        } catch (RobotCoreException e) {
            // Swallow exception -> it should never happen
        }

        while (opModeIsActive() && !isStopRequested()) {
            for (LynxModule hub : allHubs) hub.clearBulkCache();
            loop(master);
            master.run();
            telemetry.update();

            try {
                gamepad1.copy(_gamepad1);
                gamepad2.copy(_gamepad2);
            } catch (RobotCoreException e) {
                // Swallow exception -> it should never happen
            }
        }
        end();
        master.reset();
    }
    /** Initialize commands, subsystems, and triggers here!
     * NOTE: DO NOT FORGET to register subsystems/triggers and queue start-up commands
     * @param master    The CommandScheduler instance
     */
    public abstract void init(CommandScheduler master);

    /** Can be used to add additional non-Command behavior to opMode loops.
     *  Mainly intended for drive code and such.
     *  AVOID BLOCKING LOOPS LIKE THE PLAGUE! */
    public void loop(CommandScheduler master) {

    }

    /** Can be used to add on-OpMode-end behaviors.
     * AVOID BLOCKING LOOPS LIKE THE PLAGUE! */
    public void end() {

    }
}
