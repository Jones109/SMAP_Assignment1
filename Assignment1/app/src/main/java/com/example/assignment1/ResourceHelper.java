package com.example.assignment1;

public class ResourceHelper {

    //Helper method to get the right resource
    public static int getDrawableFromName(String name) {

        switch (name.toLowerCase()) {
            case "buffalo":
                return R.drawable.buffalo;
            case "camel":
                return R.drawable.camel;
            case "cheetah":
                return R.drawable.cheetah;
            case "crocodile":
                return R.drawable.crocodile;
            case "elephant":
                return R.drawable.elephant;
            case "giraffe":
                return R.drawable.giraffe;
            case "gnu":
                return R.drawable.gnu;
            case "kudu":
                return R.drawable.kudo;
            case "leopard":
                return R.drawable.leopard;
            case "lion":
                return R.drawable.lion;
            case "oryx":
                return R.drawable.oryx;
            case "ostrich":
                return R.drawable.ostrich;
            case "shark":
                return R.drawable.shark;
            case "snake":
                return R.drawable.snake;
            default:
                //Something is wrong when reading the first line from the csv file
                //This is a hack to fix it
                if (name.contains("Lion"))
                    return R.drawable.lion;

                return 0;
        }
    }
}
