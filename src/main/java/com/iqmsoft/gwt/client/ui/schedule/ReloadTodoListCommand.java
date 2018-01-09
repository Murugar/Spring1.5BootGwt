package com.iqmsoft.gwt.client.ui.schedule;

import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.iqmsoft.gwt.client.ui.MainPanel;
import com.iqmsoft.gwt.common.model.Todo;


public class ReloadTodoListCommand implements ScheduledCommand{

	private List<Todo> _todoList;
	
	private MainPanel _mainPanel;
	
	private int _index;

	public ReloadTodoListCommand(List<Todo> list, MainPanel mainPanel){
		_todoList = list;
		_mainPanel = mainPanel;
		_index = 0;
	}
	
	/**
	 * incremental add todo to the panel
	 * executed after each call of Scheduler.get().scheduleDeferred(this)
	 */
	@Override
	public void execute() {
		if (_index < _todoList.size()){
			Todo todo = _todoList.get(_index);
			_mainPanel.addTodoToPanel(todo);
			// schedule for next iteration
			Scheduler.get().scheduleDeferred(this);
			_index++;
		}
	}

}
