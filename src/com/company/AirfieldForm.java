package com.company;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;
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
    private JMenu airfieldFileMenu;
    private JMenuItem saveFile;
    private JMenuItem loadFile;
    private JMenuItem saveAirfield;
    private JMenuItem loadAirfield;
    private Logger logger;

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
        logger = LogManager.getLogger(AirfieldForm.class);
        PropertyConfigurator.configure("src/com/company/log4j2.properties");
        queueTransport = new LinkedList<>();
        airfieldCollection = new AirfieldCollection(890, 525);
        drawAirfields = new DrawAirfields(airfieldCollection);
        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        borderAirfields = BorderFactory.createTitledBorder("Аэродромы");
        parkTransport = new JButton("Припарковать транспорт");
        putTransportIntoQueue = new JButton("Поместить в список");
        addAirfield = new JButton("Добавить аэродром");
        deleteAirfield = new JButton("Удалить аэродром");
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
        airfieldFileMenu = new JMenu("Аэродром");
        saveAirfield = new JMenuItem("Сохранить");
        saveAirfield.addActionListener(e -> {
            saveAirfield();
        });
        loadAirfield = new JMenuItem("Загрузить");
        loadAirfield.addActionListener(e -> {
            loadAirfield();
        });
        fileMenu.add(saveFile);
        fileMenu.add(loadFile);
        airfieldFileMenu.add(saveAirfield);
        airfieldFileMenu.add(loadAirfield);
        menuBar.add(fileMenu);
        menuBar.add(airfieldFileMenu);
    }

    private void createTransport() {
        if (listBoxAirfields.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Аэродром не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            PlaneConfigPanel configPanel = new PlaneConfigPanel(frame);
            ITransport transport = configPanel.getTransport();
            if (transport == null) {
                return;
            }
            if (airfieldCollection.get(listBoxAirfields.getSelectedValue()).add(transport)) {
                logger.info("На аэродром " + listBoxAirfields.getSelectedValue() + " был добавлен транспорт " + transport);
                frame.repaint();
            }
        } catch (AirfieldOverflowException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
            logger.warn(e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
            logger.fatal(e.getMessage());
        }
    }

    private void placeIntoQueueTransport() {
        if (listBoxAirfields.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Аэродром не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            ITransport transport = airfieldCollection.get(listBoxAirfields.getSelectedValue()).delete(Integer.parseInt(placeTransport.getText()));
            if (transport != null) {
                queueTransport.add((Plane) transport);
                frame.repaint();
                logger.info("С аэродрома " + listBoxAirfields.getSelectedValue() + " был изъят транспорт " + transport + " и помещен в коллекцию");
            }
        } catch (AirfieldNotFoundException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Не найдено", JOptionPane.ERROR_MESSAGE);
            logger.warn(e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
            logger.fatal(e.getMessage());
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
            logger.info("Добавлен аэродром " + airfieldsField.getText());
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
                logger.info("Аэродром " + listBoxAirfields.getSelectedValue() + " удалена");
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
            try {
                airfieldCollection.saveData(fileSaveDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл сохранился", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Данные сохранены в файл " + fileSaveDialog.getSelectedFile().getPath());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }

    private void loadFile() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                airfieldCollection.loadData(fileOpenDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Данные были загружены из файла " + fileOpenDialog.getSelectedFile().getPath());
                reloadLevels();
                frame.repaint();
            } catch (AirfieldOverflowException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
                logger.warn(e.getMessage());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Файл не найден", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Некорректные данные", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }

    private void saveAirfield() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        if (listBoxAirfields.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Выберите аэродром", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                airfieldCollection.saveData(fileSaveDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл сохранился", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Данные сохранены в файл " + fileSaveDialog.getSelectedFile().getPath());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }

    private void loadAirfield() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                airfieldCollection.loadData(fileOpenDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Данные были загружены из файла " + fileOpenDialog.getSelectedFile().getPath());
                reloadLevels();
                frame.repaint();
            } catch (AirfieldOverflowException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
                logger.warn(e.getMessage());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Файл не найден", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Некорректные данные", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }
}