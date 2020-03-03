package dk.model.tools;

import dk.model.MapType;
import dk.model.TraderType;

public class TypeParser {

    public static MapType stringToMapType(String mapName){

        for(MapType type : MapType.values()){
            if(type.getName().compareTo(mapName) == 0)
                return type;
        }

        return null;
    }

    public static TraderType stringToTrader(String traderName){

        for(TraderType type : TraderType.values()){
            if(type.getName().compareTo(traderName) == 0)
                return type;
        }

        return null;
    }
}
