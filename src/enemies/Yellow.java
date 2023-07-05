package enemies;

import static helpz.Constants.Enemies.YELLOW;
import helpz.Constants.Enemies;
import managers.EnemyManager;

public class Yellow extends Enemy {

	public Yellow(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, YELLOW, em);

	}
}
