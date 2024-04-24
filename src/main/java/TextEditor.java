import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor extends JFrame {
  private JTextArea textArea;
  private JMenuBar menuBar;
  private JMenu fileMenu, editMenu;
  private JMenuItem openItem, saveItem, exitItem, cutItem, copyItem, pasteItem;

  public TextEditor() {
    setTitle("Текстовый редактор");
    setSize(800, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    textArea = new JTextArea();
    getContentPane().add(new JScrollPane(textArea));

    menuBar = new JMenuBar();
    fileMenu = new JMenu("Файл");
    editMenu = new JMenu("Редактировать");

    openItem = new JMenuItem("Открыть");
    saveItem = new JMenuItem("Сохранить");
    exitItem = new JMenuItem("Выход");
    cutItem = new JMenuItem("Вырезать");
    copyItem = new JMenuItem("Копировать");
    pasteItem = new JMenuItem("Вставить");

    fileMenu.add(openItem);
    fileMenu.add(saveItem);
    fileMenu.addSeparator();
    fileMenu.add(exitItem);

    editMenu.add(cutItem);
    editMenu.add(copyItem);
    editMenu.add(pasteItem);

    openItem.addActionListener(e -> openFile());
    saveItem.addActionListener(e -> saveFile());
    exitItem.addActionListener(e -> System.exit(0));
    cutItem.addActionListener(e -> textArea.cut());
    copyItem.addActionListener(e -> textArea.copy());
    pasteItem.addActionListener(e -> textArea.paste());

    menuBar.add(fileMenu);
    menuBar.add(editMenu);

    setJMenuBar(menuBar);
  }

  private void openFile() {
    JFileChooser fileChooser = new JFileChooser();
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        textArea.read(new FileReader(file.getAbsolutePath()), null);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  private void saveFile() {
    JFileChooser fileChooser = new JFileChooser();
    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      if (!file.getPath().toLowerCase().endsWith(".txt")) {
        file = new File(file.getPath() + ".txt");
      }
      try (PrintWriter out = new PrintWriter(file)) {
        out.println(textArea.getText());
      } catch (FileNotFoundException ex) {
        ex.printStackTrace();
      }
    }
  }
}