import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
//import java.util.Random;
import java.lang.Math;

import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Client {
  public static void main(String[] args) {
    DatagramSocket client = null;

    try {
      client = new DatagramSocket();
      InetSocketAddress server = new InetSocketAddress(InetAddress.getLocalHost(), 50000);

      String msg = "";

      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

      do {
        System.out.println("Inserisci (nickname alfa beta):");
        msg = input.readLine();

        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), server);
        client.send(packet);

        Double m = 0.;

        if (!msg.equals(".")) {
          m = Math.random() * 100.;
          System.out.println("M:" + m);

          String temp = msg.split(" ")[0];
          temp += " ";
          temp += m.toString();

          packet = new DatagramPacket(temp.getBytes(), temp.length(), server);

          client.send(packet);

          int buffer_size = 100;
          byte buffer[] = new byte[buffer_size];

          DatagramPacket fromServer = new DatagramPacket(buffer, buffer_size);
          client.receive(fromServer);

          String output = new String(fromServer.getData(), 0, fromServer.getLength());
          String data[] = output.split(" ");
          System.out.println("RTT:" + data[0] + " D:" + data[1] + " RTO:" + data[2]);

        }

      } while (!msg.equals("."));

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        client.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }
}