import javax.sound.sampled.BooleanControl;

public class File {
  public String name;
  public int size = 0;
  public boolean isfolder = false;

  public File(String name, int size, boolean isfolder) {
    this.name = name;
    this.size = size;
    this.isfolder = isfolder;
  }
}
