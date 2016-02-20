package view;

import controller.ActionButtonController;
import controller.ActionHotKeyButtonController;
import controller.CommandsBtn;
import controller.DigitalPanelBtn;
import model.ImageResources;
import model.Images;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Анатолий on 18.02.2016.
 */
public class MainWindow extends JFrame {
    JPanel mainPanel = new JPanel();
    JPanel leftTopPanel;
    JPanel rightTopPanel;
    JLabel statusLabel = new JLabel();
    final int WIDTH_WINDOW = 650;
    final int HEIGHT_WINDOW = 450;
    Images img = Images.INSTANCE;
    ActionButtonController actionBtnCont = ActionButtonController.INSTANCE;

    public void init(){
        setTitle("Эмулятор микроЭВМ УМПК-580(I8080)");
        setSize(WIDTH_WINDOW,HEIGHT_WINDOW);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            setContentPane(new JLabel(Images.INSTANCE.getImage(ImageResources.BG)));
        } catch (NoImageException e) {
            e.printStackTrace();
        }
        setLayout( new BorderLayout());
        //*MainPanel
        mainPanel.setLayout(new GridBagLayout());
        add(mainPanel,BorderLayout.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);

        FocusTraversalPolicy policy = new DefaultFocusTraversalPolicy() {

            @Override
            public Component getDefaultComponent(Container aContainer) {
                return mainPanel;
            }

        };
        setFocusTraversalPolicy(policy);
        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                ActionHotKeyButtonController.INSTANCE.hotKey(e.getKeyCode());
            }
        });
        //Add panel
        addLeftTopPanel(c);
        addRightTopPanel(c);
        addLeftCenterPanel(c);
        addRightCenterPanel(c);
        addLeftBottomPanel(c);
        addRightBottomPanel(c);
        addStatusPanel();
        setVisible(true);
    }
    private void addLeftTopPanel(GridBagConstraints c){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,16));
        panel.setBorder(BorderFactory.createTitledBorder("Шина адреса"));
        for (int i =0; i<16;i++){
            try {
                JLabel l = new JLabel(Images.INSTANCE.getImage(ImageResources.OFF));
                l.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));
                panel.add("te"+i,l);
            } catch (NoImageException e) {
                e.printStackTrace();
            }
        }
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.6;
        leftTopPanel = panel;
        mainPanel.add(panel,c);
    }
    private void addRightTopPanel(GridBagConstraints c){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,8));
        panel.setBorder(BorderFactory.createTitledBorder("Шина данных"));
        for (int i =0; i<8;i++){
            try {
                JLabel l = new JLabel(Images.INSTANCE.getImage(ImageResources.OFF));
                panel.add("te"+i,l);
            } catch (NoImageException e) {
                e.printStackTrace();
            }
        }
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.4;
        rightTopPanel = panel;
        mainPanel.add(panel,c);
    }
    private void addLeftCenterPanel(GridBagConstraints c)
    {
        //Create mainPanel(leftPanel,rightPanel)
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        //Create LeftCenterPanel -> leftPanel(leftTopPanelChild,leftCenterPanelChild,leftBottomPanelChild)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Input port"));
        //Create LeftCenterPanel -> leftPanel -> leftTopPanelChild
        JPanel leftTopPanelChild = new JPanel();
        leftTopPanelChild.setLayout(new GridLayout(1,8));
        for (int i =0; i<8;i++){
            try {
                JLabel l = new JLabel(Images.INSTANCE.getImage(ImageResources.OFF));
                leftTopPanelChild.add(l);
            } catch (NoImageException e) {
                e.printStackTrace();
            }
        }
        leftPanel.add(leftTopPanelChild, BorderLayout.NORTH);
        //Create LeftCenterPanel -> leftPanel -> leftCenterPanelChild
        JPanel leftCenterPanelChild = new JPanel();
        leftCenterPanelChild.setLayout(new GridLayout(1,8));
        for (int i =0; i<8;i++){
            try {
                JLabel l = new JLabel(Images.INSTANCE.getImage(ImageResources.SWITCH_OFF));
                l.setCursor(new Cursor(Cursor.HAND_CURSOR));
                leftCenterPanelChild.add(l);
            } catch (NoImageException e) {
                e.printStackTrace();
            }
        }
        leftPanel.add(leftCenterPanelChild, BorderLayout.CENTER);
        //Create LeftCenterPanel -> leftPanel -> leftBottomPanelChild
        JPanel leftBottomPanelChild = new JPanel();
        leftBottomPanelChild.setLayout(new GridLayout(1,8));
        for (int i = 7; i>=0;i--){
            JLabel l = new JLabel(Integer.toString(i));
            l.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
            l.setFont(new Font("Arial", Font.PLAIN, 12));
            leftBottomPanelChild.add(l);
        }
        leftPanel.add(leftBottomPanelChild, BorderLayout.SOUTH);
        panel.add(leftPanel);
        //Create LeftCenterPanel -> rightPanel(rightTopPanelChild,rightBottomPanelChild)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        //Create LeftCenterPanel -> rightPanel -> rightTopPanelChild
        JPanel rightTopPanelChild = new JPanel();
        rightTopPanelChild.setLayout(new GridLayout(1,8));
        rightTopPanelChild.setBorder(BorderFactory.createTitledBorder("Порт вывода"));
        for (int i =0; i<8;i++){
            try {
                JLabel l = new JLabel(Images.INSTANCE.getImage(ImageResources.OFF));
                rightTopPanelChild.add(l);
            } catch (NoImageException e) {
                e.printStackTrace();
            }
        }
        rightPanel.add(rightTopPanelChild, BorderLayout.NORTH);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0,3,0,0));
        //Create LeftCenterPanel -> rightPanel -> rightBottomPanelChild(rightBottomPanelChildTop,rightBottomPanelChildBottom)
        JPanel rightBottomPanelChild = new JPanel();
        rightBottomPanelChild.setLayout(new BorderLayout());
        rightBottomPanelChild.setBorder(BorderFactory.createTitledBorder("Шина управления"));
        //Create LeftCenterPanel -> rightPanel -> rightBottomPanelChild -> rightBottomPanelChildTop
        JPanel rightBottomPanelChildTop = new JPanel();
        rightBottomPanelChildTop.setLayout(new GridLayout(1,5));
        for (int i =0; i<5;i++){
            try {
                JLabel l = new JLabel(Images.INSTANCE.getImage(ImageResources.OFF));
                rightBottomPanelChildTop.add(l);
            } catch (NoImageException e) {
                e.printStackTrace();
            }
        }
        rightBottomPanelChild.add(rightBottomPanelChildTop, BorderLayout.CENTER);
        //Create LeftCenterPanel -> rightPanel -> rightBottomPanelChild -> rightBottomPanelChildBottom
        JPanel rightBottomPanelChildBottom = new JPanel();
        rightBottomPanelChildBottom.setLayout(new GridLayout(1,5));
        Font f = new Font("Arial", Font.PLAIN, 10);
        String[] data = new String[]{"MRDC","MWDC","IOWC","IORC","INTA"};
        for (String str : data)
        {
            JLabel lb = new JLabel(str);
            lb.setFont(f);
            lb.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
            rightBottomPanelChildBottom.add(lb);
        }
        rightBottomPanelChild.add(rightBottomPanelChildBottom, BorderLayout.SOUTH);
        rightPanel.add(rightBottomPanelChild, BorderLayout.CENTER);
        panel.add(rightPanel);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.6;
        mainPanel.add(panel,c);
    }
    private void addRightCenterPanel(GridBagConstraints c){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints cc = new GridBagConstraints();

        JPanel address = new JPanel();
        address.setBorder(BorderFactory.createTitledBorder("Адрес"));
        address.setLayout(new GridLayout(1,4));
        address.add(img.getImage(ImageResources._H,0,3,0,3));
        address.add(img.getImage(ImageResources._A,0,3,0,3));
        address.add(img.getImage(ImageResources._4,0,3,0,3));
        address.add(img.getImage(ImageResources._A,0,3,0,3));
        cc.gridx = 0;
        cc.gridy = 0;
        cc.weightx = 0.6;
        panel.add(address,cc);

        JPanel value = new JPanel();
        value.setLayout(new GridLayout(1,2));
        value.setBorder(BorderFactory.createTitledBorder("Значение"));
        value.add(img.getImage(ImageResources._l,0,4,0,4));
        value.add(img.getImage(ImageResources._0,0,4,0,4));

        cc.gridx = 1;
        cc.gridy = 0;
        cc.weightx = 0.4;
        panel.add(value,cc);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.4;
        mainPanel.add(panel,c);
    }
    private void addLeftBottomPanel(GridBagConstraints c){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Команды"));
        GridBagConstraints cc = new GridBagConstraints();

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(4,2));
        leftPanel.add(getCommandsButton(CommandsBtn.RELOADING,163,45));
        leftPanel.add(getCommandsButton(CommandsBtn.STEP_CYCLE,163,45));
        leftPanel.add(getCommandsButton(CommandsBtn.STEP_TEAM,163,45));
        leftPanel.add(getCommandsButton(CommandsBtn.PROGRAM_COUNTER,163,45));
        leftPanel.add(getCommandsButton(CommandsBtn.FINDING_REGISTER,163,45));
        leftPanel.add(getCommandsButton(CommandsBtn.FINDING_ADDRESSES,163,45));
        leftPanel.add(getCommandsButton(CommandsBtn.REDUCE,163,45));
        leftPanel.add(getCommandsButton(CommandsBtn.RECORD,163,45));
        cc.gridx = 0;
        cc.gridy = 0;
        cc.weightx = 0.8;
        panel.add(leftPanel,cc);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2,1));

        rightPanel.add(getCommandsButton(CommandsBtn.START,90,90));
        rightPanel.add(getCommandsButton(CommandsBtn.STOP,90,90));

        cc.gridx = 1;
        cc.gridy = 0;
        cc.weightx = 0.2;
        panel.add(rightPanel,cc);

        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.6;
        mainPanel.add(panel,c);
    }

    private void addRightBottomPanel(GridBagConstraints c){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));
        panel.setBorder(BorderFactory.createTitledBorder("Цифровая панель"));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._C,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._D,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._E,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._F,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._8,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._9,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._A,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._B,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._4,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._5,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._6,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._7,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._0,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._1,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._2,45,45));
        panel.add(getDigitalPanelButton(DigitalPanelBtn._3,45,45));
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.4;
        mainPanel.add(panel,c);

    }
    private void addStatusPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel stPanel = new JPanel();
        stPanel.setBorder(BorderFactory.createTitledBorder("Статус"));
        stPanel.add(statusLabel);

        panel.add(stPanel, BorderLayout.CENTER);

        JMenuBar mBar = new JMenuBar();
        mBar.setBackground(new Color(238,238,238));
        mBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JMenu menu = new JMenu("Файл");
        menu.setPreferredSize(new Dimension(45, 20));


        mBar.add(menu);
        menu.add(new JMenuItem("Горячие клавиши"));
        menu.add(new JMenuItem("Команды"));
        menu.add(new JMenuItem("О программе"));
        menu.add(new JSeparator());
        menu.add(new JMenuItem("Выход"));
        panel.add(mBar, BorderLayout.EAST);
        add(panel,BorderLayout.SOUTH);
    }

    private JButton getCommandsButton(CommandsBtn cBtn, int width, int height) {
        JButton btn = getButton(cBtn.text,width,height);
        btn.addActionListener(e -> actionBtnCont.setActionCommandBTN(cBtn));
        return btn;
    }
    private JButton getDigitalPanelButton(DigitalPanelBtn dBtn, int width, int height) {
        JButton btn = getButton(dBtn.text,width,height);
        btn.addActionListener(e -> actionBtnCont.setActionDigitalPanelBTN(dBtn));
        return btn;
    }
    private JButton getButton(String text, int width, int height){
        JButton btn = new JButton(text);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(width, height));
        btn.setBorder(new LineBorder(new Color(238,238,238), 3));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(221,221,221));

        btn.addActionListener(e -> btn.setFocusable(false));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(190,230,253));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(221,221,221));
            }
        });
        return  btn;
    }


}
