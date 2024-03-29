package it.ispw.daniele.backpacker.view.command_line_interface;

import it.ispw.daniele.backpacker.utils.Roles;

public class CliTouristGuideGraphicChange extends CliGuiChangeTemplate{
    private static CliTouristGuideGraphicChange instance = null;

    private CliTouristGuideGraphicChange() {
        whoAmI = Roles.TOURIST_GUIDE;
    }

    public static CliTouristGuideGraphicChange getInstance(){
        if(instance == null){
            instance = new CliTouristGuideGraphicChange();
        }
        return instance;
    }
}
