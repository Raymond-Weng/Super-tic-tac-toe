import sun.awt.image.GifImageDecoder;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalCheckBoxIcon;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static Main main;

    JFrame startUp;
    JTable jTable;

    JFrame jFrame;
    JPanel[][] panels;
    JButton[][][][] buttons;

    String player;
    boolean[][] done;
    boolean ended;

    public static void main(String[] args) {
        main = new Main();
        main.setup();
    }

    public void setup(){
        startUp = new JFrame();
        startUp.setLayout(new GridLayout(4,2));

        startUp.getContentPane().add(new JLabel());
        startUp.getContentPane().add(new JLabel("Super tic tac toe"));
        startUp.getContentPane().getComponent(1).setFont(new Font(null, Font.PLAIN, 30));
        startUp.getContentPane().add(new JCheckBox());
        ((JCheckBox)startUp.getContentPane().getComponent(2)).setIcon (new MetalCheckBoxIcon() {
            protected int getControlSize() { return 30; }
        });
        startUp.getContentPane().add(new JLabel("O AI"));
        startUp.getContentPane().getComponent(3).setFont(new Font(null, Font.PLAIN, 30));
        startUp.getContentPane().add(new JCheckBox());
        ((JCheckBox)startUp.getContentPane().getComponent(4)).setIcon (new MetalCheckBoxIcon() {
            protected int getControlSize() { return 30; }
        });
        startUp.getContentPane().add(new JLabel("X AI"));
        startUp.getContentPane().getComponent(5).setFont(new Font(null, Font.PLAIN, 30));
        startUp.add(new JLabel());
        JButton jButton = new JButton("start");
        jButton.setFont(new Font(null, Font.PLAIN, 30));
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.player = "O";
                main.ended = false;
                main.done = new boolean[3][3];
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        main.done[x][y] = false;
                    }
                }
                main.start(((JCheckBox)main.startUp.getContentPane().getComponent(2)).isSelected(),
                        ((JCheckBox)main.startUp.getContentPane().getComponent(4)).isSelected());
                main.startUp.dispose();
            }
        });
        startUp.add(jButton);

        startUp.setTitle("Super tic tac toe (Game  setting)");
        startUp.pack();
        startUp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startUp.setVisible(true);
    }

    public void start(boolean oAI, boolean xAI) {
        System.out.println("Game start");
        jFrame = new JFrame();
        jFrame.setLayout(new GridLayout(3, 3));

        panels = new JPanel[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                panels[x][y] = new JPanel();
                JPanel panel = new JPanel();
                panels[x][y].setLayout(new GridLayout(1, 1));
                panel.setLayout(new GridLayout(3, 3));
                panels[x][y].add(panel);
                panels[x][y].setBorder(new LineBorder(Color.RED, 2));
                jFrame.add(panels[x][y]);
            }
        }

        buttons = new JButton[3][3][3][3];
        for (int bx = 0; bx < 3; bx++) {
            for (int by = 0; by < 3; by++) {
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        buttons[bx][by][x][y] = new JButton();
                        buttons[bx][by][x][y].setPreferredSize(new Dimension(50, 50));
                        ((JPanel) panels[bx][by].getComponent(0)).add(buttons[bx][by][x][y]);

                        int finalBx = bx;
                        int finalBy = by;
                        int finalX = x;
                        int finalY = y;
                        buttons[bx][by][x][y].addActionListener(new AbstractAction() {
                            final int bx = finalBx;
                            final int by = finalBy;
                            final int x = finalX;
                            final int y = finalY;

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println(player + ":" + bx + ", " + by + ", " + x + ", " + y);
                                buttons[bx][by][x][y].setText(player);

                                switch (x) {
                                    case 0:
                                        switch (y) {
                                            case 0:
                                                if ((buttons[bx][by][1][0].getText().equals(player) && buttons[bx][by][2][0].getText().equals(player)) ||
                                                        (buttons[bx][by][1][1].getText().equals(player) && buttons[bx][by][2][2].getText().equals(player)) ||
                                                        (buttons[bx][by][0][1].getText().equals(player) && buttons[bx][by][0][2].getText().equals(player))) {
                                                    clean(bx, by);
                                                }
                                                break;
                                            case 1:
                                                if ((buttons[bx][by][1][1].getText().equals(player) && buttons[bx][by][2][1].getText().equals(player)) ||
                                                        (buttons[bx][by][0][0].getText().equals(player) && buttons[bx][by][0][2].getText().equals(player))) {
                                                    clean(bx, by);
                                                }
                                                break;
                                            case 2:
                                                if ((buttons[bx][by][1][2].getText().equals(player) && buttons[bx][by][2][2].getText().equals(player)) ||
                                                        (buttons[bx][by][1][1].getText().equals(player) && buttons[bx][by][2][0].getText().equals(player)) ||
                                                        (buttons[bx][by][0][1].getText().equals(player) && buttons[bx][by][0][0].getText().equals(player))) {
                                                    clean(bx, by);
                                                }
                                                break;
                                        }
                                        break;
                                    case 1:
                                        switch (y) {
                                            case 0:
                                                if ((buttons[bx][by][0][0].getText().equals(player) && buttons[bx][by][2][0].getText().equals(player)) ||
                                                        (buttons[bx][by][1][1].getText().equals(player) && buttons[bx][by][1][2].getText().equals(player))) {
                                                    clean(bx, by);
                                                }
                                                break;
                                            case 1:
                                                if ((buttons[bx][by][0][0].getText().equals(player) && buttons[bx][by][2][2].getText().equals(player)) ||
                                                        (buttons[bx][by][1][0].getText().equals(player) && buttons[bx][by][1][2].getText().equals(player)) ||
                                                        (buttons[bx][by][2][0].getText().equals(player) && buttons[bx][by][0][2].getText().equals(player)) ||
                                                        (buttons[bx][by][0][1].getText().equals(player) && buttons[bx][by][2][1].getText().equals(player))) {
                                                    clean(bx, by);
                                                }
                                                break;
                                            case 2:
                                                if ((buttons[bx][by][0][2].getText().equals(player) && buttons[bx][by][2][2].getText().equals(player)) ||
                                                        (buttons[bx][by][1][1].getText().equals(player) && buttons[bx][by][1][0].getText().equals(player))) {
                                                    clean(bx, by);
                                                }
                                                break;
                                        }
                                        break;
                                    case 2:
                                        switch (y) {
                                            case 0:
                                                if ((buttons[bx][by][1][0].getText().equals(player) && buttons[bx][by][0][0].getText().equals(player)) ||
                                                        (buttons[bx][by][1][1].getText().equals(player) && buttons[bx][by][0][2].getText().equals(player)) ||
                                                        (buttons[bx][by][2][1].getText().equals(player) && buttons[bx][by][2][2].getText().equals(player))) {
                                                    clean(bx, by);
                                                }
                                                break;
                                            case 1:
                                                if ((buttons[bx][by][1][1].getText().equals(player) && buttons[bx][by][0][1].getText().equals(player)) ||
                                                        (buttons[bx][by][2][0].getText().equals(player) && buttons[bx][by][2][2].getText().equals(player))) {
                                                    clean(bx, by);
                                                }
                                                break;
                                            case 2:
                                                if ((buttons[bx][by][1][2].getText().equals(player) && buttons[bx][by][0][2].getText().equals(player)) ||
                                                        (buttons[bx][by][1][1].getText().equals(player) && buttons[bx][by][0][0].getText().equals(player)) ||
                                                        (buttons[bx][by][2][1].getText().equals(player) && buttons[bx][by][2][0].getText().equals(player))) {
                                                    clean(bx, by);
                                                }
                                                break;
                                        }
                                        break;

                                }
                                boolean noSpace = true;
                                for (int tx = 0; tx < 3; tx++) {
                                    for (int ty = 0; ty < 3; ty++) {
                                        if (buttons[bx][by][tx][ty].getText().isEmpty()) {
                                            noSpace = false;
                                            break;
                                        }
                                    }
                                    if (!noSpace) {
                                        break;
                                    }
                                }
                                if (noSpace) {
                                    System.out.println("no one finished " + x + ", " + y);
                                    main.done[bx][by] = true;
                                    panels[bx][by].remove(0);
                                    panels[bx][by].add(new JButton("-"));
                                    panels[bx][by].getComponent(0).setEnabled(false);
                                }

                                noSpace = true;
                                for (int tx = 0; tx < 3; tx++) {
                                    for (int ty = 0; ty < 3; ty++) {
                                        if (!done[tx][ty]) {
                                            noSpace = false;
                                            break;
                                        }
                                    }
                                    if (!noSpace) {
                                        break;
                                    }
                                }
                                if(!ended && noSpace){
                                    ended = true;
                                    for (int bx = 0; bx < 3; bx++) {
                                        for (int by = 0; by < 3; by++) {
                                            for (int x = 0; x < 3; x++) {
                                                for (int y = 0; y < 3; y++) {
                                                    main.buttons[bx][by][x][y].setEnabled(false);
                                                }
                                            }
                                        }
                                    }
                                    JOptionPane.showMessageDialog(null, "Draw!", "Game ended", JOptionPane.INFORMATION_MESSAGE);
                                }

                                for (int tbx = 0; tbx < 3 && !ended; tbx++) {
                                    for (int tby = 0; tby < 3; tby++) {
                                        for (int tx = 0; tx < 3; tx++) {
                                            for (int ty = 0; ty < 3; ty++) {
                                                buttons[tbx][tby][tx][ty].setEnabled((done[x][y] || (tbx == x && tby == y)) && buttons[tbx][tby][tx][ty].getText().isEmpty());
                                            }
                                        }
                                    }
                                }

                                player = (player.equals("O")) ? "X" : "O";
                                if(!ended && ((player.equals("O") && oAI) || (player.equals("X") && xAI))){
                                    ai();
                                }
                            }
                        });
                    }
                }
            }
        }

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setTitle("Super tic tac toe");
        jFrame.pack();
        if(oAI){
            ai();
        }
    }

    public void ai() {
        boolean did = false;
        while(!did) {
            int bx = (int) (Math.random() * 3);
            int by = (int) (Math.random() * 3);
            if(done[bx][by]){
                continue;
            }
            int x = (int) (Math.random() * 3);
            int y = (int) (Math.random() * 3);
            if(buttons[bx][by][x][y].isEnabled()){
                buttons[bx][by][x][y].getActionListeners()[0].actionPerformed(null);
                did = true;
            }
        }
    }

    public void clean(int x, int y) {
        System.out.println(player + " finished " + x + ", " + y);
        main.done[x][y] = true;
        panels[x][y].remove(0);
        panels[x][y].add(new JButton(main.player));
        panels[x][y].getComponent(0).setEnabled(false);
        switch (x) {
            case 0:
                switch (y) {
                    case 0:
                        if (((main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals(main.player)) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player))) ||
                                (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player)) ||
                                ((main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)))) {
                            end();
                        }
                        break;
                    case 1:
                        if ((main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player))) {
                            end();
                        }
                        break;
                    case 2:
                        if ((main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player))) {
                            end();
                        }
                        break;
                }
                break;
            case 1:
                switch (y) {
                    case 0:
                        if ((main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals(main.player))) {
                            end();
                        }
                        break;
                    case 1:
                        if ((main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals(main.player)) && (main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals(main.player))) {
                            end();
                        }
                        break;
                    case 2:
                        if ((main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals(main.player))) {
                            end();
                        }
                        break;
                }
                break;
            case 2:
                switch (y) {
                    case 0:
                        if ((main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals(main.player)) && (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player))) {
                            end();
                        }
                        break;
                    case 1:
                        if ((main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player))) {
                            end();
                        }
                        break;
                    case 2:
                        if ((main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) ||
                                (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player))) {
                            end();
                        }
                        break;
                }
                break;
        }
    }

    public void end() {
        ended = true;
        for (int bx = 0; bx < 3; bx++) {
            for (int by = 0; by < 3; by++) {
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        main.buttons[bx][by][x][y].setEnabled(false);
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, player + " won!", "Game ended", JOptionPane.INFORMATION_MESSAGE);
    }
}
