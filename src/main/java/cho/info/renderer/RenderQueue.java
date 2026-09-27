package cho.info.renderer;

import cho.info.components.RenderCommand;
import cho.info.components.commands.EmptyCommand;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RenderQueue {

    public static Queue<RenderCommand> renderQueue = new ConcurrentLinkedQueue<>();

    public static void addToQueue(RenderCommand command) {
        renderQueue.add(command);
    }

    public static RenderCommand getLatestCommand() {
        RenderCommand currentCommand = renderQueue.poll();
        if (currentCommand == null) {
            return new EmptyCommand();
        }
        return currentCommand;
    }

}
