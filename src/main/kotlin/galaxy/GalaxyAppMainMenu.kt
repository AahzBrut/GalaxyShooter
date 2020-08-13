package galaxy

import com.almasb.fxgl.app.scene.FXGLMenu
import com.almasb.fxgl.app.scene.MenuType
import com.almasb.fxgl.dsl.FXGL
import javafx.beans.binding.StringBinding
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.shape.Rectangle

class GalaxyAppMainMenu : FXGLMenu(MenuType.MAIN_MENU) {

	override fun createActionButton(name: StringBinding, action: Runnable): Button {
		return Button("New game")
	}

	override fun createActionButton(name: String, action: Runnable): Button {
		return Button("Exit")
	}

	override fun createBackground(width: Double, height: Double): Node {
		return FXGL.Companion.texture("background/Star_Citizen_Ships.png")
	}

	override fun createProfileView(profileName: String): Node {
		return Rectangle()
	}

	override fun createTitleView(title: String): Node {
		return Rectangle()
	}

	override fun createVersionView(version: String): Node {
		return Rectangle()
	}
}