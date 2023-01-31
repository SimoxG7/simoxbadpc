import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class Server {
  public static void main(String[] args) {
    DatagramSocket server = null;

    try {
      server = new DatagramSocket(50000);
      int buffer_size = 100;
      byte buffer[] = new byte[buffer_size];

      DatagramPacket packet = new DatagramPacket(buffer, buffer_size);

      // ArrayList arr = new ArrayList<Valori>();
      Valori val = new Valori();

      while (true) {

        server.receive(packet);

        String msg = new String(packet.getData(), 0, packet.getLength());

        String data[] = msg.split(" ");

        if (!msg.equals(".")) {

          if (!val.getNick().equalsIgnoreCase(data[0]) && data.length > 2) {
            val.setNick(data[0]);
            val.setAddr(packet.getAddress().toString());
            val.setAlfa(Double.parseDouble(data[1]));
            val.setBeta(Double.parseDouble(data[2]));
          } else {
            if (val.getRTT() == 0.) {
              val.setRTT(Double.parseDouble(data[1]));
              val.setD(Double.parseDouble(data[1]) / 2.);

              val.setRTO(val.getD() * 4 + val.getRTT());

              String toClient = val.getRTO().toString();
              toClient += " ";
              toClient += val.getD().toString();
              toClient += " ";
              toClient += val.getRTT().toString();

              DatagramPacket p = new DatagramPacket(toClient.getBytes(), toClient.length(), packet.getSocketAddress());

              server.send(p);
            } else {

              Double m = Double.parseDouble(data[1]);
              Double rtt = val.getAlfa() * val.getRTT() + (1 - val.getAlfa()) * m;
              val.setRTT(rtt);

              Double d = val.getBeta() * val.getRTT() + (1 - val.getBeta()) * val.getRTT();
              val.setD(d);

              Double rto = val.getRTT() + 4 * val.getD();
              val.setRTO(rto);

              String toClient = val.getRTO().toString();
              toClient += " ";
              toClient += val.getD().toString();
              toClient += " ";
              toClient += val.getRTT().toString();

              DatagramPacket p = new DatagramPacket(toClient.getBytes(), toClient.length(), packet.getSocketAddress());

              server.send(p);
            }
          }

        }

        if (msg.equals(".")) {
          val.remove();
        }

      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        server.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}