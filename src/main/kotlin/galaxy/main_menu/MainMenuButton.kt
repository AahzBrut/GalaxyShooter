package galaxy.main_menu

import com.almasb.fxgl.dsl.getUIFactoryService
import javafx.beans.binding.Bindings
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Text

class MainMenuButton(name: String, action: EventHandler<ActionEvent>) : StackPane(){

	init {
		setOnKeyPressed { act ->
			if (act.code === KeyCode.ENTER) {
				action.handle(ActionEvent())
			} }

		children.addAll(getSelector(), getTextButton(name))
	}

	private fun getSelector(): Rectangle {
		val selector = Rectangle(5.0, 15.0, Color.WHITE)
		selector.translateX = -50.0
		selector.visibleProperty()
				.bind(focusedProperty())
		isFocusTraversable = true
		return selector
	}

	private fun getTextButton(name: String): Text {
		val text: Text = getUIFactoryService().newText(name, Color.WHITE, 16.0)
		text.fillProperty()
				.bind(Bindings.`when`(focusedProperty()).then(Color.WHITE).otherwise(Color.GREY))
		return text
	}
}
