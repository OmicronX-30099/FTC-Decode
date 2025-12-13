package org.firstinspires.ftc.teamcode.IncompleteStagedBindingsLib

import java.util.function.Supplier

class Button(val valueSupplier: Supplier<Boolean>): Supplier<Boolean> {
    private var currentStatus: Boolean = false
    private var previousStatus: Boolean = false

    init {
        BindingManager.add(this)
    }

    override fun get(): Boolean {
        return this.currentStatus
    }

    private val whenPressedTasks: StagedTasks =  StagedTasks()
    private val whenReleasedTasks: StagedTasks =  StagedTasks()
    private val whileHeldTasks: StagedTasks =  StagedTasks()
    private val whileNotHeldTasks: StagedTasks =  StagedTasks()

    infix fun whenPressed(task: Pair<Runnable, Binder>) {
        whenPressedTasks.add(Stage.Default, task.first)
    }
    @JvmName("whenPressedStaged")
    infix fun whenPressed(stagedTask: Pair<Stage, Pair<Runnable, Binder>>) {
        whenPressedTasks.add(stagedTask.first, stagedTask.second.first)
    }
    infix fun whenReleased(task: Pair<Runnable, Binder>) {
        whenReleasedTasks.add(Stage.Default, task.first)
    }
    @JvmName("whenReleasedStaged")
    infix fun whenReleased(stagedTask: Pair<Stage, Pair<Runnable, Binder>>) {
        whenReleasedTasks.add(stagedTask.first, stagedTask.second.first)
    }
    infix fun whileHeld(task: Pair<Runnable, Binder>) {
        whileHeldTasks.add(Stage.Default, task.first)
    }
    @JvmName("whileHeldStaged")
    infix fun whileHeld(stagedTask: Pair<Stage, Pair<Runnable, Binder>>) {
        whileHeldTasks.add(stagedTask.first, stagedTask.second.first)
    }
    infix fun whileNotHeld(task: Pair<Runnable, Binder>) {
        whileNotHeldTasks.add(Stage.Default, task.first)
    }
    @JvmName("whileNotHeldStaged")
    infix fun whileNotHeld(stagedTask: Pair<Stage, Pair<Runnable, Binder>>) {
        whileNotHeldTasks.add(stagedTask.first, stagedTask.second.first)
    }



    fun toggleOnPressed(): Button {
        var toggleState = false
        this whenPressed (Binder run {toggleState = !toggleState})
        return Button { toggleState }
    }

    fun toggleOnReleased(): Button {
        var toggleState = false
        this whenReleased  (Binder run {toggleState = !toggleState})
        return Button { toggleState }
    }

    infix fun and(valueSupplier: Supplier<Boolean>) = Button { this.get() and valueSupplier.get() }
    infix fun or(valueSupplier: Supplier<Boolean>) = Button { this.get() or valueSupplier.get() }
    infix fun xor(valueSupplier: Supplier<Boolean>) = Button { this.get() xor valueSupplier.get() }

    operator fun not() = Button { !get() }
    operator fun plus(otherButton: Button) = Button { this.get() and otherButton.get() }

    fun update(currentStage: String) {
        currentStatus = valueSupplier.get()

        if (currentStatus && !previousStatus) { whenPressedTasks.run(currentStage) }
        if (!currentStatus && previousStatus) { whenReleasedTasks.run(currentStage) }
        if (currentStatus) { whileHeldTasks.run(currentStage) }
        if (!currentStatus) { whileNotHeldTasks.run(currentStage) }

        previousStatus = currentStatus
    }

    internal fun clear() {
        whenPressedTasks.reset()
        whenReleasedTasks.reset()
        whileHeldTasks.reset()
        whileNotHeldTasks.reset()
    }

    private class StagedTasks() {
        private val tasks = mutableListOf<Pair<Stage, Runnable>>()

        fun run(currentStage: String?) = tasks.forEach {
            val stage = it.first
            when (stage) {
                Stage.Global -> it.second.run()
                else -> if (currentStage == it.first.name) it.second.run()
            }
        }

        fun add(stage: Stage, task: Runnable) {
            tasks.add(Pair(stage, task))
        }

        fun reset() {
            tasks.clear()
        }
    }
}