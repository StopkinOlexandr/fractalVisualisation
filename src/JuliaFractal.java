import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JuliaFractal extends JFrame {

  private static final int WIDTH = 800;
  private static final int HEIGHT = 800;
  private static final int MAX_ITER = 1000;
  private static final double ZOOM = 300;

  private BufferedImage buffer;

  public JuliaFractal() {
    super("Julia Fractal");
    setBounds(100, 100, WIDTH, HEIGHT);
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    generateFractal();
  }

  public void generateFractal() {
    double cx = -0.7;
    double cy = 0.27015;

    for (int y = 0; y < getHeight(); y++) {
      for (int x = 0; x < getWidth(); x++) {
        double zx = (x - getWidth() / 2) / ZOOM;
        double zy = (y - getHeight() / 2) / ZOOM;
        float c = julia(zx, zy, cx, cy);
        int color = Color.HSBtoRGB(c / 256f, 1, c / (c + 8f));
        buffer.setRGB(x, y, color);
      }
    }
  }

  private float julia(double zx, double zy, double cx, double cy) {
    float iter = MAX_ITER;
    while (iter > 0 && zx * zx + zy * zy < 4) {
      double tmp = zx * zx - zy * zy + cx;
      zy = 2 * zx * zy + cy;
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
    JuliaFractal fractal = new JuliaFractal();
    fractal.setVisible(true);
  }
}