package frc.robot.io;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

/** Add your docs here. */
public class NTButton {

  public static Thread ntButtonsManager = new Thread();
  public static List<Runnable> registry = new ArrayList<>();
  private static boolean continueHandling = true;

  private NetworkTableEntry entry;
  private Runnable action;

  public NTButton(Runnable toRun, String name, NetworkTable table) {
    entry = table.getEntry(name);
    action = toRun;

    entry.setBoolean(false);
  }

  public void update() {
    if (entry.getBoolean(false)) {
      action.run();
      entry.setBoolean(false);
    }
  }

  public void runConcurrently() {
    registry.add(this::update);
  }

  public static void handleConcurrently() {
    while (continueHandling) {
      long time = System.currentTimeMillis();
      long endtime = time + 50;
      for (Runnable action : registry) {
        action.run();
      }
      while (System.currentTimeMillis() < endtime) {
        try {
          Thread.sleep(5);
        } catch (InterruptedException e) {
          // shouldn't happen
          e.printStackTrace();
        }
      }
    }
  }

  public static void startConcurrentHandling() {
    ntButtonsManager = new Thread(() -> handleConcurrently());
    continueHandling = true;
    ntButtonsManager.start();
  }

  public static void stopConcurrentHandling() {
    continueHandling = false;
  }
}
