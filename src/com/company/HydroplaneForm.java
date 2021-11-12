package com.company;
import javax.swing.*;
import java.awt.*;

public class HydroplaneForm {
    private JButton createButton;
    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private JComboBox<Integer> choiceButton;
    private Hydroplane hydroplane;
    private JFrame frame;
    private DrawPicture draw;

    public void direction(JButton button) {
        String temp = button.getName();
        switch (temp) {
            case "Up":
                hydroplane.moveTrans(Direction.Up);
                break;
            case "Down":
                hydroplane.moveTrans(Direction.Down);
                break;
            case "Left":
                hydroplane.moveTrans(Direction.Left);
                break;
            case "Right":
                hydroplane.moveTrans(Direction.Right);
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

        upButton.setEnabled(false);
        downButton.setEnabled(false);
        rightButton.setEnabled(false);
        leftButton.setEnabled(false);
        createButton = new JButton("Создать");
        createButton.setBounds(0, 0, 90, 30);
        createButton.addActionListener(e -> {
            hydroplane = new Hydroplane(100 + ((int) (Math.random() * 300)), 1000 + ((int) (Math.random() * 2000)), Color.BLACK,
                    Color.YELLOW, false, false, false, choiceButton.getSelectedIndex() + 2 + choiceButton.getSelectedIndex());
            hydroplane.setPosition(10 + ((int) (Math.random() * 100)), 10 + ((int) (Math.random() * 100)), 900, 500);
            upButton.setEnabled(true);
            downButton.setEnabled(true);
            rightButton.setEnabled(true);
            leftButton.setEnabled(true);
            draw.setPlane(hydroplane);
            frame.repaint();
        });

        choiceButton = new JComboBox<>(new Integer[]{2, 4, 6});
        choiceButton.setBounds(100, 0, 90, 30);
    }

    public HydroplaneForm() {
        draw = new DrawPicture();
        frame = new JFrame("Гидроплан");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        initialization();
        frame.getContentPane().add(createButton);
        frame.getContentPane().add(upButton);
        frame.getContentPane().add(downButton);
        frame.getContentPane().add(leftButton);
        frame.getContentPane().add(rightButton);
        frame.getContentPane().add(choiceButton);
        frame.getContentPane().add(draw);
        draw.setBounds(0, 0, 900, 500);
        frame.repaint();
    }
}
