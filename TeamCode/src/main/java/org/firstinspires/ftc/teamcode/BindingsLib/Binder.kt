package org.firstinspires.ftc.teamcode.BindingsLib

object Binder {
    infix fun run(task: Runnable): Pair<Runnable, Binder> {
        return Pair(task, this)
    }

    private val buttons = mutableListOf<Button>()

    @JvmStatic
    fun update() {
        buttons.forEach { it.update() }
    }

    @JvmStatic
    fun addButton(button: Button) {
        buttons.add(button)
    }

    @JvmStatic
    fun reset() {
        buttons.forEach { it.reset() }
    }
}