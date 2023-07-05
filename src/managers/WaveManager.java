package managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import events.Wave;
import scenes.Playing;

public class WaveManager {

	private Playing playing;
	private ArrayList<Wave> waves = new ArrayList<>();
	private int enemySpawnTickLimit = 60 * 1;
	private int enemySpawnTick = enemySpawnTickLimit;
	private int enemyIndex, waveIndex;
	private int waveTickLimit = 60 * 5;
	private int waveTick = 0;
	private boolean waveStartTimer, waveTickTimerOver;

	public WaveManager(Playing playing) {
		this.playing = playing;
		createWaves();
	}

	public void update() {
		if (enemySpawnTick < enemySpawnTickLimit)
			enemySpawnTick++;

		if (waveStartTimer) {
			waveTick++;
			if (waveTick >= waveTickLimit) {
				waveTickTimerOver = true;
			}
		}

	}

	public void increaseWaveIndex() {
		waveIndex++;
		waveTickTimerOver = false;
		waveStartTimer = false;
	}

	public boolean isWaveTimerOver() {

		return waveTickTimerOver;
	}

	public void startWaveTimer() {
		waveStartTimer = true;
	}

	public int getNextEnemy() {
		enemySpawnTick = 0;
		return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
	}

	private void createWaves() {
		
		int oleadas = 102;

		for (int i = 0; i < oleadas; i++) {
			if (i <= 10) {

				waves.add(new Wave(randomWavesEasy()));
			} else if (i > 10 && i < 25) {

				waves.add(new Wave(randomWavesMedium()));
			} else if (i > 25 && i<40) {

				waves.add(new Wave(randomWavesHard()));
			}
			else if (i>40) {
				waves.add(new Wave(randomWavesHardx2()));
			}

		}

	}

	public ArrayList<Wave> getWaves() {
		return waves;
	}

	public ArrayList<Integer> randomWavesEasy() {
		int cantidad;
		int bichito;
		Random r = new Random();

		cantidad = r.nextInt(10, 20);

		int[] numeros = new int[cantidad];

		ArrayList<Integer> prueba = new ArrayList<Integer>();

		for (int j = 0; j < numeros.length; j++) {
			bichito = r.nextInt(0, 3);
			numeros[j] = bichito;

			prueba.add(bichito);

		}

		return prueba;
	}

	public ArrayList<Integer> randomWavesMedium() {
		int cantidad;
		int bichito;
		Random r = new Random();
		cantidad = r.nextInt(30, 50);

		int[] numeros = new int[cantidad];

		ArrayList<Integer> prueba = new ArrayList<Integer>();

		for (int j = 0; j < numeros.length; j++) {
			bichito = r.nextInt(0, 3);
			numeros[j] = bichito;

			prueba.add(bichito);

		}

		return prueba;
	}

	public ArrayList<Integer> randomWavesHard() {
		int cantidad;
		int bichito;
		Random r = new Random();
		cantidad = r.nextInt(50, 80);

		int[] numeros = new int[cantidad];

		ArrayList<Integer> prueba = new ArrayList<Integer>();

		for (int j = 0; j < numeros.length; j++) {
			bichito = r.nextInt(0, 3);
			numeros[j] = bichito;

			prueba.add(bichito);

		}

		return prueba;
	}
	
	public ArrayList<Integer> randomWavesHardx2() {
		int cantidad;
		int bichito;
		Random r = new Random();
		cantidad = 120;

		int[] numeros = new int[cantidad];

		ArrayList<Integer> prueba = new ArrayList<Integer>();

		for (int j = 0; j < numeros.length; j++) {
			bichito = r.nextInt(0, 3);
			numeros[j] = bichito;

			prueba.add(bichito);

		}

		return prueba;
	}
	
	
	
	

	public boolean isTimeForNewEnemy() {
		return enemySpawnTick >= enemySpawnTickLimit;
	}

	public boolean isThereMoreEnemiesInWave() {
		return enemyIndex < waves.get(waveIndex).getEnemyList().size();
	}

	public boolean isThereMoreWaves() {
		return waveIndex + 1 < waves.size();
	}

	public void resetEnemyIndex() {
		enemyIndex = 0;
	}

	public int getWaveIndex() {
		return waveIndex;
	}

	public float getTimeLeft() {
		float ticksLeft = waveTickLimit - waveTick;
		return ticksLeft / 60.0f;
	}

	public boolean isWaveTimerStarted() {
		return waveStartTimer;
	}

}
