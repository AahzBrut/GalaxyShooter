package galaxy.menu

import com.almasb.fxgl.app.scene.FXGLMenu
import com.almasb.fxgl.app.scene.MenuType
import com.almasb.fxgl.dsl.FXGL
import javafx.beans.binding.StringBinding
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class GalaxyAppMainMenu : FXGLMenu(MenuType.MAIN_MENU) {

	init {
		menuRoot.children.addAll(createMenuBody())
		menuRoot.translateX = appWidth - (appWidth - 42.0)
		menuRoot.translateY = appHeight - (appHeight - 50.0)
	}

	private fun createMenuBody(): GalaxyMenuBox {
		val menuBox = GalaxyMenuBox()

		menuBox.addAll(
				MainMenuButton("Play game", EventHandler{fireNewGame()})
				, MainMenuButton("Quit game", EventHandler{fireExit()}))

		return menuBox
	}

	override fun createActionButton(name: StringBinding, action: Runnable): Button {
		return Button()
	}

	override fun createActionButton(name: String, action: Runnable): Button {
		return Button()
	}

	override fun createBackground(width: Double, height: Double): Node {
		return FXGL.Companion.texture("background/MainMenuBackground.png")
	}

	override fun createProfileView(profileName: String): Node {
		return Rectangle()
	}

	override fun createTitleView(title: String): Node {
		val text = FXGL.getUIFactoryService().newText(title, Color.WHITE, 20.0)

		val titleRoot = StackPane()
		titleRoot.children.addAll(HBox(text))

		titleRoot.translateX = appWidth - (appWidth - 20.0)
		titleRoot.translateY = appHeight - (appHeight - 20.0)

		return titleRoot
	}

	override fun createVersionView(version: String): Node {
		val view = FXGL.getUIFactoryService().newText(version, Color.WHITE, 10.0)
		view.translateY = (FXGL.getAppHeight() - 2).toDouble()
		return view
	}
}