package COVID19;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// 키 입력을 관리해주는 클래스
public class KeyManager implements KeyListener {
	public static KeyManager instance = null;

	boolean up, down, left, right;

	private KeyManager() {
		up = false;
		down = false;
		left = false;
		right = false;
	}

	// KeyManager를 처음 할당할 경우 객체 생성 후 반환한다. (VirusManager가 이를 받아 키 입력 값을 사용)
	public static KeyManager getInstance() {
		if (instance == null)
			instance = new KeyManager();

		return instance;
	}

	@Override
	// 키가 입력된 버튼에 따라 up, down, left, right의 상태를 바꾼다
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			up = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			down = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			left = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			right = true;
	}

	@Override
	// 키가 떼어지면 해당 키를 false로 바꾼다.
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			up = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			down = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			left = false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			right = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
