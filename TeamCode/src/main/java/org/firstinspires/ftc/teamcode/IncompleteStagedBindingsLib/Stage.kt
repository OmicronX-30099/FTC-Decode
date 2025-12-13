package org.firstinspires.ftc.teamcode.IncompleteStagedBindingsLib

import kotlin.properties.Delegates

class Stage(layerName: String? = null) {
    var name: String by Delegates.notNull()
    init {
        this.name = layerName ?: "Default"
    }

    companion object {
        public val Default = Stage()
        public val Global = Stage("Global")
    }
}