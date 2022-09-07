package org.firstinspires.ftc.teamcode.command.Triggers;

import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.command.CommandScheduler;

import java.util.function.Supplier;

public class StandardTrigger extends Trigger {

    private final Command command;
    private final Supplier<Boolean> condition;

    public StandardTrigger(Supplier<Boolean> condition, Command command) {
        this.command = command;
        this.condition = condition;
    }

    @Override
    public void updateInput() {
        if (condition.get()) {
            CommandScheduler.getInstance().ScheduleCommand(this.command);
        }
    }
}
