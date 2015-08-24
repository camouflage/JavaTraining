package mygrid;
// Helper class for SparseBoundedGrid
public class OccupantInCol
{
    private Object occupant;
    private int col;
    
    public OccupantInCol(Object occ, int co) {
        occupant = occ;
        col = co;
    }

    public Object getOccupant() {
        return occupant;
    }

    public void setOccupant(Object occ) {
        occupant = occ;
    }

    public int getCol() {
        return col;
    }
}
