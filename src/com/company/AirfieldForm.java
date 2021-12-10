package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.LinkedList;
import java.util.Queue;

public class AirfieldForm {
    private Queue<Plane> queueTransport;
    private AirfieldCollection airfieldCollection;
    private JFrame frame;
    private JButton parkTransport;
    private JButton takeTransport;
    private JButton addAirfield;
    private JButton deleteAirfield;
    private JButton putTransportIntoQueue;
    private JTextField placeTransport;
    private JTextField countPlaceTransport;
    private DrawAirfields drawAirfields;
    private JPanel groupBoxTake;
    private JPanel airfieldsGroupBox;
    private JLabel placeText;
    private JLabel placeCountText;
    private JTextField airfieldsField;
    private JList<String> listBoxAirfields;
    private Border borderTake;
    private Border borderAirfields;
    private DefaultListModel<String> airfieldsList;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu campFileMenu;
    private JMenuItem saveFile;
    private JMenuItem loadFile;
    private JMenuItem saveCamp;
    private JMenuItem loadCamp;

    public AirfieldForm() {
        initialization();
        frame = new JFrame("Аэродромы");
        frame.setSize(1200, 564);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(parkTransport);
        frame.getContentPane().add(groupBoxTake);
        frame.getContentPane().add(drawAirfields);
        frame.getContentPane().add(airfieldsGroupBox);
        frame.setJMenuBar(menuBar);
        frame.repaint();
    }

    public void initialization() {
        queueTransport = new LinkedList<>();
        airfieldCollection = new AirfieldCollection(890, 525);
        drawAirfields = new DrawAirfields(airfieldCollection);
        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        borderAirfields = BorderFactory.createTitledBorder("Стоянки");
        parkTransport = new JButton("Припарковать транспорт");
        putTransportIntoQueue = new JButton("Поместить в список");
        addAirfield = new JButton("Добавить стоянку");
        deleteAirfield = new JButton("Удалить стоянку");
        placeTransport = new JTextField();
        countPlaceTransport = new JTextField();
        takeTransport = new JButton("Забрать из списка");
        placeText = new JLabel("Место: ");
        placeCountText = new JLabel("Кол-во: ");
        airfieldsField = new JTextField();
        airfieldsList = new DefaultListModel<>();
        listBoxAirfields = new JList<>(airfieldsList);
        groupBoxTake = new JPanel();
        groupBoxTake.setLayout(null);
        groupBoxTake.add(placeText);
        groupBoxTake.add(placeTransport);
        groupBoxTake.add(takeTransport);
        groupBoxTake.add(putTransportIntoQueue);
        parkTransport.setBounds(850, 10, 300, 90);
        parkTransport.addActionListener(e -> {
            createTransport();
        });
        groupBoxTake.setBounds(880, 110, 250, 160);
        placeText.setBounds(90, 20, 60, 30);
        placeTransport.setBounds(135, 20, 30, 30);
        putTransportIntoQueue.setBounds(40, 70, 170, 30);
        putTransportIntoQueue.addActionListener(e -> {
            placeIntoQueueTransport();
        });
        takeTransport.setBounds(40, 110, 170, 30);
        takeTransport.addActionListener(e -> {
            takeTransportFrame();
        });
        groupBoxTake.setBorder(borderTake);
        drawAirfields.setBounds(0, 0, 890, 525);
        airfieldsGroupBox = new JPanel();
        airfieldsGroupBox.setLayout(null);
        airfieldsGroupBox.setBounds(880, 270, 250, 240);
        airfieldsGroupBox.setBorder(borderAirfields);
        airfieldsField.setBounds(50, 30, 150, 20);
        listBoxAirfields.setBounds(50, 90, 150, 100);
        listBoxAirfields.addListSelectionListener(e -> {
            changeItemList();
        });
        addAirfield.setBounds(50, 55, 150, 30);
        addAirfield.addActionListener(e -> {
            addAirfield();
        });
        deleteAirfield.setBounds(50, 200, 150, 30);
        deleteAirfield.addActionListener(e -> {
            deleteAirfield();
        });
        airfieldsGroupBox.add(airfieldsField);
        airfieldsGroupBox.add(listBoxAirfields);
        airfieldsGroupBox.add(addAirfield);
        airfieldsGroupBox.add(deleteAirfield);
        placeCountText.setBounds(40, 20, 60, 30);
        countPlaceTransport.setBounds(85, 20, 30, 30);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("Файл");
        saveFile = new JMenuItem("Сохранить");
        saveFile.addActionListener(e -> {
            saveFile();
        });
        loadFile = new JMenuItem("Загрузить");
        loadFile.addActionListener(e -> {
            loadFile();
        });
        campFileMenu = new JMenu("Стоянка");
        saveCamp = new JMenuItem("Сохранить");
        saveCamp.addActionListener(e -> {
            saveCamp();
        });
        loadCamp = new JMenuItem("Загрузить");
        loadCamp.addActionListener(e -> {
            loadCamp();
        });
        fileMenu.add(saveFile);
        fileMenu.add(loadFile);
        campFileMenu.add(saveCamp);
        campFileMenu.add(loadCamp);
        menuBar.add(fileMenu);
        menuBar.add(campFileMenu);
    }

    private void createTransport() {
        if (listBoxAirfields.getSelectedIndex() >= 0) {
            PlaneConfigPanel configPanel = new PlaneConfigPanel(frame);
            ITransport transport = configPanel.getTransport();
            if (transport != null) {
                if (airfieldCollection.get(listBoxAirfields.getSelectedValue()).add(transport)) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Аэродром переполнен");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Аэродром не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void placeIntoQueueTransport() {
        if (listBoxAirfields.getSelectedIndex() >= 0) {
            if (!placeTransport.getText().equals("")) {
                try {
                    Plane transport = (Plane) airfieldCollection.get(listBoxAirfields.getSelectedValue()).delete(Integer.parseInt(placeTransport.getText()));
                    if (transport != null) {
                        queueTransport.add(transport);
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Не существующий транспорт", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Не существующий транспорт", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Аэродром не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reloadLevels() {
        int index = listBoxAirfields.getSelectedIndex();
        airfieldsList.removeAllElements();
        int i = 0;
        for (String name : airfieldCollection.keys()) {
            airfieldsList.add(i, name);
            i++;
        }
        int itemsCount = airfieldsList.size();
        if (itemsCount > 0 && (index < 0 || index >= itemsCount)) {
            listBoxAirfields.setSelectedIndex(0);
        } else if (index >= 0 && index < itemsCount) {
            listBoxAirfields.setSelectedIndex(index);
        }
    }

    private void addAirfield() {
        if (!airfieldsField.getText().equals("")) {
            airfieldCollection.addAirfield(airfieldsField.getText());
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Введите название аэродрома", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAirfield() {
        if (listBoxAirfields.getSelectedIndex() >= 0) {
            int result = JOptionPane.showConfirmDialog(frame, "Удалить аэродром " + listBoxAirfields.getSelectedValue() + "?", "Удаление",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                airfieldCollection.deleteAirfield(listBoxAirfields.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Аэродром не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void changeItemList() {
        drawAirfields.setSelectedItem(listBoxAirfields.getSelectedValue());
        frame.repaint();
    }

    private void takeTransportFrame() {
        if (!queueTransport.isEmpty()) {
            HydroplaneForm hydroplaneForm = new HydroplaneForm();
            hydroplaneForm.setPlane(queueTransport.remove());
            frame.repaint();
        }

    }

    private void saveFile() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (airfieldCollection.saveData(fileSaveDialog.getSelectedFile().getPath())) {
                JOptionPane.showMessageDialog(frame, "Файл успешно сохранен", "Результат", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Файл не сохранен", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadFile() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (airfieldCollection.loadData(fileOpenDialog.getSelectedFile().getPath())) {
                JOptionPane.showMessageDialog(frame, "Файл успешно загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                reloadLevels();
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Файл не загружен", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveCamp() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        if (listBoxAirfields.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Выберите стоянку", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (airfieldCollection.saveCamp(fileSaveDialog.getSelectedFile().getPath(), listBoxAirfields.getSelectedValue())) {
                JOptionPane.showMessageDialog(frame, "Файл успешно сохранен", "Результат", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Файл не сохранен", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadCamp() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (airfieldCollection.loadCamp(fileOpenDialog.getSelectedFile().getPath())) {
                JOptionPane.showMessageDialog(frame, "Файл успешно загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                reloadLevels();
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Файл не загружен", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}