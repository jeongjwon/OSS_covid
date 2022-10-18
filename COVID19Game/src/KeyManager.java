package COVID19;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Ű �Է��� �������ִ� Ŭ����
public class KeyManager implements KeyListener {
	public static KeyManager instance = null;

	boolean up, down, left, right;

	private KeyManager() {
		up = false;
		down = false;
		left = false;
		right = false;
	}

	// KeyManager�� ó�� �Ҵ��� ��� ��ü ���� �� ��ȯ�Ѵ�. (VirusManager�� �̸� �޾� Ű �Է� ���� ���)
	public static KeyManager getInstance() {
		if (instance == null)
			instance = new KeyManager();

		return instance;
	}

	@Override
	// Ű�� �Էµ� ��ư�� ���� up, down, left, right�� ���¸� �ٲ۴�
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
	// Ű�� �������� �ش� Ű�� false�� �ٲ۴�.
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
