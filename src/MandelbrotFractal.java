import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MandelbrotFractal extends JFrame {


  private static final int WIDTH = 800;
  private static final int HEIGHT = 800;
  private static final int MAX_ITER = 1000;
  private static final double ZOOM = 150;


  private BufferedImage buffer;

  public MandelbrotFractal() {
    super("Mandelbrot Fractal");
    setBounds(100, 100, WIDTH, HEIGHT);
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    generateFractal();
  }

  public void generateFractal() {
    for (int y = 0; y < getHeight(); y++) {
      for (int x = 0; x < getWidth(); x++) {
        double zx = (x - getWidth() / 2) / ZOOM;
        double zy = (y - getHeight() / 2) / ZOOM;
        float c = mandelbrot(zx, zy);
        int color = Color.HSBtoRGB(c / 256f, 1, c / (c + 8f));
        buffer.setRGB(x, y, color);
      }
    }
  }

  private float mandelbrot(double x, double y) {
    float iter = MAX_ITER;
    double zx = x;
    double zy = y;
    while (iter > 0 && zx * zx + zy * zy < 4) {
      double tmp = zx * zx - zy * zy + x;
      zy = 2 * zx * zy + y;
      zx = tmp;
      iter--;
    }
    return iter;
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(buffer, 0, 0, this);
  }

  public static void main(String[] args) {
    MandelbrotFractal fractal = new MandelbrotFractal();
    fractal.setVisible(true);
  }
}
