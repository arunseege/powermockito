package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.in28minutes.data.api.TodoService;

public class TodoBusinessImplMockitoTest_BDD {

	@Test
	public void usingMockito() {
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
		String firstElement = todos.get(0);
		assertThat(firstElement, is("Learn Spring MVC"));
		assertThat(2, is(2));
	}

	@Test
	public void usingMockitoReturnEmpty() {
		// given
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		// when
		todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
		// then
		assertThat(0, is(0));
	}

	@Test
	public void deletetodosNotRelatedToSpring() {

		// given
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

		// when
		todoBusinessImpl.deleteTodosRelatedToSpring("Ranga");
		// then
		then(todoService).should().deleteTodos("Learn to Dance");
		then(todoService).should(never()).deleteTodos("Learn Spring");
		then(todoService).should(never()).deleteTodos("Learn Spring MVC");

	}

	@Test
	public void deletetodosNotRelatedToSpringUsingArguemeneCaptur() {

		ArgumentCaptor<String> stringArguementCaptor = ArgumentCaptor.forClass(String.class);
		// given
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

		// when
		todoBusinessImpl.deleteTodosRelatedToSpring("Ranga");
		// then
		then(todoService).should().deleteTodos(stringArguementCaptor.capture());
		assertThat(stringArguementCaptor.getValue(), is("Learn to Dance"));


	}

}