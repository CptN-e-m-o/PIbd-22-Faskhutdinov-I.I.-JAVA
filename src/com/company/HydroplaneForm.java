package com.company;
import javax.swing.*;
import java.awt.*;

public class HydroplaneForm {
    private JButton createPlaneButton;
    private JButton createHydroplaneButton;
    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private JComboBox<String> choiceCountFloatButton;
    private JComboBox<String> choiceAddingButton;
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

        createPlaneButton = new JButton("Создать самолёт");
        createPlaneButton.setBounds(0, 0, 150, 30);
        createPlaneButton.addActionListener(e -> {
            transport = new Plane(100 + ((int) (Math.random() * 300)), 1000 + ((int) (Math.random() * 2000)), Color.BLACK);
            transport.setPosition(10 + ((int) (Math.random() * 100)), 10 + ((int) (Math.random() * 100)), 900, 500);
            draw.setTransport(transport);
            frame.repaint();
        });

        createHydroplaneButton = new JButton("Создать гидроплан");
        createHydroplaneButton.setBounds(170, 0, 150, 30);
        createHydroplaneButton.addActionListener(e -> {
            transport = new Hydroplane(100 + ((int) (Math.random() * 300)), 1000 + ((int) (Math.random() * 2000)), Color.GREEN, Color.BLUE, true, true, true,
                      choiceAddingButton.getSelectedIndex(), choiceCountFloatButton.getSelectedIndex());
            transport.setPosition(10 + ((int) (Math.random() * 100)), 10 + ((int) (Math.random() * 100)), 900, 500);
            draw.setTransport(transport);
            frame.repaint();
        });

        choiceAddingButton = new JComboBox<>(new String[]{"Круглые", "Прямоугольные", "Треугольные"});
        choiceAddingButton.setBounds(350, 0, 80, 30);

        choiceCountFloatButton =  new JComboBox<>(new String[]{"2 поплавка", "4 поплавка", "6 поплавков"});
        choiceCountFloatButton.setBounds(450, 0, 80, 30);
    }

    public HydroplaneForm() {
        draw = new DrawPicture();
        frame = new JFrame("Гидроплан");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        initialization();
        frame.getContentPane().add(createPlaneButton);
        frame.getContentPane().add(createHydroplaneButton);
        frame.getContentPane().add(upButton);
        frame.getContentPane().add(downButton);
        frame.getContentPane().add(leftButton);
        frame.getContentPane().add(rightButton);
        frame.getContentPane().add(choiceAddingButton);
        frame.getContentPane().add(choiceCountFloatButton);
        frame.getContentPane().add(draw);
        draw.setBounds(0, 0, 900, 500);
        frame.repaint();
    }
}
