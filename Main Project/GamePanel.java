import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class GamePanel extends Panel implements Runnable {
    int Width =1000;
    int Height=(int)(Width*(0.555));
    Dimension screen=new Dimension(Width,Height);

    int Paddle_Width=25;
    int Paddle_Height=100;

    int ball_diameter=20;

    Image image;

    Graphics graphics;

    Paddle p1,p2;

    Ball ball;


    Score score=new Score(Width,Height);

    Thread GameThread;


    GamePanel()
    {
       setPreferredSize(screen);
       GameThread=new Thread(this);
       addKeyListener(new AL());

       setFocusable(true);

       GameThread.start();
       newPaddle();
       newBall();



    }

    private void newBall() {
        Random random=new Random();
        ball=new Ball(Width/2,random.nextInt(Height-ball_diameter),ball_diameter,ball_diameter);
    }

    private void newPaddle() {
        p1=new Paddle(0,(Height-Paddle_Height)/2,Paddle_Width,Paddle_Height,1);
        p2=new Paddle(Width-Paddle_Width,(Height-Paddle_Height)/2,Paddle_Width,Paddle_Height,2);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        image=createImage(getWidth(),getHeight());
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

    }

    private void draw(Graphics g) {
        p1.draw(g);
        p2.draw(g);
        ball.draw(g);
       score.draw(g);

    }

    @Override
        public void run() {
            long lastTime=System.nanoTime();
            double amountOfTicks=60;
            double ns=1000000000/amountOfTicks;
            double delta=0;
            while(true)
            {
                long Now=System.nanoTime();
                delta+=(Now-lastTime)/ns;
                lastTime=Now;
                if(delta>=1)
                {
                    repaint();
                    move();
                    CheckCollision();
                    delta--;
                }
            }
    }

    private void CheckCollision() {
        if(ball.y<=0)
        {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y>=Height-ball_diameter)
        {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.intersects(p1))
        {
            ball.xVelocity=-ball.xVelocity;
            ball.xVelocity++;
            if(ball.yVelocity>0)
            {
                ball.yVelocity++;
            }else {
                ball.yVelocity--;
            }
            ball.setYDirection(ball.yVelocity);
            ball.setXDirection(ball.xVelocity);
        }

        if(ball.intersects(p2))
        {
            ball.xVelocity=-ball.xVelocity;
            ball.xVelocity--;
            if(ball.yVelocity>0)
            {
                ball.yVelocity++;
            }else {
                ball.yVelocity--;
            }
            ball.setYDirection(ball.yVelocity);
            ball.setXDirection(ball.xVelocity);

        }

        if(p1.y<=0)
        {
            p1.y=0;
        }
        if(p1.y>=Height-Paddle_Height)
        {
            p1.y=Height-Paddle_Height;
        }
        if(p2.y<=0)
        {
            p2.y=0;
        }
        if(p2.y>=Height-Paddle_Height)
        {
            p2.y=Height-Paddle_Height;
        }
        if(ball.x>=Width-ball_diameter)
        {
            newBall();
            newPaddle();
            score.player1++;
        }
        if(ball.x<=0)
        {
            newPaddle();
            newBall();
            score.player2++;
        }




    }

    private void move() {
        p1.move();
        p2.move();
        ball.move();
    }

    public class AL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {

            p1.KeyPressed(e);
            p2.KeyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {

            p1.KeyReleased(e);
            p2.KeyReleased(e);
        }
    }

}
