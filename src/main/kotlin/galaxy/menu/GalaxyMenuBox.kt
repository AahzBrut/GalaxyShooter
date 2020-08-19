package galaxy.menu

import javafx.scene.layout.VBox

class GalaxyMenuBox : VBox(){

	fun addAll(vararg items: MainMenuButton) {
		children.addAll(items)
	}
}