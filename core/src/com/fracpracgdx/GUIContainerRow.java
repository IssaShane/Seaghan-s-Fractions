package com.fracpracgdx;

public class GUIContainerRow extends GUIContainer {
	public GUIContainerRow() {
		super();
	}
	
	public GUIContainerRow(GUILocation location) {
		super(location);
	}
	
	@Override
	public void calibrateLocations() {
		// determine horizontal position of elements
		// NOTE: does not account for padding on container
		if (this.location.horizontal == Alignment.LEFT) {
			Posn newpos_ = contents.get(0).getPos();
			newpos_.x = this.pos.x + contents.get(0).getPaddingLeft();
			contents.get(0).setPos(newpos_);
			for (int i = 1; i < contents.size(); i++) {
				Posn newpos = contents.get(i).getPos();
				newpos.x = this.pos.x + contents.get(i).getPaddingLeft() + contents.get(i-1).getPos().x + 
						contents.get(i-1).getPos().w + contents.get(i-1).getPaddingRight();
				contents.get(i).setPos(newpos);
			}
		}
		else if (this.location.horizontal == Alignment.RIGHT) { 
			Posn newpos_ = contents.get(contents.size()-1).getPos();
			newpos_.x = this.pos.x + this.pos.w - contents.get(contents.size()-1).getPaddingRight() - newpos_.w;
			//System.out.println("newpos_.w: " + Integer.toString(newpos_.w));
			contents.get(contents.size()-1).setPos(newpos_);
			for (int i = contents.size()-2; i >= 0; i--) {
				Posn newpos = contents.get(i).getPos();
				newpos.x = contents.get(i+1).getPos().x - contents.get(i+1).getPaddingLeft() - newpos.w;
				contents.get(i).setPos(newpos);
			}
		}
		else if (this.location.horizontal == Alignment.CENTRE) {
			// find the centre
			int centrex = this.pos.x + this.pos.w/2;
			//System.out.println("centrex: " + Integer.toString(centrex));
			Posn newpos_ = contents.get(contents.size()/2).getPos();
			if (contents.size() %2 != 0) newpos_.x = centrex - (newpos_.w/2);
			else newpos_.x = centrex;
			contents.get(contents.size()/2).setPos(newpos_);
			
			// elements to the left of centre
			for (int i = contents.size()/2-1; i >= 0; i--) {
				Posn newpos = contents.get(i).getPos();
				newpos.x = contents.get(i+1).getPos().x - newpos.w - 
						contents.get(i).getPaddingRight() - contents.get(i+1).getPaddingLeft();
				contents.get(i).setPos(newpos);
			}
			// elements to the right of centre
			for (int i = contents.size()/2+1; i < contents.size(); i++) {
				Posn newpos = contents.get(i).getPos();
				newpos.x = contents.get(i-1).getPos().x + contents.get(i-1).getPos().w + 
						contents.get(i-1).getPaddingRight() + contents.get(i).getPaddingLeft();
				contents.get(i).setPos(newpos);
			}
		}
		
		// find the vertical positions
		if (this.location.vertical == Alignment.TOP) {
			for (GUIElement elem : contents) {
				Posn newpos = elem.getPos();
				newpos.y = this.pos.y + this.pos.h - newpos.h;
				elem.setPos(newpos);
			}
		}
		else if (this.location.vertical == Alignment.BOTTOM) {
			for (GUIElement elem : contents) {
				Posn newpos = elem.getPos();
				newpos.y = this.pos.y;
				elem.setPos(newpos);
			}
		}
		else if (this.location.vertical == Alignment.CENTRE) {
			int centrey = this.pos.y + this.pos.h/2;
			for (GUIElement elem : contents) {
				Posn newpos = elem.getPos();
				newpos.y = centrey - (newpos.h/2);
				elem.setPos(newpos);
			}
		}
	}
	
	@Override
	public void setPos(Posn pos) {
		this.pos = new Posn(pos);
		if (this.contents.size() > 0) calibrateLocations();
	}
}
