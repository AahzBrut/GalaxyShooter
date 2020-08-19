package galaxy.main_menu

import com.almasb.fxgl.dsl.getUIFactoryService
import javafx.beans.binding.Bindings
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseButton
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Text

class MainMenuButton(name: String, action: EventHandler<ActionEvent>) : StackPane(){

	private var text: Text = getUIFactoryService().newText(name, Color.WHITE, 16.0)

	init {
		text.fillProperty().bind(Bindings.`when`(focusedProperty().isNotEqualTo(hoverProperty())).then(Color.WHITE).otherwise(Color.GREY))

		onKeyPressed = EventHandler { act -> if (act.code === KeyCode.ENTER || act.code === KeyCode.SPACE) action.handle(ActionEvent())  }

		onMousePressed = EventHandler { act -> if (act.button === MouseButton.PRIMARY) action.handle(ActionEvent()) }

		children.addAll(getSelector(), text)
	}

	private fun getSelector(): Rectangle {
		val selector = Rectangle(5.0, 15.0, Color.WHITE)
		selector.translateX = -50.0
		selector.visibleProperty().bind(focusedProperty().isNotEqualTo(hoverProperty()))
		isFocusTraversable = true
		return selector
	}
}
