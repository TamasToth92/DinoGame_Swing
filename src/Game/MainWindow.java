package Game;

import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.Timer;

public class MainWindow extends JFrame {

    static final int WIDTH = 1280;
    static final int HEIGHT = 640;

    private boolean isEnd = true;
    private int keyCodeLe = 40;
    private int intcounter = 0;
    private long startTime;
    private long spawnTime = 8500;
    private long lastSpawn = -30000;
    private int numOfEnemy = 0;
    private boolean fallFlag = true;

    public MainWindow() {
        String path = "lionking.wav";
        BackgroundMusic music = new BackgroundMusic();
        music.playMusic(path);
        init();

    }

    private void init() {

        setLayout(null);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        JLabel backGround;
        ImageIcon img = new ImageIcon("jungle2.png");
        backGround = new JLabel("", img, JLabel.CENTER);
        backGround.setBounds(0, 0, WIDTH, HEIGHT);
        add(backGround);
        JLabel backGround2;
        backGround2 = new JLabel("", img, JLabel.CENTER);
        backGround2.setBounds(backGround.getWidth(), 0, WIDTH, HEIGHT);
        add(backGround2);

        ImageIcon img3 = new ImageIcon("transparent.png");
        JLabel trans1;
        trans1 = new JLabel("", img3, JLabel.CENTER);
        trans1.setBounds(0, 0, WIDTH, HEIGHT);
        add(trans1, 0);

        ImageDrawer cloud = new ImageDrawer("cloud2.png");
        cloud.setBounds(200, 70, 200, 200);
        trans1.add(cloud);
        ImageDrawer cloud2 = new ImageDrawer("cloud2.png");
        cloud2.setBounds(600, 30, 180, 180);
        trans1.add(cloud2);

        ImageDrawer trap = new ImageDrawer("trap.png");

        ImageDrawer bug = new ImageDrawer("bug.png");

        ImageDrawer bat = new ImageDrawer("bat.png");

        JLabel playButton;
        ImageIcon button = new ImageIcon("button.png");
        playButton = new JLabel("", button, JLabel.CENTER);
        playButton.setBounds(WIDTH / 2 - 160, HEIGHT / 2 - 94, 330, 200);
        trans1.add(playButton, 0);

        Dino dino = new Dino("dino2.png", 10, 390, 225);
        dino.setBounds(10, 390, 151, 151);
        trans1.add(dino);

        GameOver go = new GameOver("over.png");
        go.setBounds(WIDTH / 2 - 150, HEIGHT / 2 - 150, 310, 310);

        ActionListener ac = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                long time = ae.getWhen() - startTime;
                if (time - lastSpawn > spawnTime) {
                    if (numOfEnemy == 0) {
                        trap.setBounds(1280, 460, 121, 121);
                        trans1.add(trap);
                        numOfEnemy++;

                    } else if (numOfEnemy == 1) {
                        bug.setBounds(1280, 455, 226, 226);
                        trans1.add(bug);
                        numOfEnemy++;
                    } else if (numOfEnemy == 2) {
                        bat.setBounds(1300, 30, 125, 125);
                        trans1.add(bat);
                        numOfEnemy++;
                    }

                    lastSpawn = time;
                }
                cloud.setBounds(cloud.getX() - 10, cloud.getY(), cloud.getWidth(), cloud.getHeight());
                cloud2.setBounds(cloud2.getX() - 10, cloud2.getY(), cloud2.getWidth(), cloud2.getHeight());

                if (cloud.getX() < -150) {
                    cloud.setBounds(contentPane.getWidth(), cloud.getY(), cloud.getWidth(), cloud.getHeight());

                }
                if (cloud2.getX() < -150) {
                    cloud2.setBounds(contentPane.getWidth(), cloud2.getY(), cloud2.getWidth(), cloud2.getHeight());

                }

            }
        };

        Timer timer = new Timer(50, ac);
        timer.setInitialDelay(50);
        timer.start();

        trans1.getComponent(0).addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                trans1.remove(0);
                String text = "0";
                JTextField szamlalo = new JTextField(text, 3);
                szamlalo.setOpaque(false);
                szamlalo.setBorder(null);
                szamlalo.setFont(new Font("Ubuntu", Font.BOLD, 60));
                szamlalo.setEditable(false);
                szamlalo.setBounds(1100, 50, 100, 100);
                trans1.add(szamlalo, 1);

                ActionListener szamlalo2 = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (isEnd) {
                            intcounter++;
                        }
                        szamlalo.setText(Long.toString(intcounter));

                    }
                };

                Timer timer2 = new Timer(1000, szamlalo2);
                timer2.setInitialDelay(0);
                timer2.start();

                addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent ke) {

                    }

                    @Override
                    public void keyPressed(KeyEvent ke) {

                        if (ke.getKeyCode() == 10) {
                            dino.setBounds(10, 390, 151, 151);
                            dino.setVisible(true);
                            trap.setVisible(true);
                            bat.setVisible(true);
                            bug.setVisible(true);
                            intcounter = 0;
                            isEnd = true;
                            ActionListener szamlalo2 = new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    if (isEnd) {
                                        intcounter++;
                                    }
                                    szamlalo.setText(Long.toString(intcounter));

                                }
                            };

                            Timer timer2 = new Timer(1000, szamlalo2);
                            timer2.setInitialDelay(0);
                            timer2.start();
                            trans1.remove(go);

                        }

                        if (ke.getKeyCode() == 37) {
                            if (dino.getX() > -10) {
                                dino.setBounds(dino.getX() - 35, dino.getY(), dino.getWidth(), dino.getHeight());
                            }
                        } else if (ke.getKeyCode() == 38 && fallFlag) {
                            if (dino.getY() > 130) {
                                dino.setBounds(dino.getX(), dino.jump(), dino.getWidth(), dino.getHeight());
                                fallFlag = false;
                                ActionListener act = new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent ae) {
                                        dino.setBounds(dino.getX(), dino.fall(), dino.getWidth(), dino.getHeight());
                                    }
                                };
                                Timer timer = new Timer(30, act);
                                timer.setInitialDelay(80);
                                timer.start();

                            }
                        } else if (ke.getKeyCode() == 38) {
                            if (dino.getY() > 130) {
                                dino.setBounds(dino.getX(), dino.jump(), dino.getWidth(), dino.getHeight());

                            }
                        } else if (ke.getKeyCode() == 39) {
                            if (dino.getX() < trans1.getWidth() - 140) {
                                dino.setBounds(dino.getX() + 35, dino.getY(), dino.getWidth(), dino.getHeight());
                            }
                        }

                        //DEPRICATED code
                        /*else if (ke.getKeyCode() == keyCodeLe) {
                    keyCodeLe += 100000;
                    ActionListener act = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            dino.setBounds(dino.getX(), dino.fall(), dino.getWidth(), dino.getHeight());
                        }
                    };
                    Timer timer = new Timer(30, act);
                    timer.setInitialDelay(80);
                    timer.start();
                }
                         */
                    }

                    @Override
                    public void keyReleased(KeyEvent ke) {

                    }
                });

                ActionListener ac = new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        backGround.setBounds(backGround.getX() - 4, backGround.getY(), WIDTH, HEIGHT);
                        backGround2.setBounds(backGround2.getX() - 4, backGround2.getY(), WIDTH, HEIGHT);
                        trap.setBounds(trap.getX() - 10, trap.getY(), trap.getWidth(), trap.getHeight());
                        bug.setBounds(bug.getX() - 11, bug.getY(), bug.getWidth(), bug.getHeight());
                        bat.setBounds(bat.getX() - 8, bat.getY() + 1, bat.getWidth(), bat.getHeight());

                        long time = ae.getWhen() - startTime;
                        if (time - lastSpawn > spawnTime) {
                            if (numOfEnemy == 0) {
                                trap.setBounds(1280, 450, 226, 226);
                                trans1.add(trap);
                                numOfEnemy++;

                            } else if (numOfEnemy == 1) {
                                bug.setBounds(1280, 455, 226, 226);
                                trans1.add(bug);
                                numOfEnemy++;
                            } else if (numOfEnemy == 2) {
                                bat.setBounds(1300, 40, 125, 125);
                                trans1.add(bat);
                                numOfEnemy++;
                            }
                            lastSpawn = time;
                        }

                        if (backGround.getX() == -WIDTH) {
                            backGround.setBounds(WIDTH, backGround.getY(), WIDTH, HEIGHT);
                        }
                        if (backGround2.getX() == -WIDTH) {
                            backGround2.setBounds(WIDTH, backGround2.getY(), WIDTH, HEIGHT);
                        }
                        if (trap.getX() < -150) {
                            trap.setBounds(contentPane.getWidth(), trap.getY(), trap.getWidth(), trap.getHeight());

                        }
                        if (bug.getX() < -150) {
                            bug.setBounds(contentPane.getWidth(), bug.getY(), bug.getWidth(), bug.getHeight());

                        }
                        if (bat.getX() < -150) {
                            bat.setBounds(contentPane.getWidth(), 10, bat.getWidth(), bat.getHeight());

                        }
                        Rectangle r3 = dino.getBounds();
                        r3.setSize((int) r3.getWidth() - 70, (int) r3.getHeight() - 70);
                        Rectangle r2 = trap.getBounds();
                        r2.setSize((int) r2.getWidth() - 70, (int) r2.getHeight() - 70);
                        Rectangle r1 = bug.getBounds();
                        r1.setSize((int) r1.getWidth() - 70, (int) r1.getHeight() - 70);
                        Rectangle r4 = bat.getBounds();
                        r4.setSize((int) r4.getWidth() - 70, (int) r4.getHeight() - 70);
                        if (r3.intersects(r2)) {
                            dino.setVisible(false);
                            trap.setVisible(false);
                            bat.setVisible(false);
                            bug.setVisible(false);
                            isEnd = false;
                            trans1.add(go);

                        }
                        if (r3.intersects(r1)) {
                            dino.setVisible(false);
                            trap.setVisible(false);
                            bat.setVisible(false);
                            bug.setVisible(false);
                            isEnd = false;
                            trans1.add(go);

                        }
                        if (r3.intersects(r4)) {
                            dino.setVisible(false);
                            trap.setVisible(false);
                            bat.setVisible(false);
                            bug.setVisible(false);
                            isEnd = false;
                            trans1.add(go);

                        }
                    }
                };

                Timer timer = new Timer(5, ac);
                timer.start();

            }

            @Override

            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        setVisible(true);
    }

}
