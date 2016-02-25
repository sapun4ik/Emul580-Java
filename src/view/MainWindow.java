package view;
import controller.*;
import model.ImageResources;
import model.Images;
import model.RecoveryData;
import model.SaveData;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by Анатолий on 18.02.2016.
 */
public class MainWindow extends JFrame {
    JPanel mainPanel;
    JLabel statusLabel;
    final int WIDTH_WINDOW = 650;
    final int HEIGHT_WINDOW = 450;
    Images img = Images.INSTANCE;
    MainWindowController mwc = MainWindowController.INSTANCE;
    ActionButtonController actionBtnCont = ActionButtonController.INSTANCE;

    public void init() {
        setTitle("Эмулятор микроЭВМ УМПК-580(I8080)");
        setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(new JLabel(Images.INSTANCE.getImage(ImageResources.BG)));
        setLayout(new BorderLayout());
        //*MainPanel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);
        //Add panel
        addLeftTopPanel(c);
        addRightTopPanel(c);
        addLeftCenterPanel(c);
        addRightCenterPanel(c);
        addLeftBottomPanel(c);
        addRightBottomPanel(c);
        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                ActionHotKeyButtonController.INSTANCE.hotKey(e.getKeyCode());
            }
        });

        add(mainPanel, BorderLayout.CENTER);
        FocusTraversalPolicy policy = new DefaultFocusTraversalPolicy() {
            @Override
            public Component getDefaultComponent(Container aContainer) {
                return mainPanel;
            }
        };
        setFocusTraversalPolicy(policy);
        statusLabel = new JLabel();
        addStatusPanel();
        setVisible(true);
    }

    private void addLeftTopPanel(GridBagConstraints c) {
        mainPanel.add(new JPanel() {{
            setLayout(new GridLayout(1, 16));
            setBorder(BorderFactory.createTitledBorder("Шина адреса"));
            for (int i = 0; i < 16; i++) {
                JLabel l = new JLabel(Images.INSTANCE.getImage(ImageResources.OFF));
                l.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));
                mwc.magistralAddreses[i] = l;
                add(l);
            }
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 0.6;
        }});
    }

    //magistralData
    private void addRightTopPanel(GridBagConstraints c) {
        mainPanel.add(new JPanel() {{
            setLayout(new GridLayout(1, 8));
            setBorder(BorderFactory.createTitledBorder("Шина данных"));
            for (int i = 0; i < 8; i++) {
                JLabel l = new JLabel(Images.INSTANCE.getImage(ImageResources.OFF));
                mwc.magistralData[i] = l;
                add(l);
            }
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0.4;
        }}, c);
    }

    private void addLeftCenterPanel(GridBagConstraints c) {
        //Create mainPanel(leftPanel,rightPanel)
        mainPanel.add(new JPanel() {{
            setLayout(new GridLayout(1, 2));
            //Create LeftCenterPanel -> leftPanel(leftTopPanelChild,leftCenterPanelChild,leftBottomPanelChild)
            add(new JPanel() {{
                setLayout(new BorderLayout());
                setBorder(BorderFactory.createTitledBorder("Порт ввода"));
                //Create LeftCenterPanel -> leftPanel -> leftTopPanelChild
                add(new JPanel() {{
                    setLayout(new GridLayout(1, 8));
                    for (int i = 0; i < 8; i++) {
                        JLabel l = new JLabel(Images.INSTANCE.getImage(ImageResources.OFF));
                        add(l);
                    }
                }}, BorderLayout.NORTH);
                //Create LeftCenterPanel -> leftPanel -> leftCenterPanelChild
                add(new JPanel() {{
                    setLayout(new GridLayout(1, 8));
                    for (int i = 0; i < 8; i++) {
                        JLabel l = new JLabel(Images.INSTANCE.getImage(ImageResources.SWITCH_OFF));
                        l.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        add(l);
                    }
                }}, BorderLayout.CENTER);
                //Create LeftCenterPanel -> leftPanel -> leftBottomPanelChild
                add(new JPanel() {{
                    setLayout(new GridLayout(1, 8));
                    for (int i = 7; i >= 0; i--) {
                        JLabel l = new JLabel(Integer.toString(i));
                        l.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
                        l.setFont(new Font("Arial", Font.PLAIN, 12));
                        add(l);
                    }
                }}, BorderLayout.SOUTH);
            }});
            //Create LeftCenterPanel -> rightPanel(rightTopPanelChild,rightBottomPanelChild)
            add(new JPanel() {{
                setLayout(new BorderLayout());
                setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
                //Create LeftCenterPanel -> rightPanel -> rightTopPanelChild
                add(new JPanel() {{
                    setLayout(new GridLayout(1, 8));
                    setBorder(BorderFactory.createTitledBorder("Порт вывода"));
                    for (int i = 0; i < 8; i++) {
                        JLabel l = new JLabel(Images.INSTANCE.getImage(ImageResources.OFF));
                        add(l);
                    }
                }}, BorderLayout.NORTH);
                //Create LeftCenterPanel -> rightPanel -> rightBottomPanelChild(rightBottomPanelChildTop,rightBottomPanelChildBottom)
                add(new JPanel() {{
                    setLayout(new BorderLayout());
                    setBorder(BorderFactory.createTitledBorder("Шина управления"));
                    //Create LeftCenterPanel -> rightPanel -> rightBottomPanelChild -> rightBottomPanelChildTop
                    add(new JPanel() {{
                        setLayout(new GridLayout(1, 5));
                        for (int i = 0; i < 5; i++) {
                            JLabel l = new JLabel(Images.INSTANCE.getImage(ImageResources.OFF));
                            add(l);
                        }
                    }}, BorderLayout.CENTER);
                    //Create LeftCenterPanel -> rightPanel -> rightBottomPanelChild -> rightBottomPanelChildBottom
                    add(new JPanel() {{
                        setLayout(new GridLayout(1, 5));
                        Font f = new Font("Arial", Font.PLAIN, 10);
                        String[] data = new String[]{"MRDC", "MWDC", "IOWC", "IORC", "INTA"};
                        for (String str : data) {
                            JLabel lb = new JLabel(str);
                            lb.setFont(f);
                            lb.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
                            add(lb);
                        }
                    }}, BorderLayout.SOUTH);
                }}, BorderLayout.CENTER);
            }});
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0.6;
        }}, c);
    }

    private void addRightCenterPanel(GridBagConstraints c) {
        mainPanel.add(new JPanel(){{
            setLayout(new GridBagLayout());
            GridBagConstraints cc = new GridBagConstraints();
            List<ImageResources> irs = asList(ImageResources._H,ImageResources._A,ImageResources._4,ImageResources._A,ImageResources._L,ImageResources._0);
            //Address
            add(new JPanel(){{
                setBorder(BorderFactory.createTitledBorder("Адрес"));
                setLayout(new GridLayout(1, 4));
                //for(ImageResources ir : irs.subList(0,4))
                for (int i = 0 ; i < 4; i++)
                    add(mwc.digitalPanel[i] = img.getImage(irs.get(i), 0, 3, 0, 3));
                cc.gridx = 0;
                cc.gridy = 0;
                cc.weightx = 0.6;
            }}, cc);
            //Value
            add(new JPanel(){{
                setLayout(new GridLayout(1, 2));
                setBorder(BorderFactory.createTitledBorder("Значение"));
                add(mwc.digitalPanel[4] = img.getImage(irs.get(4), 0, 4, 0, 4));
                add(mwc.digitalPanel[5] = img.getImage(irs.get(5), 0, 4, 0, 4));
                cc.gridx = 1;
                cc.gridy = 0;
                cc.weightx = 0.4;
            }}, cc);
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 0.4;
        }}, c);
    }

    private void addLeftBottomPanel(GridBagConstraints c) {
        //Create mainPanel(leftPanel,rightPanel)
        mainPanel.add(new JPanel(){{
            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createTitledBorder("Команды"));
            GridBagConstraints cc = new GridBagConstraints();
            //Create mainPanel -> leftPanel
            add(new JPanel(){{
                setLayout(new GridLayout(4, 2));
                List<Processes> cBtns = asList(Processes.RELOADING,Processes.STEP_CYCLE,Processes.STEP_TEAM,Processes.PROGRAM_COUNTER,Processes.FINDING_REGISTER,
                        Processes.FINDING_ADDRESSES,Processes.REDUCE,Processes.RECORD);
                for (Processes d : cBtns)
                    add(getCommandsButton(d, 163, 45));
                cc.gridx = 0;
                cc.gridy = 0;
                cc.weightx = 0.8;
            }}, cc);
            //Create mainPanel -> rightPanel
            add(new JPanel(){{
                setLayout(new GridLayout(2, 1));
                add(getCommandsButton(Processes.START, 90, 90));
                add(getCommandsButton(Processes.STOP, 90, 90));
                cc.gridx = 1;
                cc.gridy = 0;
                cc.weightx = 0.2;
            }}, cc);

            c.gridx = 0;
            c.gridy = 2;
            c.weightx = 0.6;
        }}, c);
    }

    private void addRightBottomPanel(GridBagConstraints c) {
        //Create mainPanel
        mainPanel.add(new JPanel(){{
            setLayout(new GridLayout(4, 4));
            setBorder(BorderFactory.createTitledBorder("Цифровая панель"));
            List<DigitalPanelButton> dBtns = asList(DigitalPanelButton._C,DigitalPanelButton._D,DigitalPanelButton._E,DigitalPanelButton._F,DigitalPanelButton._8,
                    DigitalPanelButton._9,DigitalPanelButton._A,DigitalPanelButton._B,DigitalPanelButton._4,DigitalPanelButton._5,DigitalPanelButton._6,DigitalPanelButton._7,
                    DigitalPanelButton._0,DigitalPanelButton._1,DigitalPanelButton._2,DigitalPanelButton._3);
            for (DigitalPanelButton d : dBtns)
                add(getDigitalPanelButton(d, 45, 45));
            c.gridx = 1;
            c.gridy = 2;
            c.weightx = 0.4;
        }}, c);

    }

    private void addStatusPanel() {
        //Create mainPanel(stPanel,mBar)
        add(new JPanel(){{
            setLayout(new BorderLayout());
            //Create mainPanel -> stPanel
            add(new JPanel(){{
                setLayout(new BorderLayout());
                setBorder(BorderFactory.createTitledBorder("Статус"));
                statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
                statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                statusLabel.setText("Начало работы");
                add(statusLabel, BorderLayout.WEST);
            }}, BorderLayout.CENTER);
            //Create mainPanel -> mBar
            add(new JMenuBar(){{
                setBackground(new Color(238, 238, 238));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                add(new JMenu("Файл"){{
                    setPreferredSize(new Dimension(45, 20));
                    add(new JMenuItem("Сохранить данные"){{
                        addActionListener(e -> new SaveData().save());

                    }});
                    add(new JMenuItem("Восстановить данные"){{
                        addActionListener(e -> {
                            try {
                                new RecoveryData().recovery();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (SAXException ex) {
                                ex.printStackTrace();
                            } catch (ParserConfigurationException ex) {
                                ex.printStackTrace();
                            }
                        });

                    }});
                    add(new JMenuItem("Горячие клавиши"));
                    add(new JMenuItem("Команды"));
                    add(new JMenuItem("О программе"));
                    add(new JSeparator());
                    add(new JMenuItem("Выход"));
                }});
            }}, BorderLayout.EAST);
            mwc.status = statusLabel;
        }}, BorderLayout.SOUTH);
    }

    private JButton getCommandsButton(Processes cBtn, int width, int height) {
        JButton btn = getButton(cBtn.text, width, height);
        btn.addActionListener(e -> actionBtnCont.setActionCommandBTN(cBtn));
        return btn;
    }

    private JButton getDigitalPanelButton(DigitalPanelButton dBtn, int width, int height) {
        JButton btn = getButton(dBtn.text, width, height);
        btn.addActionListener(e -> actionBtnCont.setActionDigitalPanelBTN(dBtn));
        return btn;
    }

    private JButton getButton(String text, int width, int height) {
        JButton btn = new JButton(text);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(width, height));
        btn.setBorder(new LineBorder(new Color(238, 238, 238), 3));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(221, 221, 221));
        btn.addActionListener(e -> btn.setFocusable(false));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(190, 230, 253));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(221, 221, 221));
            }
        });
        return btn;
    }


}
