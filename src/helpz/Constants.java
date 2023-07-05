package helpz;

//Direcciones para el pathfinder
public class Constants {

	public static class Projectiles {

		public static final int SWORD = 0;
		public static final int SPEAR = 1;
		public static final int FIREBOLT = 2;

		public static float GetSpeed(int type) {
			switch (type) {
			case SWORD:
				return 10f;
			case SPEAR:
				return 8f;
			case FIREBOLT:
				return 5f;

			}
			return 0f;
		}

	}

	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class Towers {
		public static final int MAGO = 0;
		public static final int CAMPESINO = 1;
		public static final int CABALLERO = 2;

		public static int GetTowerCost(int towerType) {
			switch (towerType) {
			case MAGO:
				return 90;
			case CAMPESINO:
				return 60;
			case CABALLERO:
				return 120;
			}
			return 0;
		}

		public static String GetName(int towerType) {
			switch (towerType) {
			case MAGO:
				return "Mage";
			case CAMPESINO:
				return "Farmer";
			case CABALLERO:
				return "Knight";
			}
			return "";

		}

		public static int GetDefaultDmg(int towerType) {
			switch (towerType) {
			case MAGO:
				return 25;
			case CAMPESINO:
				return 15;
			case CABALLERO:
				return 80;
			}
			return 0;

		}

		public static float GetDefaultRange(int towerType) {
			switch (towerType) {
			case MAGO:
				return 80;
			case CAMPESINO:
				return 120;
			case CABALLERO:
				return 60;
			}
			return 0;
		}

		public static float GetDefaultCooldown(int towerType) {
			//la velocidad de recarga de las torretas
			switch (towerType) {
			case MAGO:
				return 20;
			case CAMPESINO:
				return 10;
			case CABALLERO:
				return 50;
			}
			return 0;
		}
	}

	public static class Enemies {
		public static final int BLUE = 0;
		public static final int GREEN = 1;
		public static final int YELLOW = 2;

		public static float GetSpeed(int enemyType) {
			switch (enemyType) {
			case BLUE:
				return 0.80f;
			case GREEN:
				return 0.65f;
			case YELLOW:
				return 0.35f;

			}
			return 0;
		}

		public static int GetReward(int enemyType) {
			switch (enemyType) {
			case BLUE:
				return 1;
			case GREEN:
				return 2;
			case YELLOW:
				return 3;

			}
			return 0;
		}

		public static int GetStartHealth(int enemyType) {

			switch (enemyType) {
			case BLUE:
				return 60;
			case GREEN:
				return 120;
			case YELLOW:
				return 350;

			}
			return 0;

		}
	}

	public static class Tiles {
		public static final int WATER_TILE = 0;
		public static final int FILL_TILE = 1;
		public static final int ROAD_TILE = 2;

	}
}
