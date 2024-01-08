package org.MiniGameKeyboard.SystemConfig;

import net.sf.json.JSONObject;
import org.MiniGameKeyboard.Enum.KeyEnum;
import org.MiniGameKeyboard.Enum.OtherKeyEnum;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
    private static Map<String,Integer>keyMap = new HashMap<>();
    private static final int[]code = new int[]{
            27,112,113,114,115,116,117,118,119,120,121,122,123,107,109,
            9,20,16,17,524,18,32,38,40,37,39,
            48,49,50,51,52,53,54,55,56,57,
            65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,
            81,82,83,84,85,86,87,88,89,90,
            97,98,99,100,101,102,103,104,105,
            106,107,108,109,110,111,112,113,114,
            115,116,117,118,119,120,121,122

    };
    public static void OtherCode(){
        for(OtherKeyEnum k:OtherKeyEnum.values()){
            int index = k.ordinal();
            String name = k.name();
            keyMap.put(name,code[index]);
        }
    }
    public static int GetOtherCode(String key){
        OtherCode();
        JSONObject object = JSONObject.fromObject(keyMap);
        return object.getInt(key);
    }
}
