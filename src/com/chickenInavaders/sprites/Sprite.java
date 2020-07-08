package src.com.chickenInavaders.sprites;
import com.sun.glass.ui.Size;
import java.awt.*;
import java.util.Dictionary;
import java.util.Observable;

public abstract class Sprite extends Observable {
    public Point position;
    protected Dictionary<SpriteState, Image> images;
    private SpriteState state;
    private Size size;
    private int dyingFrames;
    private  boolean autoDie;

    public Sprite(Dictionary<SpriteState, Image> images,Size size,boolean autoDie,int dyingFrames){
        position=new Point(0,0);
        this.images=images;
        state=SpriteState.Alive;
        this.size=size;
        this.autoDie=autoDie;
        this.dyingFrames=dyingFrames;
    }

    public void setState(SpriteState state){
        if(this.state!=SpriteState.Dead)
            this.state=state;
    }

    public SpriteState getState(){
        return state;
    }

    public Point move(int deltaX,int deltaY){
        if(state!=SpriteState.Alive)
            return new Point(0,0);
        position.x+=deltaX;
        position.y+=deltaY;
        return position;
    }

    public void paint(Graphics graphics){
        if(state!=SpriteState.Dead) {
            graphics.drawImage(images.get(state), position.x, position.y, null);
            if (state == SpriteState.Dying) {
                dyingFrames--;
                if(dyingFrames==0 && autoDie)
                    state = SpriteState.Dead;
            }else{
                setChanged();
                notifyObservers();
            }
        }
    }

    private boolean intersectX(Sprite other){
        int leftX = position.x;
        int rightX = leftX+size.width;
        int oLeftX = other.position.x;
        int oRightX = oLeftX+other.size.width;

        return (rightX>=oLeftX && rightX<=oRightX) || (oRightX>=leftX && oRightX<=rightX);
    }

    private boolean intersectY(Sprite other){
        int upY = position.y;
        int downY = upY+size.height;
        int oUpY = other.position.y;
        int oDownY = oUpY+other.size.height;

        return (downY>=oUpY && downY<=oDownY) || (oUpY<=upY && oDownY>=upY);
    }

    public boolean intersect(Sprite other){
        return intersectX(other) && intersectY(other);
    }
}


