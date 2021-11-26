package com.company;

import javax.swing.*;
import javax.swing.border.Border;

public class AirfieldForm {
    private JFrame frame;
    private JButton parkPlane;
    private JButton parkHydroplane;
    private JButton takeTransport;
    private JButton compareEquality;
    private JButton compareInequality;
    private JTextField placeTransport;
    private JTextField countPlaceTransport;
    private Airfield<Plane, Adding> airfield;
    private DrawAirfield drawCamp;
    private JPanel groupBox;
    private JPanel equateGroupBox;
    private JLabel placeText;
    private JLabel placeCountText;
    private Border borderTake;
    private Border borderCompare;

    public AirfieldForm() {
        initialization();
        frame = new JFrame("Аэродром");
        frame.setSize(1200, 564);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(parkPlane);
        frame.getContentPane().add(parkHydroplane);
        frame.getContentPane().add(groupBox);
        frame.getContentPane().add(equateGroupBox);
        frame.getContentPane().add(drawCamp);
        frame.repaint();
    }

    public void initialization() {
        airfield = new Airfield<>(890, 525);
        drawCamp = new DrawAirfield(airfield);
        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        borderCompare = BorderFactory.createTitledBorder("Сравнить");
        parkPlane = new JButton("Припарковать самолет");
        parkHydroplane = new JButton("Припарковать гидроплан");
        compareEquality = new JButton(">=");
        compareInequality = new JButton("<=");
        placeTransport = new JTextField();
        countPlaceTransport = new JTextField();
        takeTransport = new JButton("Забрать");
        placeText = new JLabel("Место: ");
        placeCountText = new JLabel("Кол-во: ");
        groupBox = new JPanel();
        groupBox.setLayout(null);
        groupBox.add(placeText);
        groupBox.add(placeTransport);
        groupBox.add(takeTransport);
        parkPlane.setBounds(850, 12, 300, 40);
        parkPlane.addActionListener(e -> createPlane());
        parkHydroplane.setBounds(850, 60, 300, 40);
        parkHydroplane.addActionListener(e -> createHydroplane());
        groupBox.setBounds(930, 150, 150, 100);
        placeText.setBounds(40, 20, 60, 30);
        placeTransport.setBounds(85, 20, 30, 30);
        takeTransport.setBounds(40, 60, 90, 30);
        takeTransport.addActionListener(e -> takePlane());
        groupBox.setBorder(borderTake);
        drawCamp.setBounds(0, 0, 890, 525);
        equateGroupBox = new JPanel();
        equateGroupBox.setLayout(null);
        equateGroupBox.setBorder(borderCompare);
        equateGroupBox.add(compareEquality);
        equateGroupBox.add(compareInequality);
        equateGroupBox.add(countPlaceTransport);
        equateGroupBox.add(placeCountText);
        equateGroupBox.setBounds(930, 300, 150, 150);
        placeCountText.setBounds(40, 20, 60, 30);
        countPlaceTransport.setBounds(85, 20, 30, 30);
        compareInequality.setBounds(40, 60, 90, 30);
        compareInequality.addActionListener(e -> compare(compareInequality.getText()));
        compareEquality.setBounds(40, 100, 90, 30);
        compareEquality.addActionListener(e -> compare(compareEquality.getText()));
    }

    private void createPlane() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);
        if (colorDialog.getColor() != null) {
            Plane transport = new Plane(100, 1000, colorDialog.getColor());
            if (airfield.add(transport)) {
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Стоянка переполнена");
            }
        }
    }

    private void createHydroplane() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);
        if (colorDialog.getColor() != null) {
            JColorChooser otherColorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, otherColorDialog);
            if (otherColorDialog.getColor() != null) {
                Plane transport = new Hydroplane(100, 1000, colorDialog.getColor(), otherColorDialog.getColor(),
                        true, true, true, 0, 0);
                if (airfield.add(transport)) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Стоянка переполнена");
                }
            }
        }
    }

    private void takePlane() {
        if (!placeTransport.getText().equals("")) {
            try {
                Plane transport = airfield.delete(Integer.parseInt(placeTransport.getText()));
                if (transport != null) {
                    HydroplaneForm excavatorForm = new HydroplaneForm();
                    excavatorForm.setPlane(transport);
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Транспорта не существует");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Транспорта не существует");
            }
        }
    }

    private void compare(String comparison) {
        int number = Integer.parseInt(countPlaceTransport.getText());
        if (!countPlaceTransport.getText().equals("")) {
            if (comparison.equals(">=")) {
                if (airfield.moreequal(number)) {
                    JOptionPane.showMessageDialog(frame, "Введенное число " + number + " больше или равно количеству занятых мест на стоянке");
                } else {
                    JOptionPane.showMessageDialog(frame, "Введенное число " + number + " не больше или равно количеству занятых мест на стоянке");
                }
            }
            if (comparison.equals("<=")) {
                if (airfield.lessequal(number)) {
                    JOptionPane.showMessageDialog(frame, "Введенное число " + number + " меньше или равно количеству занятых мест на стоянке");
                } else {
                    JOptionPane.showMessageDialog(frame, "Введенное число " + number + " не меньше или равно количеству занятых мест на стоянке");
                }
            }
        }
    }
}