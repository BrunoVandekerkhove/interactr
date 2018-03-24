package interactr.cs.kuleuven.be.UseCases;

import interactr.cs.kuleuven.be.ui.DiagramController;
import interactr.cs.kuleuven.be.ui.DiagramWindow;
import interactr.cs.kuleuven.be.ui.EventHandler;
import interactr.cs.kuleuven.be.ui.PaintBoard;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;


public class SwitchView {

    private DiagramWindow diagramWindow = new DiagramWindow();

    @Test
    public void switchViewTest() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        diagramWindow.setEventHandler(new EventHandler(new DiagramController()));
        diagramWindow.setPaintBoard(new PaintBoard(diagramWindow, diagramWindow.getEventHandler().getDiagramController()));

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DiagramWindow.replayRecording("tabkey.txt", diagramWindow);
        Field f = diagramWindow.getEventHandler().getDiagramController().getClass().getDeclaredField("activeViewIndex"); //NoSuchFieldException
        f.setAccessible(true);
        int currentView = (int) f.get(diagramWindow.getEventHandler().getDiagramController());
        assert(currentView == 1);
    }

}