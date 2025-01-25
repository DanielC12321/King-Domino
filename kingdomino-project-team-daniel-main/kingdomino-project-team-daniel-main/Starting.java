import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.*;
import javax.swing.*;

public class Starting extends JPanel
{
	BufferedImage title, controls;
	static int state = 0;
	
	public Starting()
	{
		try 
		{
			title = ImageIO.read(Starting.class.getResource("/images/titleScreen.png"));
			controls = ImageIO.read(Starting.class.getResource("/images/controls.png"));
		}
		catch (IOException e) {}
	}

	public void paint(Graphics x)
	{
		if (state == 0)
		{
			x.drawImage(title, 0, 0, getWidth(), getHeight(), null);
		}
		if (state == 1)
		{
			x.drawImage(controls, 0, 0, getWidth(), getHeight(), null);
		}
		System.out.println("Print");
	}
}