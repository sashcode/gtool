package com.sashcode.gtool.handlers;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
    /**
     * The constructor.
     */
    public SampleHandler() {
    }

    /**
     * the command has been executed, so extract extract the needed information
     * from the application context.
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {
            // JSR233経由で実行できない
            // ScriptEngineManager factory = new ScriptEngineManager();
            // ScriptEngine engine = factory.getEngineByName("groovy");

            ScriptEngine engine = new GroovyScriptEngineImpl();

            // basic exampl
            StringBuffer buffer = new StringBuffer();
            buffer.append("sum 1 〜 10 = ");
            buffer.append(engine.eval("(1..10).sum()"));
            buffer.append("\n");

            // example showing scripting variables
            engine.put("first", "HELLO");
            engine.put("second", "world");
            buffer.append(engine.eval("first.toLowerCase() + second.toUpperCase()"));

            IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
            MessageDialog.openInformation(window.getShell(), "Gtool", buffer.toString());

        } catch (ScriptException e) {
        }
        return null;
    }
}
