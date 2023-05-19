import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{
    int id;
    int yVelocity;
    int speed=10;

    Paddle(int x,int y,int Paddle_Width,int Paddle_Height,int id){
        super(x,y,Paddle_Width,Paddle_Height);
        this.id=id;

    }
    public void KeyPressed(KeyEvent e)
    {
        switch(id)
        {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_S)
                {
                    setYDirection(speed);
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP)
                {
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN)
                {
                    setYDirection(speed);
                }
                break;
        }
    }

    private void setYDirection(int Direction) {
        yVelocity=Direction;
    }


    public void KeyReleased(KeyEvent e)
    {
          switch(id)
          {
              case 1:
                  if(e.getKeyCode()==KeyEvent.VK_W)
                  {
                      setYDirection(0);
                  }
                  if(e.getKeyCode()==KeyEvent.VK_S)
                  {
                      setYDirection(0);
                  }
                  break;
              case 2:
                  if(e.getKeyCode()==KeyEvent.VK_UP)
                  {
                      setYDirection(0);
                  }
                  if(e.getKeyCode()==KeyEvent.VK_DOWN)
                  {
                      setYDirection(0);
                  }
                  break;
          }
    }
    public void move()
    {
        y=y+yVelocity;
    }
    public void draw(Graphics g)
    {
        if(id==1)
        {
            g.setColor(Color.RED);
        }else
        {
            g.setColor(Color.blue);
        }
        g.fillRect(x,y,width,height);
    }
}
