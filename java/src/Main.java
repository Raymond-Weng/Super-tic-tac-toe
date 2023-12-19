import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalCheckBoxIcon;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static Main main;

    JFrame startUp;

    JFrame jFrame;
    JPanel[][] panels;
    JButton[][][][] buttons;

    String player;
    boolean[][] done;
    boolean ended;

    int[] nextPlay;

    public static void main(String[] args) {
        main = new Main();
        main.setup();
    }

    public void setup() {
        startUp = new JFrame();
        startUp.setLayout(new GridLayout(4, 2));

        startUp.getContentPane().add(new JLabel());
        startUp.getContentPane().add(new JLabel("Super tic tac toe"));
        startUp.getContentPane().getComponent(1).setFont(new Font(null, Font.PLAIN, 30));
        startUp.getContentPane().add(new JCheckBox());
        ((JCheckBox) startUp.getContentPane().getComponent(2)).setIcon(new MetalCheckBoxIcon() {
            protected int getControlSize() {
                return 30;
            }
        });
        startUp.getContentPane().add(new JLabel("O AI"));
        startUp.getContentPane().getComponent(3).setFont(new Font(null, Font.PLAIN, 30));
        startUp.getContentPane().add(new JCheckBox());
        ((JCheckBox) startUp.getContentPane().getComponent(4)).setIcon(new MetalCheckBoxIcon() {
            protected int getControlSize() {
                return 30;
            }
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
                main.nextPlay = new int[]{-1, -1};
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        main.done[x][y] = false;
                    }
                }
                main.start(((JCheckBox) main.startUp.getContentPane().getComponent(2)).isSelected(),
                        ((JCheckBox) main.startUp.getContentPane().getComponent(4)).isSelected());
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
                                if (!ended && noSpace) {
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

                                if(done[x][y]){
                                    nextPlay[0] = -1;
                                }else{
                                    nextPlay[0] = x;
                                    nextPlay[1] = y;
                                }

                                player = (player.equals("O")) ? "X" : "O";
                                if (!ended && ((player.equals("O") && oAI) || (player.equals("X") && xAI))) {
                                    powerAI();
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
        if (oAI) {
            powerAI();
        }
    }

    public void randomAI() {
        boolean did = false;
        while (!did) {
            int bx = (int) (Math.random() * 3);
            int by = (int) (Math.random() * 3);
            if (done[bx][by]) {
                continue;
            }
            int x = (int) (Math.random() * 3);
            int y = (int) (Math.random() * 3);
            if (buttons[bx][by][x][y].isEnabled()) {
                buttons[bx][by][x][y].getActionListeners()[0].actionPerformed(null);
                did = true;
            }
        }
    }

    public void powerAI(){
        block: {
            if (nextPlay[0] == -1) {
                //TODO
                randomAI();
                break block;
            } else {
                //can win
                boolean chanceToWin = false;
                switch (nextPlay[0]) {
                    case 0:
                        switch (nextPlay[1]) {
                            case 0:
                                if (((main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals(main.player)) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player))) ||
                                        (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player)) ||
                                        ((main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)))) {
                                    chanceToWin = true;
                                }
                                break;
                            case 1:
                                if ((main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player))) {
                                    chanceToWin = true;
                                }
                                break;
                            case 2:
                                if ((main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player))) {
                                    chanceToWin = true;
                                }
                                break;
                        }
                        break;
                    case 1:
                        switch (nextPlay[1]) {
                            case 0:
                                if ((main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals(main.player))) {
                                    chanceToWin = true;
                                }
                                break;
                            case 1:
                                if ((main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals(main.player)) && (main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals(main.player))) {
                                    chanceToWin = true;
                                }
                                break;
                            case 2:
                                if ((main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals(main.player))) {
                                    chanceToWin = true;
                                }
                                break;
                        }
                        break;
                    case 2:
                        switch (nextPlay[1]) {
                            case 0:
                                if ((main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals(main.player)) && (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player))) {
                                    chanceToWin = true;
                                }
                                break;
                            case 1:
                                if ((main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player))) {
                                    chanceToWin = true;
                                }
                                break;
                            case 2:
                                if ((main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) ||
                                        (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player))) {
                                    chanceToWin = true;
                                }
                                break;
                        }
                        break;
                }
                if (chanceToWin) {
                    for(int tx = 0; tx < 3; tx++){
                        for(int ty = 0; ty < 3; ty++){
                            if(buttons[nextPlay[0]][nextPlay[1]][tx][ty].isEnabled()){
                                switch (tx) {
                                    case 0:
                                        switch (ty) {
                                            case 0:
                                                if ((buttons[nextPlay[0]][nextPlay[1]][1][0].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][2][0].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][1][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][2][2].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][0][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][0][2].getText().equals(player))) {
                                                    buttons[tx][ty][nextPlay[0]][nextPlay[1]].getActionListeners()[0].actionPerformed(null);
                                                    break block;
                                                }
                                                break;
                                            case 1:
                                                if ((buttons[nextPlay[0]][nextPlay[1]][1][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][2][1].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][0][0].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][0][2].getText().equals(player))) {
                                                    buttons[tx][ty][nextPlay[0]][nextPlay[1]].getActionListeners()[0].actionPerformed(null);
                                                    break block;
                                                }
                                                break;
                                            case 2:
                                                if ((buttons[nextPlay[0]][nextPlay[1]][1][2].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][2][2].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][1][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][2][0].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][0][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][0][0].getText().equals(player))) {
                                                    buttons[tx][ty][nextPlay[0]][nextPlay[1]].getActionListeners()[0].actionPerformed(null);
                                                    break block;
                                                }
                                                break;
                                        }
                                        break;
                                    case 1:
                                        switch (ty) {
                                            case 0:
                                                if ((buttons[nextPlay[0]][nextPlay[1]][0][0].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][2][0].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][1][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][1][2].getText().equals(player))) {
                                                    buttons[tx][ty][nextPlay[0]][nextPlay[1]].getActionListeners()[0].actionPerformed(null);
                                                    break block;
                                                }
                                                break;
                                            case 1:
                                                if ((buttons[nextPlay[0]][nextPlay[1]][0][0].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][2][2].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][1][0].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][1][2].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][2][0].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][0][2].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][0][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][2][1].getText().equals(player))) {
                                                    buttons[tx][ty][nextPlay[0]][nextPlay[1]].getActionListeners()[0].actionPerformed(null);
                                                    break block;
                                                }
                                                break;
                                            case 2:
                                                if ((buttons[nextPlay[0]][nextPlay[1]][0][2].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][2][2].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][1][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][1][0].getText().equals(player))) {
                                                    buttons[tx][ty][nextPlay[0]][nextPlay[1]].getActionListeners()[0].actionPerformed(null);
                                                    break block;
                                                }
                                                break;
                                        }
                                        break;
                                    case 2:
                                        switch (ty) {
                                            case 0:
                                                if ((buttons[nextPlay[0]][nextPlay[1]][1][0].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][0][0].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][1][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][0][2].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][2][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][2][2].getText().equals(player))) {
                                                    buttons[tx][ty][nextPlay[0]][nextPlay[1]].getActionListeners()[0].actionPerformed(null);
                                                    break block;
                                                }
                                                break;
                                            case 1:
                                                if ((buttons[nextPlay[0]][nextPlay[1]][1][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][0][1].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][2][0].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][2][2].getText().equals(player))) {
                                                    buttons[tx][ty][nextPlay[0]][nextPlay[1]].getActionListeners()[0].actionPerformed(null);
                                                    break block;
                                                }
                                                break;
                                            case 2:
                                                if ((buttons[nextPlay[0]][nextPlay[1]][1][2].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][0][2].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][1][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][0][0].getText().equals(player)) ||
                                                        (buttons[nextPlay[0]][nextPlay[1]][2][1].getText().equals(player) && buttons[nextPlay[0]][nextPlay[1]][2][0].getText().equals(player))) {
                                                    buttons[tx][ty][nextPlay[0]][nextPlay[1]].getActionListeners()[0].actionPerformed(null);
                                                    break block;
                                                }
                                                break;
                                        }
                                        break;

                                }
                            }
                        }
                    }
                }

                //can lose
                boolean[][] canPlay = new boolean[][]{
                        {true, true, true},
                        {true, true, true},
                        {true, true, true}
                };
                for(int tx = 0; tx < 3; tx++){
                    for(int ty = 0; ty < 3; ty++) {
                        enemy:{
                            if (!buttons[nextPlay[0]][nextPlay[1]][tx][ty].isEnabled()) {
                                canPlay[tx][ty] = false;
                                break enemy;
                            }
                            for (int tsx = 0; tsx < 3; tsx++) {
                                for (int tsy = 0; tsy < 3; tsy++) {
                                    switch (tsx) {
                                        case 0:
                                            switch (tsy) {
                                                case 0:
                                                    if (((main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O")))) ||
                                                            (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            ((main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))))) {
                                                        canPlay[tx][ty] = false;
                                                        break enemy;
                                                    }
                                                    break;
                                                case 1:
                                                    if ((main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O")))) {
                                                        canPlay[tx][ty] = false;
                                                        break enemy;
                                                    }
                                                    break;
                                                case 2:
                                                    if ((main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O")))) {
                                                        canPlay[tx][ty] = false;
                                                        break enemy;
                                                    }
                                                    break;
                                            }
                                            break;
                                        case 1:
                                            switch (tsy) {
                                                case 0:
                                                    if ((main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O")))) {
                                                        canPlay[tx][ty] = false;
                                                        break enemy;
                                                    }
                                                    break;
                                                case 1:
                                                    if ((main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O")))) {
                                                        canPlay[tx][ty] = false;
                                                        break enemy;
                                                    }
                                                    break;
                                                case 2:
                                                    if ((main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O")))) {
                                                        canPlay[tx][ty] = false;
                                                        break enemy;
                                                    }
                                                    break;
                                            }
                                            break;
                                        case 2:
                                            switch (tsy) {
                                                case 0:
                                                    if ((main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O")))) {
                                                        canPlay[tx][ty] = false;
                                                        break enemy;
                                                    }
                                                    break;
                                                case 1:
                                                    if ((main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O")))) {
                                                        canPlay[tx][ty] = false;
                                                        break enemy;
                                                    }
                                                    break;
                                                case 2:
                                                    if ((main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) ||
                                                            (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O"))) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals((main.player.equals("O") ? "X" : "O")))) {
                                                        canPlay[tx][ty] = false;
                                                        break enemy;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }
                int count = 0;
                for(int tx = 0; tx < 3; tx++){
                    for(int ty = 0; ty < 3; ty++){
                        count += canPlay[tx][ty] ? 1 : 0;
                    }
                }
                if(count == 0){
                    randomAI();
                    break block;
                }
                if(count == 1){
                    for(int tx = 0; tx < 3; tx++){
                        for(int ty = 0; ty < 3; ty++){
                            if(canPlay[tx][ty]){
                                buttons[nextPlay[0]][nextPlay[1]][tx][ty].getActionListeners()[0].actionPerformed(null);
                                break block;
                            }
                        }
                    }
                }

                int[][] point = new int[][]{
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                };
                for(int tx = 0; tx < 3; tx++){
                    for(int ty = 0; ty < 3; ty++){
                        if(!canPlay[tx][ty]){
                            point[tx][ty] -= 20;
                            continue;
                        }
                        //can't play -> -20

                        //give a big pressure -> +3
                        //line -> +4
                        //block -> +1
                        //going to line -> +1
                        //was block without pressure -> -3
                        //was block with pressure -> -1
                        //was lined without pressure -> -4
                        //was lined with pressure -> -1

                        //give a big pressure -> +3

                        //line -> +4
                        switch (tx) {
                            case 0:
                                switch (ty) {
                                    case 0:
                                        if (((main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals(main.player)) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player))) ||
                                                (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player)) ||
                                                ((main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)))) {
                                            point[tx][ty]+=4;
                                        }
                                        break;
                                    case 1:
                                        if ((main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player))) {
                                            point[tx][ty]+=4;
                                        }
                                        break;
                                    case 2:
                                        if ((main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player))) {
                                            point[tx][ty]+=4;
                                        }
                                        break;
                                }
                                break;
                            case 1:
                                switch (ty) {
                                    case 0:
                                        if ((main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals(main.player))) {
                                            point[tx][ty]+=4;
                                        }
                                        break;
                                    case 1:
                                        if ((main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals(main.player)) && (main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals(main.player))) {
                                            point[tx][ty]+=4;
                                        }
                                        break;
                                    case 2:
                                        if ((main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals(main.player))) {
                                            point[tx][ty]+=4;
                                        }
                                        break;
                                }
                                break;
                            case 2:
                                switch (ty) {
                                    case 0:
                                        if ((main.done[1][0] && ((JButton) panels[1][0].getComponent(0)).getText().equals(main.player)) && (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player))) {
                                            point[tx][ty]+=4;
                                        }
                                        break;
                                    case 1:
                                        if ((main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][1] && ((JButton) panels[0][1].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player)) && (main.done[2][2] && ((JButton) panels[2][2].getComponent(0)).getText().equals(main.player))) {
                                            point[tx][ty]+=4;
                                        }
                                        break;
                                    case 2:
                                        if ((main.done[1][2] && ((JButton) panels[1][2].getComponent(0)).getText().equals(main.player)) && (main.done[0][2] && ((JButton) panels[0][2].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[1][1] && ((JButton) panels[1][1].getComponent(0)).getText().equals(main.player)) && (main.done[0][0] && ((JButton) panels[0][0].getComponent(0)).getText().equals(main.player)) ||
                                                (main.done[2][1] && ((JButton) panels[2][1].getComponent(0)).getText().equals(main.player)) && (main.done[2][0] && ((JButton) panels[2][0].getComponent(0)).getText().equals(main.player))) {
                                            point[tx][ty]+=4;
                                        }
                                        break;
                                }
                                break;
                        }

                        //block -> +1

                        //going to line -> +1

                        //was block without pressure -> -3

                        //was block with pressure -> -1

                        //was lined without pressure -> -4

                        //was lined with pressure -> -1

                    }
                }
            }
            randomAI();
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
