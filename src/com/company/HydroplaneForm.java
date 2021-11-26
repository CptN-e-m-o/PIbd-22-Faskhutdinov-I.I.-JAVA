package com.company;
import javax.swing.*;
import java.awt.*;

public class HydroplaneForm {

    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;

    private Transport transport;
    private JFrame frame;
    private DrawPicture draw;

    public void direction(JButton button) {
        String temp = button.getName();
        switch (temp) {
            case "Up":
                transport.moveTransport(Direction.Up);
                break;
            case "Down":
                transport.moveTransport(Direction.Down);
                break;
            case "Left":
                transport.moveTransport(Direction.Left);
                break;
            case "Right":
                transport.moveTransport(Direction.Right);
                break;
        }
        frame.repaint();
    }

    public void initialization() {
        Icon up = new ImageIcon("C:\\Users\\CptNemo\\Pictures\\Lab1Java\\1.png");
        Icon down = new ImageIcon("C:\\Users\\CptNemo\\Pictures\\Lab1Java\\2.png");
        Icon left = new ImageIcon("C:\\Users\\CptNemo\\Pictures\\Lab1Java\\3.png");
        Icon right = new ImageIcon("C:\\Users\\CptNemo\\Pictures\\Lab1Java\\4.png");
        upButton = new JButton(up);
        upButton.setName("Up");
        upButton.setBounds(800, 300, 30, 30);
        upButton.addActionListener(e -> direction(upButton));

        downButton = new JButton(down);
        downButton.setName("Down");
        downButton.setBounds(800, 400, 30, 30);
        downButton.addActionListener(e -> direction(downButton));

        rightButton = new JButton(right);
        rightButton.setName("Right");
        rightButton.setBounds(850, 350, 30, 30);
        rightButton.addActionListener(e -> direction(rightButton));

        leftButton = new JButton(left);
        leftButton.setName("Left");
        leftButton.setBounds(750, 350, 30, 30);
        leftButton.addActionListener(e -> direction(leftButton));
    }

    public HydroplaneForm() {
        draw = new DrawPicture();
        frame = new JFrame("Гидроплан");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        initialization();
        frame.getContentPane().add(upButton);
        frame.getContentPane().add(downButton);
        frame.getContentPane().add(leftButton);
        frame.getContentPane().add(rightButton);
        frame.getContentPane().add(draw);
        draw.setBounds(0, 0, 900, 500);
        frame.repaint();
    }

    public void setPlane(Transport transport) {
        this.transport = transport;
        draw.setTransport(transport);
        frame.repaint();
    }
}
