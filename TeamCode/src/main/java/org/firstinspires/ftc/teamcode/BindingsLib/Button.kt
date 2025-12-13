package org.firstinspires.ftc.teamcode.BindingsLib

import java.util.function.Supplier

class Button(val stateSupplier: Supplier<Boolean>): Supplier<Boolean> {
    init {
        Binder.addButton(this)
    }

    private var currentState: Boolean = false
    private var previousState: Boolean = false

    override fun get(): Boolean {
        return this.currentState
    }

    private val whenPressedTaskSheet: TaskSheet = TaskSheet()
    private val whenReleasedTaskSheet: TaskSheet = TaskSheet()
    private val whileHeldTaskSheet: TaskSheet = TaskSheet()
    private val whileNotHeldTaskSheet: TaskSheet = TaskSheet()

    infix fun whenPressed(task: Pair<Runnable, Binder>) {
        whenPressedTaskSheet.addTask(task.first)
    }
    infix fun whenReleased(task: Pair<Runnable, Binder>) {
        whenReleasedTaskSheet.addTask(task.first)
    }
    infix fun whileHeld(task: Pair<Runnable, Binder>) {
        whileHeldTaskSheet.addTask(task.first)
    }
    infix fun whileNotHeld(task: Pair<Runnable, Binder>) {
        whileNotHeldTaskSheet.addTask(task.first)
    }

    fun toggleOnPressed(): Button {
        var toggle: Boolean = false
        this whenPressed (Binder run {toggle = !toggle})
        return Button {toggle}
    }
    fun toggleOnReleased(): Button {
        var toggle: Boolean = false
        this whenReleased  (Binder run {toggle = !toggle})
        return Button {toggle}
    }
    infix fun onFirstPress(task: Pair<Runnable, Binder>) {
        whenPressedTaskSheet.addTask(task.first)
    }
    infix fun onSecondPress(task: Pair<Runnable, Binder>) {
        whenReleasedTaskSheet.addTask(task.first)
    }

    infix fun and(otherStateSupplier: Supplier<Boolean>) = Button { this.get() and otherStateSupplier.get() }
    infix fun or(otherStateSupplier: Supplier<Boolean>) = Button { this.get() or otherStateSupplier.get() }
    infix fun xor(otherStateSupplier: Supplier<Boolean>) = Button { this.get() xor otherStateSupplier.get() }

    operator fun not() = Button { !get() }
    operator fun plus(otherButton: Button) = Button { this.get() and otherButton.get() }

    fun update() {
        currentState = stateSupplier.get()

        if (currentState && !previousState) { whenPressedTaskSheet.executeTasks() }
        if (!currentState && previousState) { whenReleasedTaskSheet.executeTasks() }
        if (currentState) { whileHeldTaskSheet.executeTasks() }
        if (!currentState) { whileNotHeldTaskSheet.executeTasks() }

        previousState = currentState
    }

    internal fun reset() {
        whenPressedTaskSheet.resetTasks()
        whenReleasedTaskSheet.resetTasks()
        whileHeldTaskSheet.resetTasks()
        whileNotHeldTaskSheet.resetTasks()
    }

    private class TaskSheet() {
        private val taskSheet = mutableListOf<Runnable>()

        fun executeTasks() = taskSheet.forEach {
            it.run()
        }

        fun addTask(task: Runnable) {
            taskSheet.add(task)
        }

        fun resetTasks() {
            taskSheet.clear()
        }
    }
}