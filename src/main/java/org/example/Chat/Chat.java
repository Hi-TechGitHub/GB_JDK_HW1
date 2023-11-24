package org.example.Chat;

import org.example.Chat.Model.Message;
import org.example.Chat.Action.LoadFromCSV;
import org.example.Chat.Action.SaveToCSV;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;


public class Chat extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 200;
    private static final String path = Config.pathDb;
    JButton btnSend = new JButton("Отправить.");
    JLabel lblLogin = new JLabel("Login:");
    JLabel lblPassword = new JLabel("Password:");
    JLabel lblIP = new JLabel("IP:");
    JLabel lblMessage = new JLabel("Massage:");
    JButton btnExit = new JButton("Exit");
    JTextField txtFieldLogin = new JTextField();
    JTextField txtFieldPassword = new JTextField();
    JTextField txtFieldIP = new JTextField();
    JTextField txtFieldMessage = new JTextField();
    JTextArea areaMessage = new JTextArea();
    JPanel panServer = new JPanel(new GridLayout(6, 2));
    JPanel panClient = new JPanel(new GridLayout(6, 1));
    SaveToCSV saveCSV = new SaveToCSV();
    LoadFromCSV loadCSV = new LoadFromCSV();

    String message;
    Message newMessage;

    public Chat() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("ChatWindow");
        setResizable(false);

        areaMessage.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaMessage);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panServer.add(lblLogin);
        panServer.add(txtFieldLogin);
        panServer.add(lblPassword);
        panServer.add(txtFieldPassword);
        panServer.add(lblIP);
        panServer.add(txtFieldIP);

        areaMessage.append(loadCSV.load(path));

        panClient.add(lblMessage);
        panClient.add(scrollPane);
        panClient.add(txtFieldMessage);
        panClient.add(btnSend);
        panClient.add(btnExit);

        setLayout(new GridLayout(2, 1));
        add(panServer);
        add(panClient);
        setVisible(true);

        txtFieldMessage.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        });

        btnSend.addActionListener(e -> sendMessage());

        btnExit.addActionListener(e -> {
            setVisible(false);
            new Menu().Menu();
        });
    }

    private void sendMessage() {
        message = txtFieldLogin.getText() + ": " + txtFieldMessage.getText() + "\n";
        areaMessage.append(message);
        newMessage = new Message(LocalDateTime.now(), txtFieldLogin.getText(), txtFieldMessage.getText());
        saveCSV.save(path, newMessage);
        System.out.println("Отправлено сообщение: " + message);
        txtFieldMessage.setText(null);
    }
}