package it.achdjian.plugin.ros.ui

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ui.ComboBox
import it.achdjian.plugin.ros.data.RosEnvironments
import java.awt.Component
import javax.swing.*
import javax.swing.border.EmptyBorder

class VersionSelector : ComboBox<Any>() {
    companion object {
        const val TABLE_CELL_EDITOR="JComboBox.isTableCellEditor"
        const val SHOW_ALL = "ShowAll"
    }

    init {
        updateList()
        renderer = VersionSelectorRenderer()
    }

    fun updateList() {
        val state = ApplicationManager.getApplication().getComponent(RosEnvironments::class.java, RosEnvironments())
        removeAll()
        state.versions.forEach {
            addItem(it.name)
        }
    }

}

class VersionSelectorRenderer :  JLabel() , ListCellRenderer<Any?> {

    companion object {
        const val SEPARATOR_STRING="SEPARATOR"
    }
    private var separator = JSeparator(SwingConstants.HORIZONTAL)

    init {
        isOpaque=true
        border = EmptyBorder(1,1,1,1)
    }


    override fun getListCellRendererComponent(list: JList<out Any?>?, value: Any?, index: Int, isSelected: Boolean, cellHasFocus: Boolean): Component {
        val str= value?.toString()?:""
        if (SEPARATOR_STRING == str) {
            return separator
        }
        if (isSelected) {
            background = list?.selectionForeground
            foreground = list?.selectionForeground
        } else {
            background = list?.background
            foreground = list?.foreground
        }
        font = list?.font
        text=str
        return this
    }
}