package inf101.game.States;

public enum FootType {
    WALK, 
    STAND;

    private static FootType[] vals = values(); //code gotten from https://stackoverflow.com/questions/17006239/whats-the-best-way-to-implement-next-and-previous-on-an-enum-type
    public FootType next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }
    
}
