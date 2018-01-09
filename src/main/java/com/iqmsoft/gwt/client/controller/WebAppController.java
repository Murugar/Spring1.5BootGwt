package com.iqmsoft.gwt.client.controller;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.iqmsoft.gwt.client.event.AddTodoEvent;
import com.iqmsoft.gwt.client.event.AddTodoEventHandler;
import com.iqmsoft.gwt.client.event.DeleteAllTodoEvent;
import com.iqmsoft.gwt.client.event.DeleteAllTodoEventHandler;
import com.iqmsoft.gwt.client.event.DeleteTodoEvent;
import com.iqmsoft.gwt.client.event.DeleteTodoEventHandler;
import com.iqmsoft.gwt.client.event.LoadEvent;
import com.iqmsoft.gwt.client.event.LoadEventHandler;
import com.iqmsoft.gwt.client.json.JsonHelper;
import com.iqmsoft.gwt.client.model.ModelHandler;
import com.iqmsoft.gwt.client.ui.MainPanel;
import com.iqmsoft.gwt.common.model.Todo;


public class WebAppController {

	/**
	 * Event Bus
	 */
	private SimpleEventBus _eventBus;

	/**
	 * Model Handler
	 */
	private ModelHandler _modelHandler;

	/**
	 * main panel UI
	 */
	private MainPanel _mainPanel;

	@Inject
	public WebAppController(SimpleEventBus eventBus, ModelHandler modelHandler, MainPanel mainPanel) {

		_eventBus = eventBus;
		_modelHandler = modelHandler;
		_mainPanel = mainPanel;
	}

	/**
	 * Bind all events handler
	 */
	public void bindHandlers() {

		_eventBus.addHandler(AddTodoEvent.TYPE, new AddTodoEventHandler() {

			@Override
			public void onAddTodoEventHandler(AddTodoEvent event) {
				addTodo(event.getTodoTitle());
			}
		});

		_eventBus.addHandler(DeleteTodoEvent.TYPE, new DeleteTodoEventHandler() {

			@Override
			public void onDeleteTodoEventHandler(DeleteTodoEvent event) {
				deleteTodo(event.getTodo());
			}
		});

		_eventBus.addHandler(DeleteAllTodoEvent.TYPE, new DeleteAllTodoEventHandler() {

			@Override
			public void onDeleteAllTodoEventHandler(DeleteAllTodoEvent event) {
				deleteAll();
			}
		});

		_eventBus.addHandler(LoadEvent.TYPE, new LoadEventHandler() {

			@Override
			public void onLoadEventHandler(LoadEvent event) {
				loadTodoList();
			}

		});
	}
	/**
	 * get todo list from model and reload UI
	 * 
	 * @param list
	 */
	protected void reloadList(List<Todo> list) {
		_modelHandler.reloadAll(list);
		_mainPanel.reloadTodoList();
	}

	/**
	 * ask server for stored Todo list
	 */
	protected void loadTodoList() {
		String pageBaseUrl = GWT.getHostPageBaseURL();
		// String baseUrl = GWT.getModuleBaseURL();
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, pageBaseUrl + "/rest/todos/");
		rb.setCallback(new RequestCallback() {

			public void onError(Request request, Throwable e) {
				// some error handling code here
				Window.alert("error = " + e.getMessage());
			}

			public void onResponseReceived(Request request, Response response) {
				if (200 == response.getStatusCode()) {
					String text = response.getText();
					// some code to further handle the response here
					System.out.println("text = " + text);
					Window.alert("response = " + text);
					List<Todo> todoList = JsonHelper.parseDataList(text);
					reloadList(todoList);
				}
			}
		});
		try {
			rb.send();
		} catch (RequestException e) {
			e.printStackTrace();
			Window.alert("error = " + e.getMessage());
		}
	}

	/**
	 * delete all todo from model and UI
	 */
	protected void deleteAll() {
		_modelHandler.removeAll();
		_mainPanel.removeAllTodo();
	}

	/**
	 * delete a todo (ui & model) from given id
	 * 
	 * @param todoId
	 */
	protected void deleteTodo(Todo todo) {
		_modelHandler.remove(todo);
		_mainPanel.removeTodoFromPanel(todo);
	}

	/**
	 * create and add a todo with given label
	 * 
	 * @param todoTitle
	 */
	protected void addTodo(String todoTitle) {
		Todo t = new Todo(todoTitle);
		_modelHandler.add(t);
		_mainPanel.addTodoToPanel(t);
	}
}
