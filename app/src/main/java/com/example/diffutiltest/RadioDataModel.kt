package com.example.diffutiltest

data class RadioDataModel(
    var itemVal: String = "",
    var selected: Boolean = false,
    var key: Int = 0
): DiffUtilCallbackSelectorInterface {
    override fun keyValue(): Int {
        return key
    }

    override fun contentValue(): Boolean {
        return selected
    }
}