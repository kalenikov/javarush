package com.javarush.task.task32.task3209.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import com.javarush.task.task32.task3209.View;

public class RedoAction extends AbstractAction {
    private View view;

    public RedoAction(View view) {
        this.view = view;
    }

      @Override
    public void actionPerformed(ActionEvent e) {
        view.redo();
    }
}
