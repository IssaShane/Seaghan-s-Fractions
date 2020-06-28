package com.fracpracgdx;

public class GUIContainerColumn extends GUIContainer {

	@Override
	public void calibrateLocations() {
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
		
	}
}
