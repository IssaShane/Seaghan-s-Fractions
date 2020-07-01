package com.fracpracgdx;

public class GUIContainerColumn extends GUIContainer {
	public GUIContainerColumn() { super(); }
	public GUIContainerColumn(GUILocation loc) {
		super(loc);
	}

	@Override
	public void calibrateLocations() {
		//System.out.println("top of calib this.pos.h: " + Integer.toString(this.pos.h));
		// determine horizontal location
		if (this.location.horizontal == Alignment.LEFT) { 
			for (GUIElement elem : contents) {
				Posn pos = elem.getPos();
				pos.x = elem.getPaddingLeft() + this.pos.x;
				elem.setPos(pos);
			}
		}
		else if (this.location.horizontal == Alignment.RIGHT) {
			for (GUIElement elem : contents) {
				Posn pos = elem.getPos();
				pos.x = this.pos.x + this.pos.w - elem.getPaddingRight();
				elem.setPos(pos);
			}
		}
		else if (this.location.horizontal == Alignment.CENTRE) {
			int centrex = this.pos.x + this.pos.w/2;
			for (GUIElement elem : contents) {
				Posn pos = elem.getPos();
				pos.x = centrex - pos.w/2;
				elem.setPos(pos);
			}
		}
		
		// determine vertical position
		if (this.location.vertical == Alignment.BOTTOM) {
			Posn pos_ = contents.get(contents.size()-1).getPos();
			pos_.y = this.pos.y + contents.get(contents.size()-1).getPaddingBottom();
			contents.get(contents.size()-1).setPos(pos_);
			for (int i = contents.size()-2; i >= 0; i--) {
				Posn pos = contents.get(i).getPos();
				pos.y = contents.get(i+1).getPos().y + contents.get(i+1).getPaddingTop() + contents.get(i).getPaddingBottom();
				contents.get(i).setPos(pos);
			}
		}
		else if (this.location.vertical == Alignment.TOP) {
			Posn pos_ = contents.get(0).getPos();
			pos_.y = this.pos.y + this.pos.h - pos_.h - contents.get(0).getPaddingTop();
			contents.get(0).setPos(pos_);
			for (int i = 1; i < contents.size(); i++) {
				Posn pos = contents.get(i).getPos();
				pos.y = contents.get(i-1).getPos().y - contents.get(i-1).getPaddingBottom() - contents.get(i).getPaddingTop() - pos.h;
				contents.get(i).setPos(pos);
			}
		}
		else if (this.location.vertical == Alignment.CENTRE) {
			// find the centre
			
			int centrey = this.pos.y + this.pos.h/2;
			
			Posn pos_ = contents.get(contents.size()/2).getPos();
			
			if (contents.size()%2 != 0) pos_.y = centrey - (pos_.h/2);
			else pos_.y = centrey;
			System.out.println("centre y place: " + Integer.toString(pos_.y));
			//System.out.println("CENTREY: " + Integer.toString(centrey));
			contents.get(contents.size()/2).setPos(pos_);
			
			// elements above centre
			for (int i = contents.size()/2-1; i>=0; i--) {
				Posn pos = contents.get(i).getPos();
				
				pos.y = contents.get(i+1).getPos().y + contents.get(i+1).getPos().h + contents.get(i+1).getPaddingTop() + contents.get(i).getPaddingBottom();
				contents.get(i).setPos(pos);
			}
			// elements below centre
			for (int i = contents.size()/2+1; i < contents.size(); i++) {
				System.out.println("below");
				Posn pos = contents.get(i).getPos();
				pos.y = contents.get(i-1).getPos().y - contents.get(i-1).getPaddingBottom() - contents.get(i).getPaddingTop() - pos.h;
				contents.get(i).setPos(pos);
			}
			
		}
	}
	
	@Override
	public void setPos(Posn pos) {
		this.pos = new Posn(pos);
		if (this.contents.size() > 0) calibrateLocations();
	}
}
