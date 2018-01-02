package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.in28minutes.data.api.TodoService;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoAnnotationsTestSPY {

	@Mock
	TodoService todoservice;

	@InjectMocks
	TodoBusinessImpl todobusiness;

	@Captor
	ArgumentCaptor<String> stringArguementCaptor;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieveTodosRelatedToSpring() {
		List<String> todos = Arrays.asList("Spring core", "Spring MVC", "Spring AOP", "AspectJ");
		when(todoservice.retrieveTodos("arun")).thenReturn(todos);
		assertEquals(3, todobusiness.retrieveTodosRelatedToSpring("arun").size());
	}

	@Test
	public void testRetrieveNullTodosRelatedToSpring() {
		List<String> todos = Arrays.asList("core", "MVC", "AOP", "AspectJ");

		when(todoservice.retrieveTodos("arun")).thenReturn(todos);
		assertEquals(0, todobusiness.retrieveTodosRelatedToSpring("arun").size());
		verify(todoservice).retrieveTodos("arun");
	}

	@Test
	public void deletetodosNotRelatedToSpringUsingArguementCapturBDD() {

		// given
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		given(todoservice.retrieveTodos("Ranga")).willReturn(allTodos);

		// when
		todobusiness.deleteTodosRelatedToSpring("Ranga");
		// then
		then(todoservice).should().deleteTodos(stringArguementCaptor.capture());
		assertThat(stringArguementCaptor.getValue(), is("Learn to Dance"));

	}

}
