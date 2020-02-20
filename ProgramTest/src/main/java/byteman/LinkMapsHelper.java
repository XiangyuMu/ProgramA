package byteman;


import org.jboss.byteman.rule.Rule;
import org.jboss.byteman.rule.helper.Helper;

public class LinkMapsHelper extends Helper {
    protected LinkMapsHelper(Rule rule) {
        super(rule);
    }

    public void printLinkMaps(Object mapName,Object name){
        Object maps =  linked(mapName,name);
        traceln("the map: "+maps.toString());
    }
}
