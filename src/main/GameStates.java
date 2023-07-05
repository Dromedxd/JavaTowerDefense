package main;

//no olvidar que enum es una especial que sirve para almacenar valores de las constantes

public enum GameStates {

	PLAYING,
	MENU,
	SETTINGS,
	EDITING,
	GAME_OVER,
	CREDITS;
	
	//donde se empieza el juego
	public static GameStates gameState  = MENU;
	
	public static void SetGameState(GameStates state) {
		gameState = state;
	}
	
}
